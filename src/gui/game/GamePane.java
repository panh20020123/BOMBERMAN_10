package src.gui.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import src.bomb.Bomb;
import src.entities.Bomber;
import src.entities.Entity;
import src.entities.enemy.EnemyManager;
import src.game.HighScore;
import src.game.LEVEL;
import src.graphics.Sprite;
import src.graphics.SpriteSheet;
import src.object.ObjectManager;
import src.tile.Brick;
import src.tile.TileManager;

public class GamePane {
    public AnchorPane gamePane;
    public Scene gameScene;
    public Stage gameStage;
    public Stage primaryStage;

    // * SCREEN SETTING
    public final int originalTilesSize = 16; // 16x16 tile
    public final int scale = 2;

    public int tileSize = originalTilesSize * scale; // 32
    public int maxScreenCol = 31;
    public int maxScreenRow = 13;
    public int screenWidth = 1072;
    public int screenHeight = 600;

    // * WORLD SETTINGS
    public int maxWorldCol = 31;
    public int maxWorldRow = 13;
    // public int worldWidth = tileSize * maxWorldCol; // 992
    // public int worldHeight = tileSize * maxWorldRow; // 416

    public int getWorldWidth() {
        return tileSize * maxWorldCol;
    }

    public int getWorldHeight() {
        return tileSize * maxWorldRow;
    }

    HBox canvasContainer;
    // canvas
    public GraphicsContext gc;
    public Canvas canvas;
    public String levelMapPath;
    public int levelIndex = 0;
    public LEVEL Level;
    public double levelTimeLimit = 200;
    public double levelTimePassed = 0;
    public double levelTimePaused = 0;

    // ui
    public UI ui;

    // sound
    public Sound music = new Sound();
    public Sound se = new Sound();

    // key handler
    public KeyHandler keyHandler;

    public GetImage getImage;

    // map
    public TileManager tileManager;
    public List<Brick> bricks = new ArrayList<Brick>();
    // objects
    public List<ObjectManager> obj = new ArrayList<ObjectManager>();
    public ObjectManager objManager;
    // asset
    public AssetSetter assetSetter;
    // entities
    public Bomber player;
    // enemy
    public List<Entity> enemy = new ArrayList<Entity>();
    // bomb
    public List<Bomb> bombs = new ArrayList<Bomb>();
    // collision checker
    public CollisionChecker collisionChecker;

    // game thread
    public AnimationTimer gameThread;

    // game state
    public int gameState;
    public final int NEW_GAME_STATE = 0;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;
    public final int GAME_WIN_STATE = 3;
    public final int GAME_OVER_STATE = 4;
    public final int GAME_QUIT_STATE = 5;

    public boolean isHighScore;

    public GamePane(Stage primaryStage) {
        // create game pane
        this.primaryStage = primaryStage;
        initGamePane();
        initGame();
        setupGame();
        // game loop
        createGameLoop();
    }

    public void initGamePane() {
        gamePane = new AnchorPane();
        // gamePane.setStyle(PANE_STYLE);
        gamePane.getStyleClass().add("game-background");

        canvasContainer = new HBox();
        // canvasContainer.setEffect(new DropShadow(15, Color.web("#6fe4f9")));
        canvasContainer.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(canvasContainer, 100.0);
        AnchorPane.setLeftAnchor(canvasContainer, 40.0);
        AnchorPane.setRightAnchor(canvasContainer, 40.0);

        // create level
        Level = LEVEL.values()[levelIndex];
        levelMapPath = Level.getMapPath();
        SpriteSheet.tiles = new SpriteSheet(Level.getSpriteSheetPath(), 256);
        maxWorldCol = Level.getMaxWorldCol();
        maxWorldRow = Level.getMaxWorldRow();

        // create canvas
        canvas = new Canvas(getWorldWidth(), getWorldHeight());
        // AnchorPane.setTopAnchor(canvas, 100.0);
        // AnchorPane.setLeftAnchor(canvas, 40.0);
        gc = canvas.getGraphicsContext2D();
        // canvas.setEffect(new DropShadow(15, Color.web("#6fe4f9")));

        canvasContainer.getChildren().add(canvas);

        // gamePane.getChildren().add(canvas);
        gamePane.getChildren().add(canvasContainer);

        gameScene = new Scene(gamePane, screenWidth, screenHeight);
        gameScene.getStylesheets().add("/src/gui/scenes/scenes.css");
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setTitle("Game");
    }

