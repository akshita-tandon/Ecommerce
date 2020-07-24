package com.springbootcamp.springsecurity.dto;

import org.json.simple.JSONArray;

import javax.validation.constraints.NotNull;

public class UpdateVariationDTO {

    @NotNull
    private Long productVariationId;
    private JSONArray metadataArray;
    private String primaryImageName;
    private Integer quantityAvailable;
    private Float price;
    private Boolean isActive;

    public UpdateVariationDTO() {
    }

    public Long getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(Long productVariationId) {
        this.productVariationId = productVariationId;
    }

    public JSONArray getMetadataArray() {
        return metadataArray;
    }

    public void setMetadataArray(JSONArray metadataArray) {
        this.metadataArray = metadataArray;
    }

    public String getPrimaryImageName() {
        return primaryImageName;
    }

    public void setPrimaryImageName(String primaryImageName) {
        this.primaryImageName = primaryImageName;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
