package com.controllers.homepages;

import com.controllers.loginregister.loginController;
import com.controllers.property.propertyListController;
import com.controllers.sessions.UserSession;
import com.db.IDatabaseOperations;
import com.db.database_controller;
import com.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;

import javafx.stage.Stage;

public class customerController {

    private int userID;
    private Stage primaryStage;
    private Users currentUser;

    @FXML
    private Label user_name; 

    IDatabaseOperations databaseHandler = database_controller.getInstance();

    @FXML
    private Button logout;

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
        this.primaryStage = primaryStage;
        this.userID = userID;
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

    public void goToPropertyList(ActionEvent event) throws IOException
    {
        URL url = getClass().getResource("/com/pages/property/propertyListPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        propertyListController propertyListViewController = loader.getController();
        propertyListViewController.initialize(primaryStage, userID);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}