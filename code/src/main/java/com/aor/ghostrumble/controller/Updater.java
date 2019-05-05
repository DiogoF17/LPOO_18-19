package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;

import java.util.ListIterator;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class Updater {

    private final static int ENEMY_SPAWN_RATE = 2000;
    private final static int FIRE_REFRESH_RATE = 500;
    private final static int SCORE_INCREASE_RATE = 3000;

    private final static int SCORE_TIME_INCREASE = 10;
    private final static int SCORE_KILL_INCREASE = 50;

    public final static int getScoreTimeIncrease() { return SCORE_TIME_INCREASE; }
    public final static int getScoreKillIncrease() { return SCORE_KILL_INCREASE; }
    public final static int getEnemySpawnRate() { return ENEMY_SPAWN_RATE; }
    public final static int getFireRefreshRate() { return FIRE_REFRESH_RATE; }

    public void update(Event event, HauntedHouse house) {
        processEvent(event, house);

        checkBulletCollisions(house);

        moveBullets(house);

        checkBulletCollisions(house);
        checkEnemyCollisions(house);

        moveEnemies(house);

        checkBulletCollisions(house);
        checkEnemyCollisions(house);

        increaseScoreWithKills(house);

        damagePlayer(house);

        removeFlagged(house);

        spawnEnemy(house);

        increaseScoreWithTime(house);

        //resets the event
        event.setType(Event.TYPE.NO_EVENT);

        checkForGameOver(house);
    }

    public void increaseScoreWithKills(HauntedHouse house) {
        for(Bullet bullet : house.getBullets())
            if(bullet.getKillFlag())
                house.increaseScore(SCORE_KILL_INCREASE);
    }

    public void increaseScoreWithTime(HauntedHouse house) {

        if(currentTimeMillis() - house.getLastIncrementedScore() > SCORE_INCREASE_RATE) {
            house.increaseScore(SCORE_TIME_INCREASE);
            house.setLastIncrementedScore(currentTimeMillis());
        }
    }

    public void checkForGameOver(HauntedHouse house) {
        if(house.getPlayer().getCurrentHealth() <= 0) {
            // System.out.println("You died! Your final score was " + house.getScore());
            house.init();
        }
    }

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

    public void launchHorizontalBullet(HauntedHouse house, int delta) {

        if(HauntedHouse.getMaxNumberBullets() > house.getBullets().size()) {

            Player player = house.getPlayer();

            if (currentTimeMillis() - player.getLastFired() > FIRE_REFRESH_RATE) {
                house.addBullet(new HorizontalBullet(player.getPosition().getX() + delta, player.getPosition().getY(), delta));
                player.setLastFired(currentTimeMillis());
            }
        }
    }

    public void launchVerticalBullet(HauntedHouse house, int delta) {

        if(HauntedHouse.getMaxNumberBullets() > house.getBullets().size()) {

            Player player = house.getPlayer();

            if (currentTimeMillis() - player.getLastFired() > FIRE_REFRESH_RATE) {
                house.addBullet(new VerticalBullet(player.getPosition().getX() , player.getPosition().getY() + delta, delta));
                player.setLastFired(currentTimeMillis());
            }
        }
    }

    public void moveBullets(HauntedHouse house) {
        for (Bullet bullet : house.getBullets()) {
            if (currentTimeMillis() - bullet.getLastMoved() > bullet.getSpeed()) {
                bullet.setPosition(bullet.move());
                bullet.setLastMoved(currentTimeMillis());
            }
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

            case BULLET_UP:
                launchVerticalBullet(house, -1);
                break;

            case BULLET_LEFT:
                launchHorizontalBullet(house, -1);
                break;

            case BULLET_DOWN:
                launchVerticalBullet(house, 1);
                break;

            case BULLET_RIGHT:
                launchHorizontalBullet(house, 1);

            default:
                break;

        }

    }

    private void movePlayer(Player player, Position position, HauntedHouse house) {
        if (!hitsWall(position, house)) {
            player.setPosition(position);
            player.notifyObservers();
        }
    }

    private boolean hitsWall(Position position, HauntedHouse house) {
        for (Element wall : house.getWalls()) {
            if (position.equals(wall.getPosition()))
                return true;
        }

        return false;
    }

    private void moveEnemy(Enemy enemy, Position position, HauntedHouse house) {
        // the way the game is made, the enemies will never go through walls

        if (!hitsEnemies(position, house)) {
            enemy.setPosition(position);
            enemy.setLastMoved(currentTimeMillis());
            // enemy.update(house.getPlayer());
        }
    }

    private boolean hitsEnemies(Position position, HauntedHouse house) {

        for (Enemy enemy : house.getEnemies()) {
            if(position.equals(enemy.getPosition()))
                return true;
        }

        return false;
    }

    public void removeFlagged(HauntedHouse house) {

        house.getWalls().removeIf( w -> w.flaggedForRemoval());
        house.getBullets().removeIf( b -> b.flaggedForRemoval());

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

    public void checkBulletCollisions(HauntedHouse house) {
        for(Bullet bullet : house.getBullets()) {

            if(hitsWall(bullet.getPosition(), house))
                bullet.setRemoveFlag(true);

            for(Enemy enemy : house.getEnemies()) {
                if(bullet.getPosition().equals(enemy.getPosition())) {
                    bullet.setRemoveFlag(true);
                    bullet.setKillFlag(true);
                    enemy.setRemoveFlag(true);
                }
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
