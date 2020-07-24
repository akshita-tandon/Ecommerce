package com.springbootcamp.springsecurity.configuration;

import com.springbootcamp.springsecurity.entity.Role;
import com.springbootcamp.springsecurity.entity.User;
import com.springbootcamp.springsecurity.entity.UserAttempts;
import com.springbootcamp.springsecurity.repository.UserAttemptsRepository;
import com.springbootcamp.springsecurity.repository.UserRepository;
import com.springbootcamp.springsecurity.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
@Transactional
public class UserDao {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    UserAttemptsRepository userAttemptsRepository;

    private static final int MAX_ATTEMPTS = 3;

    AppUser loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        for (Role role : user.getRole()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        System.out.println(user);
        if (user != null) {
            return new AppUser(user.getUsername(), user.getPassword(), grantedAuthorities,user.getNonlocked(),user.getActive());
        } else {
            throw new RuntimeException();
        }

    }

    public void updateFailAttempts(String username) {
        UserAttempts userAttemptExists=userAttemptsRepository.findByUsername(username);

        if(userAttemptExists!=null) {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                userAttemptExists.setAttempts(userAttemptExists.getAttempts() + 1);
                userAttemptExists.setLastModified(new Date());
                userAttemptsRepository.save(userAttemptExists);
            }
            if (userAttemptExists.getAttempts() >= MAX_ATTEMPTS) {

                User userFound=userRepository.findByUsername(username);
                userFound.setNonlocked(false);
                userRepository.save(user);
                emailService.sendEmail(user.getEmail(),"Account Locked","Your Account has been locked as you have used the three attempts");
            }
        }
      else {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                UserAttempts userAttempts = new UserAttempts();
                userAttempts.setUsername(username);
                userAttempts.setAttempts(1);
                userAttempts.setLastModified(new Date());
                userAttemptsRepository.save(userAttempts);
            }
        }
    }

    public void resetFailAttempts(String username) {
        UserAttempts userAttempts=userAttemptsRepository.findByUsername(username);
        if(userAttempts!=null){
            userAttempts.setAttempts(0);
            userAttempts.setLastModified(new Date());
            userAttemptsRepository.save(userAttempts);
        }
    }


}
