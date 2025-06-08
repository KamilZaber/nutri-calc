package com.solutions.sulmurz.nutricalc.controllers;
import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MyIngredientsController extends NutriCalcController {
    private static Scene addIngredientViewScene;
    @FXML
    private ListView<IngredientModel> ingredientsListView;
    @FXML
    private TextField nameTextField;
    @FXML
    private VBox dataSection;
    @FXML
    private VBox macroSection;
    @FXML
    private VBox mineralsSection;
    @FXML
    private VBox vitaminsSection;
    @FXML
    private Button deleteButton;
    @FXML
    private void initialize() {
        addIngredientViewScene = null;
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

    private void generateSection(VBox container, String[] fieldsNames, float[] values) {
        int i = -1;
        for(Node node: container.getChildren()) {
            if (i != -1) {
                ((TextField) node).setText(fieldsNames[i] + ": " + values[i]);
            }
            i++;
        }
        dataSection.setVisible(true);
    }

    @FXML
    private void onListObjectSelected() {
        IngredientModel ingredient = ingredientsListView.getSelectionModel().getSelectedItem();

        if (ingredient != null) {
            nameTextField.setVisible(true);
            nameTextField.setText(ingredient.getName());

            generateSection(macroSection, NutriCalcModel.getMacroSet(), ingredient.getMacroAmounts());
            generateSection(mineralsSection, NutriCalcModel.getMineralsSet(), ingredient.getMineralsAmounts());
            generateSection(vitaminsSection, NutriCalcModel.getVitaminsSet(), ingredient.getVitaminsAmounts());
        }
    }

    @FXML
    private void openAddIngredientView() {
        try {
            if (addIngredientViewScene == null) {
                addIngredientViewScene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("add_ingredient_view.fxml")));
            }
            NutriCalcMain.getPrimaryStage().setScene(addIngredientViewScene);
        }catch (IOException e) {
            showFatalPrompt();
        }
    }

    @FXML
    private void onDeleteClick() {
        IngredientModel ingredient = ingredientsListView.getSelectionModel().getSelectedItem();
        int selectionIndex;
        if(ingredient != null) {
            if(showConfirmationPrompt("Do you really want to delete the ingredient: ", ingredient.getName())) {
                selectionIndex = ingredientsListView.getItems().indexOf(ingredient);
                ingredientsListView.getItems().remove(selectionIndex);
                NutriCalcModel.getIngredientsList().remove(selectionIndex);
                dataSection.setVisible(false);
            }
        } else {
            showPrompt("Select an ingredient to delete.");
        }
    }


}
