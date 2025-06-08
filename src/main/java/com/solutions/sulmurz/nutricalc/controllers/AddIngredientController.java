package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    protected float[] getValuesArray(VBox section) throws InputMismatchException, NumberFormatException {
        ObservableList<Node> fieldsList = section.getChildren();
        float[] valuesArray = new float[fieldsList.size()];
        int i = 0;
        for (Node field : fieldsList) {
            if (!((TextField) field).getText().isEmpty()) {
                valuesArray[i] = Float.parseFloat(((TextField) field).getText());
            } else {
                throw new InputMismatchException();
            }
            i++;
        }
        return valuesArray;
    }

    @FXML
    protected void onSaveButtonClick() {
        String name = nameField.getText();
        try {
            if (!name.isEmpty()) {
                if (!NutriCalcModel.nameOccupied(name)) {
                    NutriCalcModel.getIngredientsList().add(new IngredientModel(name, getValuesArray(macroSection), getValuesArray(mineralsSection), getValuesArray(vitaminsSection)));
                    NutriCalcMain.getPrimaryStage().setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("my_ingredients_view.fxml"))));
                } else {
                    throw new IOException();
                }
            } else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException | NumberFormatException e) {
            showPrompt("Enter ingredient name and fill nutritional values fields in correct number format (352, 24.5, 0.51 etc.).");
        } catch (IOException e2) {
            showPrompt("Ingredient with provided name already exists in the database.");
        }
    }
}