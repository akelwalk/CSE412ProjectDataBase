package com.controllers.homepages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import com.controllers.sessions.UserSession;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.main.MainApplication;

public class managerController {

    @FXML
    private Button logout;
    private Button properties; 
    private Button tenants; 
    private Button leases; 
    @FXML
    private Label user_name; 
    
    public void initialize() {
        try {
            String userID = UserSession.getInstance().getEmail();
            database_controller dbController = database_controller.getInstance(); 
            user_name.setText(dbController.getName(userID));
        } catch (IllegalStateException e) {
            UserSession.cleanUserSession();
            //username.setText("ERROR");
        }
    }
    
    public void userLogOut(ActionEvent event) throws IOException {
        UserSession.cleanUserSession(); // clear user session to ensure only one object exists at a time
        MainApplication m = new MainApplication();
        m.changeScene("/com/pages/loginregister/loginPage.fxml");

    }

    public void getProperties(ActionEvent event)throws IOException 
    {
                MainApplication m = new MainApplication();
        m.changeScene("/com/pages/loginregister/loginPage.fxml");
    }

    public void getTenants(ActionEvent event)throws IOException 
    {

    }

    public void getLeases(ActionEvent event)throws IOException 
    {

    }
}