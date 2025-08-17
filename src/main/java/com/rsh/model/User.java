package com.rsh.model;

public class User extends Person{
    private String mUsername;
    private String mUserId;
  


    public  User(String firstName, String lastName, String email, String username, String userId) {

        super(firstName, lastName, email);
        this.mUsername = username;
        this.mUserId=userId;
    }

    public String getUserId() {
    return this.mUserId;
  }

}
