package com.solutions.sulmurz.nutricalc.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;

public abstract class NutriCalcView {
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
