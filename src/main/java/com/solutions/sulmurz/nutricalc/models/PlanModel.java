package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;
import com.solutions.sulmurz.nutricalc.controllers.NutriCalcController;

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
                if (elementData[0] == 1) {
                    newElement = new PlanModel(NutriCalcModel.getPlanByID(elementData[1]));
                    NutriCalcModel.getPlansList().add((PlanModel) newElement);
                    elementData = new int[2];
                    elementData[0] = 1;
                    elementData[1] = newElement.getID();
                } else if (elementData[0] == 2) {
                    newElement = new MealsSetModel(NutriCalcModel.getMealsSetByID(elementData[1]));
                    NutriCalcModel.getMealsSetsList().add((MealsSetModel) newElement);
                    elementData = new int[2];
                    elementData[0] = 2;
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

    public void calculateNutritionalValues() {

        clearNutritionalValues();

        for(int[] e: elementsList) {

            switch(e[0]) {

                case 1 -> {
                    PlanModel p = NutriCalcModel.getPlanByID(e[1]);

                    p.calculateNutritionalValues();

                    changeNutritionalValues("PLUS", 1, p.getMacroAmounts(), p.getMineralsAmounts(), p.getVitaminsAmounts());
                }

                case 2 -> {
                    MealsSetModel s = NutriCalcModel.getMealsSetByID(e[1]);

                    s.calculateNutritionalValues();

                    changeNutritionalValues("PLUS", 1, s.getMacroAmounts(), s.getMineralsAmounts(), s.getVitaminsAmounts());
                }
            }
        }
    }

    public void recalculateParents(String plusOrMinus, float[] macro, float[] minerals, float[] vitamins) {

        PlanElementModel current = this;

        while(current != null) {
            current.changeNutritionalValues(plusOrMinus, 1, macro, minerals, vitamins);
            current = current.getParentPlan();
        }
    }

    public void addElement(PlanElementModel element) {
        int[][] newElementsList = new int[elementsList.length+1][2];

        for(int i = 0; i < elementsList.length; i++) {
            newElementsList[i] = elementsList[i];
        }

        newElementsList[elementsList.length][0] = element.getType();
        newElementsList[elementsList.length][1] = element.getID();
        this.elementsList = newElementsList;
    }

    public void deleteElement(PlanElementModel element) {
        int[][] newElementsList = new int[elementsList.length-1][2];
        int j = 0;

        for(int i = 0; i < elementsList.length; i++) {
            if(!((elementsList[i][0] == element.getType()) && (elementsList[i][1] == element.getID()))) {
                newElementsList[j] = elementsList[i];
                j++;
            }
        }

        elementsList = newElementsList;
    }
}
