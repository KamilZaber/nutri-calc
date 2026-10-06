package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class MealsSetController extends NutriCalcController {
    @FXML
    private Label mealsSetNameLabel;
    @FXML
    private VBox mealsSetElementsList;
    @FXML
    private VBox macroSection;
    @FXML
    private VBox vitaminsSection;
    @FXML
    private VBox mineralsSection;
    @FXML
    private Button backButton;
    @FXML
    private MenuButton addButton;
    @FXML
    private MenuItem addIngredientButton;
    @FXML
    private MenuItem addMealButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    private MealsSetModel currentMealsSet;
    private MealsSetModel currentOldMealsSet;
    private IngredientModel selectedElement;
    private MealsSetElementController selectedController;
    private Label selectedElementLabel;
    private final ArrayList<MealsSetElementBasicData> addedIngredients = new ArrayList<>();
    private final ArrayList<MealsSetElementBasicData> deletedIngredients  = new ArrayList<>();
    boolean changed = false;

    @FXML
    private void onBackButtonClick() {
        if(changed) {
            Boolean confirmed = showConfirmationPrompt("Do you want to save changes?", "");
            if(Boolean.TRUE.equals(confirmed)) {
                saveMealsSetData();
            } else if(Boolean.FALSE.equals(confirmed)) {
                undoMealsSetChanges();
            } else {
                return;
            }
        }

        Parent root = null;
        FXMLLoader loader;
        try {
            if(currentMealsSet.getParentPlan() == null) {
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
                controller.setup(currentMealsSet.getParentPlan());
            }
        } catch (IOException e) {
            e.printStackTrace();
            showFatalPrompt();
        }
        Scene scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }

    @FXML
    private void onAddIngredientClick() {
        MealsSetElementBasicData elementToAddInfo = showChooseMealsSetElementWindow(NutriCalcModel.getIngredientsList());
        if(elementToAddInfo != null) {
            addedIngredients.add(elementToAddInfo);
            currentMealsSet.addNewElement(elementToAddInfo.getElement(), elementToAddInfo.getAmount());
            currentMealsSet.changeNutritionalValues("PLUS", elementToAddInfo.getAmount(), elementToAddInfo.getElement().getMacroAmounts(), elementToAddInfo.getElement().getMineralsAmounts(), elementToAddInfo.getElement().getVitaminsAmounts());
            addToView(elementToAddInfo.getElement(), elementToAddInfo.getAmount());
            generateNutritionalValuesSections();

            changed = true;
        }
    }

    @FXML
    private void onAddMealClick() {
        MealsSetElementBasicData elementToAddInfo = showChooseMealsSetElementWindow(NutriCalcModel.getMealsList());
        if(elementToAddInfo != null) {
            addedIngredients.add(elementToAddInfo);
            currentMealsSet.addNewElement(elementToAddInfo.getElement(), elementToAddInfo.getAmount());
            currentMealsSet.changeNutritionalValues("PLUS", elementToAddInfo.getAmount(), elementToAddInfo.getElement().getMacroAmounts(), elementToAddInfo.getElement().getMineralsAmounts(), elementToAddInfo.getElement().getVitaminsAmounts());
            addToView(elementToAddInfo.getElement(), elementToAddInfo.getAmount());
            generateNutritionalValuesSections();

            changed = true;
        }
    }

    @FXML
    private void onDeleteButtonClick() {
        if(selectedElement != null) {
            if(Boolean.TRUE.equals(showConfirmationPrompt("Do you want to delete element", selectedElement.getName()))) {
                deletedIngredients.add(new MealsSetElementBasicData(selectedElement, selectedController.getAmount()));
                mealsSetElementsList.getChildren().remove(selectedElementLabel);
                currentMealsSet.deleteElement(selectedElement);

                if(selectedElement instanceof MealModel) {
                    currentMealsSet.changeNutritionalValues("MINUS", selectedController.getAmount(), selectedElement.getMacroAmounts(), selectedElement.getMineralsAmounts(), selectedElement.getVitaminsAmounts());

                } else {
                    currentMealsSet.changeNutritionalValues("MINUS", selectedController.getAmount()/100, selectedElement.getMacroAmounts(), selectedElement.getMineralsAmounts(), selectedElement.getVitaminsAmounts());

                }

                generateNutritionalValuesSections();

                changed = true;
            }
        } else {
            showPrompt("Choose an element to delete.");
        }
    }

    @FXML
    private void onSaveButtonClick() {
        if(changed) {
            saveMealsSetData();
        } else {
            showPrompt("Nothing to save.");
        }
    }

    private void generateNutritionalValuesSections() {
        generateSection(macroSection, currentMealsSet.getMacroAmounts());
        generateSection(mineralsSection, currentMealsSet.getMineralsAmounts());
        generateSection(vitaminsSection, currentMealsSet.getVitaminsAmounts());
    }

    public void setup(MealsSetModel mealsSet) {
        currentMealsSet = mealsSet;
        mealsSetNameLabel.setText(currentMealsSet.getName());

        if(mealsSet.getElementsList() != null) {
            int i = 0;
            IngredientModel elementToAdd;
            for (String[] element : mealsSet.getElementsList()) {
                if (element[0].equals("meal")) {
                    elementToAdd = NutriCalcModel.getMealByName(element[1]);
                } else if (element[0].equals("ingredient")) {
                    elementToAdd = NutriCalcModel.getIngredientByName(element[1]);
                } else {
                    showFatalPrompt();
                    return;
                }
                addToView(elementToAdd, currentMealsSet.getElementsAmounts()[i]);
                i++;
            }

            currentOldMealsSet = new MealsSetModel(currentMealsSet.getID(), currentMealsSet.getType(), currentMealsSet.getName(), currentMealsSet.getDescription(), currentMealsSet.getMacroAmounts(), currentMealsSet.getMineralsAmounts(), currentMealsSet.getVitaminsAmounts(), currentMealsSet.getElementsList(), currentMealsSet.getElementsAmounts());
            generateNutritionalValuesSections();
        }
    }

    private void addToView(IngredientModel element, float amount) {
        Label mealsSetElement;
        MealsSetElementController elementControlller;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/meals_set_element_view.fxml")
            );

            mealsSetElement = loader.load();

            elementControlller = loader.getController();
            elementControlller.setParentMealsSetController(this);
            elementControlller.setElement(element, amount);

            mealsSetElementsList.getChildren().add(mealsSetElement);
        } catch (IOException e) {
            e.printStackTrace();
            showFatalPrompt();
        }
    }

    public void setSelectedElement(IngredientModel element, Label elementLabel) {
        this.selectedElement = element;

        if (selectedElementLabel != null) {
            selectedElementLabel.getStyleClass().remove("selected-meals-set-element");
        }

        this.selectedElementLabel = elementLabel;
        selectedElementLabel.getStyleClass().add("selected-meals-set-element");
    }

    public void setSelectedController(MealsSetElementController selectedController) {
        this.selectedController = selectedController;
    }

    private void saveMealsSetData() {
        for(MealsSetElementBasicData ingredient: addedIngredients) {
            currentMealsSet.recalculateParents("PLUS", ingredient.getElement(), ingredient.getAmount());
        }

        for(MealsSetElementBasicData ingredient: deletedIngredients) {
            currentMealsSet.recalculateParents("MINUS", ingredient.getElement(), ingredient.getAmount());
        }
    }

    private void undoMealsSetChanges() {
        currentMealsSet.replaceMealsSetData(currentOldMealsSet);
    }
}
