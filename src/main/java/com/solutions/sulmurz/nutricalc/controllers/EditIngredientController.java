package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.InputMismatchException;


public class EditIngredientController extends AddIngredientController {
    private IngredientModel selectedIngredient;
    private int selectedIndex;

    public void setSelectedIndex(int index) {
        this.selectedIndex = index;
    }

    public void setSelectedIngredient(IngredientModel ingredient) {
        this.selectedIngredient = ingredient;
    }

    public void setValues() {
        this.nameField.setText(this.selectedIngredient.getName());
        generateSection(macroSection, selectedIngredient.getMacroAmounts());
        generateSection(mineralsSection, selectedIngredient.getMineralsAmounts());
        generateSection(vitaminsSection, selectedIngredient.getVitaminsAmounts());
    }

    @FXML
    @Override
    protected void onSaveButtonClick() {
        String name = nameField.getText();
        try {
            if (!name.isEmpty()) {
                if (showConfirmationPrompt("Are you sure you want to replace these changes for the ingredient:", selectedIngredient.getName())) {
                    NutriCalcModel.getIngredientsList().set(selectedIndex, new IngredientModel(name, getValuesArray(macroSection), getValuesArray(mineralsSection), getValuesArray(vitaminsSection)));
                    NutriCalcMain.getPrimaryStage().setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("my_ingredients_view.fxml"))));
                }
            } else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException | NumberFormatException | IOException e) {
            showPrompt("Enter ingredient name and fill nutritional values fields in correct number format (352, 24.5, 0.51 etc.).");
        }
    }
}
