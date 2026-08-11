package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

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
    protected float[] macroAmounts;
    @Expose
    protected float[] vitaminsAmounts;
    @Expose
    protected float[] mineralsAmounts;
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

    public abstract void calculateNutritionalValues();

    protected void clearNutritionalValues() {
        for(int j = 0; j < 5; j++) {
            macroAmounts[j] = 0;
        }
        for(int j = 0; j < 15; j++) {
            mineralsAmounts[j] = 0;
        }
        for(int j = 0; j < 13; j++) {
            vitaminsAmounts[j] = 0;
        }
    }

    protected void changeNutritionalValues(String plusOrMinus, float amount, float[] macro, float[] minerals, float[] vitamins) {
        if(plusOrMinus.equals("PLUS")) {
            for(int j = 0; j < 5; j++) {
                macroAmounts[j] = macroAmounts[j] + amount * macro[j];
            }
            for(int j = 0; j < 15; j++) {
                mineralsAmounts[j] = mineralsAmounts[j] + amount * minerals[j];
            }
            for(int j = 0; j < 13; j++) {
                vitaminsAmounts[j] = vitaminsAmounts[j] + amount * vitamins[j];
            }
        } else if (plusOrMinus.equals("MINUS")) {
            for(int j = 0; j < 5; j++) {
                macroAmounts[j] = macroAmounts[j] - amount * macro[j];
            }
            for(int j = 0; j < 15; j++) {
                mineralsAmounts[j] = mineralsAmounts[j] - amount * minerals[j];
            }
            for(int j = 0; j < 13; j++) {
                vitaminsAmounts[j] = vitaminsAmounts[j] - amount * vitamins[j];
            }
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
