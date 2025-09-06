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

    public IngredientModel() {
        name = null;
        macroAmounts = null;
        vitaminsAmounts = null;
        mineralsAmounts = null;
    }

    public IngredientModel(String name) {
        this();
        this.name = name;
    }

    public IngredientModel(String name, float[] macroAmounts, float[] mineralsAmounts, float[] vitaminsAmounts) {
        super();
        this.name = name;
        this.macroAmounts = macroAmounts;
        this.mineralsAmounts = mineralsAmounts;
        this.vitaminsAmounts = vitaminsAmounts;
    }

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
