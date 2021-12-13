package src.object;

import src.gui.game.GamePane;

public class Flames extends ObjectManager {
    public Flames(GamePane gp) {
        super(gp);
        name = "flames";
        image = gp.getImage.powerup_flames;
    }

    public Flames(GamePane gp, int x, int y) {
        this(gp);
        this.x = x * gp.tileSize;
        this.y = y * gp.tileSize;
    }
}