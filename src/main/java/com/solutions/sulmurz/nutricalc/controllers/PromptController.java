package com.solutions.sulmurz.nutricalc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PromptController extends NutriCalcController {
    @FXML
    private Label errorMessage;
    @FXML
    private Button okButton;
    @FXML
    private void onOKButtonClick() {
        ((Stage) okButton.getScene().getWindow()).close();
    }
    protected void setErrorMessage(String message) {
        errorMessage.setText(message);
    }

    protected String getErrorMessage() {
        return errorMessage.getText();
    }
}
