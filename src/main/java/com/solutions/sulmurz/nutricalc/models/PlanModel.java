package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PlanModel extends PlanElementModel {
    @Expose
    private int[][] elementsList;

    public PlanModel() {
        super();
        this.elementsList = null;
    }

    public PlanModel(int[][] elementsList) {
        super();
        this.elementsList = elementsList;
    }

    public PlanModel(String name, String description) {
        super(name, description);
        this.elementsList = null;
    }

    public int[][] getElementsList() {
        return elementsList;
    }

    public void giveNewID() {
        this.ID = NutriCalcModel.getPlansIDs().giveID();
    }

    public static float[] sumUpMacroValues(List<PlanElementModel> elements) {
        float[] macroSummary = new float[5];
        for(PlanElementModel element: elements) {
            for(int i = 0; i < 5; i++) {
                macroSummary[i] = macroSummary[i] + element.getMacroAmounts()[i];
            }
        }
        return macroSummary;
    }

    public static float[] sumUpMineralsValues(List<PlanElementModel> elements) {
        float[] mineralsSummary = new float[15];
        for(PlanElementModel element: elements) {
            for(int i = 0; i < 15; i++) {
                mineralsSummary[i] = mineralsSummary[i] + element.getMineralsAmounts()[i];
            }
        }
        return mineralsSummary;
    }

    public static float[] sumUpVitaminsValues(List<PlanElementModel> elements) {
        float[] vitaminsSummary = new float[13];
        for(PlanElementModel element: elements) {
            for(int i = 0; i < 13; i++) {
                vitaminsSummary[i] = vitaminsSummary[i] + element.getVitaminsAmounts()[i];
            }
        }
        return vitaminsSummary;
    }
}
