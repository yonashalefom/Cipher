package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PaneBasedMenuItem extends Pane {

    //region Buttons
    public Button btnLeftOne = new Button("BUTTON TITLE");
    public Button btnLeftTwo = new Button("BUTTON TITLE");
    public Button btnLeftThree = new Button("BUTTON TITLE");
    public Button btnRightOne = new Button("BUTTON TITLE");
    public Button btnRightTwo = new Button("BUTTON TITLE");
    public Button btnRightThree = new Button("BUTTON TITLE");
    //endregion
    //region Panes
    public Pane centerPane = new Pane();
    public Pane centerPaneInside = new Pane();
    public Pane centerPaneHover = new Pane();
    //endregion
    //region Label
    public Label centerPaneTitle = new Label("TITLE");
    //endregion

    public PaneBasedMenuItem() {
        this.setWidth(700);
        this.setMaxWidth(700);
        this.setHeight(250);
        this.setMaxHeight(250);
        this.setOpacity(0.75);
        setToDefault();
    }

    public void setToDefault() {
        //region Button Left One
        btnLeftOne.setPrefWidth(174);
        btnLeftOne.setPrefHeight(40);
        btnLeftOne.setLayoutX(51);
        btnLeftOne.setLayoutY(51);
        btnLeftOne.setTextFill(Color.valueOf("#6da7d6"));
        btnLeftOne.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-font-size: 14px");
        //endregion
        //region Button Left Two
        btnLeftTwo.setPrefWidth(215);
        btnLeftTwo.setPrefHeight(40);
        btnLeftTwo.setLayoutX(0);
        btnLeftTwo.setLayoutY(105);
        btnLeftTwo.setTextFill(Color.valueOf("#6da7d6"));
        btnLeftTwo.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-font-size: 14px");
        //endregion
        //region Button Left Three
        btnLeftThree.setPrefWidth(174);
        btnLeftThree.setPrefHeight(40);
        btnLeftThree.setLayoutX(51);
        btnLeftThree.setLayoutY(158);
        btnLeftThree.setTextFill(Color.valueOf("#6da7d6"));
        btnLeftThree.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-font-size: 14px");
        //endregion
        //region Button Right One
        btnRightOne.setPrefWidth(174);
        btnRightOne.setPrefHeight(40);
        btnRightOne.setLayoutX(475);
        btnRightOne.setLayoutY(51);
        btnRightOne.setTextFill(Color.valueOf("#6da7d6"));
        btnRightOne.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-font-size: 14px");
        //endregion
        //region Button Right Two
        btnRightTwo.setPrefWidth(215);
        btnRightTwo.setPrefHeight(40);
        btnRightTwo.setLayoutX(485);
        btnRightTwo.setLayoutY(105);
        btnRightTwo.setTextFill(Color.valueOf("#6da7d6"));
        btnRightTwo.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-font-size: 14px");
        //endregion
        //region Button Right Three
        btnRightThree.setPrefWidth(174);
        btnRightThree.setPrefHeight(40);
        btnRightThree.setLayoutX(475);
        btnRightThree.setLayoutY(158);
        btnRightThree.setTextFill(Color.valueOf("#6da7d6"));
        btnRightThree.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-font-size: 14px");
        //endregion
        //region Center Pane
        centerPane.setPrefWidth(250);
        centerPane.setPrefHeight(250);
        centerPane.setLayoutX(225);
        centerPane.setLayoutY(0);
        centerPane.setStyle("-fx-background-color: #dfdfdf; -fx-background-radius: 100%");

        centerPaneInside.setPrefWidth(200);
        centerPaneInside.setPrefHeight(200);
        centerPaneInside.setLayoutX(25);
        centerPaneInside.setLayoutY(25);
        centerPaneInside.setStyle("-fx-background-color: #c8c8c8; -fx-background-radius: 100%");

        centerPaneHover.setPrefWidth(200);
        centerPaneHover.setPrefHeight(200);
        centerPaneHover.setLayoutX(0);
        centerPaneHover.setLayoutY(0);
        centerPaneHover.setStyle("-fx-background-color: #404040; -fx-background-radius: 100%; -fx-opacity: 0.0");

        centerPaneInside.getChildren().add(centerPaneHover);
        centerPaneInside.getChildren().add(centerPaneTitle);
        centerPane.getChildren().add(centerPaneInside);
        //endregion
        //region Center Pane Title
        centerPaneTitle.setMaxWidth(200);
        centerPaneTitle.setPrefWidth(200);
        centerPaneTitle.setMaxHeight(50);
        centerPaneTitle.setPrefHeight(50);
        centerPaneTitle.setAlignment(Pos.CENTER);
        centerPaneTitle.setMaxHeight(USE_COMPUTED_SIZE);
        centerPaneTitle.setFont(new Font(28));
        centerPaneTitle.setTextFill(Color.WHITE);
        centerPaneTitle.setLayoutX((centerPaneInside.getPrefWidth() / 2) - (centerPaneTitle.getPrefWidth() / 2));
        centerPaneTitle.setLayoutY((centerPaneInside.getPrefHeight() / 2) - (centerPaneTitle.getPrefHeight() / 2));
        //endregion
        this.getChildren().add(btnLeftOne);
        this.getChildren().add(btnLeftTwo);
        this.getChildren().add(btnLeftThree);
        this.getChildren().add(btnRightOne);
        this.getChildren().add(btnRightTwo);
        this.getChildren().add(btnRightThree);
        this.getChildren().add(centerPane);
        centerPaneInside.setStyle("-fx-background-image: url('../views/hill_cipher_background.png'); -fx-background-color: #6da7d6; -fx-background-radius: 100%");
    }

    public void setLeftOneButtonTitle(String name) {
        btnLeftOne.setText(name);
    }

    public void setLeftTwoButtonTitle(String name) {
        btnLeftTwo.setText(name);
    }

    public void setLeftThreeButtonTitle(String name) {
        btnLeftThree.setText(name);
    }

    public void setRightOneButtonTitle(String name) {
        btnRightOne.setText(name);
    }

    public void setRightTwoButtonTitle(String name) {
        btnRightTwo.setText(name);
    }

    public void setRightThreeButtonTitle(String name) {
        btnRightThree.setText(name);
    }

    public void setImage(String path) {
        centerPaneInside.setStyle("-fx-background-image: url('" + path + "')"); // ../images/hill_cipher_background.png
        System.out.println("Background image set.");
    }

    public void setMainTitle(String title) {
        centerPaneTitle.setText(title);
    }

}
