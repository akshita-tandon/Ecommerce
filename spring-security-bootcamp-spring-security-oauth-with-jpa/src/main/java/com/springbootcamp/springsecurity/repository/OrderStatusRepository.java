package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus,Long> {
}
