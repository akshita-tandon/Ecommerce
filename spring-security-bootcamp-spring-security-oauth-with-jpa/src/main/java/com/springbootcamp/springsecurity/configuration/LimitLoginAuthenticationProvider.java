package com.springbootcamp.springsecurity.configuration;

import com.springbootcamp.springsecurity.entity.UserAttempts;
import com.springbootcamp.springsecurity.repository.UserAttemptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserAttemptsRepository userAttemptsRepository;

    @Autowired
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        try {

            Authentication auth = super.authenticate(authentication);


            //if reach here, means login success, else an exception will be thrown
            //reset the user_attempts
            userService.resetFailAttempts(authentication.getName());

            return auth;

        } catch (LockedException e){

            //this user is locked!
            String error = "";
            UserAttempts userAttempts = userAttemptsRepository.findByUsername(authentication.getName());
            if(userAttempts!=null){
                Date lastAttempts = userAttempts.getLastModified();
                error = "User account is locked! Username : " + authentication.getName() + "Last Attempts : " + lastAttempts;
            }else{
                error = e.getMessage();
            }

            throw new LockedException(error);
        }catch (BadCredentialsException e) {

            //invalid login, update to user_attempts
            userService.updateFailAttempts(authentication.getName());
            throw e;

        }

    }

}

