package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springbootcamp.springsecurity.AuditingInfo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private int roleId;
    @NotNull
    private String authority;

    @JsonIgnore
    @ManyToMany(mappedBy = "role")
    private Set<User> user;

    public Role() {
    }
    public Role(String authority) {
        this.authority = authority;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public int getId() {
        return roleId;
    }

    public void setId(int id) {
        this.roleId = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
