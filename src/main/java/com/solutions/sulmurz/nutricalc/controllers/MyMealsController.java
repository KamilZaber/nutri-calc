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
            for (int i : meal.getMealngredients()) {
                TextField ingredientTextField = new TextField(meal.getIngredientsAmounts()[x] + "g of " + NutriCalcModel.getIngredientsList().get(i).getName());
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

    }
    @FXML
    private void onDeleteClick() {

    }
    @FXML
    private void onEditClick() {

    }
}
