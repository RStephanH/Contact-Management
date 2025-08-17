package com.rsh.model;

public abstract class Person {
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

    public String getFullName() {
        return this.mFirstName.concat(" ").concat(this.mLastName);
    }
}

