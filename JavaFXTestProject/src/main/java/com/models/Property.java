package com.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Property {
    private List<String> amenities;
    private int propertyID;
    private String address;
    private String name;
    private List<String> communityAnnouncements;

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

    @Override
    public String toString()
    {
        return propertyID+","+name+","+address+","+amenities + "COMMUNITY ANNOUNCEMENTS: " + communityAnnouncements.size();
    }


}

