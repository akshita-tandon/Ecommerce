package com.springbootcamp.springsecurity.dto;

import java.util.ArrayList;
import java.util.List;

public class SellerProfileResponseDTO {

    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
//    private Boolean isActive;
    private String companyName;
    private String companyContact;
    private Float gst;
    private List<AddressResponseDto> addressResponseDtolist;

    public SellerProfileResponseDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Float getGst() {
        return gst;
    }

    public void setGst(Float gst) {
        this.gst = gst;
    }

    //
//    public Boolean isIs_active() {
//        return isActive;
//    }
//
//    public void setIsActive(Boolean isActive) {
//        this.isActive = isActive;
//    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public List<AddressResponseDto> getAddressResponseDtolist() {
        if(addressResponseDtolist==null){
            addressResponseDtolist=new ArrayList<>();
        }
        return addressResponseDtolist;
    }

    public void setAddressResponseDtolist(List<AddressResponseDto> addressResponseDtolist) {
        this.addressResponseDtolist = addressResponseDtolist;
    }
}
