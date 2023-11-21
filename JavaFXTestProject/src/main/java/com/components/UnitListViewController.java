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


public class UnitListViewController {

    IDatabaseOperations databaseHandler = database_controller.getInstance();

    private Stage primaryStage;

    private int currPropertyID;

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

    public void initializeValues(Stage primaryStage, int propertyID)
    {
        this.selectedUnitID = -1;
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
        URL url = getClass().getResource("/com/resources/propertyView.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        PropertyViewController propertyViewController = loader.getController();
        propertyViewController.initialize(primaryStage, currPropertyID);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public void goToPropertyPage(ActionEvent event) throws IOException {
      /*  System.out.println("Property View -> Customer Home Page");
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/PropertyView.fxml");*/


        URL url = getClass().getResource("/com/resources/UnitView.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        UnitViewController unitViewController = loader.getController();
        unitViewController.initializeValues(primaryStage, unitTableView.getSelectionModel().getSelectedItem().getPropertyID(), unitTableView.getSelectionModel().getSelectedItem().getUnitID());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

}
