package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class MyPlansController extends NutriCalcController {
    @FXML
    private VBox plansBox;
    @FXML
    private Button backButton;
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
    private final ArrayList<PlanElementModel> addedElements = new ArrayList<>();
    private final ArrayList<PlanElementModel> deletedElements = new ArrayList<>();

    boolean changed = false;

    @FXML
    protected void initialize() {
        prepareView();
    }

    @FXML
    private void onBackButtonClick() {
        if(changed) {
            Boolean confirmed = showConfirmationPrompt("Do you want to save changes?", "");
            if(Boolean.TRUE.equals(confirmed)) {
                savePlanData();
            } else if(Boolean.FALSE.equals(confirmed)) {
                undoPlanChanges();
            } else {
                return;
            }
        }
        openMainMenuView();
    }

    @FXML
    private void onOpenButtonClick() {
        if(selectedPlan != null) {
            if(changed) {
                Boolean confirmed = showConfirmationPrompt("Do you want to save changes?", "");
                if(Boolean.TRUE.equals(confirmed)) {
                    savePlanData();
                } else if(Boolean.FALSE.equals(confirmed)) {
                    undoPlanChanges();
                } else {
                    return;
                }
            }

            if(NutriCalcModel.getMainPlansList().contains(selectedPlan)) {
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
                showPrompt("Unable to open such element.");
                plansBox.getChildren().clear();
                prepareView();
            }
        } else {
            showPrompt("Select a plan to open.");
        }
    }

    @FXML
    private void onAddButtonClick() {
        PlanElementBasicData planData = showAddNewElementForm("Add New Plan");
        if(planData != null) {
            PlanModel newPlan = new PlanModel(0, planData.getName(), planData.getDescription());
            NutriCalcModel.getMainPlansList().add(newPlan);
            addToView(newPlan);
            addedElements.add(newPlan);
        }

        changed = true;
    }

    @FXML
    private void onCopyButtonClick() {
        if(selectedPlan != null) {
            Boolean confirmed = showConfirmationPrompt("Do you want to copy plan ", selectedPlan.getName());
            if(Boolean.TRUE.equals(confirmed)) {
                PlanModel newPlan = new PlanModel(selectedPlan);
                NutriCalcModel.getMainPlansList().add(newPlan);
                addToView(newPlan);
                addedElements.add(newPlan);
            }
        } else {
            showPrompt("Select a plan to copy.");
        }

        changed = true;
    }

    @FXML
    private void onDeleteButtonClick() {
        if(selectedPlan != null) {
            if(Boolean.TRUE.equals(showConfirmationPrompt("Do you really want to delete plan", selectedPlan.getName()))) {
                ArrayList<PlanElementModel> planElementsToDelete = NutriCalcFunctions.traverseElements(selectedPlan);
                plansBox.getChildren().remove(selectedPlanBox);
                NutriCalcModel.getMainPlansList().remove(selectedPlan);

                for(PlanElementModel element: planElementsToDelete) {
                    if(element instanceof PlanModel) {
                        NutriCalcModel.getPlansList().remove(element);
                    } else if(element instanceof MealsSetModel) {
                        NutriCalcModel.getMealsSetsList().remove(element);
                    }
                    deletedElements.add(element);
                }

                changed = true;
            }
        } else {
            showPrompt("Select a plan to delete.");
        }
    }

    @FXML
    private void onSaveButtonClick() {
        if(changed) {
            savePlanData();
        } else {
            showPrompt("Nothing to save.");
        }
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

    private void savePlanData() {
        if(!deletedElements.isEmpty()) {
            for (PlanElementModel elementToDelete: deletedElements) {
                if(elementToDelete instanceof PlanModel) {
                    NutriCalcModel.getPlansIDs().freeID(elementToDelete.getID());
                } else if(elementToDelete instanceof MealsSetModel) {
                    NutriCalcModel.getMealsSetsIDs().freeID(elementToDelete.getID());
                }
            }
        }

        changed = false;
        showPrompt("Data saved.");
    }

    private void prepareView() {
        for(PlanModel plan: NutriCalcModel.getMainPlansList()) {
            addToView(plan);
        }

        NutriCalcModel.backupDatabases();
    }
    private void undoPlanChanges() {
        NutriCalcModel.restoreDatabases();
    }
}
