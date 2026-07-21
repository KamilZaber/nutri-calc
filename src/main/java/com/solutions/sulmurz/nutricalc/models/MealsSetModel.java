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

    public MealsSetModel(int type, String name, String description) {
        super(type, name, description);
        elementsList = null;
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
                j++;
            }
        }

        elementsList = newElementslist;
    }
}
