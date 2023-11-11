package com.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class MainApplication extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Calling MainApplication.class here so we need to ensure the fxml file is in the same package as the 
        // MainApplication file
        stg = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/resources/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650 , 500);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}

