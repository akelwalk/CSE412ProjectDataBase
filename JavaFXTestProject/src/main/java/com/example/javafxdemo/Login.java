package com.example.javafxdemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.io.IOException;
public class Login {

    public Login() {

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



    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();

    }

    public void userRegister(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("Register.fxml");

    }

    private void checkLogin() throws IOException {
        database_controller dbController = new database_controller();
        String role = dbController.checkLogin(email.getText(), password.getText());
    
        HelloApplication m = new HelloApplication();
    
        if (role != null) {
            switch (role) {
                case "PropertyManager":
                    m.changeScene("homepageManager.fxml");
                    break;
                case "Customer":
                    m.changeScene("homepageCustomer.fxml");
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