    public void initGame() {
        getImage = new GetImage();
        keyHandler = new KeyHandler(this);
        player = new Bomber(this, keyHandler);
        tileManager = new TileManager(this);
        objManager = new ObjectManager(this);
        assetSetter = new AssetSetter(this);
        collisionChecker = new CollisionChecker(this);
        ui = new UI(this);
        HighScore.loadHighScore();
    }

    public void setupGame() {
        // this.primaryStage = primaryStage;
        // primaryStage.hide();
        gameState = PLAY_STATE;
        isHighScore = false;

        // set objects (power ups)
        assetSetter.setObject();
        // set bricks
        tileManager.setBrick();
        // set enemies
        assetSetter.setEnemy();

        playMusic(0);
        playSE(1);

        // gameStage.show();
    }

    public long startTime;

    public void createGameLoop() {
        gameThread = new AnimationTimer() {
            @Override
            public void start() {
                startTime = System.nanoTime();
                super.start();
            }

            @Override
            public void handle(long now) {
                render();
                update(now);
            }
        };
        gameThread.start();
    }

    public void update(long now) {
        // System.out.println(gameState);
        if (gameState == PLAY_STATE) {
            gamePlayState();
            levelTimePassed = (double) ((now - startTime) / 1000000000);
            double levelTimeLeft = levelTimeLimit - levelTimePassed;
            if (levelTimeLeft >= 0) {
                ui.timeLabel.setText("Time: " + String.format("%.0f", levelTimeLeft));
            } else {
                gameState = GAME_OVER_STATE;
            }
        }
        if (gameState == PAUSE_STATE) {
            gamePauseState();
            startTime = (long) (now - levelTimePassed * 1000000000);
        }
        if (gameState == NEW_GAME_STATE) {
            newGameState();
        }
        if (gameState == GAME_WIN_STATE) {
            gameWinState();
        }
        if (gameState == GAME_OVER_STATE) {
            gameOverState();
        }
        if (gameState == GAME_QUIT_STATE) {
            gameQuitState();
        }
    }

