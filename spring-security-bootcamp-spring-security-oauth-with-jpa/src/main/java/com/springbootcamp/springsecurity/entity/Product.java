package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.springbootcamp.springsecurity.AuditingInfo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="productId")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id")
    private Long productId;
    @NotNull
    private String name;

    private String description;

    @Column(name = "is_cancellable")
    private Boolean isCancellable =false;
    @Column(name = "is_returnable")
    private Boolean isReturnable =false;
    @NotNull
    private String brand;
    @Column(name = "is_active")
    private Boolean isActive =false;
    @Column(name = "is_deleted")
    private Boolean isDeleted =false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private Seller seller;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<ProductVariation> productVariation;

    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<ProductReview> productReviews;

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCancellable() {
        return isCancellable;
    }

    public void setCancellable(Boolean cancellable) {
        isCancellable = cancellable;
    }

    public Boolean getReturnable() {
        return isReturnable;
    }

    public void setReturnable(Boolean returnable) {
        isReturnable = returnable;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<ProductVariation> getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(Set<ProductVariation> productVariation) {
        this.productVariation = productVariation;
    }

    public Set<ProductReview> getProductReviews() {
        return productReviews;
    }

    public void setProductReviews(Set<ProductReview> productReviews) {
        this.productReviews = productReviews;
    }

    public void insertProductVariation(ProductVariation productVariations)
    {
        if(productVariation==null)
        {
            productVariation=new HashSet<>();
        }
        productVariation.add(productVariations);
        productVariations.setProduct(this);
        this.setProductVariation(productVariation);

    }
}
