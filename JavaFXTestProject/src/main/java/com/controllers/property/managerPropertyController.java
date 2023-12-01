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
    private Button editButton;
    private Button deleteButton; 
    private Button backButton; 

    @FXML
    private Label name; 
    @FXML
    private Label address;
    @FXML 
    private Label amenities;
    @FXML 
    private Label user_name; 


    public void initialize(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        try {
            String usr = UserSession.getInstance().getEmail();
            database_controller dbController = database_controller.getInstance(); 
            int property_id = dbController.getPropertyId(UserSession.getInstance().getUserID());
            name.setText("Property Name: " + dbController.getPropertyName(property_id));
            address.setText("Address: "+ dbController.getPropertyAddress(property_id));
            List<String> amenities_list = dbController.getAmmenities(property_id);
            amenities.setText("Amenities: "+String.join(",", amenities_list));
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
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
    }

    public void editProperty(ActionEvent event) throws IOException {

    }

    public void deleteProperty(ActionEvent event) throws IOException {

    }
}
