package com.solutions.sulmurz.nutricalc;

import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import com.solutions.sulmurz.nutricalc.models.MealModel;
import com.solutions.sulmurz.nutricalc.models.PlanModel;

import java.util.HashMap;

public class NutriCalcModel {
<<<<<<< Updated upstream
    HashMap<IngredientModel, String> ingredientsList;
    HashMap<MealModel, String> mealsList;
    HashMap<PlanModel, String> plansList;
    String[] vitaminsSet = {"Vitamin A", "Vitamin D", "Vitamin E", "Vitamin K", "Vitamin C", "Vitamin B1", "Vitamin B2", "Vitamin B3", "Vitamin B5", "Vitamin B6", "Vitamin B9", "Vitamin B12"};
    String[] mineralsSet = {"Calcium", "Chloride", "Potassium", "Phosphorus", "Magnesium", "Sodium", "Iron", "Zinc", "Copper", "Manganese", "Molybdenum", "Iodine", "Fluoride", "Chromium",  "Selenium"};
=======
    private static ObjectMapper objectMapper;
    private static List<IngredientModel> ingredientsList;
    private static HashMap<MealModel, String> mealsList;
    private static HashMap<PlanModel, String> plansList;
    private final static String[] macroSet = {"Proteins", "Fats", "Carbohydrates", "Fiber", "KCal"};
    private final static String[] vitaminsSet = {"Vitamin A", "Vitamin D", "Vitamin E", "Vitamin K", "Vitamin C", "Vitamin B1", "Vitamin B2", "Vitamin B3", "Vitamin B5", "Vitamin B6", "Vitamin B9", "Vitamin B12", "Choline"};
    private final static String[] mineralsSet = {"Calcium", "Chloride", "Potassium", "Phosphorus", "Magnesium", "Sodium", "Iron", "Zinc", "Copper", "Manganese", "Molybdenum", "Iodine", "Fluoride", "Chromium",  "Selenium"};

    public NutriCalcModel() {
        ingredientsList = null;
        mealsList = null;
        plansList = null;
    }

    public static List<IngredientModel> getIngredientsList() {
        return ingredientsList;
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
            Path path = Paths.get("C:\\Users\\pc\\IdeaProjects\\NutriCalc\\src\\main\\resources\\nutriCalc_database.json");
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            Type listType = new TypeToken<List<IngredientModel>>(){}.getType();
            try {
                ingredientsList = gson.fromJson(Files.readString(path),listType);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
>>>>>>> Stashed changes
}
