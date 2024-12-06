package com.solutions.sulmurz.nutricalc.models;

import java.util.HashMap;

public class PlanElementModel {
    private String name;
    private float caloriesAmount;
    private float[] vitaminsAmounts;
    private float[] mineralsAmounts;
    private HashMap<MealModel, Integer> elementMeals = new HashMap<>();

    public String getName() {
        return name;
    }

    public float getCaloriesAmount() {
        return caloriesAmount;
    }

    public float[] getVitaminsAmounts() {
        return vitaminsAmounts;
    }

    public float[] getMineralsAmounts() {
        return mineralsAmounts;
    }

    public HashMap<MealModel, Integer> getElementMeals() {
        return elementMeals;
    }
}
