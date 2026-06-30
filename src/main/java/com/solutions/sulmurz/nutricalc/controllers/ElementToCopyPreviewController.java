package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.models.PlanElementModel;
import com.solutions.sulmurz.nutricalc.models.PlanModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ElementToCopyPreviewController {
    @FXML
    private GridPane elementBar;
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    private PlanElementModel element;
    private ChooseElementController callerController;

    @FXML
    public void onPlanBoxClick() {
        callerController.setSelectedPlan(element, elementBar);
    }

    protected void setElement(PlanElementModel element) {
        this.element = element;
        this.idLabel.setText((Integer.toString(element.getID())));
        this.nameLabel.setText(element.getName());
    }

    public void setCallerController(ChooseElementController controller) {
        this.callerController = controller;
    }
}
