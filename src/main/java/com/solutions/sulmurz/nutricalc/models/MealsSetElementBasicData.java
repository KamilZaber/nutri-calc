package com.solutions.sulmurz.nutricalc.models;

public class MealsSetElementBasicData {
    private IngredientModel element;
    private float amount;

    public MealsSetElementBasicData(IngredientModel element, float amount) {
        this.element = element;
        this.amount = amount;
    }

    public IngredientModel getElement() {
        return element;
    }

    public void setElement(IngredientModel element) {
        this.element = element;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}