package com.controllers.unit;

import com.controllers.homepages.customerController;
import com.controllers.property.propertyController;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.models.MaintenanceRequest;
import com.models.Unit;
import com.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class unitCustomerController implements Initializable {

    //Display Data


    private Unit currentUnit;
    private int selectedRequestID;


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
    private Button leaseRequest;

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

    //Rent Tab:

    @FXML
    private Text rentAmountLabel;


    public void initializeValues(Stage primaryStage, int userID, int propertyID, int unitID)
    {

        List<Unit> getUnitList = databaseHandler.unitList();
        currentUnit = getUnitList.get(0);
        this.primaryStage = primaryStage;
        this.userID = userID;
        this.unitID = unitID;

        this.selectedRequestID = -1;




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
        this.floorplan = currentUnit.getFloorPlan();
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


        //Tenant Details Tab:

        List<Users> getUserList = databaseHandler.userList();

        Users tenantUser = getUserList.get(0);

        for (int i = 0; i < getUserList.size(); i++)
        {
            if (getUserList.get(i).getUserID() == currentUnit.getUserID())
            {
                tenantUser = getUserList.get(i);
            }
        }


        //Rent Tab:

        rentAmountLabel.setText(String.valueOf(currentUnit.getRentAmount()));

        if (isRented == true)
        {
            leaseRequest.setVisible(false);
        }




    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void goBack(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/pages/unit/unitListPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        unitListController unitListViewController = loader.getController();
        System.out.println("Passing propertyID: "+ currentUnit.getPropertyID());
        unitListViewController.initializeValues(primaryStage, userID, currentUnit.getPropertyID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public void goHome(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/pages/homepages/customerPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        customerController cController = loader.getController();
        cController.initialize(primaryStage, userID);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

  /* No longer needed

   public void goToRequestList(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/pages/unit/requestPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        requestController maintenanceRequestListViewController = loader.getController();
        maintenanceRequestListViewController.initializeValues(primaryStage, userID, currentUnit.getPropertyID(), currentUnit.getUnitID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }*/

}
