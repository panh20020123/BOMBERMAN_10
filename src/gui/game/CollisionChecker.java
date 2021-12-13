package src.gui.game;

import java.util.List;

import src.entities.Entity;
import src.object.ObjectManager;

public class CollisionChecker {
    GamePane gp;

    public CollisionChecker(GamePane gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = (int) (entity.x + entity.solidArea.getX());
        int entityRightWorldX = (int) (entity.x + entity.solidArea.getX() + entity.solidArea.getWidth());
        int entityTopWorldY = (int) (entity.y + entity.solidArea.getY());
        int entityBottomWorldY = (int) (entity.y + entity.solidArea.getY() + entity.solidArea.getHeight());

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        char ch1, ch2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;

                ch1 = gp.tileManager.liveMapTile[entityLeftCol][entityTopRow];
                ch2 = gp.tileManager.liveMapTile[entityRightCol][entityTopRow];

                if (ch1 == '#') {
                    tileNum1 = 1;
                } else {
                    tileNum1 = 0;
                }

                if (ch2 == '#') {
                    tileNum2 = 1;
                } else {
                    tileNum2 = 0;
                }

                if (ch1 == '*' || ch2 == '*') {
                    entity.collisionOn = true;
                }

                if (gp.tileManager.tile[tileNum1].collision
                        || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;

                ch1 = gp.tileManager.liveMapTile[entityLeftCol][entityBottomRow];
                ch2 = gp.tileManager.liveMapTile[entityRightCol][entityBottomRow];

                if (ch1 == '#') {
                    tileNum1 = 1;
                } else {
                    tileNum1 = 0;
                }

                if (ch2 == '#') {
                    tileNum2 = 1;
                } else {
                    tileNum2 = 0;
                }

                if (ch1 == '*' || ch2 == '*')
                    entity.collisionOn = true;

                if (gp.tileManager.tile[tileNum1].collision
                        || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

                ch1 = gp.tileManager.liveMapTile[entityLeftCol][entityTopRow];
                ch2 = gp.tileManager.liveMapTile[entityLeftCol][entityBottomRow];

                if (ch1 == '#') {
                    tileNum1 = 1;
                } else {
                    tileNum1 = 0;
                }

                if (ch2 == '#') {
                    tileNum2 = 1;
                } else {
                    tileNum2 = 0;
                }

                if (ch1 == '*' || ch2 == '*')
                    entity.collisionOn = true;

                if (gp.tileManager.tile[tileNum1].collision
                        || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;

                ch1 = gp.tileManager.liveMapTile[entityRightCol][entityTopRow];
                ch2 = gp.tileManager.liveMapTile[entityRightCol][entityBottomRow];

                if (ch1 == '#') {
                    tileNum1 = 1;
                } else {
                    tileNum1 = 0;
                }

                if (ch2 == '#') {
                    tileNum2 = 1;
                } else {
                    tileNum2 = 0;
                }

                if (ch1 == '*' || ch2 == '*')
                    entity.collisionOn = true;

                if (gp.tileManager.tile[tileNum1].collision
                        || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }

    }

    public int checkObject(Entity entity, boolean isPlayer) {
        int index = 999;

        for (int i = 0; i < gp.obj.size(); i++) {
            if (gp.obj.get(i) != null) {
                // get entity's solid area position
                entity.solidArea.setX(entity.x + entity.solidArea.getX());
                entity.solidArea.setY(entity.y + entity.solidArea.getY());

                // get object's solid area position
                gp.obj.get(i).solidArea.setX(gp.obj.get(i).x + gp.obj.get(i).solidArea.getX());
                gp.obj.get(i).solidArea.setY(gp.obj.get(i).y + gp.obj.get(i).solidArea.getY());

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(gp.obj.get(i).solidArea.getBoundsInParent())) {
                            if (gp.obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;

                    case "down":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(gp.obj.get(i).solidArea.getBoundsInParent())) {
                            if (gp.obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;

                    case "left":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(gp.obj.get(i).solidArea.getBoundsInParent())) {
                            if (gp.obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;

                    case "right":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(gp.obj.get(i).solidArea.getBoundsInParent())) {
                            if (gp.obj.get(i).collision) {
                                entity.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.setX(entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.solidAreaDefaultY);
                gp.obj.get(i).solidArea.setX(gp.obj.get(i).solidAreaDefaultX);
                gp.obj.get(i).solidArea.setY(gp.obj.get(i).solidAreaDefaultY);
            }
        }
        return index;
    }

    // check player runs into entity
    public int checkEntity(Entity entity, List<Entity> target) {
        int index = 999;

        for (int i = 0; i < target.size(); i++) {
            if (target.get(i) != null && !target.get(i).equals(entity)) {
                // get entity's solid area position
                entity.solidArea.setX(entity.x + entity.solidArea.getX());
                entity.solidArea.setY(entity.y + entity.solidArea.getY());

                // get object's solid area position
                target.get(i).solidArea.setX(target.get(i).x + target.get(i).solidArea.getX());
                target.get(i).solidArea.setY(target.get(i).y + target.get(i).solidArea.getY());

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(target.get(i).solidArea.getBoundsInParent())) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;

                    case "down":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(target.get(i).solidArea.getBoundsInParent())) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;

                    case "left":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(target.get(i).solidArea.getBoundsInParent())) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;

                    case "right":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                        if (entity.solidArea.getBoundsInParent()
                                .intersects(target.get(i).solidArea.getBoundsInParent())) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }
                entity.solidArea.setX(entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.solidAreaDefaultY);
                target.get(i).solidArea.setX(target.get(i).solidAreaDefaultX);
                target.get(i).solidArea.setY(target.get(i).solidAreaDefaultY);
            }
        }
        return index;
    }

    // check entity runs into player
    public boolean checkPlayer(Entity entity, boolean isEnemy) {
        // get entity's solid area position
        entity.solidArea.setX(entity.x + entity.solidArea.getX());
        entity.solidArea.setY(entity.y + entity.solidArea.getY());

        // get object's solid area position
        gp.player.solidArea.setX(gp.player.x + gp.player.solidArea.getX());
        gp.player.solidArea.setY(gp.player.y + gp.player.solidArea.getY());

        switch (entity.direction) {
            case "up":
                entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                if (entity.solidArea.getBoundsInParent()
                        .intersects(gp.player.solidArea.getBoundsInParent())) {
                    entity.collisionOn = true;
                    if (isEnemy) {
                        return true;
                    }
                }
                break;

            case "down":
                entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                if (entity.solidArea.getBoundsInParent()
                        .intersects(gp.player.solidArea.getBoundsInParent())) {
                    entity.collisionOn = true;
                    if (isEnemy) {
                        return true;
                    }
                }
                break;

            case "left":
                entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                if (entity.solidArea.getBoundsInParent()
                        .intersects(gp.player.solidArea.getBoundsInParent())) {
                    entity.collisionOn = true;
                    if (isEnemy) {
                        return true;
                    }
                }
                break;

            case "right":
                entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                if (entity.solidArea.getBoundsInParent()
                        .intersects(gp.player.solidArea.getBoundsInParent())) {
                    entity.collisionOn = true;
                    if (isEnemy) {
                        return true;
                    }
                }
                break;
        }
        entity.solidArea.setX(entity.solidAreaDefaultX);
        entity.solidArea.setY(entity.solidAreaDefaultY);
        gp.player.solidArea.setX(gp.player.solidAreaDefaultX);
        gp.player.solidArea.setY(gp.player.solidAreaDefaultY);
        return false;
    }
}
