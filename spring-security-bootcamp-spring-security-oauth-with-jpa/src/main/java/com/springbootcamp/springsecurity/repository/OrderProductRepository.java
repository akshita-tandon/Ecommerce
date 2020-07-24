package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.OrderProduct;
import com.springbootcamp.springsecurity.entity.Seller;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct,Long> {

    Iterable<OrderProduct> findAllByIsDeleted(boolean b);
}
