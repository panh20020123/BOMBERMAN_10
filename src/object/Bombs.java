package src.object;

import src.gui.game.GamePane;

public class Bombs extends ObjectManager {
    public Bombs(GamePane gp) {
        super(gp);
        name = "bombs";
        image = gp.getImage.powerup_bombs;
    }

    public Bombs(GamePane gp, int x, int y) {
        this(gp);
        this.x = x * gp.tileSize;
        this.y = y * gp.tileSize;
    }
}
