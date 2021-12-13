package src.gui.model;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;

public class GameLabelButton extends Label {
    private final String BUTTON_STYLE = "-fx-padding: 13 15 7 15;	-fx-font-family: 'Press Start 2P';	-fx-font-size: 20;	-fx-background-color: #0b2364;	-fx-background-radius: 15;-fx-text-fill: #6fe4f9;-fx-border-color: #6fe4f9;-fx-border-radius: 15;";

    private final String BUTTON_HOVER_STYLE = "-fx-padding: 13 15 7 15;	-fx-font-family: 'Press Start 2P';	-fx-font-size: 20;	-fx-background-color: #66addf;	-fx-background-radius: 15;-fx-text-fill: #fff;-fx-border-color: #fff;-fx-border-radius: 15;";

    public GameLabelButton(String text) {
        setText(text);
        setWrapText(true);
        setStyle(BUTTON_STYLE);
        setAlignment(Pos.CENTER);
        initializeButtonListeners();
        setCursor(Cursor.HAND);
    }

    private void initializeButtonListeners() {
        this.setOnMouseEntered(event -> {
            this.setStyle(BUTTON_HOVER_STYLE);
        });
        this.setOnMouseExited(event -> {
            this.setStyle(BUTTON_STYLE);
        });
    }

}
