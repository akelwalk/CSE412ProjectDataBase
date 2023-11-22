package com.controllers.loginregister;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;

import com.db.database_controller;

import java.io.IOException;

public class registerController {

    private Stage primaryStage;


    public registerController() {

    }

    public void setStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }


    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Label errorLabel;

    @FXML
    private Button createButton;

    @FXML
    private Button backButton;

    public void returnHome(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/com/pages/loginregister/loginPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        loginController login = loader.getController();
        login.setStage(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void userRegister(ActionEvent event) throws IOException {
        boolean registered = checkRegister(); 

        if (registered == true)
        {
            returnHome(event);
        }
        else // registered = false 
        {
            // do nothing display error message
        }

    }

    private boolean checkRegister() throws IOException {

        // password contraints
        if (!(password.getText().equals(confirmPassword.getText()))) {
            errorLabel.setText("Entered passwords do not match!");
            return false;
        } else if (password.getLength() < 6) {
            errorLabel.setText("Passwords must be at least 6 characters long!");
            return false;
        } else {
            
            database_controller dbController = new database_controller();
            String registrationResult = dbController.registerUser(email.getText(), firstName.getText(), lastName.getText(), password.getText(), phoneNumber.getText());
            errorLabel.setText(registrationResult);
            return "Account successfully created!".equals(registrationResult);
        }

    }

}