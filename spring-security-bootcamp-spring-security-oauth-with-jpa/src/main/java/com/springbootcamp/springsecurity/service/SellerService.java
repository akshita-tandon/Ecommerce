package com.springbootcamp.springsecurity.service;

import com.springbootcamp.springsecurity.dto.*;
import com.springbootcamp.springsecurity.entity.Role;
import com.springbootcamp.springsecurity.entity.Seller;
import com.springbootcamp.springsecurity.exception.EmailAlreadyExistsException;
import com.springbootcamp.springsecurity.exception.PaaswordNotMatchedException;
import com.springbootcamp.springsecurity.repository.SellerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SellerService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    SellerRepository sellerRepository;


    @Autowired
    EmailService emailService;

    public boolean registerSeller(SellerRegisterDTO sellerRegisterDto) {

        Seller sellerExists = sellerRepository.findByEmail(sellerRegisterDto.getEmail());
        if (sellerExists != null) {
            throw new EmailAlreadyExistsException("Seller already registered with this E-mail.");
        } else {
            if (sellerRegisterDto.getPassword().equals(sellerRegisterDto.getConfirmPassword())) {
                ModelMapper modelMapper = new ModelMapper();
                String newPassword = bCryptPasswordEncoder.encode(sellerRegisterDto.getPassword());
                sellerRegisterDto.setPassword(newPassword);
                Seller seller = modelMapper.map(sellerRegisterDto, Seller.class);
                seller.setRole(Collections.singletonList(new Role("ROLE_SELLER")));
                seller.setActive(false);
                seller.setConfirmationToken(UUID.randomUUID().toString());
                seller.setExpiryDate(24 * 60);
                sellerRepository.save(seller);
                emailService.sendEmail(seller.getEmail(), "Account Created", "Your Account has been created. We need you to be a little patient with the approval of your Account. ");
                return true;
            } else {
                throw new PaaswordNotMatchedException("Password and confirm password does not match");
            }
        }
    }


    public PageImpl<SellerResponseDTO> pagingSeller(Pageable pageable) {
        pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "username");
        Page<Seller> sellers = sellerRepository.findAll(pageable);
        List<SellerResponseDTO> sellerResponseDTOList = sellers.stream().map(this::toSellerResponseDTO).collect(Collectors.toList());
        return new PageImpl<SellerResponseDTO>(sellerResponseDTOList, pageable, sellers.getTotalPages());
    }

    private SellerResponseDTO toSellerResponseDTO(Seller seller) {
        return new SellerResponseDTO(seller.getId(), seller.getEmail(), seller.getFirstName(), seller.getMiddleName(), seller.getLastName(), seller.getActive(), seller.getCompanyName(), seller.getCompanyContact());
    }

    public boolean updateProfile(String username, SellerUpdateProfileDTO sellerUpdateProfileDTO) {


        if (sellerUpdateProfileDTO.getFirstName() != null) {
            Seller seller = sellerRepository.findByUsername(username);
            seller.setFirstName(sellerUpdateProfileDTO.getFirstName());
            sellerRepository.save(seller);
        }
        if (sellerUpdateProfileDTO.getMiddleName() != null) {
            Seller seller = sellerRepository.findByUsername(username);
            seller.setMiddleName(sellerUpdateProfileDTO.getMiddleName());
            sellerRepository.save(seller);
        }
        if (sellerUpdateProfileDTO.getLastName() != null) {
            Seller seller = sellerRepository.findByUsername(username);
            seller.setLastName(sellerUpdateProfileDTO.getLastName());
            sellerRepository.save(seller);
        }
        if (sellerUpdateProfileDTO.getGst() != null) {
            Seller seller = sellerRepository.findByUsername(username);
            seller.setGst(sellerUpdateProfileDTO.getGst());
            sellerRepository.save(seller);
        }
        if (sellerUpdateProfileDTO.getCompanyContact() != null) {
            Seller seller = sellerRepository.findByUsername(username);
            seller.setCompanyContact(sellerUpdateProfileDTO.getCompanyContact());
            sellerRepository.save(seller);
        }
        if (sellerUpdateProfileDTO.getCompanyName() != null) {
            Seller seller = sellerRepository.findByUsername(username);
            seller.setCompanyName(sellerUpdateProfileDTO.getCompanyName());
            sellerRepository.save(seller);
        }

        return true;
    }


    public SellerProfileResponseDTO viewProfile(Principal principal) {
        String username = principal.getName();
        Seller seller = sellerRepository.findByUsername(username);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        SellerProfileResponseDTO sellerProfileResponseDTO = modelMapper.map(seller, SellerProfileResponseDTO.class);
        return sellerProfileResponseDTO;
    }


}
