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

public class Register {

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
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");

    }

    public void userRegister(ActionEvent event) throws IOException {
        checkRegister();

    }

    private void checkRegister() throws IOException {

        boolean validFormat = false;

        if (!(password.getText().equals(confirmPassword.getText()))) {
            errorLabel.setText("Entered passwords do not match!");
            return;
        } else {
            if (password.getLength() < 6) {
                errorLabel.setText("Passwords must be at least 6 characters long!");
                return;
            }
        }

        if (validFormat == true)
        {
        final String JDBC_DRIVER = "org.postgresql.Driver";
        final String DB_URL = "jdbc:postgresql://localhost:8888/cse412project";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            // Query only for email and password, the role will be determined from the result
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email.getText());
            stmt.setString(2, password.getText());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("ROLE");

                HelloApplication m = new HelloApplication();

                if ("PropertyManager".equals(role)) {
                    m.changeScene("homepageManager.fxml");
                } else if ("Customer".equals(role)) {
                    m.changeScene("homepageCustomer.fxml");
                } else {
                    // Handle any other roles if required
                    errorLabel.setText("Unrecognized role!");
                }
            } else if (email.getText().isEmpty() && password.getText().isEmpty()) {
                errorLabel.setText("Please enter your data.");
            } else {
                errorLabel.setText("Wrong email or password!");
            }

            // Clean up
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error connecting to the database.");
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

        /*
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL);

            // Query only for email and password, the role will be determined from the result
            String sql = "SELECT ROLE FROM users WHERE email = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email.getText());
            stmt.setString(2, password.getText());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("ROLE");

                HelloApplication m = new HelloApplication();

                if ("PropertyManager".equals(role)) {
                    m.changeScene("homepageManager.fxml");
                } else if ("Customer".equals(role)) {
                    m.changeScene("homepageCustomer.fxml");
                } else {
                    // Handle any other roles if required
                    errorLabel.setText("Unrecognized role!");
                }
            } else if (email.getText().isEmpty() && password.getText().isEmpty()) {
                errorLabel.setText("Please enter your data.");
            } else {
                errorLabel.setText("Wrong email or password!");
            }

            // Clean up
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Error connecting to the database.");
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
        */



    }

}