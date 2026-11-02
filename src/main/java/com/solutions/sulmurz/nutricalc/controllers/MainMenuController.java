package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.NutriCalcModel;
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
        if(Boolean.TRUE.equals(NutriCalcController.showConfirmationPrompt("Do you want to save the database file?", ""))) {
            NutriCalcModel.saveDatabase();
        }
        Platform.exit();
    }
}
