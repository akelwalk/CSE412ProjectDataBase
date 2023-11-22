package com.controllers.homepages;

import com.controllers.loginregister.loginController;
import com.controllers.property.propertyListController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;

import javafx.stage.Stage;

public class customerController {

    private Stage primaryStage;

    @FXML
    private Button logout;

    public void initialize(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public void userLogOut(ActionEvent event) throws IOException {
        /*MainApplication m = new MainApplication();
        m.changeScene("/com/resources/loginPage.fxml");*/

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
        propertyListViewController.initialize(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}