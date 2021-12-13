package src.gui.scenes.loading;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

public class loadingController implements Initializable {
    @FXML
    private ProgressBar myProgressBar;

    @FXML
    private Label logo;

    // to prevent loading screen to show again
    public static Boolean isSplashLoaded = false;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        playProgressBar();
    }

    // public void playProgressBar(ProgressBar progressBar) {
    public void playProgressBar() {
        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ONE,
                        new KeyValue(this.myProgressBar.progressProperty(), 0)),
                new KeyFrame(
                        Duration.seconds(5),
                        new KeyValue(logo.opacityProperty(), 1),
                        new KeyValue(this.myProgressBar.progressProperty(), 1),
                        new KeyValue(this.myProgressBar.opacityProperty(), 1)),
                new KeyFrame(
                        Duration.seconds(6),
                        new KeyValue(logo.opacityProperty(), 0),
                        new KeyValue(this.myProgressBar.opacityProperty(), 0)));

        task.playFromStart();
    }
}
