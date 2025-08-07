package com.rsh.model;

public class Contact extends Person{
    private String mPhone;
    public  Contact(String firstName, String lastName, String email, String phone) {
        super(firstName, lastName, email);
        this.mPhone = phone;
        System.out.println("A contact has been created");
    }

}
