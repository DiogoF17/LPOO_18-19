package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;

import java.util.ListIterator;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class Updater {

    private final static long ENEMY_SPAWN_RATE = 5000;

    public void update(Event event, HauntedHouse house) {
        processEvent(event, house);

        checkEnemyCollisions(house);
        moveEnemies(house);
        checkEnemyCollisions(house);

        damagePlayer(house);
        removeFlagged(house);

        spawnEnemy(house);

        //resets the event
        event.setType(Event.TYPE.NO_EVENT);

        // checkForGameOver(event, house);
    }

    /*public void checkForGameOver(Event event, HauntedHouse house) {
        if(house.getPlayer().getCurrentHealth() > 0)
            return;
        else {
            System.out.println("You died! Better luck next time!");
            return;
        }
    }*/

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

        } while (house.checkMonsterInPosition(valueX, valueY));

        Enemy newEnemy = null;

        switch(valueMonster) {
            case 0:
                newEnemy = new Zombie(valueX, valueY);
                break;

            case 1:
                newEnemy = new Ghost(valueX, valueY);
                break;

            case 2:
                newEnemy = new Poltergeist(valueX, valueY);
                break;

            default:
                break;
        }

        if(newEnemy != null)
            house.addEnemy(newEnemy);
    }

    public void moveEnemies(HauntedHouse house) {
        for (Enemy enemy : house.getEnemies()) {
            if (currentTimeMillis() - enemy.getLastMoved() > enemy.getSpeed()) {
                moveEnemy(enemy, enemy.move(), house);
                enemy.setLastMoved(currentTimeMillis());
            }
        }
    }

    public void processEvent(Event event, HauntedHouse house) {

        Player player = house.getPlayer();

        switch(event.getType()) {

            case PLAYER_UP:
                movePlayer(player, player.moveUp(), house);
                break;

            case PLAYER_LEFT:
                movePlayer(player, player.moveLeft(), house);
                break;

            case PLAYER_DOWN:
                movePlayer(player, player.moveDown(), house);
                break;

            case PLAYER_RIGHT:
                movePlayer(player, player.moveRight(), house);
                break;

            default:
                break;

        }

    }

    private void movePlayer(Player player, Position position, HauntedHouse house) {
        if (canPlayerMoveTo(position, house)) {
            player.setPosition(position);
            player.notifyObservers();
        }
    }

    private boolean canPlayerMoveTo(Position position, HauntedHouse house) {
        for (Element wall : house.getWalls()) {
            if (position.equals(wall.getPosition()))
                return false;
        }

        return true;
    }

    private void moveEnemy(Enemy enemy, Position position, HauntedHouse house) {
        if (canEnemyMoveTo(position, house)) {
            enemy.setPosition(position);
            enemy.update(house.getPlayer());
        }
    }

    private boolean canEnemyMoveTo(Position position, HauntedHouse house) {
/*
        for (Element wall : house.getWalls()) {
            if (position.equals(wall.getPosition()))
                return false;
        }
*/

        for (Enemy enemy : house.getEnemies()) {
            if(position.equals(enemy.getPosition()))
                return false;
        }

        return true;
    }


    public void removeFlagged(HauntedHouse house) {

        house.getWalls().removeIf( w -> w.flaggedForRemoval());

        ListIterator<Enemy> enemyItr = house.getEnemies().listIterator();
        while(enemyItr.hasNext()) {
            Enemy currentEnemy = enemyItr.next();

            if(currentEnemy.flaggedForRemoval()) {
                house.getPlayer().removeObserver(currentEnemy);
                enemyItr.remove();
            }
        }
    }


    public void checkEnemyCollisions(HauntedHouse house) {
        for(Enemy enemy : house.getEnemies()) {
            if(enemy.getPosition().equals(house.getPlayer().getPosition())) {
                enemy.setHitPlayer(true);
                enemy.setRemoveFlag(true);
            }
        }
    }

    public void damagePlayer(HauntedHouse house) {
        for (Enemy enemy : house.getEnemies()) {
            if (enemy.hasHitPlayer()) {
                house.getPlayer().damagePlayer(enemy.getDamage());
                enemy.setHitPlayer(false);
            }
        }
    }

}
