package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.models.NutriCalcFunctions;
import com.solutions.sulmurz.nutricalc.models.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.exceptions.NameOccupiedException;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import com.solutions.sulmurz.nutricalc.models.MealModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.InputMismatchException;

public class AddMealController extends NutriCalcController {
    @FXML
    protected ListView<IngredientModel> allIngredientsListView;
    @FXML
    protected ListView<IngredientModel> ingredientsListView;
    @FXML
    protected ListView<Float> ingredientsAmountsListView;
    @FXML
    protected TextField gramsTextField;
    @FXML
    protected Button addIngredientButton;
    @FXML
    protected Button deleteIngredientButton;
    @FXML
    protected Button saveButton;
    @FXML
    protected Button backButton;
    @FXML
    protected TextField nameField;
    @FXML
    protected void initialize() {
        allIngredientsListView.getItems().setAll(NutriCalcModel.getIngredientsList());
        allIngredientsListView.setCellFactory(allIngredientsListView -> new ListCell<>() {        //nowe komórki o określonym działaniu
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
        ingredientsListView.setCellFactory(ingredientsListView -> new ListCell<>() {
            @Override
            protected void updateItem(IngredientModel ingredient, boolean empty) {
                super.updateItem(ingredient, empty);
                if(empty || ingredient == null) {
                    setText(null);
                } else {
                    setText(ingredient.getName());
                }
            }
        });

        ingredientsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null) {
                allIngredientsListView.getSelectionModel().select(newSelection);
                allIngredientsListView.scrollTo(newSelection);
            }
        });
    }

    @FXML
    protected void onAddIngredientButtonClick() {
        Float grams;
        try {
            if(allIngredientsListView.getSelectionModel().getSelectedItem() != null) {
                if (!gramsTextField.getText().isEmpty()) {
                    grams = Float.parseFloat(gramsTextField.getText());
                    if (grams > 0) {
                        int indexIn = allIngredientsListView.getSelectionModel().getSelectedIndex();
                        int indexOut = ingredientsListView.getItems().indexOf(allIngredientsListView.getItems().get(indexIn));
                        if (indexOut != -1) {
                            ingredientsAmountsListView.getItems().set(indexOut,ingredientsAmountsListView.getItems().get(indexOut) + grams);
                        } else {
                        ingredientsAmountsListView.getItems().add(grams);
                        ingredientsListView.getItems().add(allIngredientsListView.getItems().get(indexIn));
                        }
                    } else throw new NumberFormatException();
                } else throw new InputMismatchException();
            } else throw new InputMismatchException();
        } catch(NumberFormatException e) {
            showPrompt("Ingredient amount has to be a number bigger than 0.");
        } catch(InputMismatchException e) {
            showPrompt("Select ingredient and enter its amount in grams.");
        }
    }

    @FXML
    protected void onDeleteIngredientButtonClick() {
        int selectedIndex = ingredientsListView.getSelectionModel().getSelectedIndex();
        float amountToAdd;
        float amount;
        if (selectedIndex != -1) {
            try {
                amountToAdd = Float.parseFloat(gramsTextField.getText());
                amount = ingredientsAmountsListView.getItems().get(selectedIndex);
                if (amountToAdd > 0 && amountToAdd < amount) {
                    ingredientsAmountsListView.getItems().set(selectedIndex, (amount - amountToAdd));

                } else {
                    if (showConfirmationPrompt("Do you really want to delete ingredient ", ingredientsListView.getItems().get(selectedIndex).getName())) {
                        ingredientsListView.getItems().remove(selectedIndex);
                        ingredientsAmountsListView.getItems().remove(selectedIndex);
                    }
                }
            } catch (NumberFormatException e) {
                showPrompt("Enter amount of grams to delete (0 for entire ingredient).");
            }
        } else {
            showPrompt("Select an ingredient to delete.");
        }
    }

    @FXML
    protected void onSaveButtonClick() {
        String name = nameField.getText();
        try {
            if (!name.isEmpty()) {
                if (!NutriCalcModel.mealNameOccupied(name)) {
                    String[] ingredientsNamesArray = NutriCalcFunctions.getNamesArray(ingredientsListView.getItems());
                    float[] ingredientsAmountsArray = NutriCalcFunctions.getValuesArray(ingredientsAmountsListView.getItems());
                    NutriCalcModel.getMealsList().add(new MealModel(name, ingredientsNamesArray, ingredientsAmountsArray, MealModel.sumUpMacroValues(ingredientsListView.getItems(), ingredientsAmountsArray), MealModel.sumUpVitaminsValues(ingredientsListView.getItems(), ingredientsAmountsArray), MealModel.sumUpMineralsValues(ingredientsListView.getItems(), ingredientsAmountsArray)));
                    NutriCalcMain.getPrimaryStage().setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("my_meals_view.fxml"))));
                } else {
                    throw new NameOccupiedException();
                }
            } else {
                throw new InputMismatchException();
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
