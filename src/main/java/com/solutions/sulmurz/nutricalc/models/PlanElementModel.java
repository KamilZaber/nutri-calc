package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

public abstract class PlanElementModel {
    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private float[] macroAmounts;
    @Expose
    private float[] vitaminsAmounts;
    @Expose
    private float[] mineralsAmounts;
    private PlanModel parentPlan;

    public PlanElementModel() {
        this.name = null;
        this.description = null;
        this.macroAmounts = null;
        this.vitaminsAmounts = null;
        this.mineralsAmounts = null;
        this.parentPlan = null;
    }

    public PlanElementModel(String name, String description, float[] macroAmounts, float[] vitaminsAmounts, float[] mineralsAmounts, PlanModel parentPlan) {
        this.name = name;
        this.description = description;
        this.macroAmounts = macroAmounts;
        this.vitaminsAmounts = vitaminsAmounts;
        this.mineralsAmounts = mineralsAmounts;
        this.parentPlan = parentPlan;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public PlanModel getParentPlan() {
        return parentPlan;
    }

    public void setParentPlan(PlanModel parentPlan) {
        this.parentPlan = parentPlan;
    }

    public void setName(String s) {
    }
}
