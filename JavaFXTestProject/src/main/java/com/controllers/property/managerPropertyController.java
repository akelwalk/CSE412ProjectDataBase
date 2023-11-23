package com.controllers.property;

import javafx.fxml.FXML;

import com.controllers.homepages.managerController;
import com.controllers.sessions.UserSession;
import com.controllers.unit.unitListController;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.models.Property;
import com.models.PropertyManager;
import com.models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class managerPropertyController {
    
    private Stage primaryStage;
    @FXML
    private Button addButton;
    private Button deleteButton;
    private Button backButton; 

    @FXML
    private Label property_id; 
    private Label name; 
    private Label address; 
    private Label amenities;
    @FXML 
    private Label user_name; 

    @FXML
    private ListView properties_list;

    public void initialize(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        try {
            String usr = UserSession.getInstance().getEmail();
            database_controller dbController = database_controller.getInstance(); 
            user_name.setText(dbController.getName(usr));
        } catch (IllegalStateException e) {
            UserSession.cleanUserSession();
            //username.setText("ERROR");
        }
    }

    public void goBack(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/pages/homepages/managerPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        managerController mc = loader.getController(); 
        mc.initialize(primaryStage, UserSession.getInstance().getUserID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void addProperty(ActionEvent event) throws IOException {

    }

    public void deleteProperty(ActionEvent event) throws IOException {

    }
}
