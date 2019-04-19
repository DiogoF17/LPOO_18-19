package com.aor.ghostrumble;

import java.util.ArrayList;
import java.util.List;

public class HauntedHouse {
    private int width;
    private int height;
    private Player player;
    private List<Wall> walls;
    private List<Enemy> enemies;
    private int frameClock;

    public HauntedHouse(int width, int height) {
        this.width = width;
        this.height = height;
        this.player = new Player();
        this.walls = createWalls();
        this.enemies = createEnemies();
        this.frameClock = 0;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Player getPlayer() { return player; }
    public List<Wall> getWalls() { return walls; }
    public List<Enemy> getEnemies() { return enemies; }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 5));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 6; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }

    private List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        enemies.add(new Ghost(60, 8));
        enemies.add(new Zombie(50, 20));

        return enemies;
    }

    private void update() {
        for (Enemy enemy : enemies) {
            if (frameClock % enemy.getSpeed() == 0) {
                enemy.move();
            }
        }
    }

    public void clockTick() {
        frameClock++;
        update();
    }

}
