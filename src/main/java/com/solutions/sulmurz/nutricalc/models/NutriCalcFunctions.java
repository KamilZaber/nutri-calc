package com.solutions.sulmurz.nutricalc.models;

import javafx.collections.ObservableList;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class NutriCalcFunctions {
    public static String[] getNamesArray(ObservableList<IngredientModel> ingredientsList) {
        String[] namesArray = new String[ingredientsList.size()];
        int i = 0;
        for(IngredientModel ingredient: ingredientsList) {
            namesArray[i] = ingredient.getName();
            i++;
        }
        return namesArray;
    }

    public static float[] getValuesArray(ObservableList<Float> valuesList) {
        float[] valuesArray = new float[valuesList.size()];
        int i = 0;
        for(Float value: valuesList) {
            valuesArray[i] = value;
            i++;
        }
        return valuesArray;
    }

    public static float[] shortenFloatArray(float[] floatArray) {
        float[] shortenedArray = null;
        int newSize = floatArray.length;
        int j = 0;
        for(int i = 0; i < floatArray.length; i++) {
            if(floatArray[i] == 0) {
                newSize--;
            }
        }
        shortenedArray = new float[newSize];
        for(int i = 0; i < floatArray.length; i++) {
            if(floatArray[i] > 0) {
                shortenedArray[j] = floatArray[i];
                j++;
            }
        }
        return shortenedArray;
    }

    public static void traverseElements(PlanModel rootPlan, Consumer<PlanModel> onPlan, Consumer<MealsSetModel> onMealsSet) {
        Set<PlanModel> visitedPlans = new HashSet<>();
        Deque<PlanModel> stack = new ArrayDeque<>();
        stack.push(rootPlan);

        while (!stack.isEmpty()) {
            PlanModel currentPlan = stack.pop();

            // zabezpieczenie przed cyklem
            if (visitedPlans.contains(currentPlan)) {
                continue;
            }

            visitedPlans.add(currentPlan);

            if (currentPlan == null) {
                continue;
            }

            onPlan.accept(currentPlan);

            int[][] elements = currentPlan.getElementsList();

            if (elements == null) {
                continue;
            }

            for (int[] element: elements) {

                int type = element[0];
                int ID = element[1];

                // PLAN
                if (type == 0) {
                    stack.push(NutriCalcModel.getPlanByID(ID));
                }

                // MEALS SET
                else if (type == 1) {
                    MealsSetModel mealsSet = NutriCalcModel.getMealsSetByID(ID);

                    if (mealsSet != null) {
                        onMealsSet.accept(mealsSet);
                    }
                }
            }
        }
    }
}
