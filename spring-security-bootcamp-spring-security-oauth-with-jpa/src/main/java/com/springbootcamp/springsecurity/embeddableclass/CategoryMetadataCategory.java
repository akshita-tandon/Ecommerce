package com.springbootcamp.springsecurity.embeddableclass;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoryMetadataCategory implements Serializable {

    Long categoryCategoryId;
    Long categoryMetadataFieldCategoryId;

    public CategoryMetadataCategory() {
    }

    public Long getCategoryCategoryId() {
        return categoryCategoryId;
    }

    public void setCategoryCategoryId(Long categoryCategoryId) {
        this.categoryCategoryId = categoryCategoryId;
    }

    public Long getCategoryMetadataFieldCategoryId() {
        return categoryMetadataFieldCategoryId;
    }

    public void setCategoryMetadataFieldCategoryId(Long categoryMetadataFieldCategoryId) {
        this.categoryMetadataFieldCategoryId = categoryMetadataFieldCategoryId;
    }

    public CategoryMetadataCategory(Long categoryCategoryId, Long categoryMetadataFieldCategoryId) {
        this.categoryCategoryId = categoryCategoryId;
        this.categoryMetadataFieldCategoryId = categoryMetadataFieldCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryMetadataCategory that = (CategoryMetadataCategory) o;
        return Objects.equals(categoryCategoryId, that.categoryCategoryId) &&
                Objects.equals(categoryMetadataFieldCategoryId, that.categoryMetadataFieldCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryCategoryId, categoryMetadataFieldCategoryId);
    }
}