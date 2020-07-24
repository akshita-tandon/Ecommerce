package com.springbootcamp.springsecurity.service;

import com.springbootcamp.springsecurity.dto.*;
import com.springbootcamp.springsecurity.entity.*;
import com.springbootcamp.springsecurity.exception.*;
import com.springbootcamp.springsecurity.repository.AddressRepository;
import com.springbootcamp.springsecurity.repository.BuyerRepository;
import com.springbootcamp.springsecurity.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuyerService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    public Boolean registerbuyer(BuyerRegisterDTO buyerRegisterDTO, HttpServletRequest request) {

        Buyer buyerExists = buyerRepository.findByEmail(buyerRegisterDTO.getEmail());
        if (buyerExists != null) {
            throw new EmailAlreadyExistsException("Buyer already registered with this E-mail.");
        } else {
            if (buyerRegisterDTO.getPassword().equals(buyerRegisterDTO.getConfirmPassword())) {
                ModelMapper modelMapper = new ModelMapper();
                String newPassword = bCryptPasswordEncoder.encode(buyerRegisterDTO.getPassword());
                buyerRegisterDTO.setPassword(newPassword);
                Buyer buyer = modelMapper.map(buyerRegisterDTO, Buyer.class);
                buyer.setRole(Arrays.asList(new Role("ROLE_BUYER")));
                buyer.setActive(false);
                buyer.setConfirmationToken(UUID.randomUUID().toString());
                buyer.setExpiryDate(24 * 60);
                buyerRepository.save(buyer);
                String appUrl = request.getScheme() + "://" + request.getServerName();
                emailService.sendEmail(buyer.getEmail(), appUrl, "Registration Confirmation",
                        buyer.getConfirmationToken());
                return true;
            } else {
                throw new PaaswordNotMatchedException("Password and confirm password does not match");
            }
        }
    }

    public Boolean confirmToken(String token, HttpServletRequest request) {

        Buyer buyer = buyerRepository.findByConfirmationToken(token);
        if (buyer == null) {
            throw new TokenNotFoundException("Your Account  is already Activated ");
        }
        Calendar cal = Calendar.getInstance();
        if ((buyer.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            //System.out.println("error found .........expired token");
            buyer.setConfirmationToken(UUID.randomUUID().toString());
            buyer.setExpiryDate(5);
            buyerRepository.save(buyer);
            String appUrl = request.getScheme() + "://" + request.getServerName();
            // Create an email instance
            emailService.sendEmail(buyer.getEmail(), appUrl, "Registration Confirmation"
                    , buyer.getConfirmationToken());

        } else {
            if (buyer.getConfirmationToken().equals(token)) {
                // System.out.println("Activated.........");
                buyer.setActive(true);
                buyer.setConfirmationToken("NULL");
                buyerRepository.save(buyer);
                return true;

            }
        }
        return false;
    }

    public Boolean reactivateToken(String email, HttpServletRequest request) {

        Buyer buyer = buyerRepository.findByEmail(email);
        if (buyer == null) {
            throw new UserNotFoundException("No User Found with this E-mail.....Please enter a correct E-mail ID");
        }
        if (!buyer.getActive()) {

            buyer.setConfirmationToken(UUID.randomUUID().toString());
            buyer.setExpiryDate(60);
            buyerRepository.saveAndFlush(buyer);
            String appUrl = request.getScheme() + "://" + request.getServerName();
            emailService.sendEmail(buyer.getEmail(), appUrl, "Registration Confirmation", buyer.getConfirmationToken());
            return true;

        } else {
            return false;
        }
    }


    //paging for buyer
    public Page<BuyerResponseDTO> pagingBuyer(Pageable pageable) {
        pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
        Page<Buyer> buyers = buyerRepository.findAll(pageable);
        List<BuyerResponseDTO> buyerResponseDTOList = buyers.getContent().stream().map(this::toBuyerResponseDTO).collect(Collectors.toList());
        return new PageImpl<BuyerResponseDTO>(buyerResponseDTOList);
    }

    private BuyerResponseDTO toBuyerResponseDTO(Buyer buyer) {
        return new BuyerResponseDTO(buyer.getId(), buyer.getEmail(), buyer.getFirstName(), buyer.getMiddleName(), buyer.getLastName(), buyer.getActive());
    }


    public boolean updateProfile(String username, UpdateProfileDTO updateProfileDTO) {

        if (updateProfileDTO.getFirstName() == null && updateProfileDTO.getMiddleName() == null && updateProfileDTO.getLastName() == null && updateProfileDTO.getContact() == null) {
            return false;
        }
        if (updateProfileDTO.getFirstName() != null) {
            User user = userRepository.findByUsername(username);
            user.setFirstName(updateProfileDTO.getFirstName());
            userRepository.save(user);
        }
        if (updateProfileDTO.getMiddleName() != null) {
            User user = userRepository.findByUsername(username);
            user.setMiddleName(updateProfileDTO.getMiddleName());
            userRepository.save(user);
        }
        if (updateProfileDTO.getLastName() != null) {
            User user = userRepository.findByUsername(username);
            user.setLastName(updateProfileDTO.getLastName());
            userRepository.save(user);
        }
        if (updateProfileDTO.getContact() != null) {
            Buyer buyer = buyerRepository.findByUsername(username);
            buyer.setContact(updateProfileDTO.getContact());
            buyerRepository.save(buyer);
        }
        return true;
    }


    public boolean deleteAddress(Long address_id) {
        Address address = addressRepository.findById(address_id).orElse(null);
        if (address == null) {
            throw new AddressNotFoundException("There is no record with the id provided.");
        } else {
            address.setIsDeleted(true);
            addressRepository.save(address);
            return true;
        }
    }

    public List<AddressResponseDto> getAddressList(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            Set<Address> addresses = user.getAddress();
            Iterator<Address> it = addresses.iterator();
            while (it.hasNext()) {
                if (it.next().getIsDeleted() == true) {    // remove even elements
                    it.remove();
                }
            }
            return addresses.stream().map(this::toAddressDto).collect(Collectors.toList());
        } else
            throw new UserNotFoundException("There is no user present in the system ");


    }

    private AddressResponseDto toAddressDto(Address address) {

        return new AddressResponseDto(address.getAddressId(),address.getCity(), address.getState(), address.getCountry(), address.getAddress(), address.getZipCode(), address.getLabel());
    }

    public BuyerProfileResponseDto viewProfile(Principal principal) {
        String username = principal.getName();
        Buyer buyer = buyerRepository.findByUsername(username);
            ModelMapper modelMapper = new ModelMapper();
            BuyerProfileResponseDto buyerProfileResponseDto = modelMapper.map(buyer, BuyerProfileResponseDto.class);
            buyerProfileResponseDto.setIsActive(buyer.getActive());
            return buyerProfileResponseDto;
    }

    public Boolean addAddress(String username, @Valid AddressDTO addressDTO) {
        User buyer = userRepository.findByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        Address address = modelMapper.map(addressDTO, Address.class);
        HashSet<Address> addressHashSet = new HashSet<Address>();
        addressHashSet.add(address);
        address.setUser(buyer);
        buyer.setAddress(addressHashSet);
        userRepository.save(buyer);
        return true;

    }
}


