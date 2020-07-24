package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.ProductReview;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewRepository extends PagingAndSortingRepository<ProductReview,Long> {
}
