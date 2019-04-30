package com.aor.ghostrumble.model;

import java.util.ArrayList;
import java.util.List;

public class HauntedHouse {
    private int width;
    private int height;
    private Player player;
    private List<Element> walls;
    private List<Enemy> enemies;

    public HauntedHouse(int width, int height) {
        this.width = width;
        this.height = height;
        this.player = new Player();
        this.walls = createWalls();
        this.enemies = new ArrayList<>();
        createEnemies();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Player getPlayer() { return player; }
    public List<Element> getWalls() { return walls; }
    public List<Enemy> getEnemies() { return enemies; }

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

    private void createEnemies() {
        addEnemy(new Ghost(60, 8));
        addEnemy(new Zombie(50, 20));
        addEnemy(new Poltergeist(6, 6));
    }

    private void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        player.addObserver(enemy);
    }

/*
    private void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
        player.removeObserver(enemy);
    }
*/


}
