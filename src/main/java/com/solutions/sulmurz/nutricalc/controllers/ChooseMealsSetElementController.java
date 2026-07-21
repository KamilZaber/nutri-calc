package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import com.solutions.sulmurz.nutricalc.models.MealsSetElementBasicData;
import com.solutions.sulmurz.nutricalc.models.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.models.PlanElementModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class ChooseMealsSetElementController extends NutriCalcController {
    @FXML
    private Label titleLabel;
    @FXML
    private ListView<IngredientModel> elementsListView;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;
    @FXML
    private TextField amountField;
    private MealsSetElementBasicData newElementData;
    private boolean chosen = false;

    @FXML
    private void onAddButtonClick() {
        if (elementsListView.getSelectionModel().getSelectedItem() != null && !amountField.getText().isEmpty()) {
            try {
                IngredientModel selectedElement = elementsListView.getSelectionModel().getSelectedItem();
                float enteredAmount = Float.parseFloat(amountField.getText());
                chosen = true;
                newElementData = new MealsSetElementBasicData(selectedElement, enteredAmount);
                ((Stage) cancelButton.getScene().getWindow()).close();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                showPrompt("Wrong number format.");
            }
        } else {
            showPrompt("Select an element to copy and enter the amount of it (grams for ingredient, pieces for meal).");
        }
    }

    @FXML
    private void onCancelButtonClick() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    public void setup(List<? extends IngredientModel> elementsList) {
        elementsListView.getItems().setAll(elementsList);
        elementsListView.setCellFactory(elementsListView -> new ListCell<>() {        //nowe komórki o określonym działaniu
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

    protected MealsSetElementBasicData getElementInfo() {
        if(chosen) {
            return newElementData;
        } else {
            return null;
        }
    }
}
