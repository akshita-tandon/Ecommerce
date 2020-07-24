package com.springbootcamp.springsecurity.controller;

import com.springbootcamp.springsecurity.dto.*;

import com.springbootcamp.springsecurity.service.BuyerService;
import com.springbootcamp.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class CustomerController {


    @Autowired
    BuyerService buyerService;


    @Autowired
    UserService userService;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-VIEW-PROFILE-OF-BUYER-LOGGED-IN///////////////////////////////////////////
    @GetMapping("buyer/profile")
    public BuyerProfileResponseDto viewProfile(Principal principal) {
        return buyerService.viewProfile(principal);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-UPDATE-PROFILE-OF-BUYER-LOGGED-IN///////////////////////////////////////////
    @PatchMapping("buyer/updateProfile")
    public ResponseEntity<String> updateProfile(Principal principal, @Valid @RequestBody UpdateProfileDTO updateProfileDTO) {

        String username = principal.getName();
        if (buyerService.updateProfile(username, updateProfileDTO))
            return new ResponseEntity("Profile is updated", HttpStatus.OK);
        else
            return new ResponseEntity("Sorry...Profile is not updated", HttpStatus.EXPECTATION_FAILED);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-UPDATE-PASSWORD-OF-BUYER-LOGGED-IN///////////////////////////////////////////
    @PatchMapping("buyer/updatePassword")
    public ResponseEntity<String> updatePassword(Principal principal, @Valid @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        String username = principal.getName();
        if (userService.updatePassword(username, passwordUpdateDTO))
            return new ResponseEntity("Password is updated", HttpStatus.OK);
        else
            return new ResponseEntity("Sorry...Password is not updated", HttpStatus.EXPECTATION_FAILED);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-ADD-ADDRESS-OF-BUYER-LOGGED-IN///////////////////////////////////////////
    @PostMapping("buyer/addAddress")
    public ResponseEntity<String> addAddress(Principal principal, @Valid @RequestBody AddressDTO addressDTO) {

        String username = principal.getName();
        if (buyerService.addAddress(username, addressDTO)) {
            return new ResponseEntity<String>("The address is added for the buyer", HttpStatus.OK);

        } else
            return new ResponseEntity<String>("The address is not added for the given id", HttpStatus.BAD_REQUEST);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-DELETE-ADDRESS-OF-BUYER-LOGGED-IN///////////////////////////////////////////
    @DeleteMapping("buyer/deleteAddress")
    public ResponseEntity<String> deleteAddress(@Valid @RequestParam Long address_id) {

        if (buyerService.deleteAddress(address_id)) {
            return new ResponseEntity<String>("The address is deleted for the given id", HttpStatus.OK);
        } else
            return new ResponseEntity<String>("The address is not deleted for the given id", HttpStatus.EXPECTATION_FAILED);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-VIEW-ADDRESSES-OF-BUYER-LOGGED-IN///////////////////////////////////////////
    @GetMapping("buyer/address")
    public List<AddressResponseDto> addressProfile(Principal principal) {

        String username = principal.getName();
        return buyerService.getAddressList(username);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-UPDATE-ADDRESS-OF-BUYER-LOGGED-IN///////////////////////////////////////////
    @PatchMapping("/buyer/updateAddress")
    public ResponseEntity<String> updateAddress(@Valid @RequestParam Long address_id, @RequestBody AddressUpdateDTO addressUpdateDTO) {

        if (userService.updateAddress(address_id, addressUpdateDTO)) {
            return new ResponseEntity<String>("The address is updated for the given id", HttpStatus.OK);
        } else
            return new ResponseEntity<String>("The address is not updated for the given id", HttpStatus.EXPECTATION_FAILED);

    }


}
