package com.solutions.sulmurz.nutricalc;

import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import com.solutions.sulmurz.nutricalc.models.MealModel;
import com.solutions.sulmurz.nutricalc.models.PlanModel;

import java.util.HashMap;

public class NutriCalcModel {
    HashMap<IngredientModel, String> ingredientsList;
    HashMap<MealModel, String> mealsList;
    HashMap<PlanModel, String> plansList;
    String[] vitaminsSet = {"Vitamin A", "Vitamin D", "Vitamin E", "Vitamin K", "Vitamin C", "Vitamin B1", "Vitamin B2", "Vitamin B3", "Vitamin B5", "Vitamin B6", "Vitamin B9", "Vitamin B12"};
    String[] mineralsSet = {"Calcium", "Chloride", "Potassium", "Phosphorus", "Magnesium", "Sodium", "Iron", "Zinc", "Copper", "Manganese", "Molybdenum", "Iodine", "Fluoride", "Chromium",  "Selenium"};
}
