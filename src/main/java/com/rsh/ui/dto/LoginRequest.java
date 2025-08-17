package com.rsh.ui.dto;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // (no setters for the  immutability in frontend)
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

