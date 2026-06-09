package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

public class MealsSetModel extends PlanElementModel {
    @Expose
    private String[][] elementsList;
    @Expose
    private float[] elementsAmounts;

    public MealsSetModel() {
        super();
        elementsList = null;
    }

    public MealsSetModel(String name, String description, float[] macroAmounts, float[] vitaminsAmounts, float[] mineralsAmounts, PlanModel parentPlan, int mealsSetID, String[][] elementsList) {
        super(name, description, macroAmounts, vitaminsAmounts,mineralsAmounts, parentPlan);
    }

    public MealsSetModel(MealsSetModel mealsSet) {
        super(mealsSet);
        this.elementsList = new String[mealsSet.getElementsList().length][2];
        for(int i = 0; i < mealsSet.getElementsList().length; i++) {
            this.elementsList[i] = mealsSet.getElementsList()[i].clone();
        }
        this.elementsAmounts = mealsSet.getElementsAmounts().clone();
    }

    public String[][] getElementsList() {
        return elementsList;
    }

    public float[] getElementsAmounts() {
        return elementsAmounts;
    }
}
