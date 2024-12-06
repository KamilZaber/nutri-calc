package com.solutions.sulmurz.nutricalc.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MyPlansView extends NutriCalcView {
    private static Scene myPlansView = null;

    private Label unreadyMessage;
    private Font font;

    @Override
    public Scene getView() {
        return myPlansView;
    }

    public MyPlansView() {
        super();
        font = Font.font("Arial", FontWeight.BOLD, 40);
        unreadyMessage = new Label("Content not yet available.");
        unreadyMessage.setFont(font);

        VBox myPlansViewLayout = new VBox();
        myPlansViewLayout.getChildren().addAll(unreadyMessage, getBackButton());
        myPlansViewLayout.setAlignment(Pos.CENTER);
        myPlansViewLayout.setSpacing(50);

        myPlansView = new Scene(myPlansViewLayout);
    }
}
