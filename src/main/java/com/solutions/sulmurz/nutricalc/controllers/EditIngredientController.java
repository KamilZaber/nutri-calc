package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.exceptions.NameOccupiedException;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.InputMismatchException;


public class EditIngredientController extends AddIngredientController {
    private IngredientModel selectedIngredient;
    private int selectedIndex;
    private String oldName;

    public void setSelectedIndex(int index) {
        this.selectedIndex = index;
    }

    public void setSelectedIngredient(IngredientModel ingredient) {
        this.selectedIngredient = ingredient;
    }

    public void setValues() {
        oldName = selectedIngredient.getName();
        nameField.setText(oldName);
        generateSection(macroSection, selectedIngredient.getMacroAmounts());
        generateSection(mineralsSection, selectedIngredient.getMineralsAmounts());
        generateSection(vitaminsSection, selectedIngredient.getVitaminsAmounts());
    }

    private void saveIngredient(String name) throws IOException {
        NutriCalcModel.getIngredientsList().set(selectedIndex, new IngredientModel(name, getSectionValuesArray(macroSection), getSectionValuesArray(mineralsSection), getSectionValuesArray(vitaminsSection)));
        NutriCalcMain.getPrimaryStage().setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("my_ingredients_view.fxml"))));
    }

    @FXML
    @Override
    protected void onSaveButtonClick() {
        String name = nameField.getText();
        try {
            if(name.isEmpty()) {
                throw new InputMismatchException();
            }
            if(!name.equals(oldName)) {
                if(NutriCalcModel.ingredientNameOccupied(name)) {
                    throw new NameOccupiedException();
                }
            }
            if (showConfirmationPrompt("Are you sure you want to replace these changes for the ingredient:", oldName)) {
                saveIngredient(name);
            }
        } catch (InputMismatchException | NumberFormatException | IOException e) {
            showPrompt("Enter ingredient name and fill nutritional values fields in correct number format (352, 24.5, 0.51 etc.).");
        } catch (NameOccupiedException noe) {
            showPrompt("Ingredient with provided name already exists in the database.");
        }
    }
}
