package src.object;

import src.gui.game.GamePane;

public class Portal extends ObjectManager {
    public Portal(GamePane gp) {
        super(gp);
        name = "portal";
        image = gp.getImage.portal;
        collision = true;
    }

    public Portal(GamePane gp, int x, int y) {
        this(gp);
        this.x = x * gp.tileSize;
        this.y = y * gp.tileSize;
    }
}
