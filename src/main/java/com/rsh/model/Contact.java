package com.rsh.model;

public class Contact extends Person{
  private String mContactId;
  private String mPhone;
  private String mUserId;

  public  Contact(String firstName, String lastName, String email, String phone, String userId) {
    super(firstName, lastName, email);
    this.mPhone = phone;
    this.mUserId=userId;
  }

  public String getPhone() {
    return this.mPhone;
  }
  public void setPhone(String phone){
    this.mPhone=phone;
  }

  public String getContactId() {
    return this.mContactId;
  }

}
