package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.NutriCalcFunctions;
import com.solutions.sulmurz.nutricalc.models.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import com.solutions.sulmurz.nutricalc.models.MealModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyMealsController extends NutriCalcController {
    private static Scene addMealViewScene ;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button backButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private VBox dataSection;
    @FXML
    private VBox macroSection;
    @FXML
    private VBox mineralsSection;
    @FXML
    private VBox vitaminsSection;
    @FXML
    private ListView<MealModel> mealsListView;
    @FXML
    private VBox ingredientsTab;
    private static boolean changed = false;

    @FXML
    private void initialize() {
        mealsListView.getItems().setAll(NutriCalcModel.getMealsList());
        mealsListView.setCellFactory(mealsListView -> new ListCell<>() {        //nowe komórki o określonym działaniu
            @Override
            protected void updateItem(MealModel meal, boolean empty) {
                super.updateItem(meal, empty);                    //nazywanie komórek...
                if(empty || meal == null) {
                    setText(null);
                } else {
                    setText(meal.getName());                      //po "nazwie" w obiekcie składnika
                }
            }
        });
    }

    @FXML
    private void onListObjectSelected() {
        MealModel meal = mealsListView.getSelectionModel().getSelectedItem();
        if (meal != null) {
            nameTextField.setVisible(true);
            nameTextField.setText(meal.getName());

            generateSection(macroSection, NutriCalcModel.getMacroSet(), meal.getMacroAmounts());
            generateSection(mineralsSection, NutriCalcModel.getMineralsSet(), meal.getMineralsAmounts());
            generateSection(vitaminsSection, NutriCalcModel.getVitaminsSet(), meal.getVitaminsAmounts());

            dataSection.setVisible(true);

            ingredientsTab.getChildren().clear();

            byte x = 0;
            for (String i : meal.getMealngredients()) {
                TextField ingredientTextField = new TextField(i + ": " + meal.getIngredientsAmounts()[x] + "g");
                ingredientTextField.setEditable(false);
                ingredientTextField.setCursor(Cursor.DEFAULT);
                ingredientTextField.setAlignment(Pos.CENTER);
                ingredientsTab.getChildren().add(ingredientTextField);
                x++;
            }
        }
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
    private void onAddClick() {
        try {
            Parent root;
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/add_meal_view.fxml")
            );
            root = loader.load();
            NutriCalcMain.getPrimaryStage().setScene(new Scene(root));
        } catch(IOException e) {
            showFatalPrompt();
        }
    }

    @FXML
    private void onDeleteClick() {
        MealModel meal = mealsListView.getSelectionModel().getSelectedItem();
        int selectionIndex;
        if(meal != null) {
            if(Boolean.TRUE.equals(showConfirmationPrompt("Do you really want to delete the meal: ", meal.getName()))) {
                selectionIndex = mealsListView.getItems().indexOf(meal);
                mealsListView.getItems().remove(selectionIndex);
                NutriCalcModel.getMealsList().remove(selectionIndex);
                dataSection.setVisible(false);
                changed = true;
            }
        } else {
            showPrompt("Select an ingredient to delete.");
        }
    }

    @FXML
    private void onEditClick() {
        MealModel meal = mealsListView.getSelectionModel().getSelectedItem();
        int selectionIndex;
        if(meal != null) {
            selectionIndex = mealsListView.getItems().indexOf(meal);
            try {
                //test czy wszystkie składniki posiłku są w bazie
                List<IngredientModel> testIngredientList = new ArrayList<>();
                float[] testIngredientsAmounts = meal.getIngredientsAmounts().clone();
                IngredientModel testIngredient;
                boolean changedInTest = false;

                for(int i = 0; i < meal.getMealngredients().length; i++) {
                    testIngredient = NutriCalcModel.getIngredientByName(meal.getMealngredients()[i]);
                    if(testIngredient == null) {
                        boolean skipIngredient = showNoIngredientPrompt(meal.getMealngredients()[i]);
                        if(!skipIngredient) {
                            return;
                        } else {
                            changedInTest = true;
                            changed = true;
                            testIngredientsAmounts[i] = 0;
                        }
                    } else {
                        testIngredientList.add(testIngredient);
                    }
                }

                if(changedInTest) {
                    testIngredientsAmounts = NutriCalcFunctions.shortenFloatArray(testIngredientsAmounts);
                }

                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("edit_meal_view.fxml"));
                Parent root = loader.load();
                EditMealController controller = loader.getController();
                controller.setValues(meal.getName(), selectionIndex, testIngredientList, testIngredientsAmounts);
                NutriCalcMain.getPrimaryStage().setScene(new Scene(root));
            } catch (IOException e) {
                showFatalPrompt();
            }
        } else {
            showPrompt("Select a meal to edit.");
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

    public void setChanged(boolean changed) {
        MyMealsController.changed = changed;
    }

    public static boolean isChanged() {
        return changed;
    }

    private boolean showNoIngredientPrompt(String name) {
        NoIngredientPromptController controller;
        VBox root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/no_ingredient_prompt_view.fxml"));
        try {
            root = loader.load();
        } catch(IOException e) {
            showFatalPrompt();
            Platform.exit();
        }
        controller = loader.getController();
        controller.setIngredientName(name);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("NutriCalc Prompt");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        return controller.skipIngredient();
    }
}
