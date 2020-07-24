package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="orderProductId")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderProductId;
    @NotNull
    private Integer quantity;
    @NotNull
    private Float price;

    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted =false;

    @OneToOne(mappedBy = "orderProduct")
    private OrderStatus orderStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @OneToOne
    @JoinColumn(name = "product_variation_id")
    private ProductVariation productVariation;


    @NotNull
    private String product_variation_metadata;

    public OrderProduct() {
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }

    public String getProduct_variation_metadata() {
        return product_variation_metadata;
    }

    public void setProduct_variation_metadata(String product_variation_metadata) {
        this.product_variation_metadata = product_variation_metadata;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "order_product_id=" + orderProductId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", is_deleted=" + isDeleted +
                '}';
    }
}
