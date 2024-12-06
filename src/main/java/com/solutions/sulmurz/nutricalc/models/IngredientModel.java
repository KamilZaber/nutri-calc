package com.solutions.sulmurz.nutricalc.models;

public class IngredientModel {
    private String name;
    private float[] vitaminsAmounts;
    private float[] mineralsAmounts;
    private float caloriesAmount;

    public float[] getVitaminsAmounts() {
        return vitaminsAmounts;
    }

    public float[] getMineralsAmounts() {
        return mineralsAmounts;
    }

    public float getCaloriesAmount() {
        return caloriesAmount;
    }

    public String getName() {
        return name;
    }
}
