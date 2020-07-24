package com.springbootcamp.springsecurity.dto;

import com.springbootcamp.springsecurity.entity.CategoryMetadataFieldValue;

import javax.persistence.Column;
import java.util.Set;

public class CategoryMetadataFieldResponseDTO {

    private Long categoryMetadataFieldId;
    @Column(unique = true)
    private String name;



    public Long getCategoryMetadataFieldId() {
        return categoryMetadataFieldId;
    }

    public void setCategoryMetadataFieldId(Long categoryMetadataFieldId) {
        this.categoryMetadataFieldId = categoryMetadataFieldId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryMetadataFieldResponseDTO(Long category_metadata_field_id, String name) {
        this.categoryMetadataFieldId = category_metadata_field_id;
        this.name = name;
    }
}
