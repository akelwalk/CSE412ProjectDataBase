package com.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.db.database_controller;
import com.main.MainApplication;

import java.io.IOException;
public class Login {

    private Stage primaryStage;


    public Login() {

    }

    public void setStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    @FXML
    private CheckBox isPropertyManager;

    public void initialize() {
        // Set the same action for email and password fields to respond to Enter key press
        EventHandler<ActionEvent> loginHandler = event -> {
            try {
                userLogin(event);
            } catch (IOException e) {
                e.printStackTrace();
                wrongLogin.setText("An error occurred while trying to log in.");
            }
        };

        email.setOnAction(loginHandler);
        password.setOnAction(loginHandler);
    }



    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();

    }

    public void userRegister(ActionEvent event) throws IOException {
        MainApplication m = new MainApplication();
        m.changeScene("/com/resources/Register.fxml");

    }

    private void checkLogin() throws IOException {
        database_controller dbController = new database_controller();
        String role = dbController.checkLogin(email.getText(), password.getText());
    
        MainApplication m = new MainApplication();
    
        if (role != null) {
            switch (role) {
                case "PropertyManager":
                    m.changeScene("/com/resources/homepageManager.fxml");
                    break;
                case "Customer":
                    m.changeScene("/com/resources/homepageCustomer.fxml");
                    break;
                default:
                    wrongLogin.setText("Unrecognized role!");
                    break;
            }
        } else if (email.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("Please enter your data.");
        } else {
            wrongLogin.setText("Wrong email or password!");
        }
    }
    



}