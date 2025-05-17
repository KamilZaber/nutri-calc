package com.solutions.sulmurz.nutricalc;

import com.solutions.sulmurz.nutricalc.controllers.NutriCalcController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class NutriCalcMain extends Application {
    private static Stage primaryStage;
    private static NutriCalcModel nutriCalcModel;

    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("main_menu_view.fxml"));
        } catch (IOException e) {
            NutriCalcController.showFatalPrompt();
        }
        Scene scene = new Scene(root);
        primaryStage = stage;
        nutriCalcModel = new NutriCalcModel();
        root.requestFocus();
        stage.setScene(scene);
        stage.setTitle("NutriCalc 0.1");
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static NutriCalcModel getNutriCalcModel() {
        return nutriCalcModel;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
