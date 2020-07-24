package com.springbootcamp.springsecurity.dto;

import com.springbootcamp.springsecurity.entity.Category;
import com.springbootcamp.springsecurity.entity.ProductVariation;

import java.util.Set;

public class BuyerProductResponseDTO {

    private Long productId;
    private String name;
    private String description;
    private Boolean isCancellable;
    private Boolean isActive;
    private Boolean isReturnable;
    private String brand;
    private Long categoryId;
    private String categoryName;
    private Set<ProductVariation> productVariation;


    public BuyerProductResponseDTO() {
    }

    public BuyerProductResponseDTO(Long productId, String name, String description, Boolean is_active, Boolean is_cancellable, Boolean is_returnable, String brand, Category category, Set<ProductVariation> productVariation) {
       this.productId=productId;
       this.name=name;
       this.description=description;
       this.isActive =is_active;
       this.isCancellable =is_cancellable;
       this.isReturnable =is_returnable;
       this.categoryId=category.getCategoryId();
       this.categoryName=category.getName();
       this.productVariation=productVariation;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public Boolean getIsCancellable() {
        return isCancellable;
    }

    public void setIsCancellable(Boolean isCancellable) {
        this.isCancellable = isCancellable;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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


    public Set<ProductVariation> getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(Set<ProductVariation> productVariation) {
        this.productVariation = productVariation;
    }
}
