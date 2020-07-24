package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends PagingAndSortingRepository<Seller,Long> {

    Optional<Seller> findById(Long address_id);
    Seller findByEmail(String email);
    Seller findByConfirmationToken(String confirmationtoken);

    @Override
    Page<Seller> findAll(Pageable pageable);

    Seller findByUsername(String username);


}
