package com.springbootcamp.springsecurity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PasswordUpdateDTO {

    @NotNull
    @NotBlank(message = "Password cannot be blank ")
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,64})",message="Password must be 8 characters long")
    private String password;
    @NotNull
    @NotBlank(message = "Password cannot be blank ")
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,64})",message="Password must be 8 characters long")
    private String confirmPassword;

    public PasswordUpdateDTO() {
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
