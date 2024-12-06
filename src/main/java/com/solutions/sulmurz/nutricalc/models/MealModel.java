package com.solutions.sulmurz.nutricalc.models;

import java.util.HashMap;

public class MealModel {
    private String name;
    private float caloriesAmount;
    private float[] vitaminsAmounts;
    private float[] mineralsAmounts;
    private HashMap<IngredientModel, Integer> mealIngredients = new HashMap<>();

    public String getName() {
        return name;
    }

    public HashMap<IngredientModel, Integer> getMealIngredients() {
        return mealIngredients;
    }

    public float getCaloriesAmount() {
        return caloriesAmount;
    }

    public float[] getMineralsAmounts() {
        return mineralsAmounts;
    }

    public float[] getVitaminsAmounts() {
        return vitaminsAmounts;
    }
}
