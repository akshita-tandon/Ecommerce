package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.CategoryMetadataFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryMetadataFieldValueRepository extends JpaRepository<CategoryMetadataFieldValue,Long> {

    Optional<CategoryMetadataFieldValue> findByFieldIdCategoryCategoryIdAndFieldIdCategoryMetadataFieldCategoryId(Long category_id, Long category_metadata_field_id);


  //  Hibernate: insert into category_metadata_field_value (metadata_values, category_id, category_metadata_field_id) values (?, ?, ?)

    @Query(value = "select metadata_values from category_metadata_field_value where category_id=:category_id AND category_metadata_field_id=:category_metadata_field_id",nativeQuery = true)
    String fetchMetadataValues(@Param("category_id") Object category_id, @Param("category_metadata_field_id") Object category_metadata_field_id);
}