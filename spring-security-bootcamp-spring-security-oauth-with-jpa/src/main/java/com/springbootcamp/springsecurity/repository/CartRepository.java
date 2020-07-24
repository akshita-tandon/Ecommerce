package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.Buyer;
import com.springbootcamp.springsecurity.entity.Cart;
import com.springbootcamp.springsecurity.entity.OrderProduct;
import com.springbootcamp.springsecurity.entity.ProductVariation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {

    Cart findByBuyerAndProductVariations(Buyer buyerId, ProductVariation productVariationId);

    List<Cart> findAllByBuyerAndIsWishListItem(Buyer buyer, boolean b);

    List<Cart> findAllByBuyer(Buyer buyer);

    List<Cart> findAllByBuyerAndIsDeleted(Buyer buyer, boolean b);

    List<Cart> findAllByBuyerAndIsDeletedAndIsWishListItem(Buyer buyer, boolean b, boolean b1);

}
