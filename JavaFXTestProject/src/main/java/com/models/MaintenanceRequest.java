package com.models;

import java.util.Date;

public class MaintenanceRequest {

    private boolean isDealtWith;
    private int requestID;
    private Date timeStamp;
    private int propertyID;
    private int unitID;
    private int userID;

    public MaintenanceRequest(boolean isDealtWith, int requestID, Date timeStamp, int propertyID, int unitID, int userID) {
        this.isDealtWith = isDealtWith;
        this.requestID = requestID;
        this.timeStamp = timeStamp;
        this.propertyID = propertyID;
        this.unitID = unitID;
        this.userID = userID;
    }

    public boolean isDealtWith() {
        return isDealtWith;
    }

    public void setDealtWith(boolean dealtWith) {
        isDealtWith = dealtWith;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    @Override
    public String toString()
    {
        return isDealtWith+","+requestID+","+timestamp.toString()+","+propertyID+","+unitID+","+userID;
    }


}

