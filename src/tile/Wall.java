package src.tile;

import javafx.scene.image.Image;

public class Wall extends Tile {

    public Wall() {
        collision = true;
    }

    public Wall(int x, int y, Image image) {
        super(x, y, image);
        collision = true;
    }
}
