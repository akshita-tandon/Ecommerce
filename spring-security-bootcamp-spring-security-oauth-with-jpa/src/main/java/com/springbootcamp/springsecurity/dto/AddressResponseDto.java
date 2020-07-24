package com.springbootcamp.springsecurity.dto;

public class AddressResponseDto {

    private long addressId;
    private String city;
    private String state;
    private String country;
    private String address;
    private Integer zipCode;
    private String label;

    public AddressResponseDto() {
    }

    public AddressResponseDto(Long addressId,String city, String state, String country, String address, Integer zip_code, String label) {
        this.addressId=addressId;
        this.city = city;
        this.state = state;
        this.country = country;
        this.address = address;
        this.zipCode = zip_code;
        this.label = label;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "AddressResponseDto{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", zip_code=" + zipCode +
                ", label='" + label + '\'' +
                '}';
    }
}
