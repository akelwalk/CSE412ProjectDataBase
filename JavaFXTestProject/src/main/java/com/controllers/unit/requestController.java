package com.controllers.unit;


import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.models.MaintenanceRequest;
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
import java.util.Date;
import java.util.List;


public class requestController {

    IDatabaseOperations databaseHandler = database_controller.getInstance();

    private Stage primaryStage;

    private int userID;

    private int currPropertyID;

    private int currUnitID;

    private int selectedRequestID;
    @FXML
    private Button homeButton;

    @FXML
    private Button viewPropertyPage;

    @FXML
   // private ListView<Propery> propertyListView = new ListView(FXCollections.observableList(Arrays.asList("one", "2", "3")));
   // private ListView<String> propertyListView = new ListView<>();

    private TableView<MaintenanceRequest> maintenanceRequestTableView;

    @FXML
    private TableColumn<MaintenanceRequest, Boolean> isDealtWithCol;

    @FXML
    private TableColumn<MaintenanceRequest, Integer> requestIDCol;

    @FXML
    private TableColumn<MaintenanceRequest, Date> timestampCol;



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

    public void initializeValues(Stage primaryStage, int userID, int propertyID, int unitID)
    {
        this.selectedRequestID = -1;
        this.userID = userID;
        isDealtWithCol.setCellValueFactory(new PropertyValueFactory<MaintenanceRequest, Boolean>("isDealtWith"));
        requestIDCol.setCellValueFactory(new PropertyValueFactory<MaintenanceRequest, Integer>("requestID"));
        timestampCol.setCellValueFactory(new PropertyValueFactory<MaintenanceRequest, Date>("timestamp"));
        this.primaryStage = primaryStage;
        this.currPropertyID = propertyID;
        this.currUnitID = unitID;
        setupTable();

    }


    private void setupTable(){

        List<MaintenanceRequest> getMaintenanceRequestList = databaseHandler.requestList();
        System.out.println(currPropertyID);

        System.out.println(getMaintenanceRequestList.size());

        for (int i = 0; i < getMaintenanceRequestList.size(); i++) {
            if (getMaintenanceRequestList.get(i).getPropertyID() == currPropertyID && getMaintenanceRequestList.get(i).getUnitID() == currUnitID) {
                maintenanceRequestTableView.getItems().addAll(getMaintenanceRequestList.get(i));
            }
        }
    }

    @FXML
    void rowClicked(MouseEvent event) {
        MaintenanceRequest clickedRequest = maintenanceRequestTableView.getSelectionModel().getSelectedItem();
        selectedRequestID = clickedRequest.getUnitID();
        System.out.println("selected UnitID: " + clickedRequest);
    }



    public void goHome(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/pages/unit/unitPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        unitController unitViewController = loader.getController();
        unitViewController.initializeValues(primaryStage, userID, currPropertyID, currUnitID);
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();

    }


    public void goToPropertyPage(ActionEvent event) throws IOException {
      /*  System.out.println("Property View -> Customer Home Page");
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/propertyPage.fxml");*/


        URL url = getClass().getResource("/com/pages/unit/unitPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        unitController unitViewController = loader.getController();
        unitViewController.initializeValues(primaryStage, userID,-1, -1);
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();


    }

}
