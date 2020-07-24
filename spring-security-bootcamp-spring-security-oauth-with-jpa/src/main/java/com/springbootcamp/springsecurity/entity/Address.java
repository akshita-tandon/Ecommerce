package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springbootcamp.springsecurity.AuditingInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
public class Address  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long addressId;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;
    @NotNull
    @Column(unique = true)
    private String address;
    @NotNull
    @Column(name = "zip_code")
    private Integer zipCode;
    @NotNull
    private String label;
    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted =false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Address() {
    }



}
