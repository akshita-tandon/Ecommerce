package com.springbootcamp.springsecurity.dto;

public class BuyerResponseDTO {

    private Long id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private Boolean isActive;

    public BuyerResponseDTO() {
    }

    public BuyerResponseDTO(Long id, String email, String first_name, String middle_name, String last_name, Boolean is_active) {
        this.id = id;
        this.email = email;
        this.firstName = first_name;
        this.middleName = middle_name;
        this.lastName = last_name;
        this.isActive = is_active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
