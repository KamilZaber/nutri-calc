package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class MainMenuController extends NutriCalcController {
    private Scene scene;
    private Parent root;
//    @FXML
//    private Button myIngredientsButton;
//    @FXML
//    private Button myMealsButton;
//    @FXML
//    private Button myPlansButton;
//    @FXML
//    private Button exitButton;
    @FXML
    private void openMyMealsView() {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("my_meals_view.fxml"));
        } catch (IOException e) {
            showFatalPrompt();
        }
        scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }

    @FXML
    private void openMyPlansView() {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("my_plans_view.fxml"));
        } catch (IOException e) {
            showFatalPrompt();
        }
        scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }

    @FXML
    private void exitNutriCalc() {
        Platform.exit();
    }
}
