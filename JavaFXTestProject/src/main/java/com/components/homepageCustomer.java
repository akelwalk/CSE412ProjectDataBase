package com.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import com.main.MainApplication;

public class homepageCustomer {

    @FXML
    private Button logout;


    public void userLogOut(ActionEvent event) throws IOException {
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/hello-view.fxml");

    }

    public void goToPropertyPage(ActionEvent event) throws IOException
    {

    }
}