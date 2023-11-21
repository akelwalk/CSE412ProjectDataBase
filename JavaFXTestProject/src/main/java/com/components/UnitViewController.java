package com.components;

import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.main.MainApplication;
import com.models.Unit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class UnitViewController implements Initializable {

    //Display Data

    private Unit currentUnit;

    private int userID;
    private int propertyID;
    private int unitID;
    private boolean isFurnished;
    private double rentAmount;
    private String floorplan;
    private String condition;
    private boolean isRented;
    private ArrayList<String> appliances;
    private boolean rentPaid;

    private Stage primaryStage;



    IDatabaseOperations databaseHandler = database_controller.getInstance();

    @FXML
    private Button homeButton;

    @FXML
    private Button viewUnitsButton;

    @FXML
    private Text unitIDText;

    @FXML
    private Text propertyIDText;

    @FXML
    private Text floorplanText;

    @FXML
    private Text conditionText;

    @FXML
    private Text isFurnishedText;

    @FXML
    private Text isRentedText;

    public void initializeValues(Stage primaryStage, int propertyID, int unitID)
    {
        currentUnit = new Unit(-1, false, 0.0, "", "", false, "", false, new Date(), -1, -1);
        this.primaryStage = primaryStage;
        this.unitID = unitID;

        List<Unit> getUnitList = databaseHandler.unitList();

        for (int i = 0; i < getUnitList.size(); i++) {
            if (getUnitList.get(i).getPropertyID() == propertyID && getUnitList.get(i).getUnitID() == unitID)
            {
                currentUnit = getUnitList.get(i);
                break;
            }
        }

        this.unitID = currentUnit.getUnitID();
        this.isFurnished = currentUnit.isFurnished();
        this.rentAmount = currentUnit.getRentAmount();
        this.floorplan = currentUnit.getFloorplan();
        this.condition = currentUnit.getCondition();
        this.isRented = currentUnit.isRented();
        this.appliances = currentUnit.getAppliances();
        this.rentPaid = currentUnit.isRentPaid();

        unitIDText.setText(String.valueOf(unitID));
        isFurnishedText.setText(String.valueOf(unitID));
        unitIDText.setText(String.valueOf(unitID));
        unitIDText.setText(String.valueOf(unitID));
        unitIDText.setText(String.valueOf(unitID));
        unitIDText.setText(String.valueOf(unitID));


        unitIDText.setText(String.valueOf(unitID));

        this.propertyID = propertyID;

        propertyIDText.setText(String.valueOf(propertyID));

        floorplanText.setText(floorplan);

        conditionText.setText(condition);

        isFurnishedText.setText(String.valueOf(isFurnished));

        isRentedText.setText(String.valueOf(isRented));


    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void goHome(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/resources/ViewUnits.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        UnitListViewController unitListViewController = loader.getController();
        System.out.println("Passing propertyID: "+ currentUnit.getPropertyID());
        unitListViewController.initializeValues(primaryStage, currentUnit.getPropertyID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public void goToUnitList(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/resources/viewmaintenancerequests.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        MaintenanceRequestListViewController maintenanceRequestListViewController = loader.getController();
        maintenanceRequestListViewController.initializeValues(primaryStage, currentUnit.getPropertyID(), currentUnit.getUnitID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

}
