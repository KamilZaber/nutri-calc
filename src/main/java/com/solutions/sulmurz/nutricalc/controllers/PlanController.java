package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;

public class PlanController extends NutriCalcController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane elementsBox;
    @FXML
    private Label planNameLabel;
    @FXML
    private VBox macroSection;
    @FXML
    private VBox mineralsSection;
    @FXML
    private VBox vitaminsSection;
    @FXML
    private MenuButton addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button openButton;
    @FXML
    private Button copyButton;
    @FXML
    private Button saveButton;
    private PlanModel currentPlan;
    private PlanElementModel selectedElement;
    private VBox selectedBox;
    private int elementCount = 0;
    private final int COLUMNS = 4;

    @FXML
    public void onAddNewPlan() {
        PlanElementBasicData planData = showAddNewElementForm("Add New Plan");
        if(planData != null) {
            PlanModel newPlan = new PlanModel(1, planData.getName(), planData.getDescription());
            NutriCalcModel.getPlansList().add(newPlan);
            currentPlan.addElement(newPlan);
            resetElementsBox();
            addToView(newPlan);
            setup(currentPlan);
        }
    }

    @FXML
    public void onAddExistingPlan() {
        PlanElementModel planToAdd = showChoosePlanElementWindow(NutriCalcModel.getPlansList());
        if(planToAdd != null) {
            PlanModel newPlan = new PlanModel((PlanModel) planToAdd);
            NutriCalcModel.getPlansList().add(newPlan);
            currentPlan.addElement(newPlan);
            currentPlan.recalculateParents("PLUS", newPlan.getMacroAmounts(), newPlan.getMineralsAmounts(), newPlan.getVitaminsAmounts());
            resetElementsBox();
            addToView(newPlan);
            setup(currentPlan);
        }
    }

    @FXML
    public void onAddNewMealSet() {
        PlanElementBasicData mealsSetData = showAddNewElementForm("Add New Meals Set");
        if(mealsSetData != null) {
            MealsSetModel newMealsSet = new MealsSetModel(2, mealsSetData.getName(), mealsSetData.getDescription());
            NutriCalcModel.getMealsSetsList().add(newMealsSet);
            currentPlan.addElement(newMealsSet);
            resetElementsBox();
            addToView(newMealsSet);
            setup(currentPlan);
        }
    }

    @FXML
    public void onAddExistingMealSet() {
        PlanElementModel mealsSetToAdd = showChoosePlanElementWindow(NutriCalcModel.getMealsSetsList());
        if(mealsSetToAdd != null) {
            MealsSetModel newMealsSet = new MealsSetModel((MealsSetModel) mealsSetToAdd);
            NutriCalcModel.getMealsSetsList().add(newMealsSet);
            currentPlan.addElement(newMealsSet);
            currentPlan.recalculateParents("PLUS", newMealsSet.getMacroAmounts(), newMealsSet.getMineralsAmounts(), newMealsSet.getVitaminsAmounts());
            resetElementsBox();
            addToView(newMealsSet);
            setup(currentPlan);
        }
    }

    @FXML
    private void onDeleteButtonClick() {
        if(selectedElement != null) {
            if(showConfirmationPrompt("Do you really want to delete element", selectedElement.getName())) {
                if(selectedElement instanceof PlanModel) {
                    ArrayList<PlanElementModel> planElementsToDelete = NutriCalcFunctions.traverseElements((PlanModel) selectedElement);
                    elementsBox.getChildren().remove(selectedBox);
                    currentPlan.deleteElement(selectedElement);
                    currentPlan.recalculateParents("MINUS", selectedElement.getMacroAmounts(), selectedElement.getMineralsAmounts(), selectedElement.getVitaminsAmounts());
                    for (PlanElementModel element: planElementsToDelete) {
                        if (element instanceof PlanModel) {
                            NutriCalcModel.getPlansList().remove(element);
                            NutriCalcModel.getPlansIDs().freeID(element.getID());
                        } else if (element instanceof MealsSetModel) {
                            NutriCalcModel.getMealsSetsList().remove(element);
                            NutriCalcModel.getMealsSetsIDs().freeID(element.getID());
                        }
                    }
                } else if (selectedElement instanceof MealsSetModel) {
                    elementsBox.getChildren().remove(selectedBox);
                    currentPlan.deleteElement(selectedElement);
                    currentPlan.recalculateParents("MINUS", selectedElement.getMacroAmounts(), selectedElement.getMineralsAmounts(), selectedElement.getVitaminsAmounts());
                    NutriCalcModel.getMealsSetsList().remove((MealsSetModel) selectedElement);
                    NutriCalcModel.getMealsSetsIDs().freeID(selectedElement.getID());
                }
                resetElementsBox();
                setup(currentPlan);
            }
        } else {
            showPrompt("Select a plan to delete.");
        }
    }

    @FXML
    private void onOpenButtonClick() {
        if (selectedBox != null) {
            try {
                Parent root = null;
                if (selectedElement instanceof PlanModel) {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/plan_view.fxml")
                    );
                    root = loader.load();
                    PlanController controller = loader.getController();
                    Scene scene = new Scene(root);
                    NutriCalcMain.getPrimaryStage().setScene(scene);
                    controller.setup((PlanModel) selectedElement);
                } else if (selectedElement instanceof MealsSetModel) {
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/meals_set_view.fxml")
                    );
                    root = loader.load();
                    MealsSetController controller = loader.getController();
                    Scene scene = new Scene(root);
                    NutriCalcMain.getPrimaryStage().setScene(scene);
                    controller.setup((MealsSetModel) selectedElement);
                }
            } catch (IOException e) {
                e.printStackTrace();
                showFatalPrompt();
            }
        } else {
            showPrompt("Select a plan element to open.");
        }
    }

    @FXML
    private void onCopyButtonClick() {
        if(selectedElement != null) {
            if(showConfirmationPrompt("Do you want to copy element ", selectedElement.getName())) {
                if(selectedElement instanceof PlanModel) {
                    PlanModel newPlan = new PlanModel((PlanModel) selectedElement);
                    NutriCalcModel.getPlansList().add(newPlan);
                    currentPlan.addElement(newPlan);
                    currentPlan.recalculateParents("PLUS", newPlan.getMacroAmounts(), newPlan.getMineralsAmounts(), newPlan.getVitaminsAmounts());
                    addToView(newPlan);
                } else if(selectedElement instanceof MealsSetModel) {
                    MealsSetModel newMealsSet = new MealsSetModel((MealsSetModel) selectedElement);
                    NutriCalcModel.getMealsSetsList().add(newMealsSet);
                    currentPlan.addElement(newMealsSet);
                    currentPlan.recalculateParents("PLUS", newMealsSet.getMacroAmounts(), newMealsSet.getMineralsAmounts(), newMealsSet.getVitaminsAmounts());
                    addToView(newMealsSet);
                }
                resetElementsBox();
                setup(currentPlan);
            }
        } else {
            showPrompt("Select a plan to copy.");
        }
    }

    @FXML
    private void onSaveButtonClick() {

    }

    @FXML
    private void onBackButtonClick() {
        Parent root = null;
        FXMLLoader loader;
        try {
            if (currentPlan.getParentPlan() == null) {
                loader = new FXMLLoader(
                        getClass().getResource("/my_plans_view.fxml")
                );
                root = loader.load();
            } else {
                loader = new FXMLLoader(
                        getClass().getResource("/plan_view.fxml")
                );
                root = loader.load();
                PlanController controller = loader.getController();
                controller.setup(currentPlan.getParentPlan());
            }
        } catch (IOException e) {
            e.printStackTrace();
            showFatalPrompt();
        }
        Scene scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }

    public void generateElementsBox() {
        int[][] elementsList = currentPlan.getElementsList();

        if(elementsList != null) {
            for (int i = 0; i < elementsList.length; i++) {
                if (elementsList[i][0] == 1) {
                    addToView(NutriCalcModel.getPlanByID(elementsList[i][1]));
                } else if (elementsList[i][0] == 2) {
                    addToView(NutriCalcModel.getMealsSetByID(elementsList[i][1]));
                }
            }
        }
    }
    public void setup(PlanModel plan) {
        currentPlan = plan;
        planNameLabel.setText(plan.getName());

        generateElementsBox();

        generateSection(macroSection, currentPlan.getMacroAmounts());
        generateSection(mineralsSection, currentPlan.getMineralsAmounts());
        generateSection(vitaminsSection, currentPlan.getVitaminsAmounts());
    }

    private void resetElementsBox() {
        elementsBox.getChildren().clear();
        elementCount = 0;
    }

    public void setSelectedElement(PlanElementModel selectedElement, VBox elementBox) {
        this.selectedElement = selectedElement;
        if (selectedBox != null) {
            selectedBox.getStyleClass().remove("selected-element");
        }

        selectedBox = elementBox;
        selectedBox.getStyleClass().add("selected-element");
    }

    private void addToView(PlanElementModel element) {
        int col = elementCount % COLUMNS;
        int row = elementCount / COLUMNS;

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/plan_element_preview_view.fxml")
            );

            VBox elementBox = loader.load();

            PlanElementPreviewController controller = loader.getController();
            element.setParentPlan(currentPlan);
            controller.setElement(element);
            controller.setParentPlanController(this);

            GridPane.setHgrow(elementBox, Priority.ALWAYS);   //GridPane zezwala dziecku na rozciąganie poziomo
            elementsBox.add(elementBox, col, row);

            elementCount++;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
