package com.springbootcamp.springsecurity.dto;

import com.springbootcamp.springsecurity.entity.Category;
import com.springbootcamp.springsecurity.entity.CategoryMetadataFieldValue;

import java.util.List;
import java.util.Set;

public class CustomerFilterCategoryResponseDTO {

    private Long categoryId;
    private String name;
    private Set<CategoryMetadataFieldValue> categoryMetadataFieldValue;
    private List<String> brands;
    private Float maximumPrice;
    private Float minimumPrice;

    public CustomerFilterCategoryResponseDTO() {
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

    public Set<CategoryMetadataFieldValue> getCategoryMetadataFieldValue() {
        return categoryMetadataFieldValue;
    }

    public void setCategoryMetadataFieldValue(Set<CategoryMetadataFieldValue> categoryMetadataFieldValue) {
        this.categoryMetadataFieldValue = categoryMetadataFieldValue;
    }

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public Float getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(Float maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public Float getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Float minimumPrice) {
        this.minimumPrice = minimumPrice;
    }
}
