package com.controllers.homepages;

import com.controllers.loginregister.loginController;
import com.controllers.property.managerPropertyController;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.controllers.sessions.UserSession;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.main.MainApplication;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class managerController {

    private int userID;
    private Stage primaryStage;
    private Users currentUser;
    private PropertyManager currentManager;
    private int selectedUnitID;

    IDatabaseOperations databaseHandler = database_controller.getInstance();

    @FXML
    private Button logout;
    private Button ediButton; 
    private Button deleteButton; 
    private Button newAnnounce; 
    private Button delAnnounce;

    @FXML
    private Label user_name1; 
    @FXML
    private Label user_name2; 
    @FXML
    private Label user_name3; 
    @FXML
    private Label user_name4; 
    @FXML
    private Label user_name5;

    @FXML
    private Label name; 
    @FXML
    private Label address;
    @FXML
    private Label amenities; 

    @FXML
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

    //Tenants Page Stuff

    @FXML
    private TableView<Tenants> tenantsTableView;
    @FXML
    private TableColumn<Tenants, Integer> unitIDColTenant;

    @FXML
    private TableColumn<Tenants, String> firstNameColTenant;

    @FXML
    private TableColumn<Tenants, String> lastNameColTenant;

    @FXML
    private TableColumn<Tenants, String> emailColTenant;

    @FXML
    private TableColumn<Tenants, String> phoneNumberColTenant;

    //Lease Page Stuff


    @FXML
    private Label user_nameLease;

    @FXML
    private TableView<LeaseRequest> leaseTableView;
    @FXML
    private TableColumn<LeaseRequest, Integer> unitIDColLease;

    @FXML
    private TableColumn<LeaseRequest, String> paymentColLease;

    @FXML
    private TableColumn<LeaseRequest, String> firstNameColLease;

    @FXML
    private TableColumn<LeaseRequest, String> lastNameColLease;

    @FXML
    private TableColumn<LeaseRequest, String> emailColLease;

    @FXML
    private TableColumn<LeaseRequest, String> phoneNumberColLease;





    public void initialize(Stage primaryStage, int userID)
    {
        this.primaryStage = primaryStage;
        this.userID = userID;

        try {
            String usr = UserSession.getInstance().getEmail();
            database_controller dbController = database_controller.getInstance(); 
            user_name1.setText(dbController.getName(usr));
            user_name2.setText(dbController.getName(usr));
            user_name3.setText(dbController.getName(usr));
            user_name4.setText(dbController.getName(usr));
            user_name5.setText(dbController.getName(usr));
            this.selectedUnitID = -1;

            //Unit Tab Table initialization
            unitIDCol.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("unitID"));
            floorplanCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("floorplan"));
            isFurnishedCol.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("isFurnished"));
            conditionCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("condition"));
            isRentedCol.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("isRented"));


           //Tenant Tab Table initialization
            unitIDColTenant.setCellValueFactory(new PropertyValueFactory<Tenants, Integer>("unitID"));
            firstNameColTenant.setCellValueFactory(new PropertyValueFactory<Tenants, String>("firstName"));
            lastNameColTenant.setCellValueFactory(new PropertyValueFactory<Tenants, String>("lastName"));
            emailColTenant.setCellValueFactory(new PropertyValueFactory<Tenants, String>("email"));
            phoneNumberColTenant.setCellValueFactory(new PropertyValueFactory<Tenants, String>("phoneNumber"));

            //Lease Requests Tab Table initialization

           /* unitIDColLease.setCellValueFactory(new PropertyValueFactory<LeaseRequest, Integer>("unitID"));
            paymentColLease.setCellValueFactory(new PropertyValueFactory<LeaseRequest, String>("paymentType"));
            firstNameColLease.setCellValueFactory(new PropertyValueFactory<LeaseRequest, String>("firstName"));
            lastNameColLease.setCellValueFactory(new PropertyValueFactory<LeaseRequest, String>("lastName"));
            emailColLease.setCellValueFactory(new PropertyValueFactory<LeaseRequest, String>("email"));
            phoneNumberColLease.setCellValueFactory(new PropertyValueFactory<LeaseRequest, String>("phoneNumber"));

*/

            int property_id = dbController.getPropertyId(UserSession.getInstance().getUserID());
            name.setText("Property Name: " + dbController.getPropertyName(property_id));
            address.setText("Address: "+ dbController.getPropertyAddress(property_id));
            List<String> amenities_list = dbController.getAmmenities(property_id);
            amenities.setText("Amenities: "+String.join(",", amenities_list));
            setupTable();
            setUpTableTenants();
            //setUpTableLeaseRequests();
        } catch (IllegalStateException e) {
            UserSession.cleanUserSession();
            //username.setText("ERROR");
        }

    }

    @FXML
    void rowClicked(MouseEvent event) {
        Unit clickedUnit = unitTableView.getSelectionModel().getSelectedItem();
        selectedUnitID = clickedUnit.getUnitID();
        System.out.println("selected UnitID: " + clickedUnit);
    }

    public void userLogOut(ActionEvent event) throws IOException {
        UserSession.cleanUserSession();
        URL url = getClass().getResource("/com/pages/loginregister/loginPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        loginController login = loader.getController();
        login.setStage(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void getTenants(ActionEvent event)throws IOException 
    {

    }

    public void getLeases(ActionEvent event)throws IOException 
    {

    }


    // public void getProperties(ActionEvent event) throws IOException {
    //     URL url = getClass().getResource("/com/pages/property/managerProperties.fxml");
    //     System.out.println(url.toString());
    //     FXMLLoader loader = new FXMLLoader(url);
    //     Parent root = loader.load();
    //     managerPropertyController mpc = loader.getController(); 
    //     mpc.initialize(primaryStage);
    //     primaryStage.setScene(new Scene(root));
    //     primaryStage.show();
    // }

    public void announcement(ActionEvent event) throws IOException {
        
    }

    public void editProperty(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/pages/editor/propertyManagerEditor.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL); // This line ensures the popup blocks interaction with other windows until it's closed
        popupStage.setTitle("Edit Property");

        Scene scene = new Scene(root);
        popupStage.setScene(scene);

        popupStage.showAndWait();
    }


    public void deleteProperty(ActionEvent event) throws IOException {
        
    }

    public void newAnnounce(ActionEvent event) throws IOException {
        
    }

    public void delAnnounce(ActionEvent event) throws IOException {
        
    }

    private void setupTable(){

        database_controller db = new database_controller(); 
        int property_id = db.getPropertyId(UserSession.getInstance().getUserID());
        List<Unit> getUnitList = databaseHandler.unitList();
        System.out.println(getUnitList.size());
        System.out.println(property_id);

        for (int i = 0; i < getUnitList.size(); i++) {
            
            if (getUnitList.get(i).getPropertyID() == property_id) {
                
                unitTableView.getItems().addAll(getUnitList.get(i));
            }
        }
    }

    private void setUpTableTenants(){

        database_controller db = new database_controller();
        int property_id = db.getPropertyId(UserSession.getInstance().getUserID());
        List<Tenants> getTenantsList = databaseHandler.tenantsList();
        System.out.println(getTenantsList.size());
        System.out.println(property_id);

        System.out.println("Adding tenants");

        for (int i = 0; i < getTenantsList.size(); i++) {

            if (getTenantsList.get(i).getPropertyID() == property_id) {
                System.out.println("added tenants");

                tenantsTableView.getItems().addAll(getTenantsList.get(i));
            }
        }
    }



   /* private void setUpTableLeaseRequests(){

        database_controller db = new database_controller();
        int property_id = db.getPropertyId(UserSession.getInstance().getUserID());
        List<LeaseRequest> getLeaseRequests = databaseHandler.leaseRequestList();
        System.out.println(getLeaseRequests.size());
        System.out.println(property_id);

        System.out.println("Adding lease requests");

        for (int i = 0; i < getLeaseRequests.size(); i++) {

            if (getLeaseRequests.get(i).getPropertyID() == property_id) {
                leaseTableView.getItems().addAll(getLeaseRequests.get(i));
            }
        }
    }*/


    /*
    Lease Table Stored here:
          <Tab text="Lease Requests">
          <content>
              <AnchorPane minHeight="0.0" minWidth="0.0" prefHeight="180.0" prefWidth="200.0">
                  <children>
                      <BorderPane prefHeight="372.0" prefWidth="600.0">
                          <left>
                              <AnchorPane prefHeight="372.0" prefWidth="128.0" BorderPane.alignment="CENTER">
                                  <children>
                                      <ImageView fitHeight="48.0" fitWidth="51.0" layoutX="14.0" layoutY="14.0" pickOnBounds="true" preserveRatio="true">
                                          <image>
                                              <Image url="https://i.imgur.com/xCvzudWb.jpg" />
                                          </image>
                                      </ImageView>
                                      <Label fx:id="user_nameLease" layoutX="14.0" layoutY="77.0" text="username" textFill="WHITE" />
                                  </children>
                              </AnchorPane>
                          </left>
                          <center>
                              <AnchorPane prefHeight="200.0" prefWidth="200.0" BorderPane.alignment="CENTER">
                                  <children>
                                      <TableView fx:id="leaseTableView" layoutX="16.0" layoutY="94.0" onMouseClicked="#rowClicked" prefHeight="184.0" prefWidth="441.0">
                                          <columns>
                                              <TableColumn fx:id="unitIDColLease" prefWidth="60.0" text="UnitID" />
                                              <TableColumn fx:id="paymentColLease" text="Payment" />
                                              <TableColumn fx:id="firstNameColLease" prefWidth="93.0" text="First Name" />
                                              <TableColumn fx:id="lastNameColLease" prefWidth="67.0" text="Last Name" />
                                              <TableColumn fx:id="emailColLease" prefWidth="82.0" text="Email" />
                                              <TableColumn fx:id="phoneNumberColLease" prefWidth="82.0" text="Phone No." />
                                          </columns>
                                          <columnResizePolicy>
                                              <TableView fx:constant="CONSTRAINED_RESIZE_POLICY" />
                                          </columnResizePolicy>
                                      </TableView>
                                  </children>
                              </AnchorPane>
                          </center>
                      </BorderPane>
                  </children>
              </AnchorPane>
          </content>
      </Tab>
     */




}