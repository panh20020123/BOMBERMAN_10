package src.gui.game;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler {
    GamePane gp;

    public boolean up, down, left, right, space;

    public KeyHandler(GamePane gp) {
        this.gp = gp;
        createKeyListeners();
    }

    public void createKeyListeners() {
        gp.gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
                    up = true;
                } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                    down = true;
                } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
                    left = true;
                } else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                    right = true;
                } else if (e.getCode() == KeyCode.SPACE) {
                    space = true;
                } else if (e.getCode() == KeyCode.ESCAPE) {
                    // if (gp.gameState == gp.PLAY_STATE) {
                    // gp.gameState = gp.PAUSE_STATE;
                    // } else if (gp.gameState == gp.PAUSE_STATE) {
                    // gp.gameState = gp.PLAY_STATE;
                    // }
                    gp.ui.pauseAction();
                }
            }
        });

        gp.gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
                    up = false;
                } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                    down = false;
                } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A) {
                    left = false;
                } else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D) {
                    right = false;
                } else if (e.getCode() == KeyCode.SPACE) {
                    space = false;
                }
            }
        });
    }
}
