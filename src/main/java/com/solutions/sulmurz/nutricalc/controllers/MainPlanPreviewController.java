package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.models.PlanModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class MainPlanPreviewController {
    @FXML
    private VBox planBox;
    @FXML
    private Label planNameLabel;
    @FXML
    private TextArea planInfoArea;
    private PlanModel plan;
    private MyPlansController myPlansController;

    @FXML
    public void onPlanBoxClick() {
        myPlansController.setSelectedPlan(plan, planBox);
    }

    public void setPlan(PlanModel plan) {
//        nameLabel.textProperty().bind(model.nameProperty());
        planNameLabel.setText(plan.getName());
        planInfoArea.setText(plan.getDescription());
        this.plan = plan;
    }

    public void setMyPlansController(MyPlansController myPlansController) {
        this.myPlansController = myPlansController;
    }
}