package com.db;

// Imports 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.*;

import com.controllers.sessions.UserSession;

//Import models:

import com.models.*;


import org.postgresql.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class database_controller implements IDatabaseOperations {
    private static database_controller instance;
    // Constants for JDBC driver and database URL
    private static final String JDBC_DRIVER = "org.postgresql.Driver";  
    // Might end up getting the port # from a text file in the event windows uses a different port
    private static final String DB_URL = "jdbc:postgresql://localhost:8888/cse412project";


    private Connection connection = null;

    // TO-DO: I think we will need to start thinking about database permissions at some point 
    //        when creating new 'propery managers' we will need to figure out when to create these 
    //        accounts as well as when and how to elevate permissions

    //Instances to allow it to be used in controllers.

    //This constructor will connect to the database.
    public database_controller()
    {
        this.initializePostgresqlDatabase();
    }

    public static database_controller getInstance() {
        if (instance == null) {
            instance = new database_controller();
        }

        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new Driver());
            this.connection = DriverManager.getConnection(DB_URL);
            System.out.println("DB connected");
        } catch (IllegalArgumentException | SQLException var5) {
            var5.printStackTrace(System.err);
        } finally {
            if (this.connection == null) {
                System.exit(-1);
            }

        }

    }

    // Method to check the user login and return the role for the view
    public String checkLogin(String email, String password) {
        String role = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            String sql = "SELECT ROLE FROM users WHERE email = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                role = rs.getString("ROLE");
            }

            return role;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return role;
    }

    // Get User ID for given email
    public int getUserID(String email) {
        int userID = -1;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            String sql = "SELECT USERID FROM USERS WHERE email = ? ;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            rs = stmt.executeQuery();

            if (rs.next()) {
                userID = rs.getInt("USERID");
            }

            return userID;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return userID;
    }

    public String registerUser(String email, String firstName, String lastName, String password, String phoneNumber) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            // Check if user already exists
            String checkUserSql = "SELECT * FROM users WHERE email = ?";
            stmt = conn.prepareStatement(checkUserSql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return "An account already exists with that email!";
            } else {
                // Insert new user
                String insertUserSql = "INSERT INTO Users VALUES ((SELECT MAX(coalesce(UserID, -1)) FROM USERS) + 1, ?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertUserSql);
                stmt.setString(1, "Customer");
                stmt.setString(2, firstName);
                stmt.setString(3, lastName);
                stmt.setString(4, email);
                stmt.setString(5, password);
                stmt.setString(6, phoneNumber);
                int affectedRows = stmt.executeUpdate();

                // this creates a Customer for this user -- if we change the role later on we should delete the customer 
                // database row for the user so they do not have dual accounts
                if (affectedRows > 0) {
                    String insertCustomerSql = "INSERT INTO Customer VALUES ((SELECT UserID FROM USERS WHERE USERS.EMAIL = ?), NULL, NULL, NULL, NULL, NULL);";
                    stmt = conn.prepareStatement(insertCustomerSql);
                    stmt.setString(1, email);
                    int affectedRowsCustomer = stmt.executeUpdate();

                    if (affectedRowsCustomer > 0) {
                        return "Account successfully created!";
                    }
                }
                return "Failed to create account.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error connecting to the database.";
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
    }

      /* public String createAnnouncement(int propertyID, String newAnnouncement) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            // Check if user already exists
            String checkUserSql = "SELECT * FROM users WHERE email = ?";
            stmt = conn.prepareStatement(checkUserSql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return "An account already exists with that email!";
            } else {
                // Insert new user
                String insertUserSql = "INSERT INTO Users VALUES ((SELECT MAX(coalesce(UserID, -1)) FROM USERS) + 1, ?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertUserSql);
                stmt.setString(1, "Customer");
                stmt.setString(2, firstName);
                stmt.setString(3, lastName);
                stmt.setString(4, email);
                stmt.setString(5, password);
                stmt.setString(6, phoneNumber);
                int affectedRows = stmt.executeUpdate();

                // this creates a Customer for this user -- if we change the role later on we should delete the customer
                // database row for the user so they do not have dual accounts
                if (affectedRows > 0) {
                    String insertCustomerSql = "INSERT INTO Customer VALUES ((SELECT UserID FROM USERS WHERE USERS.EMAIL = ?), NULL, NULL, NULL, NULL, NULL);";
                    stmt = conn.prepareStatement(insertCustomerSql);
                    stmt.setString(1, email);
                    int affectedRowsCustomer = stmt.executeUpdate();

                    if (affectedRowsCustomer > 0) {
                        return "Success!";
                    }
                }
                return "Failed.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error connecting to the database.";
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
    }
    */

    public List<Property> propertyList()
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            String sql = "SELECT * FROM PROPERTY";
            stmt = conn.prepareStatement(sql);


            rs = stmt.executeQuery();

            ArrayList returnValues = new ArrayList();

            while (rs.next()) {

                String[] amenitiesString = (String[]) rs.getArray("amenities").getArray();
                String[] communityAnnouncementsString = (String[]) rs.getArray("communityannouncements").getArray();
                System.out.println(rs.getString("communityAnnouncements"));

                returnValues.add(new Property( amenitiesString , rs.getInt("propertyID"), rs.getString("address"), rs.getString("name"), communityAnnouncementsString));
            }

            return returnValues;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    public List<Unit> unitList()
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            String sql = "SELECT * FROM UNIT";
            stmt = conn.prepareStatement(sql);


            rs = stmt.executeQuery();

            ArrayList returnValues = new ArrayList();

            while (rs.next()) {
   // public Unit(int unitID, boolean isFurnished, double rentAmount, String floorplan, String condition, boolean isRented, ArrayList<String> appliances, boolean rentPaid, Date rentDue, int propertyID, int userID) {

                String[] appliancesString = (String[]) rs.getArray("appliances").getArray();


                //  returnValues.add(new Property(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                System.out.println(rs.getBoolean("isFurnished"));
//                returnValues.add(new Unit(rs.getInt("unitid"), rs.getBoolean("isFurnished"), rs.getDouble("rentAmount"), rs.getString("floorplan"), rs.getString("condition"), rs.getBoolean("isRented"), rs.getString("appliances"),rs.getBoolean("rentPaid"),rs.getDate("rentDue"), rs.getInt("propertyID"), rs.getInt("unitID")));

                returnValues.add(new Unit(rs.getInt("unitid"), rs.getBoolean("isFurnished"), rs.getDouble("rentAmount"), rs.getString("floorplan"), rs.getString("condition"), rs.getBoolean("isRented"), appliancesString, rs.getInt("propertyid"), rs.getBoolean("rentPaid"), rs.getDate("rentDue"), rs.getInt("userID") ));


            }

            return returnValues;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    public List<MaintenanceRequest> requestList()
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            String sql = "SELECT * FROM MAINTENANCEREQUEST";
            stmt = conn.prepareStatement(sql);


            rs = stmt.executeQuery();

            ArrayList returnValues = new ArrayList();

            while (rs.next()) {
                //     public MaintenanceRequest(boolean isDealtWith, int requestID, Date timeStamp, int propertyID, int unitID, int userID) {


                //  returnValues.add(new Property(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                System.out.println("fetched maintenance rqeuest");
                returnValues.add(new MaintenanceRequest(rs.getBoolean("isdealtwith"), rs.getInt("requestID"), rs.getDate("timestamp"), rs.getInt("propertyid"), rs.getInt("unitID"), rs.getInt("userID") ));

            }

            return returnValues;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return null;
    }


    public List<Users> userList()
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            String sql = "SELECT * FROM USERS";
            stmt = conn.prepareStatement(sql);


            rs = stmt.executeQuery();

            ArrayList returnValues = new ArrayList();

            while (rs.next()) {
                System.out.println("fetched user");
                returnValues.add(new Users(rs.getInt("userid"), rs.getString("role"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"), rs.getString("phonenumber")));

            }

            return returnValues;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    public List<PropertyManager> propertyManagerList()
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            String sql = "SELECT * FROM PROPERTYMANAGER";
            stmt = conn.prepareStatement(sql);


            rs = stmt.executeQuery();

            ArrayList returnValues = new ArrayList();

            while (rs.next()) {
                System.out.println("fetched user");
                returnValues.add(new PropertyManager(rs.getInt("userid"), rs.getBoolean("isOwner"), rs.getInt("propertyid")));

            }

            return returnValues;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    public List<Customers> customerList()
    {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            String sql = "SELECT * FROM CUSTOMER";
            stmt = conn.prepareStatement(sql);


            rs = stmt.executeQuery();

            ArrayList returnValues = new ArrayList();

            while (rs.next()) {
                returnValues.add(new Customers(rs.getInt("userid"), rs.getString("paymentType"), rs.getDate("leaseStart"), rs.getDate("leaseEnd"), rs.getBoolean("isApproved"), rs.getInt("unitID"), rs.getInt("propertyID")));

            }

            System.out.println("Added all customers succesfully");
            return returnValues;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    public String getName(String email) {
        String fullName = "";
    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
    
            String sql = "SELECT firstname, lastname FROM USERS WHERE email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
    
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                fullName = firstName + " " + lastName;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
    
        return fullName;
    }
    
    public String getPropertyName(int property_id)
    {
        String property_name = "";
    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
    
            String sql = "SELECT name FROM property WHERE propertyid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, property_id);
    
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                property_name = rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
    
        return property_name;
    }

    public int getPropertyId(int usr_id)
    {
        int property_id = 0;
    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
    
            String sql = "SELECT propertyid FROM propertymanager WHERE userid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usr_id);
    
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                property_id = rs.getInt("propertyid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
    
        return property_id;
    }

    public String getPropertyAddress(int property_id)
    {
        String address = "";
    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
    
            String sql = "SELECT address FROM property WHERE propertyid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, property_id);
    
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                address = rs.getString("address");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
    
        return address;
    }

    public List<String> getAmmenities(int property_id)
    {
        List<String> amenities = new ArrayList<>();
    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
    
            String sql = "SELECT amenities FROM property WHERE propertyid = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, property_id);
    
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                String amenities_string = rs.getString("amenities"); 

                amenities_string = amenities_string.substring(1, amenities_string.length() - 1);
                String[] amenitiesArray = amenities_string.split(",");
    
                for (String amenity : amenitiesArray) {
                    String[] parts = amenity.trim().split("\\s+"); 
                    for (String part : parts) {
                        amenities.add(part.trim());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
    
        return amenities;
    }

    public List<Tenants> tenantsList() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            String sql = "SELECT * FROM USERS, UNIT WHERE USERS.USERID = UNIT.USERID AND UNIT.isrented = true;";
            stmt = conn.prepareStatement(sql);


            rs = stmt.executeQuery();

            ArrayList returnValues = new ArrayList();

            while (rs.next()) {
                System.out.println("fetched user");
                returnValues.add(new Tenants(rs.getInt("propertyid"), rs.getInt("unitID"), rs.getInt("userID"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("phoneNumber")));

            }

            return returnValues;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    public List<LeaseRequest> leaseRequestList() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            String sql = "SELECT * FROM UNIT, USERS, CUSTOMER WHERE users.userID = customer.userID AND customer.unitid = UNIT.unitid AND CUSTOMER.isapproved = false;";
            stmt = conn.prepareStatement(sql);


            rs = stmt.executeQuery();

            ArrayList returnValues = new ArrayList();

            while (rs.next()) {
                System.out.println("fetched lease requests");

                int userID = rs.getInt("userID");
                int propertyID = rs.getInt("propertyID");
                int unitID = rs.getInt("unitID");
                String paymentType = rs.getString("paymenttype");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String email = rs.getString("email");
                String phonenumber =  rs.getString("phonenumber");



                String sql2 = "SELECT USERID FROM USERS WHERE USERS.email = ?"
                        ;

                stmt = conn.prepareStatement(sql2);
                stmt.setString(1, email);

                ResultSet rs1 = stmt.executeQuery();

                while (rs1.next())
                {
                    userID = rs1.getInt("userid");
                }



                returnValues.add(new LeaseRequest(userID, propertyID, unitID, paymentType, firstName, lastName, email, phonenumber));

            }

            return returnValues;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return null;
    }


    public String createLeaseRequest(String paymentType, int unitID, int propertyID, int userID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            // Check if user already exists
            String createRequest = "UPDATE Customer SET paymentType = ?, leaseStart = (SELECT CURRENT_DATE), leaseEnd = (SELECT CURRENT_DATE + INTERVAL '1 year'), isApproved = false, unitID = ?, propertyID = ? WHERE userid = ?";
            stmt = conn.prepareStatement(createRequest);
            stmt.setString(1, paymentType);
            stmt.setInt(2, unitID);
            stmt.setInt(3, propertyID);
            stmt.setInt(4, userID);

            System.out.println(stmt.toString());
           // rs = stmt.executeQuery();

            int count = stmt.executeUpdate();

            if (count > 0)
            {
                return "Success";
            }


            /*int affectedRows = stmt.executeUpdate();

                // this creates a Customer for this user -- if we change the role later on we should delete the customer
                // database row for the user so they do not have dual accounts
            if (affectedRows > 0) {
                return "Success";
            }
            else {
                return "Fail";
            }
            *?

             */
        } catch (Exception e) {
            e.printStackTrace();
            return "Error connecting to the database.";
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    public String cancelLeaseRequest(int userID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            // Check if user already exists
            String createRequest = "UPDATE Customer SET paymentType = null, leaseStart = null, leaseEnd = null, isApproved = false, unitID = null, propertyID = null WHERE userid = ?";
            stmt = conn.prepareStatement(createRequest);
            stmt.setInt(1, userID);

            System.out.println(stmt.toString());
            stmt.executeUpdate();

            /*if (count > 0)
            {
                return "Success";
            }*/

            String deleteUnit = "UPDATE UNIT SET isrented = false, userid = null WHERE userID = ?";
            stmt = conn.prepareStatement(deleteUnit);
            stmt.setInt(1, userID);

            stmt.executeUpdate();

            /*

            int affectedRows = stmt.executeUpdate();

            // this creates a Customer for this user -- if we change the role later on we should delete the customer
            // database row for the user so they do not have dual accounts
            if (affectedRows > 0) {
                System.out.println("Succesfully cancelled lease!");
                return "Success";
            }
            else {
                return "Fail";
            }
            */

        } catch (Exception e) {
            e.printStackTrace();
            return "Error connecting to the database.";
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        return null;
    }


    public String acceptLeaseRequest(int propertyID, int unitID, int userID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            // Check if user already exists
            String createRequest = "UPDATE Customer SET isApproved = true WHERE userid = ?";
            stmt = conn.prepareStatement(createRequest);
            stmt.setInt(1, userID);
            System.out.println(stmt.toString());
            // rs = stmt.executeQuery();

                stmt.executeUpdate();

                System.out.println("Updating UNIT to isRented");

                String updateUnit = "UPDATE UNIT SET userid = ?, isrented = true WHERE propertyid = ? AND unitid = ?";
                stmt = conn.prepareStatement(updateUnit);
                stmt.setInt(1, userID);
                stmt.setInt(2, propertyID);
                stmt.setInt(3, unitID);

            System.out.println(stmt.toString());

                stmt.executeUpdate();



                String deleteRequests = "UPDATE Customer SET paymentType = null, leaseStart = null, leaseEnd = null, unitID = null, propertyID = null WHERE unitid = ? AND propertyid = ? AND isApproved = false";
                stmt = conn.prepareStatement(deleteRequests);
                stmt.setInt(1, unitID);
                stmt.setInt(2, propertyID);

            System.out.println(stmt.toString());

                stmt.executeUpdate();
                return "Success";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error connecting to the database.";
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception se) {
                se.printStackTrace();
            }
        }
        //return null;
    }




}
