package com.solutions.sulmurz.nutricalc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class NoIngredientPromptController {
    boolean skipIngredient = false;
    @FXML
    private Label ingredientNameLabel;
    @FXML
    private Button deleteButton;
    @FXML
    private Button backButton;

    @FXML
    private void onSkipButtonClick() {
        skipIngredient = true;
        ((Stage) deleteButton.getScene().getWindow()).close();
    }
    @FXML
    private void onBackButtonClick() {
        ((Stage) backButton.getScene().getWindow()).close();
    }
    public void setIngredientName(String ingredientName) {
        ingredientNameLabel.setText(ingredientName);
    }
    public boolean skipIngredient() {
        return skipIngredient;
    }
}
