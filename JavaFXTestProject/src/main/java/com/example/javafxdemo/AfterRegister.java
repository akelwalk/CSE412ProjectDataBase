package com.example.javafxdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AfterRegister {

    @FXML
    private Button logout;


    public void userLogOut(ActionEvent event) throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("hello-view.fxml");

    }
}