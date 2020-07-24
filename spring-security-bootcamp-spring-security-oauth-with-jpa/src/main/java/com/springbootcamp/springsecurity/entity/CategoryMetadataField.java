package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="categoryMetadataFieldId")
public class CategoryMetadataField {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoryMetadataFieldId;
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "categoryMetadataField",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<CategoryMetadataFieldValue> categoryMetadataFieldValues;

    public CategoryMetadataField() {
    }

    public Long getCategoryMetadataFieldId() {
        return categoryMetadataFieldId;
    }

    public void setCategoryMetadataFieldId(Long categoryMetadataFieldId) {
        this.categoryMetadataFieldId = categoryMetadataFieldId;
    }

    public Set<CategoryMetadataFieldValue> getCategoryMetadataFieldValues() {
        return categoryMetadataFieldValues;
    }

    public void setCategoryMetadataFieldValues(Set<CategoryMetadataFieldValue> categoryMetadataFieldValues) {
        this.categoryMetadataFieldValues = categoryMetadataFieldValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "CategoryMetadataField{" +
                "category_metadata_field_id=" + categoryMetadataFieldId +
                ", name='" + name + '\'' +
                ", categoryMetadataFieldValues=" + categoryMetadataFieldValues +
                '}';
    }
}
