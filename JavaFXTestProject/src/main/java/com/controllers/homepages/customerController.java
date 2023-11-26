package com.controllers.homepages;

import com.controllers.loginregister.loginController;
import com.controllers.property.propertyController;
import com.controllers.property.propertyListController;
import com.controllers.sessions.UserSession;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.models.Customers;
import com.models.Property;
import com.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class customerController {

    private int userID;
    private Stage primaryStage;
    private Users currentUser;
    private Customers currentCustomer;
    private String leaseStatus;


    @FXML
    private TabPane tabPane;

    @FXML
    private Tab homeTab;

    @FXML
    private Tab viewPropertiesTab;

    @FXML
    private Tab myUnitTab;

    @FXML
    private Tab myPropertyTab;

    @FXML
    private Tab maintenanceTab;

    @FXML
    private Tab rentTab;

    @FXML
    private Tab leaseTab;



    @FXML
    private Label user_name; 

    IDatabaseOperations databaseHandler = database_controller.getInstance();

    @FXML
    private Button logout;

    //View Property Tab Stuff

    @FXML
    private Button homeButton;

    @FXML
    private Button viewPropertyPage;

    @FXML
    // private ListView<Propery> propertyListView = new ListView(FXCollections.observableList(Arrays.asList("one", "2", "3")));
    // private ListView<String> propertyListView = new ListView<>();

    private TableView<Property> propertyTableView;

    @FXML
    private TableColumn<Property, Integer> propertyIDCol;

    @FXML
    private TableColumn<Property, String> nameCol;

    @FXML
    private TableColumn<Property, String> amenitiesCol;

    @FXML
    private TableColumn<Property, String> addressCol;

    @FXML
    private TableColumn<Property, Integer> freeUnitsCol;

    private int selectedPropertyId;



    public void initialize(Stage primaryStage, int userID)
    {
        try {
            String usr = UserSession.getInstance().getEmail();
            database_controller dbController = database_controller.getInstance(); 
            user_name.setText(dbController.getName(usr));
        } catch (IllegalStateException e) {
            UserSession.cleanUserSession();
            //username.setText("ERROR");
        }

        checkStatus();


        this.primaryStage = primaryStage;
        this.userID = UserSession.getInstance().getUserID();

        //View Property Tab Stuff
        this.selectedPropertyId = -1;
        propertyIDCol.setCellValueFactory(new PropertyValueFactory<Property, Integer>("propertyID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Property, String>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
        amenitiesCol.setCellValueFactory(new PropertyValueFactory<Property, String>("amenities"));
        freeUnitsCol.setCellValueFactory(new PropertyValueFactory<Property, Integer>("free units"));
        setupTable();

        //Remove these tabs if the user does not have unit.

        if (leaseStatus == "none") {

            tabPane.getTabs().remove(myUnitTab);
            tabPane.getTabs().remove(myPropertyTab);
            tabPane.getTabs().remove(maintenanceTab);
            tabPane.getTabs().remove(rentTab);
            tabPane.getTabs().remove(leaseTab);
        }
        else if (leaseStatus == "pending")
        {
            tabPane.getTabs().remove(myUnitTab);
            tabPane.getTabs().remove(myPropertyTab);
            tabPane.getTabs().remove(leaseTab);
        }
    }

    public void checkStatus()
    {
        //Fetch the customer object

        List<Customers> getCustomerList = databaseHandler.customerList();

        currentCustomer = getCustomerList.get(0);


        for (int i = 0; i < getCustomerList.size(); i++)
        {
            if (getCustomerList.get(i).getUserID() == userID)
            {
                currentCustomer = getCustomerList.get(i);
                break;
            }
        }

        leaseStatus ="Error";


        //Check if the customer does not have any lease requests

        if (currentCustomer.getUnitID() == 0 && currentCustomer.isApproved() == false)
        {
            leaseStatus = "none";
        }
        else if (currentCustomer.getUnitID() != 0 && currentCustomer.isApproved() == false)
        {
            leaseStatus = "pending";
        }
        else {
            leaseStatus = "approved";
        }

    }

    public void userLogOut(ActionEvent event) throws IOException {
        /*MainApplication m = new MainApplication();
        m.changeScene("/com/resources/loginPage.fxml");*/
        UserSession.cleanUserSession(); // clean users session at logout 
        URL url = getClass().getResource("/com/pages/loginregister/loginPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        loginController login = loader.getController();
        login.setStage(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    //Property Tab Stuff

    private void setupTable(){

        List<Property> getPropertyList = databaseHandler.propertyList();

        for (int i = 0; i < getPropertyList.size(); i++) {
            propertyTableView.getItems().addAll(getPropertyList.get(i));
        }
    }

    @FXML
    void rowClicked(MouseEvent event) {
        Property clickedProperty = propertyTableView.getSelectionModel().getSelectedItem();
        selectedPropertyId = clickedProperty.getPropertyID();
        System.out.println("selected propertyID: " + clickedProperty);
    }

    public void goToPropertyPage(ActionEvent event) throws IOException {
      /*  System.out.println("Property View -> Customer Home Page");
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/propertyPage.fxml");*/



        URL url = getClass().getResource("/com/pages/property/propertyPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        propertyController propertyViewController = loader.getController();
        propertyViewController.initialize(primaryStage, userID, propertyTableView.getSelectionModel().getSelectedItem().getPropertyID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }


}