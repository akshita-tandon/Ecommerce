package com.springbootcamp.springsecurity.dto;

public class ProductVariationResponseDTO {

    private Long productVariationId;
//    private Long productId;
    private Object metadata;
    private String primaryImageName;
    private Integer quantityAvailable;
    private Float price;
    public ProductResponseDTO parentProduct;

    public ProductVariationResponseDTO() {
    }

    public Long getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(Long productVariationId) {
        this.productVariationId = productVariationId;
    }

//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
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

    public ProductResponseDTO getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(ProductResponseDTO parentProduct) {
        this.parentProduct = parentProduct;
    }
}