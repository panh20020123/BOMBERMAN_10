package src.bomb;

import java.util.concurrent.atomic.AtomicBoolean;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import src.entities.Bomber;
import src.entities.Entity;
import src.entities.enemy.EnemyManager;
import src.gui.game.GamePane;
import src.gui.game.Sound;

public class Bomb {
    public GamePane gp;
    public GraphicsContext gc;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public static int bomb_radius = 1;
    public static int toExplodeTime = 120;
    public static int explodeTime = 150;

    // to check if player just set a bomb and still stand on top of the bomb
    public boolean setBomb = true;

    public int x;
    public int y;

    // bomb
    public Image bomb, bomb1, bomb2;
    // bomb explode
    public Image bomb_exploded, bomb_exploded1, bomb_exploded2;

    // flame
    public Image flame_mid_ver, flame_mid_ver1, flame_mid_ver2;
    public Image flame_mid_hor, flame_mid_hor1, flame_mid_hor2;
    public Image flame_left_hor, flame_left_hor1, flame_left_hor2;
    public Image flame_right_hor, flame_right_hor1, flame_right_hor2;
    public Image flame_top_ver, flame_top_ver1, flame_top_ver2;
    public Image flame_bot_ver, flame_bot_ver1, flame_bot_ver2;

    public Sound bombSE = new Sound();

    public Bomb(GamePane gp) {
        this.gp = gp;
        setImage();
        setBomb = true;
    }

    public Bomb(GamePane gp, int x, int y) {
        this(gp);
        this.x = x * gp.tileSize;
        this.y = y * gp.tileSize;
    }

    public void setImage() {
        bomb = gp.getImage.bomb;
        bomb1 = gp.getImage.bomb1;
        bomb2 = gp.getImage.bomb2;

        bomb_exploded = gp.getImage.bomb_exploded;
        bomb_exploded1 = gp.getImage.bomb_exploded1;
        bomb_exploded2 = gp.getImage.bomb_exploded2;

        flame_mid_ver = gp.getImage.flame_mid_ver;
        flame_mid_ver1 = gp.getImage.flame_mid_ver1;
        flame_mid_ver2 = gp.getImage.flame_mid_ver2;
        flame_mid_hor = gp.getImage.flame_mid_hor;
        flame_mid_hor1 = gp.getImage.flame_mid_hor1;
        flame_mid_hor2 = gp.getImage.flame_mid_hor2;
        flame_left_hor = gp.getImage.flame_left_hor;
        flame_left_hor1 = gp.getImage.flame_left_hor1;
        flame_left_hor2 = gp.getImage.flame_left_hor2;
        flame_right_hor = gp.getImage.flame_right_hor;
        flame_right_hor1 = gp.getImage.flame_right_hor1;
        flame_right_hor2 = gp.getImage.flame_right_hor2;
        flame_top_ver = gp.getImage.flame_top_ver;
        flame_top_ver1 = gp.getImage.flame_top_ver1;
        flame_top_ver2 = gp.getImage.flame_top_ver2;
        flame_bot_ver = gp.getImage.flame_bot_ver;
        flame_bot_ver1 = gp.getImage.flame_bot_ver1;
        flame_bot_ver2 = gp.getImage.flame_bot_ver2;
    }

    public void reset() {
        setBomb = true;
        spriteCounter = 0;
        spriteNum = 1;
        bomb_radius = 1;
        toExplodeTime = 120;
        explodeTime = 150;
    }

    public void update() {
        spriteCounter++;
        if (spriteCounter == 50) {
            bombSE.setSound(3, 0.7);
            bombSE.play();
            bombSE.loop();
        } else if (spriteCounter == toExplodeTime) {
            bombSE.stop();
            bombSE.setSound(4, 0.8);
            bombSE.play();
        } else if (spriteCounter == explodeTime) {
            bombSE.stop();
        }
        if (spriteCounter > 0 && spriteCounter < toExplodeTime) {
            int loop = 3;
            for (int i = 0; i < loop; i++) {
                if (spriteCounter > (toExplodeTime * i / loop) + 0
                        && spriteCounter < (toExplodeTime * i / loop) + (toExplodeTime * 1) / (3 * loop)) {
                    spriteNum = 1;
                } else if (spriteCounter > (toExplodeTime * i / loop) + (toExplodeTime * 1) / (3 * loop)
                        && spriteCounter < (toExplodeTime * i / loop) + (toExplodeTime * 2) / (3 * loop)) {
                    spriteNum = 2;
                } else if (spriteCounter > (toExplodeTime * i / loop) + (toExplodeTime * 2) / (3 * loop)
                        && spriteCounter < (toExplodeTime * i / loop) + (toExplodeTime * 3) / (3 * loop)) {
                    spriteNum = 3;
                }
            }
        } else if (spriteCounter >= toExplodeTime
                && spriteCounter < ((explodeTime - toExplodeTime) * 1) / 3 + toExplodeTime) {
            spriteNum = 4;
        } else if (spriteCounter > ((explodeTime - toExplodeTime) * 1) / 3 + toExplodeTime
                && spriteCounter < ((explodeTime - toExplodeTime) * 2) / 3 + toExplodeTime) {
            spriteNum = 5;
        } else if (spriteCounter > ((explodeTime - toExplodeTime) * 2) / 3 + toExplodeTime
                && spriteCounter < ((explodeTime - toExplodeTime) * 3) / 3 + toExplodeTime) {
            spriteNum = 6;
            // spriteCounter = 0; // light show
        }

    }

