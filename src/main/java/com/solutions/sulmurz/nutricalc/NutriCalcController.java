package com.solutions.sulmurz.nutricalc;

import com.solutions.sulmurz.nutricalc.views.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import static com.solutions.sulmurz.nutricalc.NutriCalcMain.*;

public class NutriCalcController {
    NutriCalcMainMenuView nutriCalcMainMenuView = null;
    NutriCalcMyPlansView nutriCalcMyPlansView = null;
    NutriCalcMyMealsView nutriCalcMyMealsView = null;
    NutriCalcMyIngredientsView nutriCalcMyIngredientsView = null;
    NutriCalcModel nutriCalcModel;

    public Scene getNutriCalcMainMenuView() {
        return nutriCalcMainMenuView.getView();
    }

    public Scene getNutriCalcMyPlansView() {
        return nutriCalcMyPlansView.getView();
    }

    public NutriCalcModel getNutriCalcModel() {
        return nutriCalcModel;
    }

    NutriCalcController() {
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("Nutricalc 0.1");

        nutriCalcMainMenuView = new NutriCalcMainMenuView();

        nutriCalcMainMenuView.getMyPlansButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (nutriCalcMyPlansView == null) {
                    nutriCalcMyPlansView = new NutriCalcMyPlansView();
                    nutriCalcMyPlansView.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            primaryStage.setScene(nutriCalcMainMenuView.getView());
                        }
                    });
                }
                primaryStage.setScene(nutriCalcMyPlansView.getView());
            }
        });

        nutriCalcMainMenuView.getMyMealsButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (nutriCalcMyMealsView == null) {
                    nutriCalcMyMealsView = new NutriCalcMyMealsView();
                    nutriCalcMyMealsView.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            primaryStage.setScene(nutriCalcMainMenuView.getView());
                        }
                    });
                }
                primaryStage.setScene(nutriCalcMyMealsView.getView());
            }
        });

        nutriCalcMainMenuView.getMyIngredientsButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (nutriCalcMyIngredientsView == null) {
                    nutriCalcMyIngredientsView = new NutriCalcMyIngredientsView();
                    nutriCalcMyIngredientsView.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            primaryStage.setScene(nutriCalcMainMenuView.getView());
                        }
                    });
                }
                primaryStage.setScene(nutriCalcMyIngredientsView.getView());
            }
        });

        nutriCalcMainMenuView.getExitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });
    }
}
