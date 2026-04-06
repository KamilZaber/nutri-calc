package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import com.solutions.sulmurz.nutricalc.models.MealModel;
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
import org.w3c.dom.Text;

import java.io.IOException;

public class MyMealsController extends NutriCalcController {
    private static Scene addMealViewScene ;
    @FXML
    private TextField nameTextField;
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
    private void onAddClick() {
        try {
            if(addMealViewScene == null) {
                addMealViewScene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("add_meal_view.fxml")));
            }
            NutriCalcMain.getPrimaryStage().setScene(addMealViewScene);
        } catch(IOException e) {
            showFatalPrompt();
        }
    }

    @FXML
    private void onDeleteClick() {
        MealModel meal = mealsListView.getSelectionModel().getSelectedItem();
        int selectionIndex;
        if(meal != null) {
            if(showConfirmationPrompt("Do you really want to delete the meal: ", meal.getName())) {
                selectionIndex = mealsListView.getItems().indexOf(meal);
                mealsListView.getItems().remove(selectionIndex);
                NutriCalcModel.getMealsList().remove(selectionIndex);
                dataSection.setVisible(false);
            }
        } else {
            showPrompt("Select an ingredient to delete.");
        }
    }

    @FXML
    private void onEditClick() {

    }
}
