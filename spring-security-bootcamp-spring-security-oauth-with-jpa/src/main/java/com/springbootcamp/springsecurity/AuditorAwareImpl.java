package com.springbootcamp.springsecurity;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication==null||authentication.getPrincipal().equals("anonymousUser") || !authentication.isAuthenticated())
        {
            return Optional.of("Self");
        }
        return Optional.ofNullable(((UserDetails) authentication.getPrincipal()).getUsername());
    }
}

