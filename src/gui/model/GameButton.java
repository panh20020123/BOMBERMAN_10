package src.gui.model;

import javafx.scene.control.Button;

public class GameButton extends Button {
    private final String BUTTON_STYLE = "-fx-padding: 13 20 7 20;	-fx-font-family: 'Press Start 2P';	-fx-font-size: 16;	-fx-background-color: #0b2364;	-fx-background-radius: 15;-fx-text-fill: #6fe4f9;";
    private final String BUTTON_HOVER_STYLE = "-fx-padding: 13 20 7 20;	-fx-font-family: 'Press Start 2P';	-fx-font-size: 16;	-fx-background-color: #66addf;	-fx-background-radius: 15;-fx-text-fill: #fff;";

    public GameButton(String text) {
        super(text);
        setStyle(BUTTON_STYLE);
        // setFocused(arg0);
        // getStyleClass().add("game-button");
        initializeButtonListeners();
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
