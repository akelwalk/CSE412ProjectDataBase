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
        m.changeScene("afterRegister.fxml");

    }

    private void checkLogin() throws IOException {
        final String JDBC_DRIVER = "org.postgresql.Driver";  
        final String DB_URL = "jdbc:postgresql://localhost:8888/cse412project"; // This port will likely be different on windows 
        
        Connection conn = null;
        PreparedStatement stmt = null;

        if (isPropertyManager.isSelected()) {
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL);

                String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND ROLE = PropertyManager";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, email.getText());
                stmt.setString(2, password.getText());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    wrongLogin.setText("Success!");
                    HelloApplication m = new HelloApplication();
                    m.changeScene("afterLogin.fxml"); // should probably have a couple of these pages one for users and another for Property managers.
                } else if (email.getText().isEmpty() && password.getText().isEmpty()) {
                    wrongLogin.setText("Please enter your data.");
                } else {
                    wrongLogin.setText("Wrong email or password!");
                }

                // Clean up
                rs.close();
                stmt.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
                wrongLogin.setText("Error connecting to the database.");
            } finally {
                try {
                    if (stmt != null) stmt.close();
                } catch (Exception se2) {
                }
                try {
                    if (conn != null) conn.close();
                } catch (Exception se) {
                    se.printStackTrace();
                }
            }

        }
        else {
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL);

                String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND ROLE = Customer";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, email.getText());
                stmt.setString(2, password.getText());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    wrongLogin.setText("Success!");
                    HelloApplication m = new HelloApplication();
                    m.changeScene("afterLogin.fxml"); // should probably have a couple of these pages one for users and another for Property managers.
                } else if (email.getText().isEmpty() && password.getText().isEmpty()) {
                    wrongLogin.setText("Please enter your data.");
                } else {
                    wrongLogin.setText("Wrong email or password!");
                }

                // Clean up
                rs.close();
                stmt.close();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
                wrongLogin.setText("Error connecting to the database.");
            } finally {
                try {
                    if (stmt != null) stmt.close();
                } catch (Exception se2) {
                }
                try {
                    if (conn != null) conn.close();
                } catch (Exception se) {
                    se.printStackTrace();
                }
            }

        }

    }


}