package com.rsh.ui.dto;

public class LoginResponse {
    private boolean success;
    private String message;

    // User info (without password)
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String mail;

    public LoginResponse() {}

    public LoginResponse(boolean success, String message, String userId, String username,
                         String firstName, String lastName, String mail) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
}


