package com.rsh.model;

import javafx.beans.property.*;

public class ContactFX {

    private final StringProperty contactId;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty userId;

    // --- Constructeur ---
    public ContactFX(String contactId, String firstName, String lastName, String email, String phone, String userId) {
        this.contactId = new SimpleStringProperty(contactId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.userId = new SimpleStringProperty(userId);
    }

    // --- Property Methods (pour JavaFX binding) ---
    public StringProperty contactIdProperty() { return contactId; }
    public StringProperty firstNameProperty() { return firstName; }
    public StringProperty lastNameProperty() { return lastName; }
    public StringProperty emailProperty() { return email; }
    public StringProperty phoneProperty() { return phone; }
    public StringProperty userIdProperty() { return userId; }

    // --- Getters classiques ---
    public String getContactId() { return contactId.get(); }
    public String getFirstName() { return firstName.get(); }
    public String getLastName() { return lastName.get(); }
    public String getEmail() { return email.get(); }
    public String getPhone() { return phone.get(); }
    public String getUserId() { return userId.get(); }

    // --- Setters classiques ---
    public void setContactId(String contactId) { this.contactId.set(contactId); }
    public void setFirstName(String firstName) { this.firstName.set(firstName); }
    public void setLastName(String lastName) { this.lastName.set(lastName); }
    public void setEmail(String email) { this.email.set(email); }
    public void setPhone(String phone) { this.phone.set(phone); }
    public void setUserId(String userId) { this.userId.set(userId); }

    // --- Helper ---
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}

