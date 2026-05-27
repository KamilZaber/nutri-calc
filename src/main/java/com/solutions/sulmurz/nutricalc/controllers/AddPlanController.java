package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.models.PlanElementBasicData;
import com.solutions.sulmurz.nutricalc.models.PlanModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPlanController extends NutriCalcController {
    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label titleLabel;
    private PlanElementBasicData newPlanElementData = null;

    @FXML
    private void onCancelButtonClick() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    @FXML
    private void onSaveButtonClick() {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        if(name.isEmpty() || description.isEmpty()) {
            showPrompt("Enter name and description.");
        } else if(name.length() < 3 || name.length() > 30 || description.length() < 3 || description.length() > 100) {
            showPrompt("Name must contain from 3 to 30 characters and description must contain from 3 to 100 characters.");
        } else {
            newPlanElementData = new PlanElementBasicData(name, description);
            ((Stage) cancelButton.getScene().getWindow()).close();
        }
    }

    public PlanElementBasicData getNewPlanElementData() {
        return newPlanElementData;
    }

    public void setTitle(String title) {
        this.titleLabel.setText(title);
    }
}
