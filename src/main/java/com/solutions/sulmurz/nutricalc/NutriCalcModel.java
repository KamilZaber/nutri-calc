package com.solutions.sulmurz.nutricalc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.solutions.sulmurz.nutricalc.controllers.NutriCalcController;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import com.solutions.sulmurz.nutricalc.models.MealModel;
import com.solutions.sulmurz.nutricalc.models.PlanModel;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class NutriCalcModel {
    private static List<IngredientModel> ingredientsList;
    private static List<MealModel> mealsList;
    //private static HashMap<MealModel, String> mealsList;
    private static HashMap<PlanModel, String> plansList;
    private final static String[] macroSet = {"Proteins", "Fats", "Carbohydrates", "Fiber", "KCal"};
    private final static String[] mineralsSet = {"Calcium", "Chloride", "Potassium", "Phosphorus", "Magnesium", "Sodium", "Iron", "Zinc", "Copper", "Manganese", "Molybdenum", "Iodine", "Fluoride", "Chromium",  "Selenium"};
    private final static String[] vitaminsSet = {"Vitamin A", "Vitamin D", "Vitamin E", "Vitamin K", "Vitamin C", "Vitamin B1", "Vitamin B2", "Vitamin B3", "Vitamin B5", "Vitamin B6", "Vitamin B9", "Vitamin B12", "Choline"};

    public NutriCalcModel() {
        ingredientsList = null;
        mealsList = null;
        plansList = null;
    }

    public static List<IngredientModel> getIngredientsList() {
        return ingredientsList;
    }

    public static List<MealModel> getMealsList() {
        return mealsList;
    }

    public static String[] getMacroSet() {
        return macroSet;
    }

    public static String[] getVitaminsSet() {
        return vitaminsSet;
    }

    public static String[] getMineralsSet() {
        return mineralsSet;
    }

    public static void loadIngredientsDatabase() {
        if(ingredientsList == null) {
            Path path = Paths.get("C:\\Users\\pc\\IdeaProjects\\NutriCalc\\src\\main\\resources\\ingredients_database.json");
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();      //żeby nie brało pod uwagę funkcji czy innych pól, które nie mają być częścią struktury obiektu JSON (POJO)
            Type listType = new TypeToken<List<IngredientModel>>(){}.getType();
            try {
                ingredientsList = gson.fromJson(Files.readString(path),listType);
            } catch (IOException e) {
                NutriCalcController.showFatalPrompt();
            }
        }
    }

    public static void loadMealsDatabase() {
        if(mealsList == null)  {
            Path path = Paths.get("C:\\Users\\pc\\IdeaProjects\\NutriCalc\\src\\main\\resources\\meals_database.json");
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            Type listType = new TypeToken<List<MealModel>>(){}.getType();
            try {
                mealsList = gson.fromJson(Files.readString(path),listType);
            } catch (IOException e) {
                NutriCalcController.showFatalPrompt();
            }
        }
    }

    public static boolean nameOccupied(String name) {
        boolean result = false;
        for(IngredientModel ingredient: ingredientsList) {
            if(ingredient.getName().equals(name)) {
                result = true;
            }
        }
        return result;
    }
}
