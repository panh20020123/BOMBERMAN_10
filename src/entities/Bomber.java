package src.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import src.bomb.Bomb;
import src.entities.enemy.EnemyManager;
import src.game.LEVEL;
import src.gui.game.GamePane;
import src.gui.game.KeyHandler;
import src.gui.game.Sound;

public class Bomber extends Entity {
    KeyHandler keyHandler;

    public int bombs = 1;
    public int lives = 3;
    public int score = 0;

    int standCounter = 0;

    public Bomber(GamePane gp, KeyHandler keyHandler) {
        super(gp);
        this.keyHandler = keyHandler;

        speed = 3;
        bombs = 1;
        lives = 3;
        score = 0;

        solidArea = new Rectangle(0, 13, 16, 16);

        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();

        setDefaultValues();
        getImage();
    }

    public void setDefaultValues() {
        x = gp.tileSize;
        y = gp.tileSize;
        // speed = 3;
        direction = "down";
    }

    public void reset() {
        bombs = 1;
        lives = 3;
        score = 0;
        setDefaultValues();
    }

    public void getImage() {
        up1 = gp.getImage.player_up1;
        up2 = gp.getImage.player_up2;
        down1 = gp.getImage.player_down1;
        down2 = gp.getImage.player_down2;
        left1 = gp.getImage.player_left1;
        left2 = gp.getImage.player_left2;
        right1 = gp.getImage.player_right1;
        right2 = gp.getImage.player_right2;
        dead1 = gp.getImage.player_dead1;
        dead2 = gp.getImage.player_dead2;
        dead3 = gp.getImage.player_dead3;
        stand_up = gp.getImage.player_stand_up;
        stand_down = gp.getImage.player_stand_down;
        stand_left = gp.getImage.player_stand_left;
        stand_right = gp.getImage.player_stand_right;
    }