    public void gamePlayState() {
        // object
        objManager.update();

        // player
        player.update();

        // enemy
        for (int i = 0; i < enemy.size(); i++) {
            if (enemy.get(i) != null) {
                enemy.get(i).update();
            }
        }

        // bomb
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i) != null) {
                bombs.get(i).update();
            }
        }

        music.play();
        se.play();
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).spriteCounter >= 50 && bombs.get(i).spriteCounter < Bomb.explodeTime) {
                bombs.get(i).bombSE.play();
            }
        }
    }

    public void gamePauseState() {
        music.pause();
        se.pause();
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).spriteCounter >= 50 && bombs.get(i).spriteCounter < Bomb.explodeTime) {
                bombs.get(i).bombSE.pause();
            }
        }
    }

    public void newGameState() {
        // level up
        if (levelIndex == Level.getLevel()) {
            music.stop();
            Level = LEVEL.values()[levelIndex];

            levelMapPath = Level.getMapPath();
            SpriteSheet.tiles = new SpriteSheet(Level.getSpriteSheetPath(), 256);
            // System.out.println(Sprite.balloom_dead._sheet._path);
            maxWorldCol = Level.getMaxWorldCol();
            maxWorldRow = Level.getMaxWorldRow();

            startTime = System.nanoTime();
            canvas.setWidth(getWorldWidth());
            canvas.setHeight(getWorldHeight());
            canvasContainer.getChildren().clear();
            canvasContainer.getChildren().add(canvas);

            // getImage = new GetImage();
            // System.out.println(Sprite.balloom_dead._sheet._path);

            // player.getImage();
            // for (int i = 0; i < enemy.size(); i++) {
            // enemy.get(i).getImage();
            // }
            // tileManager.getImage();

            bricks.clear();
            enemy.clear();
            bombs.clear();
            obj.clear();

            ui.levelLabel.setText("LEVEL " + Level.getLevel());

            tileManager.levelMapPath = levelMapPath;
            tileManager.loadMap();
            setupGame();
        }
        player.setDefaultValues();
        gameState = PLAY_STATE;
    }

    public void gameWinState() {
        levelIndex = 0;
        Bomb.bomb_radius = 1;
        endGameAction();
        // gameState = NEW_GAME_STATE;
    }

    public void gameOverState() {
        levelIndex = 0;
        Bomb.bomb_radius = 1;
        music.stop();
        se.stop();
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).spriteCounter >= 50 && bombs.get(i).spriteCounter < Bomb.explodeTime) {
                bombs.get(i).bombSE.stop();
            }
        }
        // ui.showSubScene(ui.hightScoreSubScene);
        endGameAction();

        gameThread.stop();
        // gameState = NEW_GAME_STATE;
    }

    public void gameQuitState() {
        levelIndex = 0;
        Bomb.bomb_radius = 1;
        music.stop();
        se.stop();
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).spriteCounter >= 50 && bombs.get(i).spriteCounter < Bomb.explodeTime) {
                bombs.get(i).bombSE.stop();
            }
        }

        gameThread.stop();
    }

    public void endGameAction() {
        if (HighScore.setScore.size() <= 4) {
            isHighScore = true;
        } else {
            // if (HighScore.s.length > 0) {
            // for (Map.Entry<Integer, String> entry : HighScore.hightScores.entrySet()) {
            // if (player.score >= entry.getKey()) {
            // isHighScore = true;
            // break;
            // }
            // }
            for (int i = 0; i < HighScore.setScore.size(); i++) {
                if (player.score >= HighScore.setScore.get(i).score) {
                    isHighScore = true;
                    break;
                }
            }
            // } else {
            // isHighScore = true;
            // }
        }

        if (isHighScore) {
            ui.createGameHighScoreSubScene();
            ui.showSubScene(ui.gameHightScoreSubScene);
            // for (int i = 0; i < array.length; i++) {

            // }
        }

        // AnchorPane menu;
        // try {
        // music.stop();
        // menu =
        // FXMLLoader.load(getClass().getResource("/src/gui/scenes/menu/menu.fxml"));
        // gamePane.getChildren().clear();
        // // gp.gameThread.stop();
        // gamePane.getChildren().setAll(menu);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // draw map
        tileManager.render(gc);

        // draw objects
        for (int i = 0; i < obj.size(); i++) {
            if (obj.get(i) != null) {
                obj.get(i).render(gc);
            }
        }

        // draw brick
        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i) != null) {
                bricks.get(i).render(gc);
            }
        }

        // draw bomb
        for (int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).spriteCounter >= Bomb.explodeTime) {
                bombs.get(i).spriteCounter = 0;
                bombs.remove(i);
                player.bombs++;
            } else {
                if (bombs.get(i) != null) {
                    bombs.get(i).render(gc);
                }
            }
        }

        // draw enemies
        for (int i = 0; i < enemy.size(); i++) {
            if (enemy.get(i) != null) {
                enemy.get(i).render(gc);
            }
        }

        // draw player
        player.render(gc);

        // draw UI
        ui.render(gc);
    }

    public void playMusic(int i) {
        music.setSound(i, 0.5);
        music.play();
        music.loop();
    }

    public void playSE(int i) {
        se.setSound(i, 0.7);
        se.play();
    }
}
