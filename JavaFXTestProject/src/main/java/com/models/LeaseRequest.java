package com.models;

public class LeaseRequest {

    private int userID;
    private int propertyID;
    private int unitID;
    private String paymentType;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;


    public LeaseRequest(int userID, int propertyid, int unitid, String paymentType, String firstName, String lastName, String email, String phoneNumber) {
        this.userID = userID;
        this.propertyID = propertyid;
        this.unitID = unitid;
        this.paymentType = paymentType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyid) {
        this.propertyID = propertyid;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitid) {
        this.unitID = unitid;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "LeaseRequests{" +
                "propertyid=" + propertyID +
                ", unitid=" + unitID +
                ", paymentType='" + paymentType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }




}

