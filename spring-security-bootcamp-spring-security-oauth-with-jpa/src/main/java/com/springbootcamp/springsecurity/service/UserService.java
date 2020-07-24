package com.springbootcamp.springsecurity.service;

import com.springbootcamp.springsecurity.dto.AddressUpdateDTO;
import com.springbootcamp.springsecurity.dto.PasswordUpdateDTO;
import com.springbootcamp.springsecurity.dto.ResetDTO;
import com.springbootcamp.springsecurity.entity.Address;
import com.springbootcamp.springsecurity.entity.Role;
import com.springbootcamp.springsecurity.entity.User;
import com.springbootcamp.springsecurity.exception.TokenNotFoundException;
import com.springbootcamp.springsecurity.exception.UserNotFoundException;
import com.springbootcamp.springsecurity.repository.AddressRepository;
import com.springbootcamp.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    TokenStore tokenStore;

    public Boolean recieveToken(String email, HttpServletRequest request) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("There is no user with the given email id");
        }
        if (user.getActive()) {

            user.setConfirmationToken(UUID.randomUUID().toString());
            user.setExpiryDate(5);
            userRepository.save(user);
            String appUrl = request.getScheme() + "://" + request.getServerName();
            emailService.sendEmail(user.getEmail(), appUrl, "Reset Password", user.getConfirmationToken(), "/reset/token?=");
            return true;
        }
        return false;
    }

    public Boolean resetPassword(ResetDTO resetDTO) {

        User userExists = userRepository.findByConfirmationToken(resetDTO.getConfirmationToken());
        if (userExists == null) {
            throw new TokenNotFoundException("This is an Invalid Token");
        }
        Calendar cal = Calendar.getInstance();
        if ((userExists.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            userExists.setConfirmationToken("NULl");
            userRepository.save(userExists);
            emailService.sendEmail(userExists.getEmail(), "Password Not Updated", "Your token has been expired.please get another token and try with that token to reser your password");
            return false;
        } else {
            if (resetDTO.getPassword().equals(resetDTO.getConfirmPassword())) {

                String newPassword = bCryptPasswordEncoder.encode(resetDTO.getConfirmPassword());
                userExists.setPassword(newPassword);
                userExists.setConfirmationToken("NULL");
                userRepository.save(userExists);
                emailService.sendEmail(userExists.getEmail(), "Password Updated", "Your password has been updated , please enjoy our website");
                return true;
            }
            return false;
        }

    }

    public boolean updatePassword(String username, PasswordUpdateDTO passwordUpdateDTO) {
        if (passwordUpdateDTO.getPassword().equals(passwordUpdateDTO.getConfirmPassword())) {
            String newPassword = bCryptPasswordEncoder.encode(passwordUpdateDTO.getConfirmPassword());
            User user = userRepository.findByUsername(username);
            user.setPassword(newPassword);
            userRepository.save(user);
            emailService.sendEmail(user.getEmail(), "Password Updated", "Your password has been updated for your acoount");
            return true;
        } else
            return false;
    }


    public Boolean activate(Long id) {

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("There is no User with the given id");
        } else if (!user.getActive()) {
            user.setActive(true);
            userRepository.save(user);

            emailService.sendEmail(user.getEmail(), "Account Activated", "Your account has been activated.");
            return true;
        } else
            return false;
    }

    public Boolean deactivate(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("There is no User with the given id");
        } else if (user.getActive()) {
            user.setActive(false);
            userRepository.save(user);

            emailService.sendEmail(user.getEmail(), "Account De-Activated", "Your account has been de-activated.");
            return true;
        } else {
            return false;
        }
    }

    public boolean updateAddress(Long address_id, AddressUpdateDTO addressUpdateDTO) {

        if (addressUpdateDTO.getAddress() != null) {
            Address address = addressRepository.findById(address_id).get();
            address.setAddress(addressUpdateDTO.getAddress());
            addressRepository.save(address);
        }
        if (addressUpdateDTO.getCity() != null) {
            Address address = addressRepository.findById(address_id).get();
            address.setCity(addressUpdateDTO.getCity());
            addressRepository.save(address);
        }
        if (addressUpdateDTO.getCountry() != null) {
            Address address = addressRepository.findById(address_id).get();
            address.setCountry(addressUpdateDTO.getCountry());
            addressRepository.save(address);
        }
        if (addressUpdateDTO.getLabel() != null) {
            Address address = addressRepository.findById(address_id).get();
            address.setLabel(addressUpdateDTO.getLabel());
            addressRepository.save(address);
        }
        if (addressUpdateDTO.getState() != null) {
            Address address = addressRepository.findById(address_id).get();
            address.setState(addressUpdateDTO.getState());
            addressRepository.save(address);
        }
        if (addressUpdateDTO.getZipCode() != null) {
            Address address = addressRepository.findById(address_id).get();
            address.setZipCode(addressUpdateDTO.getZipCode());
            addressRepository.save(address);
        }
        return true;

    }

    public boolean logout(String token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(accessToken);
        return true;
    }

    public List<Role> getRole(String username) {
        User user=userRepository.findByUsername(username);
        if(user!=null){
            return user.getRole();
        }
        return null;
    }
}