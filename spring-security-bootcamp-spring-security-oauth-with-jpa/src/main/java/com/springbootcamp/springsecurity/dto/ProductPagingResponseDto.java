package com.springbootcamp.springsecurity.dto;

import com.springbootcamp.springsecurity.entity.Category;
import com.springbootcamp.springsecurity.entity.Seller;

public class ProductPagingResponseDto {

    private String name;
    private Long productId;
    private Long categoryId;
    private String categoryName;
    private String description;
    private String brand;
    private Long seller_user_id;
    private Boolean isCancellable;
    private Boolean isReturnable;





    public ProductPagingResponseDto(String name, Long productId, Category category, String description, String brand, Seller seller, Category category1,Boolean isCancellable,Boolean isReturnable) {

        this.name=name;
        this.productId=productId;
        this.categoryId =category.getCategoryId();
        this.description=description;
        this.brand=brand;
        this.categoryName=category.getName();
        this.seller_user_id=seller.getId();
        this.isCancellable=isCancellable;
        this.isReturnable=isReturnable;

    }

    public ProductPagingResponseDto() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getSeller_user_id() {
        return seller_user_id;
    }

    public void setSeller_user_id(Long seller_user_id) {
        this.seller_user_id = seller_user_id;
    }


}
