package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.Category;
import com.springbootcamp.springsecurity.entity.Product;
import com.springbootcamp.springsecurity.entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Product findByName(String name);

    Product findByNameAndSellerAndBrandAndCategory(String name, Seller seller, String brand, Category category);


    Page<Product> findAllBySellerAndIsDeleted(Seller seller,Boolean isDeleted, Pageable pageable);

    Product findByProductIdAndSeller(Long productId, Seller seller);


    @Query(value = "from Product p where p.category.categoryId=:category_id AND p.isDeleted=0")
    List<Product> fetchSimilarProducts(@Param("category_id") Long category_id, Pageable pageable);


    @Query(value = "select distinct brand from product p where p.category_id=:category_id",nativeQuery = true)
    List<String> fetchBrandList(@Param("category_id") Long category_id);

    Page<Product> findAll(Pageable pageable);

    List<Product> findByCategory(Category category);

    List<Product> findByCategoryAndIsDeleted(Category category, boolean b);
}
