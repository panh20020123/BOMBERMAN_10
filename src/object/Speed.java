package src.object;

import src.gui.game.GamePane;

public class Speed extends ObjectManager {
    public Speed(GamePane gp) {
        super(gp);
        name = "speed";
        image = gp.getImage.powerup_speed;
    }

    public Speed(GamePane gp, int x, int y) {
        this(gp);
        this.x = x * gp.tileSize;
        this.y = y * gp.tileSize;
    }
}
