package com.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.db.database_controller;
import com.main.MainApplication;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import java.io.IOException;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import com.models.Property;
import com.db.database_controller;
import com.db.IDatabaseOperations;


public class PropertyListViewController implements Initializable {

    IDatabaseOperations databaseHandler = database_controller.getInstance();

    @FXML
    private Button homeButton;

    @FXML
    private ListView propertyListView;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Intialized property list view");
        propertyListView.getItems().addAll(databaseHandler.propertyList());

    }

    public void goHome(ActionEvent event) throws IOException {
        System.out.println("Property View -> Customer Home Page");
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/homepageCustomer.fxml");

    }

}
