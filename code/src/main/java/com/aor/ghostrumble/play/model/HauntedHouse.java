package com.aor.ghostrumble.play.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class HauntedHouse {
    private int width;
    private int height;
    private Player player;
    private List<Element> walls;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    private int score;
    private long lastIncrementedScore;
    private long lastSpawned;

    private final static int MAX_NUMBER_ENEMIES = 15;
    private final static int MAX_NUMBER_BULLETS = 5;

    public HauntedHouse(int width, int height) {
        this.width = width;
        this.height = height;
        init();
    }

    public void init() {
        this.player = new Player(width / 2, height / 2);
        this.walls = createWalls();
        this.enemies = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.lastSpawned = currentTimeMillis();
        this.lastIncrementedScore = currentTimeMillis();
        this.score = 0;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Player getPlayer() { return player; }
    public List<Element> getWalls() { return walls; }
    public List<Enemy> getEnemies() { return enemies; }
    public List<Bullet> getBullets() { return bullets; }
    public int getScore() { return score; }
    public long getLastIncrementedScore() { return lastIncrementedScore; }
    public long getLastSpawned() { return lastSpawned; }
    public final static int getMaxNumberEnemies() { return MAX_NUMBER_ENEMIES; }
    public final static int getMaxNumberBullets() { return MAX_NUMBER_BULLETS; }

    public void setLastSpawned(long lastSpawned) { this.lastSpawned = lastSpawned; }
    public void setLastIncrementedScore(long lastIncrementedScore) { this.lastIncrementedScore = lastIncrementedScore; }

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

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }


    public boolean hitsEnemies(Position position) {

        for (Enemy enemy : enemies) {

            if(position.equals(enemy.getPosition()))
                return true;

        }

        return false;
    }


    public boolean hitsWall(Position position) {

        for (Element wall : walls) {

            if (position.equals(wall.getPosition()))
                return true;

        }

        return false;
    }


    public void increaseScore(int amount) { score += amount; }

}
