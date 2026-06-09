package com.solutions.sulmurz.nutricalc.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.solutions.sulmurz.nutricalc.controllers.NutriCalcController;
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
    private static List<PlanModel> mainPlansList;
    private static List<PlanModel> plansList;
    private static List<MealsSetModel> mealsSetsList;

    private static IDModel mealsSetsIDs;
    private static IDModel plansIDs;
    private static IDModel mainPlansIDs;

    private final static String[] macroSet = {"Proteins", "Fats", "Carbohydrates", "Fiber", "KCal"};
    private final static String[] mineralsSet = {"Calcium", "Chloride", "Potassium", "Phosphorus", "Magnesium", "Sodium", "Iron", "Zinc", "Copper", "Manganese", "Molybdenum", "Iodine", "Fluoride", "Chromium",  "Selenium"};
    private final static String[] vitaminsSet = {"Vitamin A", "Vitamin D", "Vitamin E", "Vitamin K", "Vitamin C", "Vitamin B1", "Vitamin B2", "Vitamin B3", "Vitamin B5", "Vitamin B6", "Vitamin B9", "Vitamin B12", "Choline"};

    public NutriCalcModel() {
        loadIngredientsDatabase();
        loadMealsDatabase();
        loadMealsSetsDatabase();
        loadPlansDatabase();
        loadMainPlansDatabase();
        mealsSetsIDs = new IDModel(mealsSetsList);
        plansIDs = new IDModel(plansList);
        mainPlansIDs = new IDModel(mainPlansList);
    }

    public static List<IngredientModel> getIngredientsList() {
        return ingredientsList;
    }

    public static List<MealModel> getMealsList() {
        return mealsList;
    }

    public static  List<MealsSetModel> getMealsSetsList() { return mealsSetsList; }

    public static List<PlanModel> getPlansList() {
        return plansList;
    }

    public static List<PlanModel> getMainPlansList() {
        return mainPlansList;
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

    public static IDModel getPlansIDs() {
        return plansIDs;
    }

    public static IDModel getMainPlansIDs() {
        return mainPlansIDs;
    }

    public static IDModel getMealsSetsIDs() {
        return mealsSetsIDs;
    }


    public static IngredientModel getIngredientByName(String name) {
        IngredientModel ingredient = null;
        for(IngredientModel tempIngredient: ingredientsList) {
            if(tempIngredient.getName().equals(name)) {
                ingredient = tempIngredient;
                break;
            }
        }
        return ingredient;
    }

    public static MealModel getMealByName(String name) {
        MealModel meal = null;
        for(MealModel tempMeal: mealsList) {
            if(tempMeal.getName().equals(name)) {
                meal = tempMeal;
                break;
            }
        }
        return meal;
    }

    public static PlanModel getPlanByID(int ID) {
        PlanModel plan = null;
        for(PlanModel tempPlan: plansList) {
            if (tempPlan.getID() == ID) {
                plan = tempPlan;
                break;
            }
        }
        return plan;
    }

    public static MealsSetModel getMealsSetByID(int ID) {
        MealsSetModel mealsSet = null;
        for(MealsSetModel tempMealsSet: mealsSetsList) {
            if (tempMealsSet.getID() == ID) {
                mealsSet = tempMealsSet;
                break;
            }
        }
        return mealsSet;
    }

    private void loadIngredientsDatabase() {
        Path path = Paths.get("C:\\Program Files\\JetBrains\\IntelliJ IDEA 2026.1\\projects\\nutri-calc\\nutri-calc\\src\\main\\resources\\ingredients_database.json");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();      //żeby nie brało pod uwagę funkcji czy innych pól, które nie mają być częścią struktury obiektu JSON (POJO)
        Type listType = new TypeToken<List<IngredientModel>>(){}.getType();
        try {
            ingredientsList = gson.fromJson(Files.readString(path),listType);
        } catch (IOException e) {
            NutriCalcController.showFatalPrompt();
        }
    }

    private void loadMealsDatabase() {
        Path path = Paths.get("C:\\Program Files\\JetBrains\\IntelliJ IDEA 2026.1\\projects\\nutri-calc\\nutri-calc\\src\\main\\resources\\meals_database.json");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Type listType = new TypeToken<List<MealModel>>(){}.getType();
        try {
            mealsList = gson.fromJson(Files.readString(path),listType);
        } catch (IOException e) {
            NutriCalcController.showFatalPrompt();
        }
    }

    private void loadMealsSetsDatabase() {
        Path path = Paths.get("C:\\Program Files\\JetBrains\\IntelliJ IDEA 2026.1\\projects\\nutri-calc\\nutri-calc\\src\\main\\resources\\meals_sets_database.json");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Type listType = new TypeToken<List<MealsSetModel>>(){}.getType();
        try {
            mealsSetsList = gson.fromJson(Files.readString(path),listType);
        } catch (IOException e) {
            e.printStackTrace();
            NutriCalcController.showFatalPrompt();
        }
    }

    private void loadPlansDatabase() {
        Path path = Paths.get("C:\\Program Files\\JetBrains\\IntelliJ IDEA 2026.1\\projects\\nutri-calc\\nutri-calc\\src\\main\\resources\\plans_database.json");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Type listType = new TypeToken<List<PlanModel>>(){}.getType();
        try {
            plansList = gson.fromJson(Files.readString(path),listType);
        } catch (IOException e) {
            e.printStackTrace();
            NutriCalcController.showFatalPrompt();
        }
    }

    private void loadMainPlansDatabase() {
        Path path = Paths.get("C:\\Program Files\\JetBrains\\IntelliJ IDEA 2026.1\\projects\\nutri-calc\\nutri-calc\\src\\main\\resources\\main_plans_database.json");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Type listType = new TypeToken<List<PlanModel>>(){}.getType();
        try {
            mainPlansList = gson.fromJson(Files.readString(path),listType);
        } catch (IOException e) {
            e.printStackTrace();
            NutriCalcController.showFatalPrompt();
        }
    }

    public static boolean ingredientNameOccupied(String name) {
        boolean result = false;
        for(IngredientModel ingredient: ingredientsList) {
            if(ingredient.getName().equals(name)) {
                result = true;
            }
        }
        return result;
    }

    public static boolean mealNameOccupied(String name) {
        boolean result = false;
        for(MealModel meal: mealsList) {
            if(meal.getName().equals(name)) {
                result = true;
            }
        }
        return result;
    }
}
