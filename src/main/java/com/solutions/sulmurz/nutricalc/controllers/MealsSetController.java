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
    private IngredientModel selectedElement;
    private Label selectedElementLabel;

    @FXML
    private void onBackButtonClick() {
        Parent root = null;
        FXMLLoader loader;
        try {
            if (currentMealsSet.getParentPlan() == null) {
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

    }

    @FXML
    private void onAddMealClick() {

    }

    @FXML
    private void onDeleteButtonClick() {

    }

    @FXML
    private void onSaveButtonClick() {

    }

    public void setup(MealsSetModel mealsSet) {
        currentMealsSet = mealsSet;
        mealsSetNameLabel.setText(currentMealsSet.getName());
        Label mealsSetElement;
        MealsSetElementController elementControlller;
        int i = 0;

        for(String[] element: mealsSet.getElementsList()) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/meals_set_element_view.fxml")
                );

                mealsSetElement = loader.load();

                elementControlller = loader.getController();
                elementControlller.setParentMealsSetController(this);
                if(element[0].equals("meal")) {
                    elementControlller.setElement(NutriCalcModel.getMealByName(element[1]), mealsSet.getElementsAmounts()[i]);
                } else if(element[0].equals("ingredient")) {
                    elementControlller.setElement(NutriCalcModel.getIngredientByName(element[1]), mealsSet.getElementsAmounts()[i]);
                } else {
                    showFatalPrompt();
                    return;
                }

                mealsSetElementsList.getChildren().add(mealsSetElement);
                i++;
            } catch (IOException e) {
                e.printStackTrace();
                showFatalPrompt();
            }
        }

        generateSection(macroSection, currentMealsSet.getMacroAmounts());
        generateSection(mineralsSection, currentMealsSet.getMineralsAmounts());
        generateSection(vitaminsSection, currentMealsSet.getVitaminsAmounts());
    }

    public void setSelectedElement(IngredientModel element, Label elementLabel) {
        this.selectedElement = element;

        if (selectedElementLabel != null) {
            selectedElementLabel.getStyleClass().remove("selected-meals-set-element");
        }

        this.selectedElementLabel = elementLabel;
        selectedElementLabel.getStyleClass().add("selected-meals-set-element");
    }
}