    @Override
    public void update() {
        isDead = (lives <= 0);
        // if (!player.isExploded && !player.isDead) {
        // player.update();
        // } else if (player.isDead) {
        // // gameState = GAME_OVER_STATE;
        // } else if (player.isExploded) {
        // if () {

        // }
        // // gameState = NEW_GAME_STATE;
        // player.loseLive();
        // }

        if (isDead) {
            // gp.gameState = gp.GAME_OVER_STATE;
        } else if (isExploded) {
        } else {
            if (keyHandler.up || keyHandler.down || keyHandler.left || keyHandler.right || keyHandler.space) {
                if (keyHandler.up) {
                    direction = "up";
                } else if (keyHandler.down) {
                    direction = "down";
                } else if (keyHandler.left) {
                    direction = "left";
                } else if (keyHandler.right) {
                    direction = "right";
                }
                if (keyHandler.space) {
                    if (bombs > 0) {
                        int col, row;
                        if ((x % gp.tileSize) >= (gp.tileSize / 2) + 4) {
                            col = (x / gp.tileSize) + 1;
                        } else {
                            col = x / gp.tileSize;
                        }

                        if ((y % gp.tileSize) >= gp.tileSize / 2) {
                            row = y / gp.tileSize + 1;
                        } else {
                            row = y / gp.tileSize;
                        }

                        Bomb bomb = new Bomb(gp, col, row);
                        if (bomb.checkBomb(bomb)) {
                            bomb.setBomb = true;
                            gp.bombs.add(bomb);
                            gp.playSE(2);
                            bombs--;
                        }
                    }
                    direction = "set bomb";
                }

                collisionOn = false;
                // check bomb collision
                for (int i = 0; i < gp.bombs.size(); i++) {
                    Bomb bomb = gp.bombs.get(i);
                    bomb.checkEntity(this, bomb.x, bomb.y);
                }
                // check tile collision
                gp.collisionChecker.checkTile(this);
                // check object collision
                int objIndex = gp.collisionChecker.checkObject(this, true);
                pickUpObject(objIndex);
                // check enemy collision
                int enemyIndex = gp.collisionChecker.checkEntity(this, gp.enemy);
                if (enemyIndex != 999) {
                    Entity enemy = gp.enemy.get(enemyIndex);
                    hitEnemy(enemy);
                    return;
                }

                // if collision is false , player can move
                if (!collisionOn) {
                    switch (direction) {
                        case "up":
                            y -= speed;
                            break;

                        case "down":
                            y += speed;
                            break;

                        case "left":
                            x -= speed;
                            break;

                        case "right":
                            x += speed;
                            break;
                    }
                }

                spriteCounter++;
                if (spriteCounter > 10) {
                    if (spriteNum != 2) {
                        spriteNum = 2;
                    } else if (spriteNum != 1) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            } else {
                standCounter++;
                if (standCounter == 20) {
                    spriteNum = 0;
                    standCounter = 0;
                }
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objName = gp.obj.get(i).name;
            int col = gp.obj.get(i).x / gp.tileSize;
            int row = gp.obj.get(i).y / gp.tileSize;
            switch (objName) {
                case "speed":
                    speed++;
                    gp.ui.showGameMessage("+1 SPEED", x, y + gp.tileSize / 16, Color.web("#6fe4f9"), true);
                    powerUp(i, col, row);
                    break;
                case "flames":
                    Bomb.bomb_radius++;
                    gp.ui.showGameMessage("+1 FLAME", x, y + gp.tileSize / 16, Color.web("#6fe4f9"), true);
                    powerUp(i, col, row);
                    break;
                case "bombs":
                    bombs++;
                    gp.ui.showGameMessage("+1 BOMB", x, y + gp.tileSize / 16, Color.web("#6fe4f9"), true);
                    powerUp(i, col, row);
                    break;
                case "portal":
                    if (gp.enemy.size() <= 0) {
                        gp.levelIndex++;
                        if (gp.levelIndex >= LEVEL.values().length) {
                            gp.gameState = gp.GAME_WIN_STATE;
                            // gp.music.stop();
                            // gp.playSE(9);
                            // gp.se = new Sound();
                        } else {
                            gp.gameState = gp.NEW_GAME_STATE;
                        }
                    }
                    break;
            }
        }
    }

    public void powerUp(int i, int col, int row) {
        gp.obj.remove(i);
        gp.tileManager.liveMapTile[col][row] = ' ';
        gp.playSE(6);
    }

    public void hitEnemy(Entity entity) {
        if (entity instanceof EnemyManager) {
            EnemyManager enemy = (EnemyManager) entity;
            String enemyName = enemy.name;
            // switch (enemyName) {
            // case "enemy":
            // gp.enemy.remove(enemy);
            // loseLive();
            isExploded = true;
            enemy.isExploded = true;
            // break;
            // }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        // Image image = null;

        if (!isExploded) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 0) {
                        image = stand_up;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (spriteNum == 0) {
                        image = stand_down;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    if (spriteNum == 0) {
                        image = stand_left;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    if (spriteNum == 0) {
                        image = stand_right;
                    }
                    break;

            }
        } else {
            explodeCounter++;
            if (explodeCounter > 0 && explodeCounter <= 15) {
                if (explodeCounter == 1) {
                    lives--;
                    gp.ui.livesLabel.setText("Lives: " + lives);
                    gp.playSE(5);
                }
                image = dead1;
            } else if (explodeCounter > 15 && explodeCounter <= 30) {
                image = dead2;
            } else if (explodeCounter > 30 && explodeCounter <= 45) {
                image = dead3;
            } else {
                image = null;
                if (isDead) {
                    gp.gameState = gp.GAME_OVER_STATE;
                } else {
                    gp.gameState = gp.NEW_GAME_STATE;
                }
                isExploded = false;
                explodeCounter = 0;
            }
        }
        gc.drawImage(image, x, y);
    }
}
