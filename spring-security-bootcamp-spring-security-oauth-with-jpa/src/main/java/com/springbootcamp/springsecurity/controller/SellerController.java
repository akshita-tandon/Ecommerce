package com.springbootcamp.springsecurity.controller;

import com.springbootcamp.springsecurity.dto.*;
import com.springbootcamp.springsecurity.entity.Address;
import com.springbootcamp.springsecurity.entity.User;
import com.springbootcamp.springsecurity.repository.AddressRepository;
import com.springbootcamp.springsecurity.repository.SellerRepository;
import com.springbootcamp.springsecurity.repository.UserRepository;
import com.springbootcamp.springsecurity.service.ImageService;
import com.springbootcamp.springsecurity.service.SellerService;
import com.springbootcamp.springsecurity.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;


@RestController
public class SellerController {

    @Autowired
    SellerService sellerService;
    @Autowired
    UserService userService;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    UserRepository userRepository;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////TO-VIEW-A-SELLER-PROFILE///////////////////////////////////////////
    @GetMapping("seller/profile")
    public SellerProfileResponseDTO viewProfile(Principal principal) {
        return sellerService.viewProfile(principal);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////TO-UPDATE-A-SELLER-PROFILE///////////////////////////////////////////
    @PatchMapping("seller/updateProfile")
    public ResponseEntity<String> updateProfile(Principal principal, @Valid @RequestBody SellerUpdateProfileDTO sellerUpdateProfileDTO) {

        String username = principal.getName();
        if (sellerService.updateProfile(username, sellerUpdateProfileDTO))
            return new ResponseEntity("Profile is updated", HttpStatus.OK);
        else
            return new ResponseEntity("Sorry...Profile is not updated", HttpStatus.BAD_REQUEST);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////TO-UPDATE-A-PASSWORD///////////////////////////////////////////
    @PatchMapping("seller/updatePassword")
    public ResponseEntity<String> updatePassword(Principal principal, @Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        String username = principal.getName();
        if (userService.updatePassword(username, passwordUpdateDTO))
            return new ResponseEntity("Password is updated", HttpStatus.OK);
        else
            return new ResponseEntity("Sorry...Password is not updated", HttpStatus.BAD_REQUEST);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////TO-ADD-ADDRESS-OF-A-SELLER///////////////////////////////////////////
    @PostMapping("seller/addAddress")
    public ResponseEntity<String> addAddress(Principal principal, @Valid @RequestBody AddressDTO addressDTO) {

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        System.out.println(user.toString());
        System.out.println(user.getId().toString());
        Long id = addressRepository.fetchAddresses(user.getId());
        if (id != null)
            return new ResponseEntity("Address cannot be set!", HttpStatus.BAD_REQUEST);

        else {
            ModelMapper modelMapper = new ModelMapper();
            Address address = modelMapper.map(addressDTO, Address.class);
            HashSet<Address> addressHashSet = new HashSet<Address>();
            addressHashSet.add(address);
            address.setUser(user);
            user.setAddress(addressHashSet);
            userRepository.save(user);
            return new ResponseEntity("Address changed", HttpStatus.OK);

        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////TO-UPDATE-A-SELLER-PROFILE///////////////////////////////////////////
    @PatchMapping("/seller/updateAddress")
    public ResponseEntity<String> updateAddress(@Valid @RequestParam Long address_id, @RequestBody AddressUpdateDTO addressUpdateDTO) {

        if (userService.updateAddress(address_id, addressUpdateDTO)) {
            return new ResponseEntity<String>("The address is updated for the given id", HttpStatus.OK);
        } else
            return new ResponseEntity<String>("The address is not updated for the given id", HttpStatus.NOT_FOUND);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////TO-UPDATE-A-IMAGE///////////////////////////////////////////
    @PostMapping("/uploadimage")
    public String uploadImage(@RequestBody MultipartFile image, Principal principal) throws IOException {
        return imageService.uploadImage(image, principal);
    }

}