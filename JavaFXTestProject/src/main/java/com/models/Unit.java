package com.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Unit {
    private int unitID;
    private boolean isFurnished;
    private double rentAmount;
    private String floorPlan;
    private String condition;
    private boolean isRented;
    private ArrayList<String> appliances;
    private int propertyID;
    private boolean rentPaid;
    private Date rentDue;
    private int userID;

    public Unit(int unitID, boolean isFurnished, double rentAmount, String floorPlan, String condition, boolean isRented, String[] appliances, int propertyID, boolean rentPaid, Date rentDue, int userID) {
        this.unitID = unitID;
        this.isFurnished = isFurnished;
        this.rentAmount = rentAmount;
        this.floorPlan = floorPlan;
        this.condition = condition;
        this.isRented = isRented;

        this.appliances = new ArrayList<String>();

        for (int i = 0 ; i < appliances.length; i++)
        {
            this.appliances.add(appliances[i]);
        }

        this.propertyID = propertyID;
        this.rentPaid = rentPaid;
        this.rentDue = rentDue;
        this.userID = userID;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public boolean isFurnished() {
        return isFurnished;
    }

    public void setFurnished(boolean furnished) {
        isFurnished = furnished;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(int rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public ArrayList<String> getAppliances() {
        return appliances;
    }

    public void setAppliances(ArrayList<String> appliances) {
        this.appliances = appliances;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public boolean isRentPaid() {
        return rentPaid;
    }

    public void setRentPaid(boolean rentPaid) {
        this.rentPaid = rentPaid;
    }

    public Date getRentDue() {
        return rentDue;
    }

    public void setRentDue(Date rentDue) {
        this.rentDue = rentDue;
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
        return unitID+","+ isFurnished +"," + rentAmount+"," + floorPlan+"," + condition+"," + isRented+"," + rentPaid+"," + rentDue.toString();
    }


}


