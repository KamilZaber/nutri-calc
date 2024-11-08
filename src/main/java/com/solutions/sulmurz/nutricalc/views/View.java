package com.solutions.sulmurz.nutricalc.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;

public abstract class View {
    private Button backButton;
    public abstract Scene getView();
    public View() {
        backButton = new Button("BACK");
        backButton.setPrefSize(100,50);
    }
    public Button getBackButton() {
        return backButton;
    }
}
