package com.example.contacts;

import androidx.annotation.NonNull;

/**
 * Sets up a Contact class which can be stored into a list
 */

public class Contact {
    private String name;
    private String phoneNumber;
    private String eMail;
    private String address;

    /**
     * DVC for the Contact class
     */
    public Contact() {
        name = "BLANK_NAME";
        phoneNumber = "";
        eMail = "";
        address = "";
    }

    /**
     * EVC for the Contact class
     *
     * @param name - name of the contact
     * @param phoneNumber - phone number of the contact
     * @param eMail - email of the contact
     * @param address - address of the contact
     */
    public Contact(String name, String phoneNumber, String eMail, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.address = address;
    }

    /**
     * @return String representation of the Contact (in this case, the Contact's name)
     */
    @NonNull
    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets the name of the Contact
     *
     * @return contact's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Contact
     *
     * @param name - name to set as contact's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the phone number of the Contact
     *
     * @return - contact's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the Contact
     *
     * @param phoneNumber - phone number to set as contact's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the email address of the Contact
     *
     * @return - contact's email address
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * Sets the email address of the Contact
     *
     * @param eMail - email address to set as contact's email address
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * Gets the physical address of the Contact
     *
     * @return - contact's physical address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the physical address of the Contact
     *
     * @param address - street address to set as contact's address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}

