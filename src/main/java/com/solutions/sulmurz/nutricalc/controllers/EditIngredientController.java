package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.exceptions.NameOccupiedException;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.InputMismatchException;

public class EditIngredientController extends AddIngredientController {
    private IngredientModel selectedIngredient;
    private int selectedIndex;
    private String oldName;
    @FXML
    private Button backButton;

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

    @Override
    @FXML
    protected void onBackButtonClick() {
        if(changed) {
            Boolean confirmed = showConfirmationPrompt("Do you want to save the changes for ingredient:", oldName);
            if(Boolean.TRUE.equals(confirmed)) {
                saveIngredient();
            } else if(Boolean.FALSE.equals(confirmed)) {
                openMyIngredientsView();
            }
        } else {
            openMyIngredientsView();
        }
    }

    @FXML
    @Override
    protected void onSaveButtonClick() {
        if(changed) {
            if(Boolean.TRUE.equals(showConfirmationPrompt("Are you sure you want to replace these changes for the ingredient:", oldName))) {
                saveIngredient();
            }
        } else {
            showPrompt("Nothing to save.");
        }
    }

    private void saveIngredient() {
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
            Parent root;
            NutriCalcModel.getIngredientsList().set(selectedIndex, new IngredientModel(name, getSectionValuesArray(macroSection), getSectionValuesArray(vitaminsSection), getSectionValuesArray(mineralsSection)));
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/my_ingredients_view.fxml"));
                root = loader.load();
                MyIngredientsController controller = loader.getController();
                controller.setChanged(true);
                NutriCalcMain.getPrimaryStage().setScene(new Scene(root));
            } catch (IOException ioe) {
                ioe.printStackTrace();
                showFatalPrompt();
            }
        } catch (InputMismatchException | NumberFormatException ie) {
            showPrompt("Enter ingredient name and fill nutritional values fields in correct number format (352, 24.5, 0.51 etc.).");
        } catch (NameOccupiedException noe) {
            showPrompt("Ingredient with provided name already exists in the database.");
        }
    }

    @Override
    protected void generateSection(VBox container, float[] values) {
        int i = 0;
        for(Node node: container.getChildren()) {
            ((TextField) node).setText(Float.toString(values[i]));
            i++;
        }
    }

    @FXML
    public void change() {
        changed = true;
    }
}
