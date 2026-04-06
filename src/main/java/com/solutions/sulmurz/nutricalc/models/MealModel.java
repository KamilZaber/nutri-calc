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
    private String[] mealIngredients;

    public MealModel() {
        name = null;
        macroAmounts = null;
        vitaminsAmounts = null;
        mineralsAmounts = null;
        ingredientsAmounts = null;
        mealIngredients = null;
    }

    public MealModel(String name, String[] mealIngredients, float[] ingredientsAmounts, float[] macroAmounts, float[] vitaminsAmounts, float[] mineralsAmounts) {
        this.name = name;
        this.mealIngredients = mealIngredients;
        this.ingredientsAmounts = ingredientsAmounts;
        this.macroAmounts = macroAmounts;
        this.vitaminsAmounts = vitaminsAmounts;
        this.mineralsAmounts = mineralsAmounts;
    }

    public String getName() {
        return name;
    }

    //public List<IngredientModel> getMealIngredients() {
    //    return mealIngredients;
    //}

    public String[] getMealngredients() {
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
