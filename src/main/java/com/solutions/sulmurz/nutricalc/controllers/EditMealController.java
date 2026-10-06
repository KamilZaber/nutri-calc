package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.NutriCalcFunctions;
import com.solutions.sulmurz.nutricalc.models.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.exceptions.NameOccupiedException;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import com.solutions.sulmurz.nutricalc.models.MealModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

public class EditMealController extends AddMealController {
    private int selectedIndex;
    private String oldName;
    @FXML
    private Button backButton;

    public void setValues(String mealName, int selectedIndex, List<IngredientModel> ingredientsList, float[] ingredientsAmounts) {
        this.selectedIndex = selectedIndex;
        oldName = mealName;
        nameField.setText(oldName);
        ingredientsListView.getItems().addAll(ingredientsList);
        for (float ingredientAmount: ingredientsAmounts) {
            ingredientsAmountsListView.getItems().add(ingredientAmount);
        }
    }

    private void saveMeal() {
        try {
            String[] ingredientsNamesArray = NutriCalcFunctions.getNamesArray(ingredientsListView.getItems());
            float[] ingredientsAmountsArray = NutriCalcFunctions.getValuesArray(ingredientsAmountsListView.getItems());
            NutriCalcModel.getMealsList().set(selectedIndex, new MealModel(nameField.getText(), ingredientsNamesArray, ingredientsAmountsArray, MealModel.sumUpMacroValues(ingredientsListView.getItems(), ingredientsAmountsArray), MealModel.sumUpVitaminsValues(ingredientsListView.getItems(), ingredientsAmountsArray), MealModel.sumUpMineralsValues(ingredientsListView.getItems(), ingredientsAmountsArray)));
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("my_meals_view.fxml"));
            Parent root = loader.load();
            MyMealsController controller = loader.getController();
            controller.setChanged(true);
            NutriCalcMain.getPrimaryStage().setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showPrompt("Error occured while loading new window.");
        }
    }

    @Override
    @FXML
    protected void onBackButtonClick() {
        if(changed) {
            Boolean confirmed = showConfirmationPrompt("Do you want to save the changes for meal:", oldName);
            if(Boolean.TRUE.equals(confirmed)) {
                saveMeal();
            } else if(Boolean.FALSE.equals(confirmed)) {
                openMyMealsView();
            }
        } else {
            openMyMealsView();
        }
    }

    @FXML
    protected void onSaveButtonClick() {
        if(changed) {
            String name = nameField.getText();
            try {
                if(name.isEmpty()) {
                    throw new InputMismatchException();
                }
                if(!name.equals(oldName)) {
                    if (NutriCalcModel.mealNameOccupied(name)) {
                        throw new NameOccupiedException();
                    }
                }
                if(Boolean.TRUE.equals(showConfirmationPrompt("Are you sure you want to replace these changes for the meal:", oldName))) {
                    saveMeal();
                }
            } catch (InputMismatchException | NumberFormatException e) {
                showPrompt("Enter meal name, choose meal ingredients and amounts of them.");
            } catch (NameOccupiedException noe) {
                showPrompt("Ingredient with provided name already exists in the database.");
            }
        } else {
            showPrompt("Nothing to save.");
        }
    }
}
