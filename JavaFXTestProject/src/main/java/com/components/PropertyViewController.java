package com.components;

import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.main.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private Text propertyIDText;

    public void initializeValues(Stage primaryStage, int propertyID)
    {
        this.primaryStage = primaryStage;
        this.propertyID = propertyID;
        propertyIDText.setText(String.valueOf(this.propertyID));
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void goHome(ActionEvent event) throws IOException {
        System.out.println("Property View -> Customer Home Page");
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/homepageCustomer.fxml");


    }

}
