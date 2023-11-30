package com.controllers.unit;

import com.controllers.homepages.customerController;
import com.controllers.property.propertyController;
import com.controllers.sessions.UserSession;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.models.MaintenanceRequest;
import com.models.Unit;
import com.models.Users;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
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
    private TabPane tabPane;

    @FXML
    private Tab homeTab;

    @FXML
    private Tab leaseTab;


    @FXML
    private Button propertyButton;
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

    @FXML
    private Label rentAmountLabel;

    //Lease Tab:

    @FXML
    private Button propertyButton1;

    @FXML
    private Button homeButton1;
    @FXML
    private Button leaseRequest1;

    @FXML
    private Text unitIDText1;

    @FXML
    private Text propertyIDText1;

    @FXML
    private Text floorplanText1;

    @FXML
    private Text conditionText1;

    @FXML
    private Text isFurnishedText1;

    @FXML
    private Text isRentedText1;

    @FXML
    private Text rentAmountLabel1;

    @FXML
    private Label leaseStartLabel;

    @FXML
    private Label leaseEndLabel;


    String selectedPayment;

    @FXML
    private ComboBox paymentBox = new ComboBox();






    public void initializeValues(Stage primaryStage, int userID, int propertyID, int unitID)
    {

        selectedPayment = "None";

        paymentBox.getItems().addAll("Cash", "Credit", "Debit");


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


        //Rent Tab:

        rentAmountLabel.setText(String.valueOf(currentUnit.getRentAmount()));

        if (isRented == true)
        {
            tabPane.getTabs().remove(leaseTab);
        }


        paymentBox.setOnAction((event) -> {
            int selectedIndex = paymentBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = paymentBox.getSelectionModel().getSelectedItem();

            selectedPayment = (String) selectedItem;

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ComboBox.getValue(): " + paymentBox.getValue());

            System.out.print(selectedPayment);
        });

        database_controller dbController = new database_controller();
        String leaseStart = dbController.getCurrentDate();
        String leaseEnd = dbController.getCurrentDate1Year();


        leaseStartLabel.setText(leaseStart);
        leaseEndLabel.setText(leaseEnd);

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

    public void sendRequest(ActionEvent event) throws IOException {

        if (checkRequest())
        {
            URL url = getClass().getResource("/com/pages/homepages/customerPage.fxml");
            System.out.println(url.toString());
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            customerController cController = loader.getController();
            cController.initialize(primaryStage, userID);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

    }



    public boolean checkRequest()
    {
        if (selectedPayment == "None")
        {
            return false;
        }
        else {

            database_controller dbController = new database_controller();
            String registrationResult = dbController.createLeaseRequest(selectedPayment, currentUnit.getUnitID(), currentUnit.getPropertyID(), UserSession.getInstance().getUserID());
            System.out.println(registrationResult);
            return "Success".equals(registrationResult);

        }

    }

    //Lease stuff

}
