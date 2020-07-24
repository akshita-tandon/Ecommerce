package com.springbootcamp.springsecurity.controller;


import com.springbootcamp.springsecurity.entity.Role;
import com.springbootcamp.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class LoginController {



    @Autowired
    UserService userService;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////TO-LOGOUT///////////////////////////////////////////
    @PostMapping("/doLogout")
    public ResponseEntity<String> logout(@RequestParam String token) {

        if(userService.logout(token))
            return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Logged out unsuccess", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/user/role")
    public ResponseEntity<Object> getRole(@RequestParam String username){
        List<Role> roleList=new ArrayList<>();
        roleList= userService.getRole(username);
        if (roleList==null) {
            return new ResponseEntity<>("no user found",HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(roleList,HttpStatus.OK);
    }
}
