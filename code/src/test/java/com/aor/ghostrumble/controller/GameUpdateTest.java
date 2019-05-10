package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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

        List<Element> walls = new ArrayList<>();
        Element wall = new Element(12, 12);
        wall.setRemoveFlag(true);
        walls.add(wall);
        walls.add(wall);

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

        Mockito.when(house.getWalls()).thenReturn(walls);
        Mockito.when(house.getBullets()).thenReturn(bullets);
        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getPlayer()).thenReturn(player);

        Mockito.doNothing().when(player).removeObserver(any(PlayerObserver.class));

        updater.removeFlagged(house);

        int finalCount = house.getWalls().size() +
                        house.getBullets().size() +
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

        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getCurrentHealth()).thenReturn(2);
        Mockito.when(house.getPlayer()).thenReturn(player);

        updater.checkForGameOver(house);

        Mockito.verify(house, times(0)).init();

        Mockito.when(player.getCurrentHealth()).thenReturn(0);

        updater.checkForGameOver(house);

        Mockito.verify(house, times(1)).init();
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
    public void testProcessEvent() {
        Updater updater = new Updater();
        PlayerUpdater playerUpdater = Mockito.mock(PlayerUpdater.class);
        BulletsUpdater bulletsUpdater = Mockito.mock(BulletsUpdater.class);
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Player player = Mockito.mock(Player.class);

        // return random positions; doesn't really matter in this unit test
        Mockito.when(player.moveUp()).thenReturn(new Position(1, 1));
        Mockito.when(player.moveDown()).thenReturn(new Position(2, 1));
        Mockito.when(player.moveLeft()).thenReturn(new Position(3, 1));
        Mockito.when(player.moveRight()).thenReturn(new Position(4, 1));

        Mockito.when(house.getPlayer()).thenReturn(player);
        updater.setPlayerUpdater(playerUpdater);
        updater.setBulletsUpdater(bulletsUpdater);

        updater.processEvent(new Event(Event.TYPE.PLAYER_UP), house);
        Mockito.verify(playerUpdater, times(1)).movePlayer(player, player.moveUp(), house);

        updater.processEvent(new Event(Event.TYPE.PLAYER_LEFT), house);
        Mockito.verify(playerUpdater, times(1)).movePlayer(player, player.moveLeft(), house);

        updater.processEvent(new Event(Event.TYPE.PLAYER_RIGHT), house);
        Mockito.verify(playerUpdater, times(1)).movePlayer(player, player.moveRight(), house);

        updater.processEvent(new Event(Event.TYPE.PLAYER_DOWN), house);
        Mockito.verify(playerUpdater, times(1)).movePlayer(player, player.moveDown(), house);

        updater.processEvent(new Event(Event.TYPE.BULLET_UP), house);
        Mockito.verify(bulletsUpdater, times(1)).launchVerticalBullet(house, -1);

        updater.processEvent(new Event(Event.TYPE.BULLET_RIGHT), house);
        Mockito.verify(bulletsUpdater, times(1)).launchHorizontalBullet(house, 1);

        updater.processEvent(new Event(Event.TYPE.BULLET_LEFT), house);
        Mockito.verify(bulletsUpdater, times(1)).launchHorizontalBullet(house, -1);

        updater.processEvent(new Event(Event.TYPE.BULLET_DOWN), house);
        Mockito.verify(bulletsUpdater, times(1)).launchVerticalBullet(house, 1);

    }
}
