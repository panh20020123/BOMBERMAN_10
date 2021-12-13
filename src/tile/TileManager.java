package src.tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;
import src.gui.game.GamePane;

public class TileManager {
    GamePane gp;
    public Tile[] tile;
    public char[][] liveMapTile;
    public char[][] inputMapTile;
    public String levelMapPath;

    public TileManager(GamePane gp) {
        this.gp = gp;
        this.levelMapPath = gp.levelMapPath;

        tile = new Tile[10];

        liveMapTile = new char[gp.maxWorldCol][gp.maxScreenRow];
        inputMapTile = new char[gp.maxWorldCol][gp.maxScreenRow];
        getImage();
        loadMap();
    }

    public void getImage() {
        // grass
        tile[0] = new Grass();
        tile[0].image = gp.getImage.grass;

        // wall
        tile[1] = new Wall();
        tile[1].image = gp.getImage.wall;
        tile[2] = new Wall();
        tile[2].image = gp.getImage.wall1;

        // brick
        tile[3] = new Brick();
        tile[3].image = gp.getImage.brick;
    }

    public void loadMap() {
        try {
            File f = new File(levelMapPath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                    inputMapTile[col][row] = line.charAt(col);
                    liveMapTile[col][row] = line.charAt(col);
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBrick() {
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                if (liveMapTile[col][row] == '*') {
                    gp.bricks.add(new Brick(gp, col * gp.tileSize, row * gp.tileSize, tile[3].image));
                }
            }
        }
    }

    public void render(GraphicsContext gc) {
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                if (row == 0 || row == gp.maxWorldRow - 1 || col == 0 || col == gp.maxWorldCol - 1) {
                    gc.drawImage(tile[1].image, col * gp.tileSize, row * gp.tileSize);
                } else if (liveMapTile[col][row] == '#') {
                    gc.drawImage(tile[2].image, col * gp.tileSize, row * gp.tileSize);
                } else {
                    gc.drawImage(tile[0].image, col * gp.tileSize, row * gp.tileSize);
                }
            }
        }
    }
}
