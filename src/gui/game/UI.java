package src.gui.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import src.game.HighScore;
import src.game.LEVEL;
import src.gui.model.GameButton;
import src.gui.model.GameLabel;
import src.gui.model.GameLabelButton;
import src.gui.model.GameSubScene;

public class UI {
    GamePane gp;
    GraphicsContext gc;
    AnchorPane ap;

    public GameSubScene sceneToHide;

    public GameSubScene menuHowToPlaySubScene;
    public GameSubScene menuHightScoreSubScene;
    public GameSubScene menuSettingsSubScene;

    public GameSubScene gameSettingsSubScene;
    public GameSubScene gameLevelSubScene;
    public GameSubScene gameOverSubScene;
    public GameSubScene gameHightScoreSubScene;

    public GameLabel livesLabel;
    public GameLabel scoreLabel;
    public GameLabel timeLabel;
    public GameLabel levelLabel;

    public GameLabelButton pauseButton;

    public Font gameFont = Font.font("Poppins", FontWeight.BOLD, 16);
    public List<Message> messages = new ArrayList<Message>();

    public UI(GamePane gp) {
        this.gp = gp;
        this.gc = gp.gc;
        this.ap = gp.gamePane;
        createGameSubScenes();
        createButtons();
        createGameInfoLabels();
    }

    public UI(AnchorPane ap) {
        this.ap = ap;
    }

    // * InGame Messages
    public class Message {
        public String message = "";
        public int x;
        public int y;
        public Color color;
        public int messageCounter = 0;
        public boolean messageOn = false;
        public boolean moveWithPlayers = false;

        public Message(String message, int x, int y, Color color, boolean moveWithPlayers) {
            this.message = message;
            this.x = x;
            this.y = y;
            this.color = color;
            this.moveWithPlayers = moveWithPlayers;
        }
    }

    public void showGameMessage(String message, int x, int y, Color color, boolean moveWithPlayers) {
        Message newMessage = new Message(message, x, y, color, moveWithPlayers);
        newMessage.messageOn = true;
        messages.add(newMessage);
    }

    public void render(GraphicsContext gc) {
        for (int i = 0; i < messages.size(); i++) {
            Message m = messages.get(i);
            if (m.messageOn) {
                gc.setFill(m.color);
                gc.setFont(gameFont);
                if (m.moveWithPlayers) {
                    gc.fillText(m.message, gp.player.x - 30, gp.player.y - (5 + i * 16));
                } else {
                    gc.fillText(m.message, m.x, m.y);
                }
            }
            m.messageCounter++;
            if (m.messageCounter > 90) {
                m.messageOn = false;
                m.messageCounter = 0;
                messages.remove(m);
            }
        }
    }

    // * Buttons
    public void createButtons() {
        createPauseButton();
    }

    public void createPauseButton() {
        pauseButton = new GameLabelButton("||");
        // pauseButton.setStyle(BUTTON_STYLE);
        pauseButton.setLayoutX(960);
        pauseButton.setLayoutY(30);
        pauseButton.setPrefWidth(72);
        // pauseButton.getStyleClass().add("game-btn");
        ap.getChildren().add(pauseButton);
        // pauseButton.setOnAction(value);

        pauseButton.setOnMouseClicked(e -> {
            pauseAction();
        });
    }

    public void pauseAction() {
        if (gp.gameState == gp.PLAY_STATE) {
            gp.gameState = gp.PAUSE_STATE;
            pauseButton.setText("▶");
        } else if (gp.gameState == gp.PAUSE_STATE) {
            gp.gameState = gp.PLAY_STATE;
            pauseButton.setText("||");
        }
        showSubScene(gameSettingsSubScene);
    }

    // * SubScenes
    public void createMenuSubScenes() {
        createHowToPlaySubScene();
        createMenuHighScoreSubScene();
    }

    public void createGameSubScenes() {
        createPauseSubScene();
        // createGameHighScoreSubScene();
    }

    public void showSubScene(GameSubScene subScene) {
        if (sceneToHide == null) {
            subScene.moveSubScene();
            sceneToHide = subScene;
        } else if (!sceneToHide.equals(subScene)) {
            sceneToHide.moveSubScene();
            subScene.moveSubScene();
            sceneToHide = subScene;
        } else {
            sceneToHide.moveSubScene();
        }
    }

