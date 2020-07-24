package com.springbootcamp.springsecurity.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springbootcamp.springsecurity.entity.Category;
import com.springbootcamp.springsecurity.entity.CategoryMetadataFieldValue;


import java.util.List;
import java.util.Set;

public class CustomerCategoryResponseDTO {

    private Long categoryId;
    private String name;

    private Set<CustomerCategoryResponseDTO> childcategory;
    private Set<CategoryMetadataFieldValue> categoryMetadataFieldValue;
    @JsonIgnore
    private CategoryResponseDTO parentcategory;

    public CustomerCategoryResponseDTO()
    {
    }

    public CategoryResponseDTO getParentcategory() {
        return parentcategory;
    }

    public void setParentcategory(CategoryResponseDTO parentcategory) {
        this.parentcategory = parentcategory;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CustomerCategoryResponseDTO> getChildcategory() {
        return childcategory;
    }

    public void setChildcategory(Set<CustomerCategoryResponseDTO> childcategory) {
        this.childcategory = childcategory;
    }

    public Set<CategoryMetadataFieldValue> getCategoryMetadataFieldValue() {
        return categoryMetadataFieldValue;
    }

    public void setCategoryMetadataFieldValue(Set<CategoryMetadataFieldValue> categoryMetadataFieldValue) {
        this.categoryMetadataFieldValue = categoryMetadataFieldValue;
    }
}
