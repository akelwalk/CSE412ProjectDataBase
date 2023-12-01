package com.models;

import com.db.database_controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Property {
    private List<String> amenities;
    private int propertyID;
    private String address;
    private String name;
    private List<String> communityAnnouncements;

    private int freeUnits;

    public Property(String[] amenities, int propertyID, String address, String name, String[] communityAnnouncements)
    {
        this.propertyID = propertyID;
        this.address = address;
        this.name = name;

        this.amenities = new ArrayList<String>();
        this.communityAnnouncements = new ArrayList<String>();


        for (int i = 0 ; i < amenities.length; i++)
        {
            this.amenities.add(amenities[i]);
        }

        for (int i = 0 ; i < communityAnnouncements.length; i++) {
            this.communityAnnouncements.add(communityAnnouncements[i]);
        }

        database_controller dbController = new database_controller();
        this.freeUnits = dbController.getFreeUnits(propertyID);



    }

    public List<String> getAmenities()
    {
    return this.amenities;
    }
    public int getPropertyID()
    {
    return this.propertyID;
    }

    public String getAddress()
    {
    return this.address;
    }

    public String getName()
    {
    return this.name;
    }

    public List<String> getCommunityAnnouncements ()
    {
    return this.communityAnnouncements;
    }

    public int getFreeUnits() {
        return freeUnits;
    }

    public void setFreeUnits(int freeUnits) {
        this.freeUnits = freeUnits;
    }

    @Override
    public String toString()
    {
        return propertyID+","+name+","+address+","+amenities + "COMMUNITY ANNOUNCEMENTS: " + communityAnnouncements.size();
    }


}

