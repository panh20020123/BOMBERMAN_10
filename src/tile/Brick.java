package src.tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import src.gui.game.GamePane;

public class Brick extends Tile {
    public static int explodeTime = 30;

    public Brick(int x, int y, Image image) {
        super(x, y, image);
        explode = true;
        collision = true;
    }

    public Brick() {
        explode = true;
        collision = true;
    }

    public Brick(GamePane gp, int x, int y) {
        super(gp, x, y);
        explode = true;
        collision = true;
    }

    public Brick(GamePane gp, int x, int y, Image image) {
        super(gp, x, y, image);
        explode = true;
        collision = true;
    }

    public void render(GraphicsContext gc) {
        if (isExploded) {
            explodeCounter++;
            if (explodeCounter >= 0 && explodeCounter <= explodeTime * 1 / 3) {
                image = gp.getImage.brick_exploded;
            } else if (explodeCounter > explodeTime * 1 / 3 && explodeCounter <= explodeTime * 2 / 3) {
                image = gp.getImage.brick_exploded1;
            } else if (explodeCounter > explodeTime * 2 / 3 && explodeCounter <= explodeTime * 3 / 3) {
                image = gp.getImage.brick_exploded2;
            } else {
                image = null;
                int col = x / gp.tileSize;
                int row = y / gp.tileSize;
                char live = gp.tileManager.liveMapTile[col][row];
                char input = gp.tileManager.inputMapTile[col][row];
                if (input == '*') {
                    gp.tileManager.liveMapTile[col][row] = ' ';
                } else {
                    gp.tileManager.liveMapTile[col][row] = input;
                }
                gp.bricks.remove(this);
                isExploded = false;
            }
        }
        gc.drawImage(image, x, y);
    }
}
