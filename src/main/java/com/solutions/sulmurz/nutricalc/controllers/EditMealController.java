package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.NutriCalcFunctions;
import com.solutions.sulmurz.nutricalc.models.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.exceptions.NameOccupiedException;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import com.solutions.sulmurz.nutricalc.models.MealModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

public class EditMealController extends AddMealController {
    private int selectedIndex;
    private String oldName;

    public void setValues(String mealName, int selectedIndex, List<IngredientModel> ingredientsList, float[] ingredientsAmounts) {
        this.selectedIndex = selectedIndex;
        oldName = mealName;
        nameField.setText(oldName);
        ingredientsListView.getItems().addAll(ingredientsList);
        for (float ingredientAmount: ingredientsAmounts) {
            ingredientsAmountsListView.getItems().add(ingredientAmount);
        }
    }

    private void saveMeal(String name) throws IOException {
        String[] ingredientsNamesArray = NutriCalcFunctions.getNamesArray(ingredientsListView.getItems());
        float[] ingredientsAmountsArray = NutriCalcFunctions.getValuesArray(ingredientsAmountsListView.getItems());
        NutriCalcModel.getMealsList().set(selectedIndex, new MealModel(name, ingredientsNamesArray, ingredientsAmountsArray, NutriCalcFunctions.sumUpMacroValues(ingredientsListView.getItems(), ingredientsAmountsArray), NutriCalcFunctions.sumUpVitaminsValues(ingredientsListView.getItems(), ingredientsAmountsArray), NutriCalcFunctions.sumUpMineralsValues(ingredientsListView.getItems(), ingredientsAmountsArray)));
        NutriCalcMain.getPrimaryStage().setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("my_meals_view.fxml"))));
    }

    @FXML
    protected void onSaveButtonClick() {
        String name = nameField.getText();
        try {
            if (name.isEmpty()) {
                throw new InputMismatchException();
            }
            if (!name.equals(oldName)) {
                if (NutriCalcModel.mealNameOccupied(name)) {
                    throw new NameOccupiedException();
                }
            }
            if (showConfirmationPrompt("Are you sure you want to replace these changes for the ingredient:", oldName)) {
                saveMeal(name);
            }
        } catch (InputMismatchException | NumberFormatException e) {
            showPrompt("Enter meal name, choose meal ingredients and amounts of them.");
        } catch (NameOccupiedException noe) {
            showPrompt("Ingredient with provided name already exists in the database.");
        } catch (IOException ioe) {
            showPrompt("Error occured while loading new window.");
        }
    }
}
