package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

import java.util.Arrays;

public abstract class PlanElementModel {
    @Expose
    protected int ID;
    @Expose
    private int type;
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
        this.macroAmounts = new float[5];
        this.vitaminsAmounts = new float[13];
        this.mineralsAmounts = new float [15];
        this.parentPlan = null;
    }

    public PlanElementModel(int type, String name, String description) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.macroAmounts = new float[5];
        this.vitaminsAmounts = new float[13];
        this.mineralsAmounts = new float [15];
        this.parentPlan = null;
    }

    public PlanElementModel(PlanElementModel planElement) {
        this.type = planElement.type;
        this.name = planElement.name;
        this.description = planElement.description;
        this.macroAmounts = planElement.getMacroAmounts().clone();
        this.vitaminsAmounts = planElement.getVitaminsAmounts().clone();
        this.mineralsAmounts = planElement.getMineralsAmounts().clone();
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

    protected void assignNewID() {
        if(type == 0) {
            this.ID = NutriCalcModel.getMainPlansIDs().giveID();
        } else if(type == 1) {
            this.ID = NutriCalcModel.getPlansIDs().giveID();
        } else if(type == 2) {
            this.ID = NutriCalcModel.getMealsSetsIDs().giveID();
        }
    }
    public int getID() {
        return ID;
    }

    public int getType() {
        return type;
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
