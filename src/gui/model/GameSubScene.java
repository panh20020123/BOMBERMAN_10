package src.gui.model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class GameSubScene extends SubScene {
    private final static String BACKGROUND_IMAGE = "/res/img/UI/blue_panel.png";

    public boolean isHidden;

    private static int width = 400;
    private static int height = 250;

    public double toX = -((1072 + this.getWidth()) / 2);

    public GameSubScene() {
        super(new AnchorPane(), width, height);
        prefWidth(width);
        prefHeight(height);
        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, width, height, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root = (AnchorPane) this.getRoot();
        root.setBackground(new Background(image));

        setLayoutX(1072);
        setLayoutY((600 - height) / 2);
        // isHidden = true;
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));

        // decide what to move
        transition.setNode(this);

        if (isHidden) {
            transition.setToX(0);
        } else {
            transition.setToX(toX);
        }
        isHidden = !isHidden;

        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
