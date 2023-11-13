package com.models;

import java.util.Date;

public class Customers {

    private int userID;
    private String paymentType;
    private Date leaseStart;
    private Date leaseEnd;
    boolean isApproved;
    int unitID;
    int propertyID;

    public Customers(int userID, String paymentType, Date leaseStart, Date leaseEnd, boolean isApproved, int unitID, int propertyID) {
        this.userID = userID;
        this.paymentType = paymentType;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
        this.isApproved = isApproved;
        this.unitID = unitID;
        this.propertyID = propertyID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getLeaseStart() {
        return leaseStart;
    }

    public void setLeaseStart(Date leaseStart) {
        this.leaseStart = leaseStart;
    }

    public Date getLeaseEnd() {
        return leaseEnd;
    }

    public void setLeaseEnd(Date leaseEnd) {
        this.leaseEnd = leaseEnd;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    @Override
    public String toString()
    {
        return userID+","+paymentType+","+leaseStart.toString()+","+leaseEnd.toString()+","+isApproved+","+unitID+","+propertyID;
    }


}

