package com.components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;

import com.main.MainApplication;
import javafx.stage.Stage;

public class homepageCustomer {

    private Stage primaryStage;

    @FXML
    private Button logout;

    public void setStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }


    public void userLogOut(ActionEvent event) throws IOException {
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/hello-view.fxml");

    }

    public void goToPropertyPage(ActionEvent event) throws IOException
    {
        URL url = getClass().getResource("/com/resources/ViewProperties.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        PropertyListViewController propertyListViewController = loader.getController();
        propertyListViewController.setStage(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}