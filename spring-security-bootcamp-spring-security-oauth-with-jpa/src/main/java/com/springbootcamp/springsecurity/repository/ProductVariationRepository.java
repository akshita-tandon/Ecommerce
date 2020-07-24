package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.Product;
import com.springbootcamp.springsecurity.entity.ProductVariation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariationRepository extends CrudRepository<ProductVariation,Long> {

    List<ProductVariation> findByProduct(Product product, Pageable pageable);

    @Query(value = "select metadata from product_variation",nativeQuery = true)
    List<Object> fetchMetadata();

    @Query(value = "select MIN(price) from product_variation where product_id IN (select product_id from product where category_id=:category_id)",nativeQuery = true)
    Float getMinprice(@Param("category_id")Long category_id);

    @Query(value = "select MAX(price) from product_variation where product_id IN (select product_id from product where category_id=:category_id)",nativeQuery = true)
    Float getMaxprice(@Param("category_id")Long category_id);
}
