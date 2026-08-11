package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import com.solutions.sulmurz.nutricalc.models.MealModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MealsSetElementController extends NutriCalcController {
    @FXML
    private Label elementLabel;
    private MealsSetController parentMealsSetController;
    private IngredientModel currentElement;
    private float amount;

    @FXML
    private void onMealsSetElementClick() {
        parentMealsSetController.setSelectedElement(currentElement, elementLabel);
        parentMealsSetController.setSelectedController(this);
    }

    public void setParentMealsSetController(MealsSetController mealsSetController) {
        parentMealsSetController = mealsSetController;
    }

    public void setElement(IngredientModel element, float amount) {
        this.currentElement = element;
        this.amount = amount;

        if(element instanceof MealModel) {
            elementLabel.setText(amount + " x " + element.getName());

        } else {
            elementLabel.setText(amount + "g of " + element.getName());
        }
    }

    public float getAmount() {
        return amount;
    }
}
