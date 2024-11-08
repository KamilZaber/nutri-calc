package com.solutions.sulmurz.nutricalc;

import com.solutions.sulmurz.nutricalc.views.NutriCalcMainMenuView;
import com.solutions.sulmurz.nutricalc.views.NutriCalcMyPlansView;
import javafx.application.Application;
import javafx.stage.Stage;

public class NutriCalcMain extends Application {
    public static Stage primaryStage;
    private NutriCalcController nutriCalcController;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        nutriCalcController = new NutriCalcController();
        stage.setScene(nutriCalcController.getNutriCalcMainMenuView());
        stage.show();
    }
}
