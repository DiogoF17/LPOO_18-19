package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;

import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class EnemiesUpdater {

    private final static int ENEMY_SPAWN_RATE = 2000;

    public final static int getEnemySpawnRate() { return ENEMY_SPAWN_RATE; }


    public void spawnEnemy(HauntedHouse house) {

        if(HauntedHouse.getMaxNumberEnemies() > house.getEnemies().size())

            if(currentTimeMillis() - house.getLastSpawned() > ENEMY_SPAWN_RATE) {
                addEnemy(house);
                house.setLastSpawned(currentTimeMillis());
            }
    }


    private void addEnemy(HauntedHouse house) {
        Random random = new Random();
        int valueMonster = random.nextInt(3);
        int valueX;
        int valueY;

        do {
            if (random.nextBoolean()) {
                if (random.nextBoolean())
                    valueX = 1;
                else valueX = house.getWidth() - 2;

                valueY = random.nextInt(house.getHeight() - 7) + 6;
            } else {
                if (random.nextBoolean())
                    valueY = 6;
                else valueY = house.getHeight() - 2;

                valueX = random.nextInt(house.getWidth() - 2) + 1;
            }

        } while (house.hitsEnemies(new Position(valueX, valueY)));

        switch(valueMonster) {
            case 0:
                house.addEnemy(new Zombie(valueX, valueY));
                break;

            case 1:
                house.addEnemy(new Ghost(valueX, valueY));
                break;

            case 2:
                house.addEnemy(new Poltergeist(valueX, valueY));
                break;

            default:
                break;
        }

    }


    public void moveEnemies(HauntedHouse house) {

        house.getPlayer().notifyObservers();

        for (Enemy enemy : house.getEnemies()) {
            if (currentTimeMillis() - enemy.getLastMoved() > enemy.getSpeed()) {
                moveEnemy(enemy, enemy.move(), house);
                // enemy.setLastMoved(currentTimeMillis());
            }
        }
    }


    private void moveEnemy(Enemy enemy, Position position, HauntedHouse house) {
        // the way the game is made, the enemies will never go through walls

        if (!house.hitsEnemies(position)) {
            enemy.setPosition(position);
            enemy.setLastMoved(currentTimeMillis());
            // enemy.update(house.getPlayer());
        }
    }
}
