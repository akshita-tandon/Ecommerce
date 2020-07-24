package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class ProductReview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_review_id")
    private Long productReviewId;
    @NotNull
    private String review;
    @NotNull
    private Integer rating;
    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted =false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_user_id",referencedColumnName = "user_id")
    private Buyer buyer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "product_id")
    private Product product;

    public ProductReview() {
    }

    public Long getProductReviewId() {
        return productReviewId;
    }

    public void setProductReviewId(Long productReviewId) {
        this.productReviewId = productReviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
