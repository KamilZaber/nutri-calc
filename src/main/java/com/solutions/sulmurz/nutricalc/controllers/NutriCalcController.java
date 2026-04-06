package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import com.solutions.sulmurz.nutricalc.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

public abstract class NutriCalcController {
    @FXML
    private void openMainMenuView() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("main_menu_view.fxml"));
        } catch (IOException e) {
            showFatalPrompt();
        }
        Scene scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }

    @FXML
    private void openMyIngredientsView() {
        NutriCalcModel.loadIngredientsDatabase();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("my_ingredients_view.fxml"));
        } catch (IOException e) {
            showFatalPrompt();
        }
        Scene scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }

    @FXML
    private void openMyMealsView() {
        NutriCalcModel.loadIngredientsDatabase();
        NutriCalcModel.loadMealsDatabase();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("my_meals_view.fxml"));
        } catch (IOException e) {
            showFatalPrompt();
        }
        Scene scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }

    @FXML
    private void openMyPlansView() {
        Scene scene;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("my_plans_view.fxml"));  //tuta generator spersonalizowanych scen z danymi GUI jak kursor, wielkość okna
        } catch (IOException e) {
            showFatalPrompt();
        }
        scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }

    protected void generateSection(VBox container, String[] fieldsNames, float[] values) {
        int i = -1;
        for(Node node: container.getChildren()) {
            if(i != -1) {
                ((TextField) node).setText(fieldsNames[i] + ": " + values[i]);
            }
            i++;
        }
    }

    protected void generateSection(VBox container, float[] values) {
        int i = 0;
        for(Node node: container.getChildren()) {
            ((TextField) node).setText(Float.toString(values[i]));
            i++;
        }
    }

    protected float[] getSectionValuesArray(VBox section) throws InputMismatchException, NumberFormatException {
        ObservableList<Node> fieldsList = section.getChildren();
        float[] valuesArray = new float[fieldsList.size()];
        int i = 0;
        for (Node field : fieldsList) {
            if (!((TextField) field).getText().isEmpty()) {
                valuesArray[i] = Float.parseFloat(((TextField) field).getText());
            } else {
                throw new InputMismatchException();
            }
            i++;
        }
        return valuesArray;
    }

    protected float[] getValuesArray(ObservableList<Float> valuesList) {
        float[] valuesArray = new float[valuesList.size()];
        int i = 0;
        for(Float value: valuesList) {
            valuesArray[i] = value;
            i++;
        }
        return valuesArray;
    }

    protected String[] getNamesArray(ObservableList<IngredientModel> ingredientsList) {
        String[] namesArray = new String[ingredientsList.size()];
        int i = 0;
        for(IngredientModel ingredient: ingredientsList) {
            namesArray[i] = ingredient.getName();
            i++;
        }
        return namesArray;
    }

    protected float[] sumUpMacroValues(List<IngredientModel> ingredients, float[] ingredientAmounts) {
        float[] macroSummary = new float[5];
        int i = 0;
        for(IngredientModel ingredient: ingredients) {
            for(int j = 0; j < 5; j++) {
                macroSummary[j] = macroSummary[j] + ((ingredient.getMacroAmounts()[j])/100*ingredientAmounts[i]);
            }
            i++;
        }
        return macroSummary;
    }

    protected float[] sumUpVitaminsValues(List<IngredientModel> ingredients, float[] ingredientAmounts) {
        float[] macroSummary = new float[13];
        int i = 0;
        for(IngredientModel ingredient: ingredients) {
            for(int j = 0; j < 13; j++) {
                macroSummary[j] = macroSummary[j] + ((ingredient.getVitaminsAmounts()[j])/100*ingredientAmounts[i]);
            }
            i++;
        }
        return macroSummary;
    }

    protected float[] sumUpMineralsValues(List<IngredientModel> ingredients, float[] ingredientAmounts) {
        float[] macroSummary = new float[15];
        int i = 0;
        for(IngredientModel ingredient: ingredients) {
            for(int j = 0; j < 15; j++) {
                macroSummary[j] = macroSummary[j] + ((ingredient.getMineralsAmounts()[j])/100*ingredientAmounts[i]);
            }
            i++;
        }
        return macroSummary;
    }

    protected void showPrompt(String message) {
        PromptController controller;
        VBox root = null;
        FXMLLoader loader = new FXMLLoader(NutriCalcController.class.getClassLoader().getResource("prompt_view.fxml"));
        try {
            root = loader.load();
        } catch(IOException e) {
            showFatalPrompt();
            Platform.exit();
        }
        controller = loader.getController();
        controller.setErrorMessage(message);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("NutriCalc Error");
        stage.initModality(Modality.APPLICATION_MODAL); //blokuje inne okna
        stage.showAndWait();
    }

    protected boolean showConfirmationPrompt(String question, String name) {
        ConfirmationPromptController controller;
        VBox root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/confirmation_prompt_view.fxml"));
        try {
            root = loader.load();
        } catch(IOException e) {
            showFatalPrompt();
            Platform.exit();
        }
        controller = loader.getController();
        controller.setConfirmationQuestion(question);
        controller.setIngredientName(name);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("NutriCalc Prompt");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        return controller.wasConfirmed();
    }

    public static void showFatalPrompt() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("NutriCalc Error");
        alert.setHeaderText("An error occured while running the program.");
        alert.setContentText("Program loading failure. Files may be corrupted.");
        alert.showAndWait();
    }
}
