package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.NutriCalcFunctions;
import com.solutions.sulmurz.nutricalc.models.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.models.PlanElementBasicData;
import com.solutions.sulmurz.nutricalc.models.PlanModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MyPlansController extends NutriCalcController {
    @FXML
    private VBox plansBox;
    @FXML
    private Button openButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button copyButton;
    @FXML
    private Button saveButton;
    private PlanModel selectedPlan;
    private VBox selectedPlanBox;

    @FXML
    protected void initialize() {
        for(PlanModel plan: NutriCalcModel.getMainPlansList()) {
            addToView(plan);
        }
    }

    @FXML
    private void onOpenButtonClick() {
        if(selectedPlan != null) {
            Parent root = null;
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/plan_view.fxml")
                );
                root = loader.load();
                PlanController controller = loader.getController();
                controller.setup(selectedPlan);
            } catch (IOException e) {
                e.printStackTrace();
                showFatalPrompt();
            }
            Scene scene = new Scene(root);
            NutriCalcMain.getPrimaryStage().setScene(scene);
        } else {
            showPrompt("Select a plan to open.");
        }
    }

    @FXML
    private void onAddButtonClick() {
        Parent root = null;
        AddPlanController controller;
        PlanElementBasicData planData;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/add_plan_view.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            showFatalPrompt();
        }
        controller = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add New Plan");
        stage.showAndWait();
        planData = controller.getNewPlanElementData();
        if(planData != null) {
            PlanModel newPlan = new PlanModel(0, planData.getName(), planData.getDescription());
            NutriCalcModel.getMainPlansList().add(newPlan);
            addToView(newPlan);
        }
    }

    @FXML
    private void onCopyButtonClick() {
        if(selectedPlan != null) {
            if(showConfirmationPrompt("Do you want to copy plan ", selectedPlan.getName())) {
                PlanModel newPlan = new PlanModel(selectedPlan);
                NutriCalcModel.getMainPlansList().add(newPlan);
                addToView(newPlan);
            }
        } else {
            showPrompt("Select a plan to copy.");
        }
    }

    @FXML
    private void onDeleteButtonClick() {

    }

    @FXML
    private void onSaveButtonClick() {

    }

    @FXML
    private void addToView(PlanModel plan) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/plan_preview_view.fxml")
            );

            VBox view = loader.load();

            MainPlanPreviewController controller = loader.getController();
            controller.setPlan(plan);
            controller.setMyPlansController(this);

            plansBox.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSelectedPlan(PlanModel selectedPlan, VBox planBox) {
        this.selectedPlan = selectedPlan;

        if(selectedPlanBox != null) {
            selectedPlanBox.getStyleClass().remove("selected-plan");
        }

        selectedPlanBox = planBox;
        selectedPlanBox.getStyleClass().add("selected-plan");
    }
}
