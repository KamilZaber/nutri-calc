package com.solutions.sulmurz.nutricalc.models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PlanModel {
    private String name;
    private float caloriesAmount;
    private float[] vitaminsAmounts;
    private float[] mineralsAmounts;
    private List<PlanElementModel> planElements = new LinkedList<>();

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

    public List<PlanElementModel> getPlanElements() {
        return planElements;
    }
}
