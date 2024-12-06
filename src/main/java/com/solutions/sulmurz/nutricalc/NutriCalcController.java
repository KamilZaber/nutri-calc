package com.solutions.sulmurz.nutricalc;

import com.solutions.sulmurz.nutricalc.views.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import static com.solutions.sulmurz.nutricalc.NutriCalcMain.*;

public class NutriCalcController {
    MainMenuView mainMenuView = null;
    MyPlansView myPlansView = null;
    MyMealsView myMealsView = null;
    MyIngredientsView myIngredientsView = null;
    NutriCalcModel nutriCalcModel;

    public Scene getNutriCalcMainMenuView() {
        return mainMenuView.getView();
    }

    public Scene getNutriCalcMyPlansView() {
        return myPlansView.getView();
    }

    public NutriCalcModel getNutriCalcModel() {
        return nutriCalcModel;
    }

    NutriCalcController() {
        primaryStage.setWidth(800);
        primaryStage.setHeight(900);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Nutricalc 0.1");

        mainMenuView = new MainMenuView();

        mainMenuView.getMyPlansButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (myPlansView == null) {
                    myPlansView = new MyPlansView();
                    myPlansView.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            primaryStage.setScene(mainMenuView.getView());
                        }
                    });
                }
                primaryStage.setScene(myPlansView.getView());
            }
        });

        mainMenuView.getMyMealsButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (myMealsView == null) {
                    myMealsView = new MyMealsView();
                    myMealsView.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            primaryStage.setScene(mainMenuView.getView());
                        }
                    });
                }
                primaryStage.setScene(myMealsView.getView());
            }
        });

        mainMenuView.getMyIngredientsButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (myIngredientsView == null) {
                    myIngredientsView = new MyIngredientsView();
                    myIngredientsView.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            primaryStage.setScene(mainMenuView.getView());
                        }
                    });
                }
                primaryStage.setScene(myIngredientsView.getView());
            }
        });

        mainMenuView.getExitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });
    }
}
