package com.solutions.sulmurz.nutricalc.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenuView extends NutriCalcView {
    private static Scene mainMenuView = null;

    private Font titleFont;
    private Label titleLabel;
    private Button myPlansButton;
    private Button myMealsButton;
    private Button myIngredientsButton;
    private Button exitButton;
    private VBox mainMenuLayout;

    @Override
    public Scene getView() {
        return mainMenuView;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public Button getMyPlansButton() {
        return myPlansButton;
    }

    public Button getMyMealsButton() {
        return myMealsButton;
    }

    public Button getMyIngredientsButton() {
        return myIngredientsButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public MainMenuView() {
        super();
        titleFont = Font.font("BLACK METAL", FontWeight.BOLD, 40);
        titleLabel = new Label("NutriCalc 0.1");
        myPlansButton = new Button("My Plans");
        myMealsButton = new Button("My Meals");
        myIngredientsButton = new Button("My Ingredients");
        exitButton = new Button("Exit");

        myPlansButton.setPrefSize(150, 100);
        myMealsButton.setPrefSize(150, 100);
        myIngredientsButton.setPrefSize(150, 100);
        exitButton.setPrefSize(150, 100);
        titleLabel.setFont(titleFont);

        mainMenuLayout = new VBox();
        mainMenuLayout.setAlignment(Pos.CENTER);
        mainMenuLayout.setSpacing(10);
        mainMenuLayout.getChildren().addAll(titleLabel, myPlansButton, myMealsButton, myIngredientsButton, exitButton);

        mainMenuView = new Scene(mainMenuLayout);
    }
}
