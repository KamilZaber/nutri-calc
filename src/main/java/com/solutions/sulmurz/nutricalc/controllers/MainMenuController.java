package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {
    private Scene scene;
    private Parent root;
    @FXML
    private Button myIngredientsButton;
    @FXML
    private Button myMealsButton;
    @FXML
    private Button myPlansButton;
    @FXML
    private Button exitButton;
    @FXML
    public void openMyIngredientsView() throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("my_ingredients_view.fxml"));
        scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }
    @FXML
    public void openMyMealsView() throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("my_meals_view.fxml"));
        scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }
    @FXML
    public void openMyPlansView() throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("my_plans_view.fxml"));
        scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }
    @FXML
    public void exitNutriCalc() throws IOException {
        NutriCalcMain.getPrimaryStage().close();
    }
}
