package com.springbootcamp.springsecurity.dto;

public class AllProductVariationResponseDTO {

    private Long productVariationId;
    private Long productId;
    private Object metadata;
    private String primaryImageName;
    private Integer quantityAvailable;
    private Float price;

    public AllProductVariationResponseDTO() {
    }

    public AllProductVariationResponseDTO(Long productVariationId, Object metadata, Float price, Integer quantity_available, Long productId, String primary_image_name) {

        this.productVariationId=productVariationId;
        this.metadata=metadata;
        this.price=price;
        this.quantityAvailable =quantity_available;
        this.productId=productId;
        this.primaryImageName =primary_image_name;
    }

    public Long getProductVariationId() {
        return productVariationId;
    }

    public void setProductVariationId(Long productVariationId) {
        this.productVariationId = productVariationId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

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
}
