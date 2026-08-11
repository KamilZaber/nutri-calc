package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.annotations.Expose;

public class MealsSetModel extends PlanElementModel {
    @Expose
    private String[][] elementsList;
    @Expose
    private float[] elementsAmounts;

    public MealsSetModel() {
        super();
        elementsList = new String[0][0];
    }

    public MealsSetModel(int type, String name, String description) {
        super(type, name, description);
        elementsList = new String[0][0];
        assignNewID();
    }

    public MealsSetModel(MealsSetModel mealsSet) {
        super(mealsSet);
        this.elementsList = new String[mealsSet.getElementsList().length][2];
        for(int i = 0; i < mealsSet.getElementsList().length; i++) {
            this.elementsList[i] = mealsSet.getElementsList()[i].clone();
        }
        this.elementsAmounts = mealsSet.getElementsAmounts().clone();
        assignNewID();
    }

    public void calculateNutritionalValues() {
        float[] macroSummary = new float[5];
        float[] mineralsSummary = new float[15];
        float[] vitaminsSummary = new float[13];
        float tempAmount = 0;

        IngredientModel element = null;

        clearNutritionalValues();

        for(int i = 0 ; i < elementsList.length; i++) {
            if(elementsList[i][0].equals("meal")) {
                element = NutriCalcModel.getMealByName(elementsList[i][1]);
                tempAmount = elementsAmounts[i];
            } else if (elementsList[i][0].equals("ingredient")) {
                element = NutriCalcModel.getIngredientByName(elementsList[i][1]);
                tempAmount = elementsAmounts[i]/100;
            }

            for(int j = 0; j < 5; j++) {
                macroSummary[j] = macroSummary[j] + tempAmount * element.getMacroAmounts()[j];
            }
            for(int j = 0; j < 15; j++) {
                mineralsSummary[j] = mineralsSummary[j] + tempAmount * element.getMineralsAmounts()[j];
            }
            for(int j = 0; j < 13; j++) {
                vitaminsSummary[j] = vitaminsSummary[j] + tempAmount * element.getVitaminsAmounts()[j];
            }
        }

        macroAmounts = macroSummary;
        mineralsAmounts = mineralsSummary;
        vitaminsAmounts = vitaminsSummary;
    }

    public void recalculateParents(String plusOrMinus, IngredientModel element, float amount) {
        if(!(element instanceof MealModel)) {
            amount = amount/100;
        }

        PlanElementModel current = this;

        while(current != null) {
            current.changeNutritionalValues(plusOrMinus, amount, element.getMacroAmounts(), element.getMineralsAmounts(), element.getVitaminsAmounts());
            current = current.getParentPlan();
        }
    }

    public String[][] getElementsList() {
        return elementsList;
    }

    public float[] getElementsAmounts() {
        return elementsAmounts;
    }

    public void addNewElement(IngredientModel newElement, float amount) {
        int n = elementsList.length;
        String[][] newElementsList = new String[n + 1][2];
        float[] newAmountsList = new float[n + 1];

        for(int i = 0; i < n; i++) {
            newElementsList[i] = elementsList[i];
            newAmountsList[i] = elementsAmounts[i];
        }
        if(newElement instanceof MealModel) {
            newElementsList[n][0] = "meal";
        } else {
            newElementsList[n][0] = "ingredient";
        }
        newElementsList[n][1] = newElement.getName();
        newAmountsList[n] = amount;

        elementsList = newElementsList;
        elementsAmounts = newAmountsList;
    }

    public void deleteElement(IngredientModel selectedElement) {
        String[][] newElementslist = new String[elementsList.length-1][2];
        float[] newAmountsList = new float[elementsList.length-1];
        String type = null;
        int j = 0;

        if(selectedElement instanceof MealModel) {
            type = "meal";
        } else if(selectedElement != null) {
            type = "ingredient";
        }

        for(int i = 0; i < elementsList.length; i++) {
            if(!(elementsList[i][0].equals(type) && elementsList[i][1].equals(selectedElement.getName()))) {
                newElementslist[j] = elementsList[i];
                newAmountsList[j] = elementsAmounts[i];
                j++;
            }
        }

        elementsList = newElementslist;
        elementsAmounts = newAmountsList;
    }
}
