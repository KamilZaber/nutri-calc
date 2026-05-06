package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

public class MealsSetModel extends PlanElementModel {
    @Expose
    private int mealsSetID;
    @Expose
    private String[][] elementsList;
    @Expose
    private float[] elementsAmounts;

    public MealsSetModel() {
        super();
        mealsSetID = NutriCalcModel.giveMealsSetID();
        elementsList = null;
    }

    public MealsSetModel(String name, String description, float[] macroAmounts, float[] vitaminsAmounts, float[] mineralsAmounts, PlanModel parentPlan, int mealsSetID, String[][] elementsList) {
        super(name, description, macroAmounts, vitaminsAmounts,mineralsAmounts, parentPlan);
        this.mealsSetID = mealsSetID;
    }

    public int getMealsSetID() {
        return mealsSetID;
    }

    public String[][] getElementsList() {
        return elementsList;
    }

    public float[] getElementsAmounts() {
        return elementsAmounts;
    }
}
