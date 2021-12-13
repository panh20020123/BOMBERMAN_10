package src.entities.enemy;

import src.bomb.Bomb;
import src.entities.Entity;
import src.gui.game.GamePane;

public class EnemyManager extends Entity {
    public String name;
    public int killScore;

    public EnemyManager(GamePane gp) {
        super(gp);
        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();
    }

    @Override
    public void update() {
        isDead = isExploded;
        if (isDead || isExploded) {
        } else {
            setAction();

            collisionOn = false;
            for (int i = 0; i < gp.bombs.size(); i++) {
                Bomb bomb = gp.bombs.get(i);
                bomb.checkEntity(this, bomb.x, bomb.y);
            }
            gp.collisionChecker.checkTile(this);
            gp.collisionChecker.checkObject(this, false);
            gp.collisionChecker.checkEntity(this, gp.enemy);
            if (gp.collisionChecker.checkPlayer(this, true)) {
                gp.player.hitEnemy(this);
                return;
            }

            // if collision is false , entity can move
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
            } else {
                update();
                return;
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
}
