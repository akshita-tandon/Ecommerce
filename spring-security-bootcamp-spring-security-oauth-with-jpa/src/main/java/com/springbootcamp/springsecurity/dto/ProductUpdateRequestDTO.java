package com.springbootcamp.springsecurity.dto;

import javax.validation.constraints.NotNull;

public class ProductUpdateRequestDTO {

    @NotNull
    private Long productId;
    private String name;
    private String description;
    private Boolean isCancellable;
    private Boolean isReturnable;

    public ProductUpdateRequestDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsCancellable() {
        return isCancellable;
    }

    public void setIsCancellable(Boolean isCancellable) {
        this.isCancellable = isCancellable;
    }

    public Boolean getIsReturnable() {
        return isReturnable;
    }

    public void setIsReturnable(Boolean isReturnable) {
        this.isReturnable = isReturnable;
    }
}