    public void createHowToPlaySubScene() {
        menuHowToPlaySubScene = new GameSubScene();
        menuHowToPlaySubScene.toX = -(510);
        // menuHowToPlaySubScene.setWidth(1000);
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("./res/howtoplay/HowToPlay.txt", StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fromText;
        String line = "";
        try {
            while ((fromText = in.readLine()) != null) {
                line += fromText + '\n';
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Text text = new Text();
        text.setText(line);
        text.setWrappingWidth(340.0);
        text.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");

        AnchorPane.setTopAnchor(text, 10.0);
        AnchorPane.setLeftAnchor(text, 30.0);
        AnchorPane.setRightAnchor(text, 30.0);
        menuHowToPlaySubScene.getPane().getChildren().add(text);
        ap.getChildren().add(menuHowToPlaySubScene);
    }

    public void createMenuHighScoreSubScene() {
        menuHightScoreSubScene = new GameSubScene();
        menuHightScoreSubScene.toX = -(510);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(HighScore.highScoreFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fromText;
        String line = "";
        try {
            while ((fromText = in.readLine()) != null) {
                line += fromText + '\n';
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Label label = new Label();
        label.setText(line);
        label.setTextFill(Color.web("#330099"));
        label.setFont(new Font("iCielBC Cubano", 25));

        AnchorPane.setTopAnchor(label, 10.0);
        AnchorPane.setLeftAnchor(label, 150.0);
        AnchorPane.setRightAnchor(label, 30.0);
        menuHightScoreSubScene.getPane().getChildren().add(label);
        ap.getChildren().add(menuHightScoreSubScene);
    }

    public void createPauseSubScene() {
        gameSettingsSubScene = new GameSubScene();
        // gameSettings.setLayoutX((1072 - gameSettings.getWidth()) / 2);
        GameLabelButton exitToMenuButton = new GameLabelButton("Quit");
        GameLabelButton backToGameButton = new GameLabelButton("Back");
        backToGameButton.setOnMouseClicked(e -> {
            gp.gameState = gp.PLAY_STATE;
            showSubScene(gameSettingsSubScene);
        });
        exitToMenuButton.setOnMouseClicked(e -> {
            try {
                // gp.levelIndex = 0;
                // gp.Level = LEVEL.values()[gp.levelIndex];
                gp.gameState = gp.GAME_QUIT_STATE;
                AnchorPane menu = FXMLLoader.load(getClass().getResource("/src/gui/scenes/menu/menu.fxml"));
                ap.getChildren().clear();
                ap.getChildren().remove(gameHightScoreSubScene);
                gp.isHighScore = false;
                // gp.gameThread.stop();
                ap.getChildren().setAll(menu);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setSpacing(80);
        AnchorPane.setTopAnchor(buttonContainer, (double) (160));
        AnchorPane.setLeftAnchor(buttonContainer, (double) 0);
        AnchorPane.setRightAnchor(buttonContainer, (double) 0);
        buttonContainer.getChildren().add(exitToMenuButton);
        buttonContainer.getChildren().add(backToGameButton);
        gameSettingsSubScene.getPane().getChildren().add(buttonContainer);
        ap.getChildren().add(gameSettingsSubScene);
    }

    public void createGameHighScoreSubScene() {
        gameHightScoreSubScene = new GameSubScene();
        GameLabel text = new GameLabel("New High Score!");
        AnchorPane.setTopAnchor(text, (double) (40));
        AnchorPane.setLeftAnchor(text, (double) 30);

        GameLabel highScoreName = new GameLabel("Name: ");

        String LABEL_STYLE = "-fx-font-size: 20;-fx-text-fill: #6fe4f9;-fx-font-family: 'Poppins';-fx-background-color: #0b2364;-fx-background-radius: 15;-fx-padding: 5 10 5 10;-fx-border-color: #6fe4f9;-fx-border-radius: 15;";

        String TEXT_FIELD_STYLE = "-fx-font-size: 16;-fx-text-fill: #fff;-fx-font-family: 'Poppins';-fx-background-color: #0b2364;-fx-background-radius: 15;-fx-border-color: #6fe4f9;-fx-border-radius: 15;";

        highScoreName.setStyle(LABEL_STYLE);
        // highScoreName.setFont(Font.font("Poppins", 12));
        TextField highScoreNameInput = new TextField();
        // highScoreNameInput.setFont(new Font("Poppins", 16));
        highScoreNameInput.setStyle(TEXT_FIELD_STYLE);
        highScoreNameInput.setPrefWidth(200);

        HBox highScoreNameContainer = new HBox();
        highScoreNameContainer.setAlignment(Pos.CENTER);
        highScoreNameContainer.setSpacing(30);
        AnchorPane.setTopAnchor(highScoreNameContainer, (double) (100));
        AnchorPane.setLeftAnchor(highScoreNameContainer, (double) 30);
        AnchorPane.setRightAnchor(highScoreNameContainer, (double) 30);

        highScoreNameContainer.getChildren().add(highScoreName);
        highScoreNameContainer.getChildren().add(highScoreNameInput);

        GameLabelButton saveHightScoreButton = new GameLabelButton("Save");
        AnchorPane.setTopAnchor(saveHightScoreButton, (double) (160));
        AnchorPane.setLeftAnchor(saveHightScoreButton, (double) 150);

        saveHightScoreButton.setOnMouseClicked(e -> {
            String name = highScoreNameInput.getText();
            HighScore.saveHightScore(gp.player.score, name);
            ap.getChildren().remove(gameHightScoreSubScene);
            AnchorPane menu;
            try {
                menu = FXMLLoader.load(getClass().getResource("/src/gui/scenes/menu/menu.fxml"));
                ap.getChildren().clear();
                ap.getChildren().setAll(menu);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        gameHightScoreSubScene.getPane().getChildren().add(text);
        gameHightScoreSubScene.getPane().getChildren().add(highScoreNameContainer);
        gameHightScoreSubScene.getPane().getChildren().add(saveHightScoreButton);
        ap.getChildren().add(gameHightScoreSubScene);
    }

    // * game info label
    public void createGameInfoLabels() {
        createLivesLabel();
        createScoreLabel();
        createTimeLabel();
        createLevelLabel();
    }

    public void createLivesLabel() {
        livesLabel = new GameLabel("Lives: " + gp.player.lives);
        livesLabel.setLayoutX(40);
        livesLabel.setLayoutY(30);
        ap.getChildren().add(livesLabel);
    }

    public void createScoreLabel() {
        scoreLabel = new GameLabel("Score: " + gp.player.score);
        scoreLabel.setLayoutX(300);
        scoreLabel.setLayoutY(30);
        ap.getChildren().add(scoreLabel);
    }

    public void createTimeLabel() {
        double levelTimeLeft = gp.levelTimeLimit - gp.levelTimePassed;
        timeLabel = new GameLabel("Time: " + String.format("%.0f", levelTimeLeft));
        timeLabel.setLayoutX(700);
        timeLabel.setLayoutY(30);
        ap.getChildren().add(timeLabel);
    }

    public void createLevelLabel() {
        levelLabel = new GameLabel("LEVEL " + gp.Level.getLevel());

        String LEVEL_LABEL_STYLE = "-fx-font-size: 30;-fx-text-fill: #fff;-fx-font-family: 'Press Start 2P';-fx-background-color: transparent;-fx-background-radius: 15;-fx-padding: 13 20 7 20";

        levelLabel.setStyle(LEVEL_LABEL_STYLE);
        // levelLabel.setLayoutX(430);
        // levelLabel.setLayoutY(530);
        levelLabel.setEffect(new DropShadow(10, Color.web("#6fe4f9")));

        HBox levelLabelContainer = new HBox();
        levelLabelContainer.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(levelLabelContainer, (double) 530);
        AnchorPane.setLeftAnchor(levelLabelContainer, (double) 40);
        AnchorPane.setRightAnchor(levelLabelContainer, (double) 40);
        levelLabelContainer.getChildren().add(levelLabel);

        ap.getChildren().add(levelLabelContainer);
    }
}
