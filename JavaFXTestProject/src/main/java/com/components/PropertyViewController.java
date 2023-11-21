package com.components;

import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.main.MainApplication;
import com.models.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class PropertyViewController implements Initializable {

    //Display Data

    private Property currentProperty;

    private String amenities;
    private int propertyID;
    private String address;
    private String name;
    private List<String> communityAnnouncements;

    private Stage primaryStage;



    IDatabaseOperations databaseHandler = database_controller.getInstance();

    @FXML
    private Button homeButton;

    @FXML
    private Button viewUnitsButton;

    @FXML
    private Text propertyIDText;

    @FXML
    private Text nameText;

    @FXML
    private Text addressText;

    @FXML
    private Text amenitiesText;

    @FXML
    private Text communityAnnouncementsText;

    public void initialize(Stage primaryStage, int propertyID)
    {
        currentProperty = new Property("", -1, "", "", "");
        this.primaryStage = primaryStage;
        this.propertyID = propertyID;
        propertyIDText.setText(String.valueOf(this.propertyID));

        List<Property> getPropertyList = databaseHandler.propertyList();

        for (int i = 0; i < getPropertyList.size(); i++) {
            if (getPropertyList.get(i).getPropertyID() == propertyID)
            {
                currentProperty = getPropertyList.get(i);
                break;
            }
        }

        this.amenities = currentProperty.getAmenities();
        this.address = currentProperty.getAddress();
        this.name = currentProperty.getName();
        this.communityAnnouncements = currentProperty.getCommunityAnnouncements();

        amenitiesText.setText(amenities);
        addressText.setText(address);
        nameText.setText(name);
        communityAnnouncementsText.setText(communityAnnouncements.get(0));


    }


    public void goHome(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/resources/ViewProperties.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        PropertyListViewController propertyListViewController = loader.getController();
        propertyListViewController.setStage(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void goToUnitList(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/resources/ViewUnits.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        UnitListViewController unitListViewController = loader.getController();
        System.out.println("Passing propertyID: "+ currentProperty.getPropertyID());
        unitListViewController.initializeValues(primaryStage, currentProperty.getPropertyID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

}
