package src.entities.enemy;

import java.util.Random;

import src.gui.game.GamePane;

public class Balloom extends EnemyManager {
    public Balloom(GamePane gp) {
        super(gp);
        name = "balloom";

        explode = true;

        setDefaultValues();
        getImage();
    }

    public Balloom(GamePane gp, int x, int y) {
        this(gp);
        this.x = x * gp.tileSize;
        this.y = y * gp.tileSize;
    }

    public void setDefaultValues() {
        direction = "down";
        speed = 1;
        killScore = 100;
    }

    public void getImage() {
        up1 = gp.getImage.balloom_up1;
        up2 = gp.getImage.balloom_up2;
        down1 = gp.getImage.balloom_down1;
        down2 = gp.getImage.balloom_down2;
        left1 = gp.getImage.balloom_left1;
        left2 = gp.getImage.balloom_left2;
        right1 = gp.getImage.balloom_right1;
        right2 = gp.getImage.balloom_right2;
        dead1 = gp.getImage.balloom_dead1;
        dead2 = gp.getImage.balloom_dead2;
        dead3 = gp.getImage.balloom_dead3;
    }

    @Override
    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 90) {
            Random rand = new Random();
            int i = rand.nextInt(100) + 1; // pick a number from 1 to 100

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

            actionLockCounter = 0;
        }
    }
}
