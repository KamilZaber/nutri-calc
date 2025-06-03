package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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
        com.solutions.sulmurz.nutricalc.NutriCalcModel.loadIngredientsDatabase();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("my_ingredients_view.fxml"));
        } catch (IOException e) {
            showFatalPrompt();
        }
        Scene scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
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

    protected void showConfirmationPrompt() {}

    public static void showFatalPrompt() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("NutriCalc Error");
        alert.setHeaderText("An error occured while running the program.");
        alert.setContentText("Program loading failure. Files may be corrupted.");
        alert.showAndWait();
    }
}
