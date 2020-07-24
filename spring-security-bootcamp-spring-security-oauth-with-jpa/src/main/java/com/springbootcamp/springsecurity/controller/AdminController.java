package com.springbootcamp.springsecurity.controller;

import com.springbootcamp.springsecurity.dto.BuyerResponseDTO;
import com.springbootcamp.springsecurity.dto.SellerResponseDTO;
import com.springbootcamp.springsecurity.service.BuyerService;
import com.springbootcamp.springsecurity.service.SellerService;
import com.springbootcamp.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    SellerService sellerService;

    @Autowired
    BuyerService buyerService;

    @Autowired
    UserService userService;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-FIND-ALL-BUYERS/////////////////////////////////////////////////
    @GetMapping("/buyers")
    public Page<BuyerResponseDTO> findAllBuyers(Pageable pageable) {

        return buyerService.pagingBuyer(pageable);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-FIND-A-SELLERS/////////////////////////////////////////////////
    @GetMapping("/sellers")
    public Page<SellerResponseDTO> findAllSellers(Pageable pageable) {

        PageImpl<SellerResponseDTO> sellerResponseDTOS = sellerService.pagingSeller(pageable);
        return sellerResponseDTOS;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-ACTIVATE-A-BUYER/////////////////////////////////////////////////
    @PatchMapping("/activate/buyer")
    public ResponseEntity<String> activateBuyer(@RequestParam Long id) {

        if (userService.activate(id)) {
            return new ResponseEntity<>("Buyer is activated ", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Buyer is already activated", HttpStatus.BAD_REQUEST);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-DEACTIVATE-A-BUYER/////////////////////////////////////////////////
    @PatchMapping("/deactivate/buyer")
    public ResponseEntity<String> deactivateBuyer(@RequestParam Long id) {

        if (userService.deactivate(id)) {
            return new ResponseEntity<>("Buyer is de-activated ", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Buyer is already de-activated", HttpStatus.BAD_REQUEST);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-ACTIVATE-A-BUYER/////////////////////////////////////////////////
    @PatchMapping("/activate/seller")
    public ResponseEntity<String> activateSeller(@RequestParam Long id) {

        if (userService.activate(id))
            return new ResponseEntity<>("Seller is activated ", HttpStatus.OK);
        else
            return new ResponseEntity<>("Seller is already activated", HttpStatus.BAD_REQUEST);


    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////TO-DEACTIVATE-A-SELLER/////////////////////////////////////////////////
    @PatchMapping("/deactivate/seller")
    public ResponseEntity<String> deactivateSeller(@RequestParam Long id) {


        if (userService.deactivate(id))
            return new ResponseEntity<>("Seller is de-activated ", HttpStatus.OK);
        else
            return new ResponseEntity<>("Seller is already de-activated", HttpStatus.BAD_REQUEST);

    }

}
