package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category,Long> {
    Category findByName(String books);

    Page<Category> findAll(Pageable pageable);
    //make a finder method by orderbycategoryId

    @Query(value = "select category_id from category where category_id NOT IN (select parent_id from category where parent_id IS NOT NULL)",nativeQuery = true)
    List<Long> fetchLeafCategoryId();

    @Query(nativeQuery = true,value = "select * from category c where c.parent_id=:parent_id")
    List<Category> fetchNextChild(@Param("parent_id") Long parent_id);

    @Query(value = "select category_id from category where parent_id IS NULL",nativeQuery = true)
    List<Long> fetchParentId();
}
