package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.Buyer;
import com.springbootcamp.springsecurity.entity.OrderProduct;
import com.springbootcamp.springsecurity.entity.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders,Long> {
    Orders findByOrderProduct(OrderProduct orderProduct);

    Iterable<Orders> findAllByBuyer(Buyer buyer);
}
