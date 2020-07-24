package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Long> {

    Buyer findByEmail(String email);
    Buyer findByConfirmationToken(String token);

    Buyer findByUsername(String username);
}
