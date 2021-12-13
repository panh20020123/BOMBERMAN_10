package src.object;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import src.gui.game.GamePane;

public class ObjectManager {
    public GamePane gp;
    public Image image;
    public String name;
    public boolean collision = false;
    // public int worldX, worldY;
    public int x, y;
    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);

    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public static List<Character> objChar = new ArrayList<Character>();

    public ObjectManager(GamePane gp) {
        this.gp = gp;
    }

    public void update() {
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                if (gp.tileManager.liveMapTile[col][row] == 'f') {
                    Flames flames = new Flames(gp, col, row);
                    if (checkObject(flames)) {
                        gp.obj.add(flames);
                    }
                    // ObjectManager.objChar.add('f');
                } else if (gp.tileManager.liveMapTile[col][row] == 's') {
                    Speed speed = new Speed(gp, col, row);
                    if (checkObject(speed)) {
                        gp.obj.add(speed);
                    }
                    // ObjectManager.objChar.add('s');
                } else if (gp.tileManager.liveMapTile[col][row] == 'b') {
                    Bombs bombs = new Bombs(gp, col, row);
                    if (checkObject(bombs)) {
                        gp.obj.add(bombs);
                    }
                    // ObjectManager.objChar.add('b');
                } else if (gp.tileManager.liveMapTile[col][row] == 'x') {
                    Portal portal = new Portal(gp, col, row);
                    if (checkObject(portal)) {
                        gp.obj.add(portal);
                    }
                    // ObjectManager.objChar.add('x');
                }
            }
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, x, y);
        // gc.drawImage(gp.tileManager.tile[3].image, x, y);
    }

    // check if can add an object here
    public boolean checkObject(ObjectManager newObj) {
        for (int i = 0; i < gp.obj.size(); i++) {
            if (gp.obj.get(i).x == newObj.x && gp.obj.get(i).y == newObj.y) {
                return false;
            }
        }
        return true;
    }
}
