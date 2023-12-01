package com.controllers.unit;


import com.controllers.homepages.customerController;
import com.controllers.property.propertyController;
import com.controllers.sessions.UserSession;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.models.Customers;
import com.models.Unit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public class unitListController {

    IDatabaseOperations databaseHandler = database_controller.getInstance();

    private Stage primaryStage;

    private int userID;

    private int currPropertyID;

    private Customers currentCustomer;
    private String leaseStatus;

    private int selectedUnitID;
    @FXML
    private Button homeButton;

    @FXML
    private Button viewPropertyPage;

    @FXML
   // private ListView<Propery> propertyListView = new ListView(FXCollections.observableList(Arrays.asList("one", "2", "3")));
   // private ListView<String> propertyListView = new ListView<>();

    private TableView<Unit> unitTableView;

    @FXML
    private TableColumn<Unit, Integer> unitIDCol;

    @FXML
    private TableColumn<Unit, String> floorplanCol;

    @FXML
    private TableColumn<Unit, Boolean> isFurnishedCol;

    @FXML
    private TableColumn<Unit, String> conditionCol;

    @FXML
    private TableColumn<Unit, Boolean> isRentedCol;

    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle, Stage primaryStage, int propertyID) {
        this.selectedUnitID = -1;
        this.currPropertyID = -1;
        unitIDCol.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("unitID"));
        floorplanCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("floorplan"));
        //isFurnishedCol.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("isFurnished"));
        conditionCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("condition"));
       // isRented.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("isRented"));
        setupTable();
    }*/

    public void initializeValues(Stage primaryStage, int userID, int propertyID)
    {
        checkStatus();
        this.selectedUnitID = -1;
        this.userID = userID;
        unitIDCol.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("unitID"));
        floorplanCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("floorplan"));
        isFurnishedCol.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("isFurnished"));
        conditionCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("condition"));
        isRentedCol.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("isRented"));
        this.primaryStage = primaryStage;
        this.currPropertyID = propertyID;
        setupTable();

    }


    private void setupTable(){

        List<Unit> getUnitList = databaseHandler.unitList();
        System.out.println(getUnitList.size());
        System.out.println(currPropertyID);

        for (int i = 0; i < getUnitList.size(); i++) {
            if (getUnitList.get(i).getPropertyID() == currPropertyID && getUnitList.get(i).getUnitID() != currentCustomer.getUnitID()) {
                unitTableView.getItems().addAll(getUnitList.get(i));
            }
        }
    }

    @FXML
    void rowClicked(MouseEvent event) {
        Unit clickedUnit = unitTableView.getSelectionModel().getSelectedItem();
        selectedUnitID = clickedUnit.getUnitID();
        System.out.println("selected UnitID: " + clickedUnit);
    }



    public void goBack(ActionEvent event) throws IOException {

        System.out.println(leaseStatus);
        if (currentCustomer.getPropertyID() == currPropertyID) {
            URL url = getClass().getResource("/com/pages/homepages/customerPage.fxml");
            System.out.println(url.toString());
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            customerController cController = loader.getController();
            cController.initialize(primaryStage, userID);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        else {
            URL url = getClass().getResource("/com/pages/property/propertyPage.fxml");
            System.out.println(url.toString());
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            propertyController propertyViewController = loader.getController();
            propertyViewController.initialize(primaryStage, userID, currPropertyID);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

    }


    public void goToUnitPage(ActionEvent event) throws IOException {
      /*  System.out.println("Property View -> Customer Home Page");
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/propertyPage.fxml");*/

        URL url = getClass().getResource("/com/pages/unit/unitPageCustomer.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        unitCustomerController unitViewController = loader.getController();
        unitViewController.initializeValues(primaryStage, userID, unitTableView.getSelectionModel().getSelectedItem().getPropertyID(), unitTableView.getSelectionModel().getSelectedItem().getUnitID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    public void checkStatus()
    {
        //Fetch the customer object

        List<Customers> getCustomerList = databaseHandler.customerList();

        currentCustomer = getCustomerList.get(0);

        System.out.println("The current userID is: " + UserSession.getInstance().getUserID());


        for (int i = 0; i < getCustomerList.size(); i++)
        {
            if (getCustomerList.get(i).getUserID() == UserSession.getInstance().getUserID())
            {
                currentCustomer = getCustomerList.get(i);
                System.out.println("Current customer FOUND! ->" + getCustomerList.get(i).getUnitID());
                break;
            }
        }

        System.out.println("Could not find the given user on customers!");

        System.out.println("Current Unit ID: " + currentCustomer.getUnitID());

        leaseStatus ="Error";


        if (currentCustomer.getPropertyID() == currPropertyID)
        {
            leaseStatus = "approved";
        }

    }

}
