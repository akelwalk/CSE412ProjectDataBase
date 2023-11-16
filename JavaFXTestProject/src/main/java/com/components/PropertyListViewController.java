package com.components;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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



public class PropertyListViewController implements Initializable {

    IDatabaseOperations databaseHandler = database_controller.getInstance();

    @FXML
    private Button homeButton;

    @FXML
   // private ListView<Propery> propertyListView = new ListView(FXCollections.observableList(Arrays.asList("one", "2", "3")));
   // private ListView<String> propertyListView = new ListView<>();

    private TableView<Property> propertyTableView;

    @FXML
    private TableColumn<Property, Integer> idCol;

    @FXML
    private TableColumn<Property, String> typeCol;

    @FXML
    private TableColumn<Animal, String> nameCol;


    public void goHome(ActionEvent event) throws IOException {
        System.out.println("Property View -> Customer Home Page");
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/homepageCustomer.fxml");

    }

}
