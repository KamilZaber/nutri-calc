package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;

public class MyIngredientsController extends NutriCalcController {
    @FXML
    private ListView<IngredientModel> ingredientsListView;
    IngredientModel ingredient;
    @FXML
    private Label nameTextField;
    @FXML
    private TextField macroTitle;
    @FXML
    private TextField mineralsTitle;
    @FXML
    private TextField vitaminsTitle;
    @FXML
    private VBox macroAmountsVBox;
    @FXML
    private VBox mineralsAmountsVBox;
    @FXML
    private VBox vitaminsAmountsVBox;

    @FXML
    private void initialize() {
        ingredientsListView.getItems().setAll(NutriCalcModel.getIngredientsList());
        ingredientsListView.setCellFactory(ingredientsListView -> new ListCell<>() {        //nowe komórki o określonym działaniu
            @Override
            protected void updateItem(IngredientModel ingredient, boolean empty) {
                super.updateItem(ingredient, empty);                    //nazywanie komórek...
                if(empty || ingredient == null) {
                    setText(null);
                } else {
                    setText(ingredient.getName());                      //po "nazwie" w obiekcie składnika
                }
            }
        });

    }

    private Label[] createLabelsArray(String[] names, float[] values) {
        Label[] labelsArray = new Label[names.length];
        for(int i = 0;i < values.length;i++) {
            labelsArray[i] = new Label(names[i] + ": " + values[i]);
        }
        return labelsArray;
    }

    @FXML
    private void onMouseClicked() {
        ingredient = ingredientsListView.getSelectionModel().getSelectedItem();

        if (ingredient != null) {
            nameTextField.setVisible(true);
            nameTextField.setText(ingredient.getName());

            macroAmountsVBox.getChildren().clear();
            macroAmountsVBox.getChildren().add(macroTitle);
            macroTitle.setVisible(true);
            macroAmountsVBox.getChildren().addAll(createLabelsArray(NutriCalcModel.getMacroSet(), ingredient.getMacroAmounts()));

            mineralsAmountsVBox.getChildren().clear();
            mineralsAmountsVBox.getChildren().add(mineralsTitle);
            mineralsTitle.setVisible(true);
            mineralsAmountsVBox.getChildren().addAll(createLabelsArray(NutriCalcModel.getMineralsSet(), ingredient.getMineralsAmounts()));

            vitaminsAmountsVBox.getChildren().clear();
            vitaminsAmountsVBox.getChildren().add(vitaminsTitle);
            vitaminsTitle.setVisible(true);
            vitaminsAmountsVBox.getChildren().addAll(createLabelsArray(NutriCalcModel.getVitaminsSet(), ingredient.getVitaminsAmounts()));
        }
    }
}
