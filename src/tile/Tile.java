package src.tile;

import javafx.scene.image.Image;
import src.gui.game.GamePane;

public class Tile {

    public GamePane gp;
    public Image image;
    public boolean collision = false;
    public boolean explode = false;
    public boolean isExploded = false;

    public int explodeCounter = 0;

    public int x;
    public int y;

    public Tile() {
    }

    public Tile(int x, int y, Image image) {
        this(null, x, y, image);
    }

    public Tile(GamePane gp, int x, int y) {
        this(gp, x, y, null);
    }

    public Tile(GamePane gp, int x, int y, Image image) {
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.image = image;
    }
}
