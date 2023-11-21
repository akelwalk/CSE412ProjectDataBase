package com.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import com.main.MainApplication;

public class managerController {

    @FXML
    private Button logout;
    private Button properties; 
    private Button tenants; 
    private Button leases; 


    public void userLogOut(ActionEvent event) throws IOException {
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/loginPage.fxml");

    }

    public void getProperties(ActionEvent event)throws IOException 
    {
                MainApplication m = new MainApplication();
        m.changeScene("/com/resources/loginPage.fxml");
    }

    public void getTenants(ActionEvent event)throws IOException 
    {

    }

    public void getLeases(ActionEvent event)throws IOException 
    {

    }
}