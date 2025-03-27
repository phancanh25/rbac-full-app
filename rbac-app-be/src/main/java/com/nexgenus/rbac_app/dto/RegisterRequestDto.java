package com.nexgenus.rbac_app.dto;

import com.nexgenus.rbac_app.entity.Role;

import java.util.Set;

public class RegisterRequestDto {
    private String username;
    private String password;
    private String role;

    public RegisterRequestDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public RegisterRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
