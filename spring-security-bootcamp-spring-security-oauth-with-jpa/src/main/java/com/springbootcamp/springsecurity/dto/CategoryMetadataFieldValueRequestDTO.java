package com.springbootcamp.springsecurity.dto;

import java.util.List;

public class CategoryMetadataFieldValueRequestDTO {

    private Long category_id;
    private Long[] metadata_field_id;
    private List<String>[] metadatavalues;

    public CategoryMetadataFieldValueRequestDTO() {
    }

    public Integer countArray(){
        return metadata_field_id.length;
    }
    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Long getMetadata_field_id(int i) {

        return  metadata_field_id[i];

    }

    public void setMetadata_field_id(Long[] metadata_field_id) {
        this.metadata_field_id = metadata_field_id;
    }

    public List<String> getMetadatavalues(int j) {

       return metadatavalues[j];

    }

    public void setMetadatavalues(List<String>[] metadatavalues) {
        this.metadatavalues = metadatavalues;
    }

    public String convertToString(List<String> values){

        String metadataValues= String.join(", ", values);
        return metadataValues;
    }

}
