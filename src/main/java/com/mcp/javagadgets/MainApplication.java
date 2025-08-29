package com.mcp.javagadgets;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplication extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        loadMainView();
        primaryStage.setTitle("Java Gadgets");
        primaryStage.show();
    }

    public static void loadMainView() throws IOException {
        Parent root = FXMLLoader.load(
                MainApplication.class.getResource("view/MainView.fxml")
        );
        primaryStage.setScene(new Scene(root, 1000, 900));
    }

    public static void loadDesensitizationView() throws IOException {
        Parent root = FXMLLoader.load(
                MainApplication.class.getResource("view/DesensitizationView.fxml")
        );
        primaryStage.setScene(new Scene(root, 1000, 900));
    }

    public static void main(String[] args) {
        launch();
    }
}