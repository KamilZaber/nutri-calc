package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

public class IngredientModel {
    @Expose
    private String name;
    @Expose
    private float[] macroAmounts;
    @Expose
    private float[] vitaminsAmounts;
    @Expose
    private float[] mineralsAmounts;

    public String getName() {
        return name;
    }

    public float[] getMacroAmounts() {
        return macroAmounts;
    }

    public float[] getVitaminsAmounts() {
        return vitaminsAmounts;
    }

    public float[] getMineralsAmounts() {
        return mineralsAmounts;
    }
}
