package com.springbootcamp.springsecurity.controller;

import com.springbootcamp.springsecurity.dto.BuyerRegisterDTO;
import com.springbootcamp.springsecurity.dto.SellerRegisterDTO;
import com.springbootcamp.springsecurity.service.BuyerService;
import com.springbootcamp.springsecurity.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    SellerService sellerService;

    @Autowired
    BuyerService buyerService;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////WELCOME////////////////////////////////////////////////////////
    @GetMapping("/welcome")
    public String helloWorldBeanInternationalized() {
        String message = messageSource.getMessage("hello.message", null, LocaleContextHolder.getLocale());
        return message;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////TO-REGISTER-A-SELLER///////////////////////////////////////////
    @PostMapping("/register/seller")
    public ResponseEntity<String> registerSeller(@Valid @RequestBody SellerRegisterDTO sellerRegisterDto, HttpServletRequest request) {

        if (sellerService.registerSeller(sellerRegisterDto)) {
            return new ResponseEntity<>("Seller is registered", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Seller is  not registered", HttpStatus.BAD_REQUEST);
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////TO-REGISTER-A-BUYER///////////////////////////////////////////
    @PostMapping("/register/buyer")
    public ResponseEntity<String> registerBuyer(@Valid @RequestBody BuyerRegisterDTO buyerRegisterDTO, HttpServletRequest request) {

        if (buyerService.registerbuyer(buyerRegisterDTO, request)) {
            return new ResponseEntity<>("Buyer is registered", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Buyer is  not registered", HttpStatus.BAD_REQUEST);
        }

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////TO-CONFIRM-A-BUYER///////////////////////////////////////////
    @PutMapping("/confirm/buyer")
    public ResponseEntity<String> confirmToken(@Valid @RequestParam String token, HttpServletRequest request) {

        if (buyerService.confirmToken(token, request)) {
            return new ResponseEntity<>("Buyer is confirmed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Buyer is  not confirmed", HttpStatus.BAD_REQUEST);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////TO-REACTIVATION-LINK-A-BUYER///////////////////////////////////////////
    @PostMapping("/reactivatelink/buyer")
    public ResponseEntity<String> reactivateTokenForBuyer(@Valid @RequestParam String email, HttpServletRequest request) {
        if (buyerService.reactivateToken(email, request)) {
            return new ResponseEntity<>("Re-activation token is sent", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("This user is already activated", HttpStatus.BAD_REQUEST);
        }
    }


}
