package com.controllers.unit;

import com.controllers.homepages.managerController;
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
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class unitController implements Initializable {

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

    @FXML
    private Label tenantFirstName;

    @FXML
    private Label tenantLastName;

    @FXML
    private Label tenantPhoneNumber;

    @FXML
    private Label tenantEmail;

    //Rent Tab:

    @FXML
    private Label rentAmountLabel;

    @FXML
    private Label rentDueLabel;

    @FXML
    private Label rentPaidLabel;


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

    public void initializeValues(Stage primaryStage, int userID, int propertyID, int unitID)
    {

        List<Unit> getUnitList = databaseHandler.unitList();
        currentUnit = getUnitList.get(0);
        this.primaryStage = primaryStage;
        this.userID = userID;
        this.unitID = unitID;

        this.selectedRequestID = -1;

        isDealtWithCol.setCellValueFactory(new PropertyValueFactory<MaintenanceRequest, Boolean>("isDealtWith"));
        requestIDCol.setCellValueFactory(new PropertyValueFactory<MaintenanceRequest, Integer>("requestID"));
        timestampCol.setCellValueFactory(new PropertyValueFactory<MaintenanceRequest, Date>("timestamp"));




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

        setupTable();

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


        tenantFirstName.setText(tenantUser.getFirstName());
        tenantLastName.setText(tenantUser.getLastName());
        tenantEmail.setText(tenantUser.getEmail());
        tenantPhoneNumber.setText(tenantUser.getPhoneNumber());

        //Rent Tab:

        rentAmountLabel.setText(String.valueOf(currentUnit.getRentAmount()));
        rentDueLabel.setText(String.valueOf(currentUnit.getRentDue()));
        rentPaidLabel.setText(String.valueOf(currentUnit.isRentPaid()));




    }


    private void setupTable(){

        List<MaintenanceRequest> getMaintenanceRequestList = databaseHandler.requestList();
        System.out.println(currentUnit.getPropertyID());

        System.out.println(getMaintenanceRequestList.size());

        for (int i = 0; i < getMaintenanceRequestList.size(); i++) {
            if (getMaintenanceRequestList.get(i).getPropertyID() == currentUnit.getPropertyID() && getMaintenanceRequestList.get(i).getUnitID() == currentUnit.getUnitID()) {
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


    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void goBack(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/pages/homepages/managerPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        managerController mcController = loader.getController();
        mcController.initialize(primaryStage, userID);
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
