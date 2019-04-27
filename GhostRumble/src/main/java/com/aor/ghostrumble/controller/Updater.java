package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;

import java.util.Iterator;

import static java.lang.System.currentTimeMillis;

public class Updater {

    public void update(Event event, HauntedHouse house) {
        processEvent(event, house);
        moveEnemies(house);


        checkEnemyCollisions(house);
        removeFlagged(house);

        //resets the event
        event.setType(Event.TYPE.NO_EVENT);
    }

    private void moveEnemies(HauntedHouse house) {
        for (Enemy enemy : house.getEnemies()) {
            if (currentTimeMillis() - enemy.getLastMoved() > enemy.getSpeed()) {
                moveElement(enemy, enemy.move(), house);
                enemy.setLastMoved(currentTimeMillis());
            }
        }
    }

    public void processEvent(Event event, HauntedHouse house) {

        Player player = house.getPlayer();

        switch(event.getType()) {

            case PLAYER_UP:
                moveElement(player, player.moveUp(), house);
                break;

            case PLAYER_LEFT:
                moveElement(player, player.moveLeft(), house);
                break;

            case PLAYER_DOWN:
                moveElement(player, player.moveDown(), house);
                break;

            case PLAYER_RIGHT:
                moveElement(player, player.moveRight(), house);
                break;

            default:
                break;

        }

    }

    private void moveElement(Movable movable, Position position, HauntedHouse house) {
        if (canMoveTo(position, house))
            movable.setPosition(position);
    }

    private boolean canMoveTo(Position position, HauntedHouse house) {
        for (Element wall : house.getWalls()) {
            if (position.equals(wall.getPosition())) return false;
        }

        return true;
    }

    private void removeFlagged(HauntedHouse house) {

        house.getWalls().removeIf( w -> w.flaggedForRemoval());
        house.getEnemies().removeIf( e -> e.flaggedForRemoval());
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
