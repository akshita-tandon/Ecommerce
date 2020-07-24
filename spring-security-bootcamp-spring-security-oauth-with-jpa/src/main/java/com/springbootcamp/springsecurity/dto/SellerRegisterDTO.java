package com.springbootcamp.springsecurity.dto;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class SellerRegisterDTO {


    @NotNull
    private Float gst;
    @NotNull
    @Column(unique = true)
    private String companyName;
    @NotNull
    @Size(max=10)
    @Column(unique = true)
    private String companyContact;

    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    @Email(message = "Email cannot be blank ")
    @Column(unique = true)
    private String email;
    @NotNull
    @NotBlank(message = "First Name cannot be blank ")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Last Name cannot be blank ")
    private String lastName;
    @NotNull
    @NotBlank(message = "Password cannot be blank ")
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,64})",message="Password must be 8 characters long")
    private String password;

    @NotNull
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,64})",message="Password must be 8 characters long")
    private String confirmPassword;

    public SellerRegisterDTO() {
    }

    public Float getGst() {
        return gst;
    }

    public void setGst(Float gst) {
        this.gst = gst;
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

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
