package com.db;

import java.util.List;

import com.models.Property;

public interface IDatabaseOperations {

    public List<Property> propertyList();

    String checkLogin(String email, String password);
    String registerUser(String email, String firstName, String lastName, String password, String phoneNumber);

}
