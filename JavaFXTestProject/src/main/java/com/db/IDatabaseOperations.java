package com.db;

import java.util.List;

import com.models.*;

public interface IDatabaseOperations {

    public List<Property> propertyList();
    public List<Users> userList();
    public List<PropertyManager> propertyManagerList();
    public List<Customers> customerList();
    public List<MaintenanceRequest> maintenanceRequestList();
    public List<Unit> unitList();


    String checkLogin(String email, String password);
    String registerUser(String email, String firstName, String lastName, String password, String phoneNumber);

}
