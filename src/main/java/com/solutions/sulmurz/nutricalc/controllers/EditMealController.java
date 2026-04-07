package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.exceptions.NameOccupiedException;
import com.solutions.sulmurz.nutricalc.models.MealModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.InputMismatchException;

public class EditMealController extends AddMealController {
    private MealModel selectedMeal;
    private int selectedIndex;
    private String oldName;

    public void setSelectedIndex(int index) {
        this.selectedIndex = index;
    }

    public void setSelectedMeal(MealModel meal) {
        this.selectedMeal = meal;
    }

    public void setValues() {
        for(int i = 0; i < selectedMeal.getMealngredients().length; i++) {
            oldName = selectedMeal.getName();
            nameField.setText(oldName);
            ingredientsAmountsListView.getItems().add(selectedMeal.getIngredientsAmounts()[i]);
            ingredientsListView.getItems().add(NutriCalcModel.getIngredientByName(selectedMeal.getMealngredients()[i]));
        }
    }

    private void saveMeal(String name) throws IOException {
        String[] ingredientsNamesArray = getNamesArray(ingredientsListView.getItems());
        float[] ingredientsAmountsArray = getValuesArray(ingredientsAmountsListView.getItems());
        NutriCalcModel.getMealsList().set(selectedIndex, new MealModel(name, ingredientsNamesArray, ingredientsAmountsArray, sumUpMacroValues(ingredientsListView.getItems(), ingredientsAmountsArray), sumUpVitaminsValues(ingredientsListView.getItems(), ingredientsAmountsArray), sumUpMineralsValues(ingredientsListView.getItems(), ingredientsAmountsArray)));
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
