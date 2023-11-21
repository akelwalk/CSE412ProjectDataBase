package com.components;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.Event;


import com.db.database_controller;
import com.main.MainApplication;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import java.io.IOException;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import com.models.Property;
import com.db.database_controller;
import com.db.IDatabaseOperations;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;



public class PropertyListViewController {

    IDatabaseOperations databaseHandler = database_controller.getInstance();

    private Stage primaryStage;

    private int selectedPropertyId;
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

    public void initialize(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.selectedPropertyId = -1;
        propertyIDCol.setCellValueFactory(new PropertyValueFactory<Property, Integer>("propertyID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Property, String>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
        amenitiesCol.setCellValueFactory(new PropertyValueFactory<Property, String>("amenities"));
        freeUnitsCol.setCellValueFactory(new PropertyValueFactory<Property, Integer>("free units"));
        setupTable();
    }

    public void setStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }


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



    public void goHome(ActionEvent event) throws IOException {
        System.out.println("Property View -> Customer Home Page");
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/homepageCustomer.fxml");

        URL url = getClass().getResource("/com/resources/homepageCustomer.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        homepageCustomer homepageCustomer = loader.getController();
        homepageCustomer.initialize(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public void goToPropertyPage(ActionEvent event) throws IOException {
      /*  System.out.println("Property View -> Customer Home Page");
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/PropertyView.fxml");*/



        URL url = getClass().getResource("/com/resources/propertyView.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        PropertyViewController propertyViewController = loader.getController();
        propertyViewController.initialize(primaryStage, propertyTableView.getSelectionModel().getSelectedItem().getPropertyID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

}
