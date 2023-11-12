package com.models;

import java.util.ArrayList;

public class Property {
    private String amenities;
    private int propertyID;
    private String address;
    private String name;
    private ArrayList<String> communityAnnouncements;

    public String getAmenities()
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

    public ArrayList<String> getCommunityAnnouncements()
    {
    return this.communityAnnouncements;
    }

    @Override
    public String toString()
    {
        return propertyID+","+name+","+address+","+amenities;
    }


}

