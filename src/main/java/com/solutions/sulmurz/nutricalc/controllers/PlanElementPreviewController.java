package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.models.PlanElementModel;
import com.solutions.sulmurz.nutricalc.models.PlanModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class PlanElementPreviewController {
    @FXML
    private VBox elementBox;
    @FXML
    private Label elementNameLabel;
    @FXML
    private TextArea elementInfoArea;
    private PlanElementModel element;
    private PlanController parentPlanController;

    @FXML
    public void onElementBoxClick() {
        parentPlanController.setSelectedElement(element,elementBox);
    }

    public void setElement(PlanElementModel element) {
        String type;
        if(element instanceof PlanModel) {
            type = "Plan";
        } else {
            type = "Meals Set";
        }

        this.element = element;
        this.elementNameLabel.setText(element.getName());
        this.elementInfoArea.setText("Type: " + type);
    }

    public void setParentPlanController(PlanController parentPlanController) {
        this.parentPlanController = parentPlanController;
    }
}
