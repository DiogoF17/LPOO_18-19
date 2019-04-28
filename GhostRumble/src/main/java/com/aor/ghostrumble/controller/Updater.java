package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;

import java.util.ListIterator;

import static java.lang.System.currentTimeMillis;

public class Updater {

    public void update(Event event, HauntedHouse house) {
        processEvent(event, house);

        checkEnemyCollisions(house);
        moveEnemies(house);
        checkEnemyCollisions(house);

        removeFlagged(house);

        //resets the event
        event.setType(Event.TYPE.NO_EVENT);
    }

    private void moveEnemies(HauntedHouse house) {
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
        for (Element wall : house.getWalls()) {
            if (position.equals(wall.getPosition()))
                return false;
        }

        for (Enemy enemy : house.getEnemies()) {
            if(position.equals(enemy.getPosition()))
                return false;
        }

        return true;
    }


    private void removeFlagged(HauntedHouse house) {

        house.getWalls().removeIf( w -> w.flaggedForRemoval());

        ListIterator<Enemy> enemyItr = house.getEnemies().listIterator();
        while(enemyItr.hasNext()) {
            Enemy currentEnemy = enemyItr.next();

            if(currentEnemy.flaggedForRemoval()) {
                house.getPlayer().getObservers().remove(currentEnemy);
                enemyItr.remove();
            }
        }
    }


    /*
    private boolean checkForGameOver(HauntedHouse house) {
        return house.getPlayer().getCurrentHealth() <= 0;
    }
    */

    private void checkEnemyCollisions(HauntedHouse house) {
        for(Enemy enemy : house.getEnemies()) {
            if(enemy.getPosition().equals(house.getPlayer().getPosition())) {
                house.getPlayer().damagePlayer(enemy.getDamage());
                enemy.setRemoveFlag(true);
            }
        }
    }

}
