package com.aor.ghostrumble.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class HauntedHouse {
    private int width;
    private int height;
    private Player player;
    private List<Element> walls;
    private List<Enemy> enemies;
    private long lastSpawned;

    private final static int MAX_NUMBER_ENEMIES = 10;

    public HauntedHouse(int width, int height) {
        this.width = width;
        this.height = height;
        this.player = new Player();
        this.walls = createWalls();
        this.enemies = new ArrayList<>();
        this.lastSpawned = currentTimeMillis();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Player getPlayer() { return player; }
    public List<Element> getWalls() { return walls; }
    public List<Enemy> getEnemies() { return enemies; }
    public long getLastSpawned() { return lastSpawned; }
    public final static int getMaxNumberEnemies() { return MAX_NUMBER_ENEMIES; }

    public void setLastSpawned(long lastSpawned) { this.lastSpawned = lastSpawned; }

    private List<Element> createWalls() {
        List<Element> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Element(c, 5));
            walls.add(new Element(c, height - 1));
        }

        for (int r = 6; r < height - 1; r++) {
            walls.add(new Element(0, r));
            walls.add(new Element(width - 1, r));
        }

        return walls;
    }


    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        player.addObserver(enemy);
    }

/*
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
        player.removeObserver(enemy);
    }
*/

    public boolean checkMonsterInPosition(int x, int y) {
        for(Enemy enemy : enemies)
            if(enemy.getPosition().getX() == x && enemy.getPosition().getY() == y)
                return true;

        return false;
    }


}
