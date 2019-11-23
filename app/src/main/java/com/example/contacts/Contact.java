package com.example.contacts;

import androidx.annotation.NonNull;

public class Contact {
    private String name;
    private String phoneNumber;
    private String eMail;

    public Contact() {
        name = "BLANK_NAME";
        phoneNumber = "";
        eMail = "";
    }

    public Contact(String name, String phoneNumber, String eMail) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
