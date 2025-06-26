package com.pm.springsecuritymonolithic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class AuthRequestDTO {

    @NotNull(message = "email is required")
    @Email(message = "email must be of valid format")
    private String email;

    @NotNull(message = "password is required")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
