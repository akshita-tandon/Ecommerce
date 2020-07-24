package com.springbootcamp.springsecurity.controller;

import com.springbootcamp.springsecurity.dto.ResetDTO;
import com.springbootcamp.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class ForgotPasswordController {
    
    @Autowired
    UserService userService;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-RECIEVE-TOKEN-FOR-PASSWORD-CHANGE///////////////////////////////////////////
    @PostMapping("/recievetoken")
    public ResponseEntity<String> recieveTokenBasedUrl(@Valid @RequestParam String email, HttpServletRequest request) {

        if (userService.recieveToken(email, request)) {
            return new ResponseEntity<String>("Token is sent for reset password", HttpStatus.OK);
        } else
            return new ResponseEntity<>("You are not activated..Please activate your account", HttpStatus.BAD_REQUEST);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////TO-RESET-PASSWORD-OF-BUYER///////////////////////////////////////////
    @PutMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody ResetDTO resetDTO) {

        if (userService.resetPassword(resetDTO)) {
            return new ResponseEntity<>("Your Password is updated", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Your password is not updated", HttpStatus.BAD_REQUEST);

    }

}
