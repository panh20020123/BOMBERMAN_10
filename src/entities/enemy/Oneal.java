package src.entities.enemy;

import java.util.Random;

import src.gui.game.GamePane;

public class Oneal extends EnemyManager {
    public Oneal(GamePane gp) {
        super(gp);
        name = "oneal";

        explode = true;

        setDefaultValues();
        getImage();
    }

    public Oneal(GamePane gp, int x, int y) {
        this(gp);
        this.x = x * gp.tileSize;
        this.y = y * gp.tileSize;
    }

    public void setDefaultValues() {
        direction = "down";
        speed = 1;
        killScore = 200;
    }

    public void getImage() {
        up1 = gp.getImage.oneal_up1;
        up2 = gp.getImage.oneal_up2;
        down1 = gp.getImage.oneal_down1;
        down2 = gp.getImage.oneal_down2;
        left1 = gp.getImage.oneal_left1;
        left2 = gp.getImage.oneal_left2;
        right1 = gp.getImage.oneal_right1;
        right2 = gp.getImage.oneal_right2;
        dead1 = gp.getImage.oneal_dead1;
        dead2 = gp.getImage.oneal_dead2;
        dead3 = gp.getImage.oneal_dead3;
    }

    @Override
    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random rand = new Random();
            int ai = rand.nextInt(100) + 1; // pick a number from 1 to 100
            int i = rand.nextInt(100) + 1;
            int s = rand.nextInt(100) + 1;

            if (ai <= 50) {
                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }
            } else {
                if (this.x > gp.player.x) {
                    direction = "left";
                } else if (this.x < gp.player.x) {
                    direction = "right";
                } else {
                    if (this.y > gp.player.y) {
                        direction = "up";
                    } else if (this.y < gp.player.y) {
                        direction = "down";
                    }
                }
            }

            if (s <= 30) {
                speed = 1;
            }
            if (s > 30 && s <= 80) {
                speed = 2;
            }
            if (s > 80 && s <= 100) {
                speed = 3;
            }

            actionLockCounter = 0;
        }
    }
}
