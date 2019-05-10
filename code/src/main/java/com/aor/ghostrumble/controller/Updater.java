package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;

import java.util.ListIterator;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class Updater {

    private PlayerUpdater playerUpdater;
    private EnemiesUpdater enemiesUpdater;
    private BulletsUpdater bulletsUpdater;

    public Updater() {
        this.playerUpdater = new PlayerUpdater();
        this.enemiesUpdater = new EnemiesUpdater();
        this.bulletsUpdater = new BulletsUpdater();
    }

    public void setPlayerUpdater(PlayerUpdater playerUpdater) {
        this.playerUpdater = playerUpdater;
    }
    public void setEnemiesUpdater(EnemiesUpdater enemiesUpdater) {
        this.enemiesUpdater = enemiesUpdater;
    }
    public void setBulletsUpdater(BulletsUpdater bulletsUpdater) {
        this.bulletsUpdater = bulletsUpdater;
    }


    private final static int SCORE_INCREASE_RATE = 3000;

    private final static int SCORE_TIME_INCREASE = 10;
    private final static int SCORE_KILL_INCREASE = 50;

    public final static int getScoreTimeIncrease() { return SCORE_TIME_INCREASE; }
    public final static int getScoreKillIncrease() { return SCORE_KILL_INCREASE; }

    public void update(Event event, HauntedHouse house) {
        processEvent(event, house);

        checkBulletCollisions(house);

        bulletsUpdater.moveBullets(house);

        checkBulletCollisions(house);
        checkEnemyCollisions(house);

        enemiesUpdater.moveEnemies(house);

        checkBulletCollisions(house);
        checkEnemyCollisions(house);

        increaseScoreWithKills(house);

        playerUpdater.damagePlayer(house);

        removeFlagged(house);

        enemiesUpdater.spawnEnemy(house);

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
            System.out.println("You died! Your final score was " + house.getScore());
            house.init();
        }
    }


    public void processEvent(Event event, HauntedHouse house) {

        Player player = house.getPlayer();

        switch(event.getType()) {

            case PLAYER_UP:
                playerUpdater.movePlayer(player, player.moveUp(), house);
                break;

            case PLAYER_LEFT:
                playerUpdater.movePlayer(player, player.moveLeft(), house);
                break;

            case PLAYER_DOWN:
                playerUpdater.movePlayer(player, player.moveDown(), house);
                break;

            case PLAYER_RIGHT:
                playerUpdater.movePlayer(player, player.moveRight(), house);
                break;

            case BULLET_UP:
                bulletsUpdater.launchVerticalBullet(house, -1);
                break;

            case BULLET_LEFT:
                bulletsUpdater.launchHorizontalBullet(house, -1);
                break;

            case BULLET_DOWN:
                bulletsUpdater.launchVerticalBullet(house, 1);
                break;

            case BULLET_RIGHT:
                bulletsUpdater.launchHorizontalBullet(house, 1);

            default:
                break;

        }

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

            if(house.hitsWall(bullet.getPosition()))
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

}
