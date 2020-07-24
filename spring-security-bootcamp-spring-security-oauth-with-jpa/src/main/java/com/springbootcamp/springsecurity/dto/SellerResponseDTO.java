package com.springbootcamp.springsecurity.dto;

public class SellerResponseDTO {

    private Long user_id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private Boolean isActive;
    private String companyName;
    private String companyContact;

    public SellerResponseDTO() {
    }

    public SellerResponseDTO(Long id, String email, String first_name, String middle_name, String last_name, Boolean is_active, String company_name, String company_contact) {
        this.user_id = id;
        this.email = email;
        this.firstName = first_name;
        this.middleName = middle_name;
        this.lastName = last_name;
        this.isActive = is_active;
        this.companyName = company_name;
        this.companyContact = company_contact;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String comapny_contact) {
        this.companyContact = companyContact;
    }
}
