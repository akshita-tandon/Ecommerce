package com.springbootcamp.springsecurity.dto;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class BuyerRegisterDTO {

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
    @NotNull
    @NotBlank(message = "Last Name cannot be blank ")
    private String lastName;
    @NotNull
    @NotBlank(message = "Password cannot be blank ")
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,64})",message="Password must be 8 characters long")
    private String password;
    @NotNull
    @NotBlank(message = "Password cannot be blank ")
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,64})",message="Password must be 8 characters long")
    private String confirmPassword;

    @NotNull
    @Size(max = 10)
    @Column(unique = true)
    //@Pattern(regexp = "(\\+91|0)[0-9]{9}")
    private String contact;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
