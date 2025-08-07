package com.rsh.model;

public class User extends Person{
    private String mUsername;
    private String mPassword;

    public  User(String firstName, String lastName, String email, String username, String password) {

        super(firstName, lastName, email);
        this.mUsername = username;
        this.mPassword = password;
    }


}
