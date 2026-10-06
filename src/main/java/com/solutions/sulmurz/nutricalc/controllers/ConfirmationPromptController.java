package com.solutions.sulmurz.nutricalc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class ConfirmationPromptController {
    Boolean confirmed = null;
    @FXML
    private Label confirmationQuestion;
    @FXML
    private Label name;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    @FXML
    private void onNoButtonClick() {
        confirmed = false;
        ((Stage) noButton.getScene().getWindow()).close();
    }
    @FXML
    private void onYesButtonClick() {
        confirmed = true;
        ((Stage) yesButton.getScene().getWindow()).close();
    }

    public void setName(String name) {
        if(!name.isEmpty()) {
            this.name.setText(name + "?");
        } else {
            this.name.setVisible(false);
        }
    }

    public void setConfirmationQuestion(String question) {
        this.confirmationQuestion.setText(question);
    }

    public Boolean wasConfirmed() {
        return confirmed;
    }
}
