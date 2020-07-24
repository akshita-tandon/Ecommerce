package com.springbootcamp.springsecurity.embeddableclass;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartCompositeKey implements Serializable {

    private Long cartBuyerId;
    private Long cartProductVariationId;

    public CartCompositeKey(Long cartBuyerId, Long cartProductvariationId) {
        this.cartBuyerId = cartBuyerId;
        this.cartProductVariationId = cartProductvariationId;
    }

    public CartCompositeKey() {
    }

    public Long getCartBuyerId() {
        return cartBuyerId;
    }

    public void setCartBuyerId(Long cartBuyerId) {
        this.cartBuyerId = cartBuyerId;
    }

    public Long getCartProductvariationId() {
        return cartProductVariationId;
    }

    public void setCartProductvariationId(Long cartProductvariationId) {
        this.cartProductVariationId = cartProductvariationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartCompositeKey that = (CartCompositeKey) o;
        return Objects.equals(cartBuyerId, that.cartBuyerId) &&
                Objects.equals(cartProductVariationId, that.cartProductVariationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartBuyerId, cartProductVariationId);
    }
}
