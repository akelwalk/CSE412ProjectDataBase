package com.controllers.loginregister;

import com.controllers.homepages.customerController;
import com.controllers.sessions.UserSession;
import com.controllers.homepages.managerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.net.URL;

import org.postgresql.shaded.com.ongres.scram.common.util.UsAsciiUtils;

import com.db.database_controller;
import com.main.MainApplication;

import java.io.IOException;
public class loginController {

    private Stage primaryStage;


    public loginController() {

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
        URL url = getClass().getResource("/com/pages/loginregister/registerPage.fxml");
        System.out.println(url.toString());
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        registerController register = loader.getController();
        register.setStage(primaryStage);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    private void checkLogin() throws IOException {
        database_controller dbController = new database_controller();
        String role = dbController.checkLogin(email.getText(), password.getText());
        int userID = dbController.getUserID(email.getText());

        MainApplication m = new MainApplication();

        if (role != null) {
            UserSession.getInstance(userID,email.getText(),role); // create user session object 

            switch (role) {
                case "PropertyManager":
                    URL url = getClass().getResource("/com/pages/homepages/managerPage.fxml");
                    System.out.println(url.toString());
                    FXMLLoader loader = new FXMLLoader(url);
                    Parent root = loader.load();
                    managerController mcController = loader.getController();
                    mcController.initialize(primaryStage, userID);
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
                    break;
                case "Customer":
                    url = getClass().getResource("/com/pages/homepages/customerPage.fxml");
                    System.out.println(url.toString());
                    loader = new FXMLLoader(url);
                    root = loader.load();
                    customerController cController = loader.getController();
                    cController.initialize(primaryStage, userID);
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();
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