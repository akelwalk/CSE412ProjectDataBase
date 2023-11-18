package com.components;


import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.main.MainApplication;
import com.models.Property;
import com.models.Unit;
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

    private int currPropertyID = -1;

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
    private TableColumn<Unit, Boolean> isRented;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.selectedUnitID = -1;
        unitIDCol.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("unitID"));
        floorplanCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("floorplan"));
        isFurnishedCol.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("isFurnished"));
        conditionCol.setCellValueFactory(new PropertyValueFactory<Unit, String>("condition"));
        isRented.setCellValueFactory(new PropertyValueFactory<Unit, Boolean>("isRented"));
        setupTable();
    }

    public void getParameters(Stage primaryStage, int propertyID)
    {
        this.primaryStage = primaryStage;
        this.currPropertyID = propertyID;
    }


    private void setupTable(){

        List<Unit> getUnitList = databaseHandler.unitList();

        for (int i = 0; i < getUnitList.size(); i++) {
            if (getUnitList.get(i).getPropertyID() == currPropertyID) {
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
        propertyViewController.initializeValues(primaryStage, unitTableView.getSelectionModel().getSelectedItem().getPropertyID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

}
