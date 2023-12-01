package com.main;

import com.controllers.loginregister.loginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Calling MainApplication.class here so we need to ensure the fxml file is in the same package as the 
        // MainApplication file
        stg = primaryStage;





        /*FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/resources/loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650 , 500);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();*/



        URL url = getClass().getResource("/com/pages/loginregister/loginPage.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        loginController loginController = loader.getController();
        loginController.setStage(primaryStage);
        Scene loginScene = new Scene(root,800,600);
        primaryStage.setScene(loginScene);
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

