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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
    private PlanModel currentOldPlan;
    private PlanElementModel selectedElement;
    private final ArrayList<PlanElementModel> addedElements = new ArrayList<>();
    private final ArrayList<PlanElementModel> deletedElements = new ArrayList<>();
    private VBox selectedBox;
    private int elementCount = 0;
    private final int COLUMNS = 4;
    private boolean changed = false;

    @FXML
    public void onAddNewPlan() {
        changed = true;
        PlanElementBasicData planData = showAddNewElementForm("Add New Plan");
        if(planData != null) {
            PlanModel newPlan = new PlanModel(1, planData.getName(), planData.getDescription());
            NutriCalcModel.getPlansList().add(newPlan);
            addedElements.add(newPlan);
            currentPlan.addElement(newPlan);
            resetElementsBox();
            generateData();

            changed = true;
        }
    }

    @FXML
    public void onAddExistingPlan() {
        PlanElementModel planToAdd = showChoosePlanElementWindow(NutriCalcModel.getPlansList());
        if(planToAdd != null) {
            PlanModel newPlan = new PlanModel((PlanModel) planToAdd);
            NutriCalcModel.getPlansList().add(newPlan);
            addedElements.add(newPlan);
            currentPlan.addElement(newPlan);
            currentPlan.changeNutritionalValues("PLUS", 1, newPlan.getMacroAmounts(), newPlan.getMineralsAmounts(), newPlan.getVitaminsAmounts());
            resetElementsBox();
            generateData();

            changed = true;
        }
    }

    @FXML
    public void onAddNewMealSet() {
        PlanElementBasicData mealsSetData = showAddNewElementForm("Add New Meals Set");
        if(mealsSetData != null) {
            MealsSetModel newMealsSet = new MealsSetModel(2, mealsSetData.getName(), mealsSetData.getDescription());
            NutriCalcModel.getMealsSetsList().add(newMealsSet);
            addedElements.add(newMealsSet);
            currentPlan.addElement(newMealsSet);
            resetElementsBox();
            generateData();

            changed = true;
        }
    }

    @FXML
    public void onAddExistingMealSet() {
        PlanElementModel mealsSetToAdd = showChoosePlanElementWindow(NutriCalcModel.getMealsSetsList());
        if(mealsSetToAdd != null) {
            MealsSetModel newMealsSet = new MealsSetModel((MealsSetModel) mealsSetToAdd);
            NutriCalcModel.getMealsSetsList().add(newMealsSet);
            addedElements.add(newMealsSet);
            currentPlan.addElement(newMealsSet);
            currentPlan.changeNutritionalValues("PLUS", 1, newMealsSet.getMacroAmounts(), newMealsSet.getMineralsAmounts(), newMealsSet.getVitaminsAmounts());
            resetElementsBox();
            generateData();

            changed = true;
        }
    }

    @FXML
    private void onDeleteButtonClick() {
        if(selectedElement != null) {
            if(Boolean.TRUE.equals(showConfirmationPrompt("Do you really want to delete element", selectedElement.getName()))) {
                if(selectedElement instanceof PlanModel) {
                    ArrayList<PlanElementModel> planElementsToDelete = NutriCalcFunctions.traverseElements((PlanModel) selectedElement);
                    elementsBox.getChildren().remove(selectedBox);
                    currentPlan.deleteElement(selectedElement);
                    for (PlanElementModel element: planElementsToDelete) {
                        if(element instanceof PlanModel) {
                            deletedElements.add(element);
                            NutriCalcModel.getPlansList().remove(element);
                        } else if(element instanceof MealsSetModel) {
                            deletedElements.add(element);
                            NutriCalcModel.getMealsSetsList().remove(element);
                        }
                    }
                } else if(selectedElement instanceof MealsSetModel) {
                    elementsBox.getChildren().remove(selectedBox);
                    currentPlan.deleteElement(selectedElement);
                    deletedElements.add(selectedElement);
                    NutriCalcModel.getMealsSetsList().remove((MealsSetModel) selectedElement);
                }

                currentPlan.changeNutritionalValues("MINUS", 1, selectedElement.getMacroAmounts(), selectedElement.getMineralsAmounts(), selectedElement.getVitaminsAmounts());
                resetElementsBox();
                generateData();

                changed = true;
            }
        } else {
            showPrompt("Select a plan to delete.");
        }
    }

    @FXML
    private void onOpenButtonClick() {
        if(selectedBox != null) {
            if(selectedElement != null) {
                if(changed) {
                    Boolean confirmed = showConfirmationPrompt("Do you want to save changes?", "");
                    if(Boolean.TRUE.equals(confirmed)) {
                        savePlanData();
                    } else if (Boolean.FALSE.equals(confirmed)){
                        undoPlanChanges();
                    } else {
                        return;
                    }
                }


                try {
                    Parent root;
                    if (selectedElement instanceof PlanModel && NutriCalcModel.getPlansList().contains(selectedElement)) {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/plan_view.fxml")
                        );
                        root = loader.load();
                        PlanController controller = loader.getController();
                        Scene scene = new Scene(root);
                        NutriCalcMain.getPrimaryStage().setScene(scene);
                        controller.setup((PlanModel) selectedElement);
                    } else if (selectedElement instanceof MealsSetModel && NutriCalcModel.getMealsSetsList().contains(selectedElement)) {
                        FXMLLoader loader = new FXMLLoader(
                                getClass().getResource("/meals_set_view.fxml")
                        );
                        root = loader.load();
                        MealsSetController controller = loader.getController();
                        Scene scene = new Scene(root);
                        NutriCalcMain.getPrimaryStage().setScene(scene);
                        controller.setup((MealsSetModel) selectedElement);
                    } else {
                        showPrompt("Unable to open such element.");
                        resetElementsBox();
                        setup(currentOldPlan);
                    }
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                    showFatalPrompt();
                    resetElementsBox();
                    setup(currentOldPlan);
                }
            } else {
                showPrompt("Unable to open such element.");
                resetElementsBox();
                setup(currentOldPlan);
            }
        } else {
            showPrompt("Select a plan element to open.");
        }
    }

    @FXML
    private void onCopyButtonClick() {
        if(selectedElement != null) {
            if(Boolean.TRUE.equals(showConfirmationPrompt("Do you want to copy element ", selectedElement.getName()))) {
                if(selectedElement instanceof PlanModel) {
                    PlanModel newPlan = new PlanModel((PlanModel) selectedElement);
                    NutriCalcModel.getPlansList().add(newPlan);
                    addedElements.add(newPlan);
                    currentPlan.addElement(newPlan);
                } else if(selectedElement instanceof MealsSetModel) {
                    MealsSetModel newMealsSet = new MealsSetModel((MealsSetModel) selectedElement);
                    NutriCalcModel.getMealsSetsList().add(newMealsSet);
                    addedElements.add(newMealsSet);
                    currentPlan.addElement(newMealsSet);
                }
                currentPlan.changeNutritionalValues("PLUS", 1, selectedElement.getMacroAmounts(), selectedElement.getMineralsAmounts(), selectedElement.getVitaminsAmounts());
                resetElementsBox();
                generateData();
                changed = true;
            }
        } else {
            showPrompt("Select a plan to copy.");
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
        NutriCalcModel.backupDatabases();
        currentOldPlan = new PlanModel(currentPlan.getID(), currentPlan.getType(), currentPlan.getName(), currentPlan.getDescription(), currentPlan.getMacroAmounts(), currentPlan.getMineralsAmounts(), currentPlan.getVitaminsAmounts(), currentPlan.getElementsList());
        planNameLabel.setText(plan.getName());
        changed = false;

        generateData();
    }

    private void generateData() {
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

    private void savePlanData() {
        if(!addedElements.isEmpty()) {
            for (PlanElementModel elementToAdd : addedElements) {
                currentPlan.recalculateParents("PLUS", elementToAdd.getMacroAmounts(), elementToAdd.getMineralsAmounts(), elementToAdd.getVitaminsAmounts());
            }
        }

        if(!deletedElements.isEmpty()) {
            for (PlanElementModel elementToDelete: deletedElements) {
                currentPlan.recalculateParents("MINUS", elementToDelete.getMacroAmounts(), elementToDelete.getMineralsAmounts(), elementToDelete.getVitaminsAmounts());
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

    private void undoPlanChanges() {
//        if(!deletedElements.isEmpty()) {
//            for (PlanElementModel element : deletedElements) {
//                if (element instanceof PlanModel) {
//                    NutriCalcModel.getPlansList().add((PlanModel) element);
//                } else if (element instanceof MealsSetModel) {
//                    NutriCalcModel.getMealsSetsList().add((MealsSetModel) element);
//                }
//            }
//        }
//
//        if(!addedElements.isEmpty()) {
//            for (PlanElementModel element : addedElements) {
//                if (element instanceof PlanModel) {
//                    NutriCalcModel.getPlansList().remove((PlanModel) element);
//                    NutriCalcModel.getPlansIDs().freeID(element.getID());
//
//                } else if (element instanceof MealsSetModel) {
//                    NutriCalcModel.getMealsSetsList().remove((MealsSetModel) element);
//                    NutriCalcModel.getMealsSetsIDs().freeID(element.getID());
//                }
//            }
//        }
        if(!addedElements.isEmpty()) {
            for (PlanElementModel addedElement: addedElements) {
                if(addedElement instanceof PlanModel) {
                    NutriCalcModel.getPlansIDs().freeID(addedElement.getID());
                } else if(addedElement instanceof MealsSetModel) {
                    NutriCalcModel.getMealsSetsIDs().freeID(addedElement.getID());
                }
            }
        }

        NutriCalcModel.restoreDatabases();
        currentPlan.replacePlanData(currentOldPlan);
    }
}