    public void render(GraphicsContext gc) {
        this.gc = gc;
        if (spriteNum == 1) {
            drawFlame(new AtomicBoolean(true), bomb, x, y);
        } else if (spriteNum == 2) {
            drawFlame(new AtomicBoolean(true), bomb1, x, y);
        } else if (spriteNum == 3) {
            drawFlame(new AtomicBoolean(true), bomb2, x, y);
        } else if (spriteNum == 4) {
            drawFlame(new AtomicBoolean(true), bomb_exploded, x, y);
            AtomicBoolean up = new AtomicBoolean(true), down = new AtomicBoolean(true), left = new AtomicBoolean(true),
                    right = new AtomicBoolean(true);
            for (int i = 1; i < bomb_radius; i++) {
                int mid = i * gp.tileSize;
                drawFlame(up, flame_mid_ver, x, y - mid);
                drawFlame(down, flame_mid_ver, x, y + mid);
                drawFlame(left, flame_mid_hor, x - mid, y);
                drawFlame(right, flame_mid_hor, x + mid, y);
            }
            int last = bomb_radius * gp.tileSize;
            drawFlame(up, flame_top_ver, x, y - last);
            drawFlame(down, flame_bot_ver, x, y + last);
            drawFlame(left, flame_left_hor, x - last, y);
            drawFlame(right, flame_right_hor, x + last, y);
        } else if (spriteNum == 5) {
            drawFlame(new AtomicBoolean(true), bomb_exploded1, x, y);
            AtomicBoolean up = new AtomicBoolean(true), down = new AtomicBoolean(true), left = new AtomicBoolean(true),
                    right = new AtomicBoolean(true);
            for (int i = 1; i < bomb_radius; i++) {
                int mid = i * gp.tileSize;
                drawFlame(up, flame_mid_ver1, x, y - mid);
                drawFlame(down, flame_mid_ver1, x, y + mid);
                drawFlame(left, flame_mid_hor1, x - mid, y);
                drawFlame(right, flame_mid_hor1, x + mid, y);
            }
            int last = bomb_radius * gp.tileSize;
            drawFlame(up, flame_top_ver1, x, y - last);
            drawFlame(down, flame_bot_ver1, x, y + last);
            drawFlame(left, flame_left_hor1, x - last, y);
            drawFlame(right, flame_right_hor1, x + last, y);
        } else if (spriteNum == 6) {
            drawFlame(new AtomicBoolean(true), bomb_exploded2, x, y);
            AtomicBoolean up = new AtomicBoolean(true), down = new AtomicBoolean(true), left = new AtomicBoolean(true),
                    right = new AtomicBoolean(true);
            for (int i = 1; i < bomb_radius; i++) {
                int mid = i * gp.tileSize;
                drawFlame(up, flame_mid_ver2, x, y - mid);
                drawFlame(down, flame_mid_ver2, x, y + mid);
                drawFlame(left, flame_mid_hor2, x - mid, y);
                drawFlame(right, flame_mid_hor2, x + mid, y);
            }
            int last = bomb_radius * gp.tileSize;
            drawFlame(up, flame_top_ver2, x, y - last);
            drawFlame(down, flame_bot_ver2, x, y + last);
            drawFlame(left, flame_left_hor2, x - last, y);
            drawFlame(right, flame_right_hor2, x + last, y);
        }
    }

