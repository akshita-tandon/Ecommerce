package com.springbootcamp.springsecurity.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUser implements UserDetails {

    private String username;
    private String password;
    List<GrantedAuthority> grantAuthorities;
    private Boolean AccountNonLocked;
    private Boolean Enabled;

    public AppUser(String username, String password, List<GrantedAuthority> grantAuthorities,Boolean AccountNonLocked,
      Boolean Enabled) {
        this.username = username;
        this.password = password;
        this.grantAuthorities = grantAuthorities;
        this.AccountNonLocked=AccountNonLocked;
        this.Enabled=Enabled;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return grantAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return AccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Enabled;
    }
}