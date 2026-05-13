package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

import java.util.List;

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
        super(name, macroAmounts, vitaminsAmounts, mineralsAmounts);
        this.mealIngredients = mealIngredients;
        this.ingredientsAmounts = ingredientsAmounts;
    }

    public static float[] sumUpMacroValues(List<IngredientModel> ingredients, float[] ingredientAmounts) {
        float[] macroSummary = new float[5];
        int i = 0;
        for(IngredientModel ingredient: ingredients) {
            for(int j = 0; j < 5; j++) {
                macroSummary[j] = macroSummary[j] + ((ingredient.getMacroAmounts()[j])/100*ingredientAmounts[i]);
            }
            i++;
        }
        return macroSummary;
    }

    public static float[] sumUpVitaminsValues(List<IngredientModel> ingredients, float[] ingredientAmounts) {
        float[] vitaminsSummary = new float[13];
        int i = 0;
        for(IngredientModel ingredient: ingredients) {
            for(int j = 0; j < 13; j++) {
                vitaminsSummary[j] = vitaminsSummary[j] + ((ingredient.getVitaminsAmounts()[j])/100*ingredientAmounts[i]);
            }
            i++;
        }
        return vitaminsSummary;
    }

    public static float[] sumUpMineralsValues(List<IngredientModel> ingredients, float[] ingredientAmounts) {
        float[] mineralsSummary = new float[15];
        int i = 0;
        for(IngredientModel ingredient: ingredients) {
            for(int j = 0; j < 15; j++) {
                mineralsSummary[j] = mineralsSummary[j] + ((ingredient.getMineralsAmounts()[j])/100*ingredientAmounts[i]);
            }
            i++;
        }
        return mineralsSummary;
    }

    public String[] getMealngredients() {
        return mealIngredients;
    }

    public float[] getIngredientsAmounts() {
        return ingredientsAmounts;
    }
}
