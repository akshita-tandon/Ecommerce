package com.springbootcamp.springsecurity.dto;

public class CartResponseDTO {

    private BuyerResponseDTO buyer;
    private ProductVariationResponseDTO productVariations;
    private Integer quantity;

    public CartResponseDTO() {
    }


    public BuyerResponseDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerResponseDTO buyer) {
        this.buyer = buyer;
    }

    public ProductVariationResponseDTO getProductVariations() {
        return productVariations;
    }

    public void setProductVariations(ProductVariationResponseDTO productVariations) {
        this.productVariations = productVariations;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
