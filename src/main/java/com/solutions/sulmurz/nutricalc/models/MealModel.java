package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

public class MealModel extends IngredientModel {
    @Expose
    private float[] ingredientsAmounts;
    @Expose
    private String[] mealIngredients;

    public MealModel() {
        super();
        ingredientsAmounts = null;
        mealIngredients = null;
    }

    public MealModel(String name, String[] mealIngredients, float[] ingredientsAmounts, float[] macroAmounts, float[] vitaminsAmounts, float[] mineralsAmounts) {
        super(name,macroAmounts, vitaminsAmounts, mineralsAmounts);
        this.mealIngredients = mealIngredients;
        this.ingredientsAmounts = ingredientsAmounts;
    }

    public String[] getMealngredients() {
        return mealIngredients;
    }

    public float[] getIngredientsAmounts() {
        return ingredientsAmounts;
    }
}
