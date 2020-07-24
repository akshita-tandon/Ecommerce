package com.springbootcamp.springsecurity.dto;

import com.springbootcamp.springsecurity.entity.OrderStatus;

public class OrderProductSellerResponseDTO {

    private Long orderProductId;
    private Integer quantity;
    private Float price;
    private Boolean isDeleted;
    private OrderStatusResponseDto orderStatus;
    private ProductVariationResponseDTO productVariation;

    public OrderProductSellerResponseDTO() {
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public OrderStatusResponseDto getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusResponseDto orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ProductVariationResponseDTO getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariationResponseDTO productVariation) {
        this.productVariation = productVariation;
    }
}
