package com.solutions.sulmurz.nutricalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class NutriCalcMain extends Application {
    private static Stage primaryStage;
    private NutriCalcModel nutriCalcModel;
    private NutriCalcView nutriCalcView;
    private NutriCalcController nutriCalcController;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main_menu_view.fxml"));
        Scene scene = new Scene(root);
        primaryStage = stage;
        root.requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
