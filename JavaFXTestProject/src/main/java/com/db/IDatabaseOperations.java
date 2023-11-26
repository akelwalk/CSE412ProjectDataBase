package com.db;

import java.util.List;

import com.models.*;

public interface IDatabaseOperations {

    public List<Property> propertyList();
    public List<Users> userList();
    public List<PropertyManager> propertyManagerList();
    public List<Customers> customerList();
    public List<MaintenanceRequest> requestList();
    public List<Unit> unitList();
    public List<Tenants> tenantsList();
    public List<LeaseRequest> leaseRequestList();
    public List<String> getAmmenities(int property_id);



    public String checkLogin(String email, String password);
    public String registerUser(String email, String firstName, String lastName, String password, String phoneNumber);
    public String getName(String email);
    public String getPropertyName(int property_id);
    public String getPropertyAddress(int property_id);

    public int getPropertyId(int usr_id);


}
