package com.aor.ghostrumble;

public class Updater {

    public void update(Event event, HauntedHouse house) {
        processEvent(event, house);
        moveEnemies(house);
    }

    private void moveEnemies(HauntedHouse house) {
        for (Enemy enemy : house.getEnemies()) {
            enemy.move();
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
        for (Position wall : house.getWalls()) {
            if (position.equals(wall)) return false;
        }

        for (Enemy enemy : house.getEnemies()) {
            if (position.equals(enemy.getPosition())) return false;
        }
        return true;
    }

}
