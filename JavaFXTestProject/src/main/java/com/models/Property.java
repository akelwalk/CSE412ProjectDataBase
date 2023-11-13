package com.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Property {
    private String amenities;
    private int propertyID;
    private String address;
    private String name;
    private List<String> communityAnnouncements;

    public Property(String amenities, int propertyID, String address, String name, String communityAnnouncementsString)
    {
        this.amenities = amenities;
        this.propertyID = propertyID;
        this.address = address;
        this.name = name;
        this.communityAnnouncements = Arrays.asList(communityAnnouncementsString.split("\\s*,\\s*"));
        ;


    }

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

    public List<String> getCommunityAnnouncements()
    {
    return this.communityAnnouncements;
    }

    @Override
    public String toString()
    {
        return propertyID+","+name+","+address+","+amenities + "COMMUNITY ANNOUNCEMENTS: " + communityAnnouncements.size();
    }


}

