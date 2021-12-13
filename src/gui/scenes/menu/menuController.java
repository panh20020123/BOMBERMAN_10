package src.gui.scenes.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import src.gui.game.GamePane;
import src.gui.game.Sound;
import src.gui.game.UI;
import src.gui.model.GameLabelButton;
import src.gui.model.GameSubScene;
import src.gui.scenes.loading.loadingController;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;

public class menuController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Button quit;

    @FXML
    private Button showHighscore;

    @FXML
    private Button showInstruction;

    @FXML
    private Button startGame;

    public UI ui;

    public Sound music;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ui = new UI(root);
        ui.createMenuSubScenes();

        music = new Sound();
        music.setSound(8, 0.4);
        music.play();
        music.loop();
        // load loading scene
        if (!loadingController.isSplashLoaded) {
            loadSplashScene();
        }
    }

    public void loadSplashScene() {
        try {
            AnchorPane loadingPane = FXMLLoader.load(getClass().getResource("/src/gui/scenes/loading/loading.fxml"));
            root.getChildren().setAll(loadingPane);

            // fade in
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(5.5), loadingPane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), loadingPane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();
            loadingController.isSplashLoaded = true;

            // fade out
            fadeIn.setOnFinished(e -> {
                fadeOut.play();
            });

            // load menu scene
            fadeOut.setOnFinished(e -> {
                try {
                    AnchorPane parentContent = FXMLLoader
                            .load(getClass().getResource("/src/gui/scenes/menu/menu.fxml"));
                    root.getChildren().setAll(parentContent);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        Stage primaryStage;
        primaryStage = (Stage) root.getScene().getWindow();
        GamePane gamePane = new GamePane(primaryStage);
        // primaryStage.hide();
        // gamePane.setupGame();
        gamePane.gameState = gamePane.NEW_GAME_STATE;
        music.stop();
        primaryStage.setScene(gamePane.gameScene);
    }

    public void showHighscore() {
        ui.showSubScene(ui.menuHightScoreSubScene);
    }

    public void showHowToPlay() {
        ui.showSubScene(ui.menuHowToPlaySubScene);
    }

    public void quit() {
        System.exit(0);
    }
}