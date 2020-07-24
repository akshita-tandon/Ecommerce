package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.springbootcamp.springsecurity.embeddableclass.CartCompositeKey;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="cartId")

public class Cart implements Serializable {


    @EmbeddedId
    private CartCompositeKey cartId;
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long cartId;
    @NotNull
    private Integer quantity;
    @NotNull
    @Column(name = "is_wishlist_item")
    private Boolean isWishListItem=false;
    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted =false;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variation_id")
    @MapsId("cartProductVariationId")
    private ProductVariation productVariations;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_user_id")
    @MapsId("cartBuyerId")
    @JsonIgnore
    private Buyer buyer;

    public Cart() {
    }

    public Cart(Buyer buyer,ProductVariation productVariation) {
        this.cartId = new CartCompositeKey(buyer.getId(),productVariation.getProductVariationId());
        this.buyer=buyer;
        this.productVariations=productVariation;
    }

    public CartCompositeKey getCartId() {
        return cartId;
    }

    public void setCartId(CartCompositeKey cartId) {
        this.cartId = cartId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getWishListItem() {
        return isWishListItem;
    }

    public void setWishListItem(Boolean wishListItem) {
        isWishListItem = wishListItem;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public ProductVariation getProductVariations() {
        return productVariations;
    }

    public void setProductVariations(ProductVariation productVariations) {
        this.productVariations = productVariations;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }


}
