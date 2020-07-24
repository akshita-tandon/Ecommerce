package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Seller extends User implements Serializable {

    @NotNull
    private Float gst;
    @NotNull
    @Column(name = "company_name",unique = true)
    private String companyName;
    @NotNull
    @Size(max = 10)
    //@Pattern(regexp = "(\\+91|0)[0-9]{9}")
    @Column(name = "company_contact",unique = true)
    private String companyContact;

    @JsonIgnore
    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Product> products;


    public Seller() {
    }

    public Float getGst() {
        return gst;
    }

    public void setGst(Float gst) {
        this.gst = gst;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }


}
