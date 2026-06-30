package com.solutions.sulmurz.nutricalc.controllers;

import com.solutions.sulmurz.nutricalc.models.PlanElementModel;
import com.solutions.sulmurz.nutricalc.models.PlanModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChooseElementController extends NutriCalcController {
    @FXML
    private Label titleLabel;
    @FXML
    private VBox elementsBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;
    private PlanElementModel selectedElement;
    private GridPane selectedElementBar;
    private boolean chosen = false;

    @FXML
    private void onCancelButtonClick() {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    @FXML
    private void onAddButtonClick() {
        if(selectedElement != null) {
            chosen = true;
            ((Stage) cancelButton.getScene().getWindow()).close();
        } else {
            showPrompt("Select an element to copy.");
        }
    }

    public void setSelectedPlan(PlanElementModel element, GridPane elementBox) {
        this.selectedElement = element;

        if(selectedElementBar != null) {
            selectedElementBar.getStyleClass().remove("selected-element-bar");
        }

        selectedElementBar = elementBox;
        selectedElementBar.getStyleClass().add("selected-element-bar");
    }

    @FXML
    protected void setup(List<? extends PlanElementModel> elementsList) {
        for(PlanElementModel element: elementsList) {
            addToView(element);
        }
    }

    private void addToView(PlanElementModel element) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/element_to_copy_preview_view.fxml")
            );

            GridPane elementBar = loader.load();
            ElementToCopyPreviewController controller = loader.getController();
            controller.setElement(element);
            controller.setCallerController(this);

            elementsBox.getChildren().add(elementBar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlanElementModel getSelectedElement() {
        if(chosen) {
            return selectedElement;
        } else {
            return null;
        }
    }
}
