package com.solutions.sulmurz.nutricalc.controllers;
import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MyIngredientsController extends NutriCalcController {
    @FXML
    private ListView<IngredientModel> ingredientsListView;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button backButton;
    @FXML
    private VBox dataSection;
    @FXML
    private VBox macroSection;
    @FXML
    private VBox mineralsSection;
    @FXML
    private VBox vitaminsSection;
    private static boolean changed = false;

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

    @FXML
    private void onBackButtonClick() {
        if(changed) {
            Boolean confirmed = showConfirmationPrompt("Do you want to save changes?", "");
            if(Boolean.TRUE.equals(confirmed)) {
                showPrompt("Changes saved");
                changed = false;
            } else if(Boolean.FALSE.equals(confirmed)) {
                NutriCalcModel.restoreDatabases();
                changed = false;
            } else {
                return;
            }
        }
        openMainMenuView();
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

            dataSection.setVisible(true);
        }
    }

    @FXML
    private void onAddClick() {
        try {
            Parent root;
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/add_ingredient_view.fxml")
            );
            root = loader.load();
            NutriCalcMain.getPrimaryStage().setScene(new Scene(root));
        } catch (IOException e) {
            showFatalPrompt();
        }
    }

    @FXML
    private void onDeleteClick() {
        IngredientModel ingredient = ingredientsListView.getSelectionModel().getSelectedItem();
        if(ingredient != null) {
            if(Boolean.TRUE.equals(showConfirmationPrompt("Do you really want to delete the ingredient: ", ingredient.getName()))) {
                ingredientsListView.getItems().remove(ingredient);
                NutriCalcModel.getIngredientsList().remove(ingredient);
                dataSection.setVisible(false);
                changed = true;
            }
        } else {
            showPrompt("Select an ingredient to delete.");
        }
    }

    @FXML
    private void onEditClick() {
        IngredientModel ingredient = ingredientsListView.getSelectionModel().getSelectedItem();
        int selectionIndex;
        if(ingredient != null) {
            selectionIndex = ingredientsListView.getItems().indexOf(ingredient);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edit_ingredient_view.fxml"));
                    Parent root = loader.load();
                    EditIngredientController controller = loader.getController();
                    controller.setSelectedIngredient(ingredient);
                    controller.setSelectedIndex(selectionIndex);
                    controller.setValues();
                    NutriCalcMain.getPrimaryStage().setScene(new Scene(root));
                } catch (IOException e) {
                    showFatalPrompt();
                }
        } else {
            showPrompt("Select an ingredient to edit.");
        }
    }

    @FXML
    private void onSaveClick() {
        if(changed) {
            if(Boolean.TRUE.equals(showConfirmationPrompt("Do you really want to save changes?", ""))) {
                changed = false;
                showPrompt("Changes saved");
            }
        } else {
            showPrompt("Nothing to save.");
        }
    }

    public static boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        MyIngredientsController.changed = changed;
    }
}
