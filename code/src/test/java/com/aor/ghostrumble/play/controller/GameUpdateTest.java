package com.aor.ghostrumble.play.controller;

import com.aor.ghostrumble.play.controller.event.*;
import com.aor.ghostrumble.play.model.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class GameUpdateTest {

    @Test
    public void testDeleteEnemies() {

        Updater updater = new Updater();
        Position position = new Position(0, 30);

        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Player player = new Player();
        player.setPosition(position);

        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Enemy first = new Zombie(i, 10);
            Enemy second = new Zombie(i, 20);
            player.addObserver(first);
            player.addObserver(second);
            enemies.add(first);
            enemies.add(second);
        }

        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getPlayer()).thenReturn(player);

        updater.removeFlagged(house);
        assertEquals(20, enemies.size());

        for (Enemy enemy : enemies) {
            enemy.setRemoveFlag(true);
        }

        updater.removeFlagged(house);
        assertEquals(0, enemies.size());
        assertEquals(0, player.getObservers().size());
    }

    @Test
    public void testRemoveFlagged() {
        Updater updater = new Updater();
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Player player = Mockito.mock(Player.class);

        List<Bullet> bullets = new ArrayList<>();
        Bullet bullet = new HorizontalBullet(12, 12, 1);
        bullet.setRemoveFlag(true);
        bullets.add(bullet);
        bullets.add(bullet);

        List<Enemy> enemies = new ArrayList<>();
        Enemy enemy = new Zombie(12, 12);
        enemy.setRemoveFlag(true);
        enemies.add(enemy);
        enemies.add(enemy);

        Mockito.when(house.getBullets()).thenReturn(bullets);
        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getPlayer()).thenReturn(player);

        Mockito.doNothing().when(player).removeObserver(any(PlayerObserver.class));

        updater.removeFlagged(house);

        int finalCount = house.getBullets().size() +
                         house.getEnemies().size();

        assertEquals(0, finalCount);
    }

    @Test
    public void testCheckEnemyCollisions() {
        Updater updater = new Updater();
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Player player = Mockito.mock(Player.class);
        Position position = new Position(15, 15);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Zombie(15, 15));

        Mockito.when(player.getPosition()).thenReturn(position);
        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(house.getEnemies()).thenReturn(enemies);

        updater.checkEnemyCollisions(house);

        assertTrue(enemies.get(0).flaggedForRemoval());
    }

    @Test
    public void testBulletWallCollision() {
        Updater updater = new Updater();
        HauntedHouse house = Mockito.mock(HauntedHouse.class);


        List<Bullet> bullets = new ArrayList<>();
        bullets.add(new HorizontalBullet(15, 15, 0));

        List<Enemy> enemies = new ArrayList<>();

        Mockito.when(house.hitsWall(any(Position.class))).thenReturn(true);
        Mockito.when(house.getBullets()).thenReturn(bullets);
        Mockito.when(house.getEnemies()).thenReturn(enemies);

        updater.checkBulletCollisions(house);

        assertTrue(bullets.get(0).flaggedForRemoval());
    }

    @Test
    public void testBulletEnemyCollision() {
        Updater updater = new Updater();
        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        List<Element> walls = new ArrayList<>();

        List<Bullet> bullets = new ArrayList<>();
        bullets.add(new HorizontalBullet(15, 15, 0));

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Zombie(15, 15));

        Mockito.when(house.getWalls()).thenReturn(walls);
        Mockito.when(house.getBullets()).thenReturn(bullets);
        Mockito.when(house.getEnemies()).thenReturn(enemies);

        updater.checkBulletCollisions(house);

        assertTrue(bullets.get(0).flaggedForRemoval()
                            && bullets.get(0).getKillFlag()
                            && enemies.get(0).flaggedForRemoval()
        );
    }

    @Test
    public void testIncreaseScoreWithKills() {
        Updater updater = new Updater();
        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        List<Bullet> bullets = new ArrayList<>();
        bullets.add(new HorizontalBullet(15, 15, 0));
        bullets.add(new HorizontalBullet(13, 13, 0));
        bullets.add(new HorizontalBullet(11, 11, 0));
        bullets.add(new HorizontalBullet(6, 9, 0));
        bullets.add(new HorizontalBullet(7, 9, 0));
        bullets.get(0).setKillFlag(true);
        bullets.get(3).setKillFlag(true);

        Mockito.when(house.getBullets()).thenReturn(bullets);

        updater.increaseScoreWithKills(house);

        Mockito.verify(house, times(2)).increaseScore(Updater.getScoreKillIncrease());
    }

    @Test
    public void testCheckForGameOver() {
        Updater updater = new Updater();
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        EventQueue queue = Mockito.mock(EventQueue.class);

        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getCurrentHealth()).thenReturn(2);
        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(queue.close()).thenReturn(false);

        updater.checkForGameOver(queue, house);

        Mockito.verify(house, times(0)).init();

        Mockito.when(player.getCurrentHealth()).thenReturn(0);

        assertTrue(updater.checkForGameOver(queue, house));
    }

    @Test
    public void testIncreaseScoreTime() {
        Updater updater = new Updater();
        HauntedHouse house = new HauntedHouse(50, 50);

        house.setLastIncrementedScore(3000 - currentTimeMillis());
        updater.increaseScoreWithTime(house);

        house.setLastIncrementedScore(currentTimeMillis() - 3000);
        updater.increaseScoreWithTime(house);

        house.setLastIncrementedScore(currentTimeMillis() - 3001);
        updater.increaseScoreWithTime(house);
        updater.increaseScoreWithTime(house);

        assertEquals(2 * Updater.getScoreTimeIncrease(), house.getScore());
    }

    @Test
    public void testWalkThroughEnemies() {
        Updater updater = Mockito.mock(Updater.class);
        PlayerUpdater playerUpdater = Mockito.mock(PlayerUpdater.class);
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Player player = new Player(9, 10);
        Enemy enemy = new Zombie(10, 10);
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemy);
        EventQueue queue = new EventQueue();

        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(updater.getPlayerUpdater()).thenReturn(playerUpdater);
        Mockito.doCallRealMethod().when(updater).checkEnemyCollisions(house);
        Mockito.when(
                playerUpdater.movePlayer(any(Player.class), any(Position.class), any(HauntedHouse.class))
        ).thenCallRealMethod();

        queue.raiseEvent(new EventPlayerRight());
        queue.raiseEvent(new EventPlayerRight());
        queue.executeEvents(updater, house);

        assertTrue(enemy.hasHitPlayer());
        assertTrue(enemy.flaggedForRemoval());
        assertEquals(new Position(11, 10), player.getPosition());
    }

}
