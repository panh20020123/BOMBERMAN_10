package src.game;

public enum LEVEL {

    LEVEL_1(1, "./res/levels/level1Map.txt", "/src/graphics/classic11.png", 31, 13),
    LEVEL_2(2, "./res/levels/level3Map.txt", "/src/graphics/classic.png", 31, 13);
    // LEVEL_3(3, "./res/levels/level2Map.txt", "/src/graphics/classic11.png", 31,
    // 13);

    private int level;
    private String mapPath;
    private String spriteSheetPath;
    private int maxWorldCol;
    private int maxWorldRow;

    private LEVEL(int level, String mapPath, String spriteSheetPath, int maxWorldCol, int maxWorldRow) {
        this.level = level;
        this.mapPath = mapPath;
        this.spriteSheetPath = spriteSheetPath;
        this.maxWorldCol = maxWorldCol;
        this.maxWorldRow = maxWorldRow;
    }

    public int getLevel() {
        return level;
    }

    public String getMapPath() {
        return mapPath;
    }

    public String getSpriteSheetPath() {
        return spriteSheetPath;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

}
