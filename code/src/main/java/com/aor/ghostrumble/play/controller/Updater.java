package com.aor.ghostrumble.play.controller;

import com.aor.ghostrumble.play.controller.event.EventQueue;
import com.aor.ghostrumble.play.model.*;

import java.util.ListIterator;

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

    public PlayerUpdater getPlayerUpdater() { return playerUpdater; }
    public EnemiesUpdater getEnemiesUpdater() { return enemiesUpdater; }
    public BulletsUpdater getBulletsUpdater() { return bulletsUpdater; }


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

    public boolean update(EventQueue eventQueue, HauntedHouse house) {
        eventQueue.executeEvents(this, house);

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

        return !checkForGameOver(eventQueue, house);
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

    public boolean checkForGameOver(EventQueue queue, HauntedHouse house) {
        return (house.getPlayer().getCurrentHealth() <= 0) ||
                queue.close();
    }

    public void removeFlagged(HauntedHouse house) {

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
