package com.solutions.sulmurz.nutricalc.controllers;

<<<<<<< Updated upstream
import com.solutions.sulmurz.nutricalc.NutriCalcMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class MyIngredientsController {
    private Parent root;
    private Scene scene;
    @FXML
    private Button backButton;
    @FXML
    private void openMainMenuView() throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("main_menu_view.fxml"));
        scene = new Scene(root);
        NutriCalcMain.getPrimaryStage().setScene(scene);
=======
import com.solutions.sulmurz.nutricalc.NutriCalcModel;
import com.solutions.sulmurz.nutricalc.models.IngredientModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MyIngredientsController extends NutriCalcController {
    @FXML
    private ListView<IngredientModel> ingredientsListView;
    @FXML
    private Label nameTextField;
    @FXML
    private VBox macroAmountsVBox;
    @FXML
    private VBox mineralsAmountsVBox;
    @FXML
    private VBox vitaminsAmountsVBox;

    @FXML
    private void initialize() {
        ingredientsListView.getItems().setAll(NutriCalcModel.getIngredientsList());
        ingredientsListView.setCellFactory(ingredientsListView -> new ListCell<>() {        //nowe komórki o określonym działaniu
            @Override
            protected void updateItem(IngredientModel ingredient, boolean empty) {
                super.updateItem(ingredient, empty);                    //nazywanie komórek...
                if(empty || ingredient == null) {
                    setText(null);
                } else {
                    setText(ingredient.getName());                      //po "nazwie" w obiekcie składnika
                }
            }
        });

    }

    private void generateSection(String name, VBox container, String[] fieldsNames, float[] values) {
        int i = 0;
        for(Node node: container.getChildren()) {
            if(node instanceof TextField) {
                ((TextField) node).setText(name);
            } else if(node instanceof Label) {
                ((Label) node).setText(fieldsNames[i] + ": " + values[i]);
                i++;
            }
            node.setVisible(true);
        }
    }

    @FXML
    private void onMouseClicked() {
        IngredientModel ingredient = ingredientsListView.getSelectionModel().getSelectedItem();

        if (ingredient != null) {
            nameTextField.setVisible(true);
            nameTextField.setText(ingredient.getName());

            generateSection("MACRO: ", macroAmountsVBox, NutriCalcModel.getMacroSet(), ingredient.getMacroAmounts());
            generateSection("MINERALS: ", mineralsAmountsVBox, NutriCalcModel.getMineralsSet(), ingredient.getMineralsAmounts());
            generateSection("VITAMINS: ", vitaminsAmountsVBox, NutriCalcModel.getVitaminsSet(), ingredient.getVitaminsAmounts());
        }
>>>>>>> Stashed changes
    }
}
