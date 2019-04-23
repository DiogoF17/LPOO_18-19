package com.aor.ghostrumble;

import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;

public class HauntedHouse {
    private int width;
    private int height;
    private Player player;
    private List<Position> walls;
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

        return enemies;
    }

    private void update() {
        for (Enemy enemy : enemies) {
            enemy.move();
        }
    }

    public void clockTick() {
        frameClock++;
        update();
    }

    public void processKey(KeyStroke key) {

        switch(key.getKeyType()) {

            case Character:

                switch(key.getCharacter()) {

                    case 'w':
                        moveElement(player, player.moveUp());
                        break;

                    case 'a':
                        moveElement(player, player.moveLeft());
                        break;

                    case 's':
                        moveElement(player, player.moveDown());
                        break;

                    case 'd':
                        moveElement(player, player.moveRight());
                        break;

                    default:
                        break;

                }

                break;

            default:

                break;

        }

    }

    private void moveElement(Movable movable, Position position) {
        if (canMoveTo(position))
            movable.setPosition(position);
    }

    private boolean canMoveTo(Position position) {
        for (Position wall : walls) {
            if (position.equals(wall)) return false;
        }
        for (Enemy enemy : enemies) {
            if (position.equals(enemy.getPosition())) return false;
        }
        return true;
    }

}