    public boolean checkTile(int col, int row) {
        col = col / gp.tileSize;
        row = row / gp.tileSize;
        if (col > 0 && col < gp.maxWorldCol && row > 0 & row < gp.maxWorldRow) {
            char c = gp.tileManager.liveMapTile[col][row];
            if (c == ' ' || c == 'p' || c == '1' || c == '2') {
                return true;
            }
            for (int i = 0; i < gp.bricks.size(); i++) {
                if (col == gp.bricks.get(i).x / gp.tileSize && row == gp.bricks.get(i).y / gp.tileSize) {
                    gp.bricks.get(i).isExploded = true;
                }
            }
        }
        return false;
    }

    public void checkEntity(Entity entity, int bombX, int bombY) {
        Rectangle solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        // is in area?
        int entityLeftX = (int) (entity.x + entity.solidArea.getX());
        int entityRightX = (int) (entity.x + entity.solidArea.getX() + entity.solidArea.getWidth());
        int entityTopY = (int) (entity.y + entity.solidArea.getY());
        int entityBottomY = (int) (entity.y + entity.solidArea.getY() + entity.solidArea.getHeight());

        int bombLeftX = (int) (bombX + solidArea.getX());
        int bombRightX = (int) (bombX + solidArea.getX() + solidArea.getWidth());
        int bombTopY = (int) (bombY + solidArea.getY());
        int bombBottomY = (int) (bombY + solidArea.getY() + solidArea.getHeight());

        // check dang dung trong hay khong
        if (((entityLeftX > bombLeftX && entityLeftX < bombRightX)
                || (entityRightX > bombLeftX && entityRightX < bombRightX))
                && ((entityTopY > bombTopY && entityTopY < bombBottomY)
                        || (entityBottomY > bombTopY && entityBottomY < bombBottomY))) {
            // check bom da no chua
            if (spriteCounter < toExplodeTime) {
                if (setBomb && entity instanceof Bomber) {
                    entity.collisionOn = false;
                } else {
                    // entity.collisionOn = true;
                    switch (entity.direction) {
                        case "up":
                            entity.direction = "down";
                            break;
                        case "down":
                            entity.direction = "up";
                            break;
                        case "left":
                            entity.direction = "right";
                            break;
                        case "right":
                            entity.direction = "left";
                            break;
                    }
                }
            } else {
                entity.isExploded = true;
            }
        } else {
            if (entity instanceof Bomber) {
                setBomb = false;
            }
            // get entity's solid area position
            entity.solidArea.setX(entity.x + entity.solidArea.getX());
            entity.solidArea.setY(entity.y + entity.solidArea.getY());

            // get object's solid area position
            solidArea.setX(bombX + solidArea.getX());
            solidArea.setY(bombY + solidArea.getY());
            switch (entity.direction) {
                case "up":
                    entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                    if (entity.solidArea.getBoundsInParent()
                            .intersects(solidArea.getBoundsInParent())) {
                        entity.collisionOn = true;
                    }
                    break;

                case "down":
                    entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                    if (entity.solidArea.getBoundsInParent()
                            .intersects(solidArea.getBoundsInParent())) {
                        entity.collisionOn = true;
                    }

                    break;

                case "left":
                    entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                    if (entity.solidArea.getBoundsInParent()
                            .intersects(solidArea.getBoundsInParent())) {
                        entity.collisionOn = true;
                    }
                    break;

                case "right":
                    entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                    if (entity.solidArea.getBoundsInParent()
                            .intersects(solidArea.getBoundsInParent())) {
                        entity.collisionOn = true;
                    }
                    break;

            }
            entity.solidArea.setX(entity.solidAreaDefaultX);
            entity.solidArea.setY(entity.solidAreaDefaultY);
        }
    }

    public void checkEnemy(int bombX, int bombY) {
        for (int i = 0; i < gp.enemy.size(); i++) {
            checkEntity(gp.enemy.get(i), bombX, bombY);
        }
    }

    public void checkPlayer(int bombX, int bombY) {
        checkEntity(gp.player, bombX, bombY);
    }

    public void drawFlame(AtomicBoolean direction, Image image, int bombX, int bombY) {
        if (direction.get() && checkTile(bombX, bombY)) {
            checkEnemy(bombX, bombY);
            checkPlayer(bombX, bombY);
            gc.drawImage(image, bombX, bombY);
        } else {
            direction.set(false);
        }

    }

    // check xem co them duoc bom o day khong
    public boolean checkBomb(Bomb newBomb) {
        for (int i = 0; i < gp.bombs.size(); i++) {
            if (newBomb.x == gp.bombs.get(i).x && newBomb.y == gp.bombs.get(i).y) {
                return false;
            }
        }
        return true;
    }
}
