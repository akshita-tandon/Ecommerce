package com.springbootcamp.springsecurity.dto;

import com.springbootcamp.springsecurity.entity.Seller;

import javax.validation.constraints.NotNull;

public class ProductRequestDTO {

    @NotNull
    private String name;
    private String description;
    @NotNull
    private Long categoryId;
    private Boolean isCancellable =false;
    private Boolean isReturnable =false;
    @NotNull
    private String brand;
    private Seller seller;

    public ProductRequestDTO() {
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

    public Boolean getIsCancellable() {
        return isCancellable;
    }

    public void setIsCancellable(Boolean isCancellable) {
        this.isCancellable = isCancellable;
    }

    public Boolean getIsReturnable() {
        return isReturnable;
    }

    public void setIsReturnable(Boolean isReturnable) {
        this.isReturnable = isReturnable;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
