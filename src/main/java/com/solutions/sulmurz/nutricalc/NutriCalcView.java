package com.solutions.sulmurz.nutricalc;

import javafx.scene.Scene;
import javafx.scene.control.Button;

public abstract class NutriCalcView {
//    public static Scene mainMenuView = null;
//    public static Scene myIngredientsView = null;
//    public static Scene myMealsView = null;
//    public static Scene myPlansView = null;

    protected Button backButton;
    public abstract Scene getView();
    public NutriCalcView() {
        backButton = new Button("BACK");
        backButton.setPrefSize(100,50);
    }
    public Button getBackButton() {
        return backButton;
    }
}
