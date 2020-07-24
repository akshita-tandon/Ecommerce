package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.springbootcamp.springsecurity.embeddableclass.CategoryMetadataCategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="fieldId")
public class CategoryMetadataFieldValue implements Serializable {


    @EmbeddedId
    CategoryMetadataCategory fieldId;

    @JsonIgnore
    @MapsId("categoryCategoryId")
    @ManyToOne
    @JoinColumn (name = "category_id")
    Category category;

    @MapsId("categoryMetadataFieldCategoryId")
    @ManyToOne
    @JoinColumn (name = "category_metadata_field_id")
    CategoryMetadataField categoryMetadataField;


    @Convert(converter = StringListConverter.class)
    List<String> metadataValues = new ArrayList<String>();


    public CategoryMetadataFieldValue() {
    }


    public CategoryMetadataFieldValue(Category category, CategoryMetadataField categoryMetadataField) {
        this.fieldId = new CategoryMetadataCategory(category.getCategoryId(),categoryMetadataField.getCategoryMetadataFieldId());
        this.category = category;
        this.categoryMetadataField = categoryMetadataField;

    }


    public CategoryMetadataCategory getFieldId() {
        return fieldId;
    }

    public void setFieldId(CategoryMetadataCategory fieldId) {
        this.fieldId = fieldId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryMetadataField getCategoryMetadataField() {
        return categoryMetadataField;
    }

    public void setCategoryMetadataField(CategoryMetadataField categoryMetadataField) {
        this.categoryMetadataField = categoryMetadataField;
    }

    public List<String> getMetadataValues() {
        return metadataValues;
    }

    public void setMetadataValues(List<String> metadataValues) {
        this.metadataValues = metadataValues;
    }
}