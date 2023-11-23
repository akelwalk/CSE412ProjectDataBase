package com.controllers.property;

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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public class announcementsController {

    //Display Data

    private Property currentProperty;
    private Users currentUser;

    private List<String> amenities;
    private int propertyID;
    private String address;
    private String name;
    private ObservableList<String> communityAnnouncements = FXCollections.observableArrayList();


    private Stage primaryStage;

    private int userID;



    IDatabaseOperations databaseHandler = database_controller.getInstance();

    @FXML
    private TextField enterAnnouncementField;

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private Label errorLabel;





    public void initialize(Stage primaryStage, int userID, int propertyID)
    {
        this.primaryStage = primaryStage;
        this.userID = userID;
        this.propertyID = propertyID;


        List<Property> getPropertyList = databaseHandler.propertyList();



        currentProperty = getPropertyList.get(0);

        for (int i = 0; i < getPropertyList.size(); i++) {
            if (getPropertyList.get(i).getPropertyID() == propertyID)
            {
                currentProperty = getPropertyList.get(i);
                break;
            }

        }

        List<Users> getUsersList = databaseHandler.userList();
        currentUser = getUsersList.get(0);

        for (int i = 0; i < getUsersList.size(); i++) {
            if (getUsersList.get(i).getUserID() == userID)
            {
                currentUser = getUsersList.get(i);
                break;
            }

        }

            List<PropertyManager> getManagerList = databaseHandler.propertyManagerList();

            PropertyManager currentManager = getManagerList.get(0);

            for (int i = 0; i < getManagerList.size(); i++) {
                if (getManagerList.get(i).getUserID() == userID)
                {
                    currentManager = getManagerList.get(i);
                    break;
                }

            }




    }


    public void goBack(ActionEvent event) throws IOException {


        URL url = getClass().getResource("/com/pages/property/propertyPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        propertyController pc = loader.getController();
        pc.initialize(primaryStage, userID, propertyID);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void createAnnouncement(ActionEvent event) throws IOException {

        boolean check = checkCreated();

        if (check) {
            URL url = getClass().getResource("/com/pages/property/propertyPage.fxml");
            System.out.println(url.toString());
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            propertyController pc = loader.getController();
            pc.initialize(primaryStage, userID, propertyID);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }


    }

    private boolean checkCreated() throws IOException {

        // password contraints

        if (enterAnnouncementField.getLength() < 1) {
            errorLabel.setText("Password cannot be blank!");
            return false;
        } else {

            database_controller dbController = new database_controller();
           // String registrationResult = dbController.registerUser(email.getText(), firstName.getText(), lastName.getText(), password.getText(), phoneNumber.getText());
            String registrationResult = "Not implemented yet!";
            errorLabel.setText(registrationResult);
            return "Success!".equals(registrationResult);
        }

    }

}
