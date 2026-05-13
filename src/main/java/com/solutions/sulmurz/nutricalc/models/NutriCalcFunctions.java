package com.solutions.sulmurz.nutricalc.models;

import javafx.collections.ObservableList;

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
}
