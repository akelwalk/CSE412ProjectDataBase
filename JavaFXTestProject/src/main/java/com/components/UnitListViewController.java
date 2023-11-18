package com.components;


import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.main.MainApplication;
import com.models.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.ResourceBundle;


public class UnitListViewController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        propertyViewController.initializeValues(primaryStage, propertyTableView.getSelectionModel().getSelectedItem().getPropertyID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

}
