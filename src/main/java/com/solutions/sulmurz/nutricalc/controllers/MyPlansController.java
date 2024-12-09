package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class MyPlansController {
    private Parent root;
    private Scene scene;
    @FXML
    private Button backButton;
    @FXML
    private void openMainMenuView() throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("main_menu_view.fxml"));
        scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
    }
}
