package com.springbootcamp.springsecurity.configuration;

import com.springbootcamp.springsecurity.entity.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class GrantAuthorityImpl implements GrantedAuthority {

    private List<Role> authority;

    public GrantAuthorityImpl(List<Role> authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        //System.out.println("................."+authority);
        for (Role auth :authority)
        {
            System.out.println(".....//////////"+auth.getAuthority());
            return String.valueOf(auth.getAuthority());
        }
        //return String.valueOf(authority.get(0).getAuthority());
        return null;
    }
}