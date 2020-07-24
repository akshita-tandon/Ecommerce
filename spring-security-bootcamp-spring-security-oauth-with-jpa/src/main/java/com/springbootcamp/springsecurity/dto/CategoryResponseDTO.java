package com.springbootcamp.springsecurity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springbootcamp.springsecurity.entity.Category;
import com.springbootcamp.springsecurity.entity.CategoryMetadataFieldValue;

import java.util.List;
import java.util.Set;


public class CategoryResponseDTO
{
    private Long categoryId;
    private String name;
    @JsonIgnore
    private Set<CategoryResponseDTO> childcategory;
    private List<Category> nextCategory;
    private CategoryResponseDTO parentcategory;
    private Set<CategoryMetadataFieldValue> categoryMetadataFieldValue;

    public CategoryResponseDTO() {
    }


//    public CategoryResponseDTO(Long categoryId, String name, Set<CategoryResponseDTO> childcategory, List<Category> nextCategory, CategoryResponseDTO parentcategory, Set<CategoryMetadataFieldValue> categoryMetadataFieldValue) {
//        this.categoryId = categoryId;
//        this.name = name;
//        this.childcategory = childcategory;
//        this.nextCategory = nextCategory;
//        this.parentcategory = parentcategory;
//        this.categoryMetadataFieldValue = categoryMetadataFieldValue;
//    }

    public List<Category> getNextCategory() {
        return nextCategory;
    }


    public void setNextCategory(List<Category> nextCategory) {
        this.nextCategory = nextCategory;
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

    public Set<CategoryResponseDTO> getChildcategory() {
        return childcategory;
    }

    public void setChildcategory(Set<CategoryResponseDTO> childcategory) {
        this.childcategory = childcategory;
    }

    public CategoryResponseDTO getParentcategory() {
        return parentcategory;
    }

    public void setParentcategory(CategoryResponseDTO parentcategory) {
        this.parentcategory = parentcategory;
    }

    public Set<CategoryMetadataFieldValue> getCategoryMetadataFieldValue() {
        return categoryMetadataFieldValue;
    }

    public void setCategoryMetadataFieldValue(Set<CategoryMetadataFieldValue> categoryMetadataFieldValue) {
        this.categoryMetadataFieldValue = categoryMetadataFieldValue;
    }
}
