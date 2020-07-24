package com.springbootcamp.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="categoryId")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long categoryId;
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER)
    private Set<Category> childCategory;// change name to childcategory

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Product> product;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CategoryMetadataFieldValue> categoryMetadataFieldValue;

    @NotNull
    @JsonIgnore
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    public Category() {
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

    public Set<Category> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(Set<Category> childCategory) {
        this.childCategory = childCategory;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<CategoryMetadataFieldValue> getCategoryMetadataFieldValue() {
        return categoryMetadataFieldValue;
    }

    public void setCategoryMetadataFieldValue(Set<CategoryMetadataFieldValue> categoryMetadataFieldValue) {
        this.categoryMetadataFieldValue = categoryMetadataFieldValue;
    }
}
