package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.List;

public class MealModel {
    @Expose
    private String name;
    @Expose
    private float[] macroAmounts;
    @Expose
    private float[] vitaminsAmounts;
    @Expose
    private float[] mineralsAmounts;
    @Expose
    private float[] ingredientsAmounts;
    //@Expose
    //private List<IngredientModel> mealIngredients;
    @Expose
    private int[] mealIngredients;

    public String getName() {
        return name;
    }

    //public List<IngredientModel> getMealIngredients() {
    //    return mealIngredients;
    //}

    public int[] getMealngredients() {
        return mealIngredients;
    }

    public float[] getCaloriesAmount() {
        return macroAmounts;
    }

    public float[] getMineralsAmounts() {
        return mineralsAmounts;
    }

    public float[] getVitaminsAmounts() {
        return vitaminsAmounts;
    }

    public float[] getMacroAmounts() {
        return macroAmounts;
    }

    public float[] getIngredientsAmounts() {
        return ingredientsAmounts;
    }
}
