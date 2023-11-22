package com.db;

// Imports 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.*;

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
        return null;
    }

    public List<PropertyManager> propertyManagerList()
    {
        return null;
    }

    public List<Customers> customerList()
    {
        return null;
    }




    // Future SQL Queries use a string format for the sql string being used to the function call 
    // - To further abstract this process we can seperate the implementation by concerns to ensure 
    //   that input captures are done in the source class and then passed to the database class return 
    //   values should be though out as to seperates the two as much as possible 


}
