package com.components;

import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.main.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PropertyViewController implements Initializable {

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
