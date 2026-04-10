package com.solutions.sulmurz.nutricalc.models;

import javafx.collections.ObservableList;
import java.util.List;

public class NutriCalcFunctions {
    public static String[] getNamesArray(ObservableList<IngredientModel> ingredientsList) {
        String[] namesArray = new String[ingredientsList.size()];
        int i = 0;
        for(IngredientModel ingredient: ingredientsList) {
            namesArray[i] = ingredient.getName();
            i++;
        }
        return namesArray;
    }

    public static float[] getValuesArray(ObservableList<Float> valuesList) {
        float[] valuesArray = new float[valuesList.size()];
        int i = 0;
        for(Float value: valuesList) {
            valuesArray[i] = value;
            i++;
        }
        return valuesArray;
    }

    public static float[] shortenFloatArray(float[] floatArray) {
        float[] shortenedArray = null;
        int newSize = floatArray.length;
        int j = 0;
        for(int i = 0; i < floatArray.length; i++) {
            if(floatArray[i] == 0) {
                newSize--;
            }
        }
        shortenedArray = new float[newSize];
        for(int i = 0; i < floatArray.length; i++) {
            if(floatArray[i] > 0) {
                shortenedArray[j] = floatArray[i];
                j++;
            }
        }
        return shortenedArray;
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
        float[] macroSummary = new float[13];
        int i = 0;
        for(IngredientModel ingredient: ingredients) {
            for(int j = 0; j < 13; j++) {
                macroSummary[j] = macroSummary[j] + ((ingredient.getVitaminsAmounts()[j])/100*ingredientAmounts[i]);
            }
            i++;
        }
        return macroSummary;
    }

    public static float[] sumUpMineralsValues(List<IngredientModel> ingredients, float[] ingredientAmounts) {
        float[] macroSummary = new float[15];
        int i = 0;
        for(IngredientModel ingredient: ingredients) {
            for(int j = 0; j < 15; j++) {
                macroSummary[j] = macroSummary[j] + ((ingredient.getMineralsAmounts()[j])/100*ingredientAmounts[i]);
            }
            i++;
        }
        return macroSummary;
    }
}
