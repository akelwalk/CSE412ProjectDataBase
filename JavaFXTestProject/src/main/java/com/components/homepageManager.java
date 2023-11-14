package com.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import com.main.MainApplication;

public class homepageManager {

    @FXML
    private Button logout;
    private Button properties; 
    private Button tenants; 
    private Button leases; 


    public void userLogOut(ActionEvent event) throws IOException {
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/hello-view.fxml");

    }

    public void getProperties(ActionEvent event)throws IOException 
    {
                MainApplication m = new MainApplication();
        m.changeScene("/com/resources/hello-view.fxml");
    }

    public void getTenants(ActionEvent event)throws IOException 
    {

    }

    public void getLeases(ActionEvent event)throws IOException 
    {

    }
}