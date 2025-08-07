package com.rsh.model;

public class Person {
    protected int mId;
    protected String mFirstName;
    protected String mLastName;
    protected String mEmail;

    public Person(String firstName, String lastName, String email) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mEmail = email;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public int getId() {
        return this.mId;
    }

    public String getFullName() {
        return this.mFirstName.concat(" ").concat(this.mLastName);

    }
}
