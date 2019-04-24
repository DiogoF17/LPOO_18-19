package com.aor.ghostrumble;

import java.util.ArrayList;
import java.util.List;

public class HauntedHouse {
    private int width;
    private int height;
    private Player player;
    private List<Position> walls;
    private List<Enemy> enemies;

    public HauntedHouse(int width, int height) {
        this.width = width;
        this.height = height;
        this.player = new Player();
        this.walls = createWalls();
        this.enemies = createEnemies();
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Player getPlayer() { return player; }
    public List<Position> getWalls() { return walls; }
    public List<Enemy> getEnemies() { return enemies; }

    private List<Position> createWalls() {
        List<Position> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Position(c, 5));
            walls.add(new Position(c, height - 1));
        }

        for (int r = 6; r < height - 1; r++) {
            walls.add(new Position(0, r));
            walls.add(new Position(width - 1, r));
        }

        return walls;
    }

    private List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();

        enemies.add(new Ghost(60, 8));
        enemies.add(new Zombie(50, 20));
        enemies.add(new Poltergeist(6, 6));

        return enemies;
    }


}
