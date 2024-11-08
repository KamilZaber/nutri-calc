package com.solutions.sulmurz.nutricalc.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NutriCalcMyIngredientsView extends View {
    private static Scene myIngredientsView = null;

    private Label unreadyMessage;
    private Font font;

    @Override
    public Scene getView() {
        return myIngredientsView;
    }

    public NutriCalcMyIngredientsView() {
        super();
        font = Font.font("Arial", FontWeight.BOLD, 40);
        unreadyMessage = new Label("Content not yet available.");
        unreadyMessage.setFont(font);

        VBox myPlansViewLayout = new VBox();
        myPlansViewLayout.getChildren().addAll(unreadyMessage, getBackButton());
        myPlansViewLayout.setAlignment(Pos.CENTER);
        myPlansViewLayout.setSpacing(50);

        myIngredientsView = new Scene(myPlansViewLayout);
    }
}
