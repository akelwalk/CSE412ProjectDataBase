package com.controllers.homepages;

import com.controllers.loginregister.loginController;
import com.controllers.property.managerPropertyController;
import com.controllers.property.propertyController;
import com.controllers.property.propertyListController;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.models.Property;
import com.models.PropertyManager;
import com.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.controllers.sessions.UserSession;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.main.MainApplication;
import javafx.stage.Stage;

public class managerController {

    private int userID;
    private Stage primaryStage;
    private Users currentUser;
    private PropertyManager currentManager;

    IDatabaseOperations databaseHandler = database_controller.getInstance();

    @FXML
    private Button logout;
    private Button properties; 
    private Button tenants; 
    private Button leases; 
    private Button viewPropertyButton;
    private Button announcement_button;

    @FXML
    private Label user_name; 


    public void initialize(Stage primaryStage, int userID)
    {
        this.primaryStage = primaryStage;
        this.userID = userID;

        try {
            String usr = UserSession.getInstance().getEmail();
            database_controller dbController = database_controller.getInstance(); 
            user_name.setText(dbController.getName(usr));
        } catch (IllegalStateException e) {
            UserSession.cleanUserSession();
            //username.setText("ERROR");
        }

        List<PropertyManager> getManagerList = databaseHandler.propertyManagerList();
        List<Users> getUserList = databaseHandler.userList();


        currentUser = getUserList.get(0);

        for (int i = 0; i < getUserList.size(); i++) {
            if (getUserList.get(i).getUserID() == userID)
            {
                currentUser = getUserList.get(i);
                break;
            }

        }

        currentManager = getManagerList.get(0);

        for (int i = 0; i < getManagerList.size(); i++) {
            if (getManagerList.get(i).getUserID() == userID)
            {
                currentManager = getManagerList.get(i);
                break;
            }

        }
    }


    public void userLogOut(ActionEvent event) throws IOException {
        UserSession.cleanUserSession();
        URL url = getClass().getResource("/com/pages/loginregister/loginPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        loginController login = loader.getController();
        login.setStage(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void getTenants(ActionEvent event)throws IOException 
    {

    }

    public void getLeases(ActionEvent event)throws IOException 
    {

    }

    public void goToMyProperty(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/pages/property/propertyPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        propertyController propertyViewController = loader.getController();
        propertyViewController.initialize(primaryStage, userID, currentManager.getPropertyID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public void getProperties(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/pages/property/managerProperties.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        managerPropertyController mpc = loader.getController(); 
        mpc.initialize(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void announcement(ActionEvent event) throws IOException {
        
    }
}