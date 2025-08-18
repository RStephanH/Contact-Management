package com.rsh.ui.dto;

public class ContactDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String userId;

    public ContactDTO() {}

    public ContactDTO( String firstName, String lastName, String email, String phone, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userId=userId;
    }

    public ContactDTO(String id, String firstName, String lastName, String email, String phone, String userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userId=userId;
    }

    // Getters & setters
    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return this.firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return this.phone; }
    public void setPhone(String phone) { this.phone = phone; }


    public String getUserId() { return this.userId; }
    public void setUserId(String userId) { this.userId = userId; }
}

