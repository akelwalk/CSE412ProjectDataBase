package com.models;

import java.util.ArrayList;
import java.util.Date;

public class Unit {
    private int unitID;
    private boolean isFurnished;
    private double rentAmount;
    private String floorplan;
    private String condition;
    private boolean isRented;
    private ArrayList<String> appliances;
    private boolean rentPaid;
    private Date rentDue;

    private String isRentedString;
    private String isFurnishedString;

    private int propertyID;
    private int userID;

    public Unit(int unitID, boolean isFurnished, double rentAmount, String floorplan, String condition, boolean isRented, String appliances, boolean rentPaid, Date rentDue, int propertyID, int userID) {
        this.unitID = unitID;
       /* if (isFurnished.equals("t"))
        {
            this.isFurnished = true;
        }
        else
        {
            this.isFurnished = false;
        }*/


        this.rentAmount = rentAmount;
        this.floorplan = floorplan;
        this.condition = condition;


        /*if (isRented.equals("t"))
        {
            this.isRented = true;
        }
        else
        {
            this.isRented = false;
        }*/

        this.isRented = isRented;
        this.isFurnished = isFurnished;

        this.appliances = new ArrayList<String>(); //Placeholder until I figure out how to parse arrays.
        this.rentDue = rentDue;
        this.propertyID = propertyID;
        this.userID = userID;
        this.rentPaid = rentPaid;

        if (isRented)
        {
            this.isRentedString = "T";
        }
        else {
            this.isRentedString = "F";
        }

        if (isFurnished)
        {
            this.isFurnishedString = "T";
        }
        else {
            this.isFurnishedString = "F";
        }
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

    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public String getFloorplan() {
        return floorplan;
    }

    public void setFloorplan(String floorplan) {
        this.floorplan = floorplan;
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

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getIsRentedString() {
        return isRentedString;
    }

    public void setIsRentedString(String isRentedString) {
        this.isRentedString = isRentedString;
    }

    public String getIsFurnishedString() {
        return isFurnishedString;
    }

    public void setIsFurnishedString(String isFurnishedString) {
        this.isFurnishedString = isFurnishedString;
    }


    @Override
    public String toString()
    {
        return unitID+","+ isFurnished +"," + rentAmount+"," + floorplan+"," + condition+"," + isRented+"," + rentPaid+"," + rentDue.toString();
    }


}


