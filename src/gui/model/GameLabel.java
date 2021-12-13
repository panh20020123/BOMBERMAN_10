package src.gui.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class GameLabel extends Label {
    public final String LABEL_STYLE = "-fx-font-size: 20;-fx-text-fill: #6fe4f9;-fx-font-family: 'Press Start 2P';-fx-background-color: #0b2364;-fx-background-radius: 15;-fx-padding: 13 20 7 20;-fx-border-color: #6fe4f9;-fx-border-radius: 15;";

    public GameLabel(String text) {
        setText(text);
        setWrapText(true);
        setStyle(LABEL_STYLE);
        setAlignment(Pos.CENTER);
    }
}
