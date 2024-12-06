package com.solutions.sulmurz.nutricalc.views;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MyIngredientsView extends NutriCalcView {
    private static Scene myIngredientsView = null;

    private Font titleFont;
    private Image magnifierSymbol;
    private ImageView magnifierSymbolView;
    private BorderPane myIngredientsViewLayout;
    private TextField searchField;
    private ListView myIngredientsList;
    private TextArea ingredientOverview;
    private ScrollPane ingredientsListScroll;
    //private HBox overviewPanel;
    private ScrollPane overviewScroll;
    private Button addButton;
    private Button editButton;
    private Button deleteButton;
    private Button searchButton;
    private Pane buttonsPanel;
    private HBox searchPanel;


    @Override
    public Scene getView() {
        return myIngredientsView;
    }

    public MyIngredientsView() {
        super();
        titleFont = Font.font("Arial", FontWeight.BOLD, 40);
        magnifierSymbol = new Image("lupa.png", 15, 15, false, false);
        magnifierSymbolView = new ImageView(magnifierSymbol);

        myIngredientsViewLayout = new BorderPane();
        searchPanel = new HBox();
        searchField = new TextField();
        searchButton = new Button();
        myIngredientsList = new ListView<>();
        ingredientOverview = new TextArea();
        ingredientsListScroll = new ScrollPane();
        overviewScroll = new ScrollPane();
        buttonsPanel = new Pane();
        addButton = new Button("ADD");
        editButton = new Button("EDIT");
        deleteButton = new Button("DELETE");

        searchField.setPrefSize(800,20);
        searchButton.setGraphic(magnifierSymbolView);
        searchPanel.getChildren().addAll(searchField, searchButton);
        myIngredientsList.setPrefSize(370,2000);
        ingredientsListScroll.setContent(myIngredientsList);
        ingredientsListScroll.setPrefSize(390, 2000);
        ingredientOverview.setEditable(false);
        ingredientOverview.setPrefSize(370, 2000);
        overviewScroll.setContent(ingredientOverview);
        overviewScroll.setPrefSize(390,2000);
        addButton.setPrefSize(100,50);
        editButton.setPrefSize(100,50);
        deleteButton.setPrefSize(100,50);
        addButton.setLayoutX(0);
        editButton.setLayoutX(135);
        deleteButton.setLayoutX(270);
        backButton.setLayoutX(684);
        buttonsPanel.getChildren().addAll(addButton, editButton, deleteButton, backButton);
        myIngredientsViewLayout.setTop(searchPanel);
        myIngredientsViewLayout.setLeft(ingredientsListScroll);
        myIngredientsViewLayout.setRight(overviewScroll);
        myIngredientsViewLayout.setBottom(buttonsPanel);

        myIngredientsView = new Scene(myIngredientsViewLayout);
    }
}
