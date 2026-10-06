package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.exceptions.NameOccupiedException;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.InputMismatchException;

public class AddIngredientController extends NutriCalcController {
    @FXML
    protected TextField nameField;
    @FXML
    protected Button saveButton;
    @FXML
    protected VBox macroSection;
    @FXML
    protected VBox mineralsSection;
    @FXML
    protected VBox vitaminsSection;
    @FXML
    private Button backButton;
    protected boolean changed = false;

    @FXML
    protected void onSaveButtonClick() {
        if(changed) {
            saveIngredient();
        } else {
            showPrompt("Nothing to save.");
        }
    }

    @FXML
    protected void onBackButtonClick() {
        if(changed) {
            Boolean confirmed = showConfirmationPrompt("Do you want to save this ingredient?","");
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
    private void change() {
        changed = true;
    }

    private void saveIngredient() {
        String name = nameField.getText();
        try {
            if (!name.isEmpty()) {
                if (!NutriCalcModel.ingredientNameOccupied(name)) {
                    NutriCalcModel.getIngredientsList().add(new IngredientModel(name, getSectionValuesArray(macroSection), getSectionValuesArray(vitaminsSection), getSectionValuesArray(mineralsSection)));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/my_ingredients_view.fxml"));
                    Parent root = loader.load();
                    MyIngredientsController controller = loader.getController();
                    controller.setChanged(true);
                    NutriCalcMain.getPrimaryStage().setScene(new Scene(root));
                } else {
                    throw new NameOccupiedException();
                }
            } else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException | NumberFormatException e) {
            showPrompt("Enter ingredient name and fill nutritional values fields in correct number format (352, 24.5, 0.51 etc.).");
        } catch (NameOccupiedException noe) {
            showPrompt("Ingredient with provided name already exists in the database.");
        } catch (IOException ioe) {
            showPrompt("Error occured while loading new window.");
        }
    }
}