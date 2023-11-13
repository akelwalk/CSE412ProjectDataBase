package com.models;

public class PropertyManager {

    private int userID;
    private boolean isOwner;
    private int propertyID;

    public PropertyManager(int userID, boolean isOwner, int propertyID) {
        this.userID = userID;
        this.isOwner = isOwner;
        this.propertyID = propertyID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
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
        return userID+","+isOwner+","+propertyID+",";
    }


}

