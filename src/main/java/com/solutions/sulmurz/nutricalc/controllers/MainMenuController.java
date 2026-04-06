package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class MainMenuController extends NutriCalcController {
    //    @FXML
//    private Button myIngredientsButton;
//    @FXML
//    private Button myMealsButton;
//    @FXML
//    private Button myPlansButton;
//    @FXML
//    private Button exitButton;
    @FXML
    private void exitNutriCalc() {
        Platform.exit();
    }
}
