package com.example.javafxdemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
public class Login {

    public Login() {

    }

    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;



    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();

    }

    public void userRegister(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("afterLogin.fxml");

    }

    private void checkLogin() throws IOException {
        HelloApplication m = new HelloApplication();
        if(username.getText().toString().equals("test") && password.getText().toString().equals("test12345")) {
            wrongLogin.setText("Success!");

            m.changeScene("afterLogin.fxml");
        }

        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("Please enter your data.");
        }


        else {
            wrongLogin.setText("Wrong username or password!");
        }
    }


}