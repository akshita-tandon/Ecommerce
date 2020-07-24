package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.CategoryMetadataField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CategoryMetadataFieldRepository extends CrudRepository<CategoryMetadataField,Long> {

    CategoryMetadataField findByName(String fieldname);

    Page<CategoryMetadataField> findAll(Pageable pageable);
}
