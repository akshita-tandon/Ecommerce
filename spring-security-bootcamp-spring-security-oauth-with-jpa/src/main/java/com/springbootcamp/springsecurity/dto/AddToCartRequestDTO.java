package com.springbootcamp.springsecurity.dto;

import javax.validation.constraints.NotNull;

public class AddToCartRequestDTO {

    @NotNull
    private Integer quantity;
    private Boolean isWishlistItem = false;
    private Boolean isDeleted = false;
    @NotNull
    private Long productVariationId;

    public AddToCartRequestDTO() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(Long productVariationId) {
        this.productVariationId = productVariationId;
    }

    public Boolean getWishlistItem() {
        return isWishlistItem;
    }

    public void setWishlistItem(Boolean wishlistItem) {
        isWishlistItem = wishlistItem;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

}
