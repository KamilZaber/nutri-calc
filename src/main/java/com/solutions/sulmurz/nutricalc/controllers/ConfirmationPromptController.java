package com.solutions.sulmurz.nutricalc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class ConfirmationPromptController {
    boolean confirmed = false;
    @FXML
    private Label confirmationQuestion;
    @FXML
    private Label ingredientName;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    @FXML
    private void onNoButtonClick() {
        ((Stage) noButton.getScene().getWindow()).close();
    }
    @FXML
    private void onYesButtonClick() {
        confirmed = true;
        ((Stage) yesButton.getScene().getWindow()).close();
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName.setText(ingredientName + "?");
    }

    public void setConfirmationQuestion(String question) {
        this.confirmationQuestion.setText(question);
    }

    public boolean wasConfirmed() {
        return confirmed;
    }

}
