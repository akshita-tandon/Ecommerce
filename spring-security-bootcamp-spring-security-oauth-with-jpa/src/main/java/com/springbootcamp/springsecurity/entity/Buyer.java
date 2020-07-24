package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="user_id")
public class Buyer extends User{

    @NotNull
    //@Pattern(regexp = "(\\+91|0)[0-9]{9}")
    @Size(max=10)
    @Column(unique = true)
    private String contact;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Orders> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<ProductReview> productReviews;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Cart> cart;


    public Buyer() {
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    public Set<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(Set<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public Set<Cart> getCart() {
        return cart;
    }

    public void setCart(Set<Cart> cart) {
        this.cart = cart;
    }






}
