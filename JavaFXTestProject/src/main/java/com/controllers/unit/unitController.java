package com.controllers.unit;

import com.controllers.homepages.managerController;
import com.controllers.sessions.UserSession;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.models.Customers;
import com.models.MaintenanceRequest;
import com.models.Unit;
import com.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private String r_paid; 

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

    @FXML
    private Label username1;
    @FXML
    private Label username2;
    @FXML
    private Label username3;
    @FXML
    private Label username4;
    @FXML
    private Label username5;

    //Rent Tab:

    @FXML
    private Label rentAmountLabel;

    @FXML
    private Label rentDueLabel;

    @FXML
    private Label rentPaidLabel;

    @FXML
    private Label userName1; 

    @FXML
    private Label userName2; 

    @FXML
    private Label userName3; 

    @FXML
    private Label userName4; 
    
    @FXML
    private Label userName5; 


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

    //Edit Tab

    @FXML
    private TextField floorplanField;

    @FXML
    private TextField conditionField;

    @FXML
    private ComboBox isFurnishedCombo;

    @FXML
    private TextField rentField;

    @FXML
    private ComboBox rentCombo;

    @FXML
    private Button saveButton;

    @FXML
    private Button resetButton;

    private String selectedRent;
    private boolean selectedFurnished;

    @FXML
    private Label errorLabel;

    //Lease Tab

    @FXML
    private Label leaseStartLabel;

    @FXML
    private Label leaseEndLabel;

    @FXML
    private Button terminateButton;

    private Date leaseStart;

    private Date leaseEnd;

    @FXML

    
    public void terminateLease(ActionEvent event) throws IOException {
      /*  System.out.println("Property View -> Customer Home Page");
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/propertyPage.fxml");*/
        if (checkRequest())
        {

        }
        URL url = getClass().getResource("/com/pages/unit/unitPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        unitController unit = loader.getController();
        database_controller dbcontroller = new database_controller();;
        unit.initializeValues(primaryStage, UserSession.getInstance().getUserID(), currentUnit.getPropertyID(), currentUnit.getUnitID());
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();

    }

    @FXML
    public void renewLease(ActionEvent event) throws IOException {
      /*  System.out.println("Property View -> Customer Home Page");
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/propertyPage.fxml");*/

        database_controller dbController = new database_controller();
        String registrationResult = dbController.renewLease(currentUnit.getUserID());


        URL url = getClass().getResource("/com/pages/unit/unitPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        unitController unit = loader.getController();
        database_controller dbcontroller = new database_controller();;
        unit.initializeValues(primaryStage, UserSession.getInstance().getUserID(), currentUnit.getPropertyID(), currentUnit.getUnitID());
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();

    }

    //Maintenance Tab

    private MaintenanceRequest currentRequest;

    @FXML
    void resolveRequest(ActionEvent even) throws IOException{
        System.out.println("calling resolveRequests()");
        System.out.println("selected Maintenance: " );

        database_controller dbController = new database_controller();
        String registrationResult = dbController.resolveRequest(currentRequest.getRequestID(), currentRequest.getUnitID(), currentRequest.getPropertyID(),currentRequest.getUserID()) ;
        System.out.println(registrationResult);
        System.out.println("Success".equals(registrationResult));

        initializeValues(primaryStage, UserSession.getInstance().getUserID(), currentUnit.getPropertyID(), currentUnit.getUnitID());

        //initializeValues(primaryStage, UserSession.getInstance().getUserID(), currentUnit.getPropertyID(), currentUnit.getUnitID());


            /*if (checkAcceptLease()) {
                initialize(primaryStage, UserSession.getInstance().getUserID());
            }*/


    }



    public boolean checkRequest()
    {


        database_controller dbController = new database_controller();
        String registrationResult = dbController.cancelLeaseRequest(currentUnit.getUserID());
        return "Success".equals(registrationResult);


    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    @FXML
    void saveEdit(ActionEvent event) throws IOException {

        int countErrors = 0;

        StringBuilder errors = new StringBuilder();
        errors.append("Errors in the following fields: ");

    boolean newFurnished = selectedFurnished ;
    boolean temp_nr = false; 

    if (selectedRent.equals("Paid"))
    {
        temp_nr = true;
    }
    else
    {
        temp_nr = false;
    }
    boolean newRent = temp_nr;
    double newAmount = 0.0;

        if (!isNumeric(rentField.getText()))
        {
            errors.append("rent amount ");
            countErrors++;
        }
        else {

            newAmount = Double.valueOf(rentField.getText());
        }

    String newFloorplan = floorplanField.getText();

    if (newFloorplan.isBlank())
    {
        errors.append("floorplan ");
                countErrors++;
    }
    String newCondition = conditionField.getText();

        if (newCondition.isBlank())
        {
            errors.append("condition ");
            countErrors++;

        }

        if (countErrors == 0) {
            errorLabel.setText("");
            System.out.println("New values: :" + newFurnished + "," + newRent + ", " + newAmount + ", " + newFloorplan + ", " + newCondition);

            database_controller dbController = new database_controller();
            dbController.editUnit(currentUnit.getPropertyID(), currentUnit.getUnitID(), newFloorplan, newCondition, newFurnished, newAmount, newRent);


        }
        else {
            errorLabel.setText(errors.toString());
        }


        }

    @FXML
    void resetEdit(ActionEvent event) throws IOException {
            initializeEdit();
           }



    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tenantTab;


    public void initializeValues(Stage primaryStage, int userID, int propertyID, int unitID)
    {

        try {
            String usr = UserSession.getInstance().getEmail();
            database_controller dbController = database_controller.getInstance();

            System.out.println(usr);

            username1.setText(dbController.getName(usr));
            username2.setText(dbController.getName(usr));
            username3.setText(dbController.getName(usr));
            username4.setText(dbController.getName(usr));
            username5.setText(dbController.getName(usr));


            //userName1.setText("Hello World");


           /* userName1.setText(dbController.getName(usr));
            userName2.setText(dbController.getName(usr));
            userName3.setText(dbController.getName(usr));
            userName4.setText(dbController.getName(usr));
            userName5.setText(dbController.getName(usr));*/
        } catch (IllegalStateException e) {
            UserSession.cleanUserSession();
            //username.setText("ERROR");
        }

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

        if (currentUnit.isRented() == false)
        {
            tabPane.getTabs().remove(tenantTab);
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


        if (rentPaid) 
        {
            r_paid = "Paid";
        }
        else
        {
            r_paid = "Unpaid";
        }

        //Edit Tab

        isFurnishedCombo.setOnAction((event) -> {
            int selectedIndex = isFurnishedCombo.getSelectionModel().getSelectedIndex();
            Object selectedItem = isFurnishedCombo.getSelectionModel().getSelectedItem();

            String selected = (String) selectedItem;

            if (selected == "True")
            {
                selectedFurnished = true;
            }
            else if (selected == "False")
            {
                selectedFurnished = false;
            }
            else {
                selectedFurnished = isFurnished;
            }
        });

        rentCombo.setOnAction((event) -> {
            int selectedIndex = rentCombo.getSelectionModel().getSelectedIndex();
            Object selectedItem = rentCombo.getSelectionModel().getSelectedItem();

            String selected = (String) selectedItem;

            if (selected == "Paid")
            {
                selectedRent = "Paid";
            }
            else if (selected == "Unpaid")
            {
                selectedRent = "Unpaid";
            }
            else {
                selectedRent = r_paid;
            }
        });

        initializeEdit();


            //Fetch the customer object

            List<Customers> getCustomerList = databaseHandler.customerList();

            Customers currentCustomer = getCustomerList.get(0);

            System.out.println("The current userID is: " + UserSession.getInstance().getUserID());


            for (int i = 0; i < getCustomerList.size(); i++)
            {
                if (getCustomerList.get(i).getUserID() == currentUnit.getUserID())
                {
                    currentCustomer = getCustomerList.get(i);
                    System.out.println("Current customer FOUND! ->" + getCustomerList.get(i).getUnitID());
                    break;
                }
            }

            this.leaseStart = currentCustomer.getLeaseStart();
            this.leaseEnd = currentCustomer.getLeaseEnd();

        leaseStartLabel.setText(String.valueOf(currentCustomer.getLeaseStart()));
        leaseEndLabel.setText(String.valueOf(currentCustomer.getLeaseEnd()));







    }

    public void initializeEdit()
    {
        floorplanField.setText(floorplan);
        conditionField.setText(condition);
        rentField.setText(Double.toString(rentAmount));

        isFurnishedCombo.getItems().addAll("True", "False");
        rentCombo.getItems().addAll("True", "False");

        if (isFurnished)
        {
            isFurnishedCombo.getSelectionModel().select(0);
            selectedFurnished = true;
        }
        else {
            isFurnishedCombo.getSelectionModel().select(1);
            selectedFurnished = false;
        }

        if (rentPaid)
        {
            rentCombo.getSelectionModel().select(0);
            selectedRent = "Paid";
        }
        else {
            rentCombo.getSelectionModel().select(1);
            selectedRent = "Unpaid";
        }

    }


    private void setupTable(){

        List<MaintenanceRequest> getMaintenanceRequestList = databaseHandler.requestList();
        System.out.println(currentUnit.getPropertyID());

        System.out.println(getMaintenanceRequestList.size());

        maintenanceRequestTableView.getItems().clear();

        for (int i = 0; i < getMaintenanceRequestList.size(); i++) {
            if (getMaintenanceRequestList.get(i).getPropertyID() == currentUnit.getPropertyID() && getMaintenanceRequestList.get(i).getUnitID() == currentUnit.getUnitID()) {
                maintenanceRequestTableView.getItems().addAll(getMaintenanceRequestList.get(i));
            }
        }
    }

    @FXML
    void rowClicked(MouseEvent event) {
        currentRequest = maintenanceRequestTableView.getSelectionModel().getSelectedItem();
        selectedRequestID = currentRequest.getUnitID();
        System.out.println("selected UnitID: " + currentRequest);
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
        primaryStage.setScene(new Scene(root,800,600));
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
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();

    }*/

}
