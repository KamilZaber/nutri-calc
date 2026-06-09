package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;
import com.solutions.sulmurz.nutricalc.controllers.NutriCalcController;

import java.util.Arrays;
import java.util.List;

public class PlanModel extends PlanElementModel {
    @Expose
    private int[][] elementsList;

    public PlanModel() {
        super();
        this.elementsList = null;
    }

    public PlanModel(PlanModel plan) {
        super(plan);
        int[][] oldElementsList = plan.getElementsList();
        if(oldElementsList != null) {
            this.elementsList = new int[oldElementsList.length][2];
            for (int i = 0; i < oldElementsList.length; i++) {
                int[] elementData = oldElementsList[i];
                PlanElementModel newElement;
                if (elementData[0] == 0) {
                    newElement = new PlanModel(NutriCalcModel.getPlanByID(elementData[1]));
                    NutriCalcModel.getPlansList().add((PlanModel) newElement);
                    elementData = new int[2];
                    elementData[1] = newElement.getID();
                } else if (elementData[0] == 1) {
                    newElement = new MealsSetModel(NutriCalcModel.getMealsSetByID(elementData[1]));
                    NutriCalcModel.getMealsSetsList().add((MealsSetModel) newElement);
                    elementData = new int[2];
                    elementData[0] = 1;
                    elementData[1] = newElement.getID();
                } else {
                    NutriCalcController.showFatalPrompt();
                }
                this.elementsList[i] = elementData;
            }
        }
        assignNewID();
    }

    public PlanModel(int type, String name, String description) {
        super(type, name, description);
        this.elementsList = null;
        assignNewID();
    }

    public int[][] getElementsList() {
        return elementsList;
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
