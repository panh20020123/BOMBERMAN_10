package src.gui.game;

import src.entities.enemy.Balloom;
import src.entities.enemy.Oneal;
import src.object.Bombs;
import src.object.Flames;
import src.object.ObjectManager;
import src.object.Portal;
import src.object.Speed;
import src.tile.Brick;

public class AssetSetter {
    GamePane gp;

    public AssetSetter(GamePane gp) {
        this.gp = gp;
    }

    public void setObject() {
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                if (gp.tileManager.inputMapTile[col][row] == 'f') {
                    gp.tileManager.liveMapTile[col][row] = '*';
                } else if (gp.tileManager.inputMapTile[col][row] == 's') {
                    gp.tileManager.liveMapTile[col][row] = '*';
                } else if (gp.tileManager.inputMapTile[col][row] == 'b') {
                    gp.tileManager.liveMapTile[col][row] = '*';
                } else if (gp.tileManager.inputMapTile[col][row] == 'x') {
                    gp.tileManager.liveMapTile[col][row] = '*';
                }
            }
        }
    }

    public void setEnemy() {
        for (int col = 0; col < gp.maxWorldCol; col++) {
            for (int row = 0; row < gp.maxWorldRow; row++) {
                if (gp.tileManager.liveMapTile[col][row] == '1') {
                    gp.enemy.add(new Balloom(gp, col, row));
                } else if (gp.tileManager.liveMapTile[col][row] == '2') {
                    gp.enemy.add(new Oneal(gp, col, row));
                }
            }
        }
    }
}
