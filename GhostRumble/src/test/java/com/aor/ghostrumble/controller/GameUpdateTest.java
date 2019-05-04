package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class GameUpdateTest {

    @Test
    public void testEnemyMovement() {
        Updater updater = new Updater();

        Player player = new Player();
        Position standard = new Position(15, 10);
        player.setPosition(standard);

        List<Element> walls = new ArrayList<>();

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Zombie(20, 10));
        enemies.add(new Zombie(16, 18));
        enemies.add(new Zombie(15, 14));
        enemies.add(new Ghost(12, 12));
        enemies.add(new Ghost(15, 12));
        enemies.add(new Ghost(10, 10));

        for (Enemy enemy : enemies) {
            player.addObserver(enemy);
        }
        player.notifyObservers();

        List<Enemy> expected = new ArrayList<>();
        expected.add(new Zombie(19, 10));
        expected.add(new Zombie(15, 18));
        expected.add(new Zombie(15, 13));
        expected.add(new Ghost(13, 11));
        expected.add(new Ghost(15, 11));
        expected.add(new Ghost(11, 10));


        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(house.getWalls()).thenReturn(walls);
        Mockito.when(house.getEnemies()).thenReturn(enemies);

        updater.moveEnemies(house);

        for (int i = 0; i < 6; i++) {
            assertEquals(expected.get(i).getPosition(), enemies.get(i).getPosition());
        }
    }

    @Test
    public void testDeleteEnemies() {

        Updater updater = new Updater();
        Position position = new Position(0, 30);

        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Player player = Mockito.mock(Player.class);

        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            enemies.add(new Zombie(i, 10));
            enemies.add(new Ghost(i, 20));
        }

        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(player.getPosition()).thenReturn(position);

        updater.removeFlagged(house);
        assertEquals(20, enemies.size());

        for (Enemy enemy : enemies) {
            enemy.setRemoveFlag(true);
        }

        updater.removeFlagged(house);
        assertEquals(0, enemies.size());
    }

    @Test
    public void testPlayerDamage() {
        Updater updater = new Updater();
        Player player = new Player();
        Position position = new Position(0, 30);

        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Poltergeist(0, 30));

        player.setPosition(position);

        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getPlayer()).thenReturn(player);

        assertEquals(20, player.getCurrentHealth());

        updater.checkEnemyCollisions(house);
        updater.damagePlayer(house);
        updater.removeFlagged(house);

        assertEquals(15, player.getCurrentHealth());
        assertEquals(0, enemies.size());
    }

    @Test
    public void testPlayerMove() {
        Updater updater = new Updater();
        Player player = new Player();
        Position position = new Position(10, 10);
        Event event = new Event(Event.TYPE.NO_EVENT);

        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        List<Element> walls = new ArrayList<>();

        player.setPosition(position);

        Mockito.when(house.getEnemies()).thenReturn(new ArrayList<>());
        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(house.getWalls()).thenReturn(walls);

        event.setType(Event.TYPE.PLAYER_UP);
        updater.processEvent(event, house);
        assertEquals(new Position(10, 9), player.getPosition());

        event.setType(Event.TYPE.PLAYER_DOWN);
        updater.processEvent(event, house);
        assertEquals(position, player.getPosition());

        event.setType(Event.TYPE.PLAYER_LEFT);
        updater.processEvent(event, house);
        assertEquals(new Position(9, 10), player.getPosition());

        event.setType(Event.TYPE.PLAYER_RIGHT);
        updater.processEvent(event, house);
        assertEquals(position, player.getPosition());

        walls.add(new Element(9, 10));
        walls.add(new Element(10, 11));
        walls.add(new Element(11, 10));
        walls.add(new Element(10, 9));

        event.setType(Event.TYPE.PLAYER_DOWN);
        updater.processEvent(event, house);
        assertEquals(position, player.getPosition());

        event.setType(Event.TYPE.PLAYER_UP);
        updater.processEvent(event, house);
        assertEquals(position, player.getPosition());

        event.setType(Event.TYPE.PLAYER_LEFT);
        updater.processEvent(event, house);
        assertEquals(position, player.getPosition());

        event.setType(Event.TYPE.PLAYER_RIGHT);
        updater.processEvent(event, house);
        assertEquals(position, player.getPosition());
    }


    @Test
    public void testSpawnEnemy() {
        Updater updater = new Updater();
        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Zombie(20, 10));
        enemies.add(new Zombie(16, 18));
        enemies.add(new Zombie(15, 14));
        enemies.add(new Ghost(12, 12));
        enemies.add(new Ghost(15, 12));
        enemies.add(new Ghost(10, 10));

        long value = 0;

        Mockito.when(house.getLastSpawned()).thenReturn(value);
        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getWidth()).thenReturn(100);
        Mockito.when(house.getHeight()).thenReturn(100);
        Mockito.when(house.checkMonsterInPosition(any(Integer.class), any(Integer.class))).thenReturn(false);

        updater.spawnEnemy(house);

        Mockito.verify(house, times(1)).addEnemy(any(Enemy.class));

        enemies.add(new Ghost(15, 15));
        enemies.add(new Ghost(30, 30));
        enemies.add(new Ghost(21, 22));
        enemies.add(new Ghost(2, 2));
        enemies.add(new Ghost(5, 2));
        enemies.add(new Ghost(0, 10));

        updater.spawnEnemy(house);

        Mockito.verify(house, times(1)).addEnemy(any(Enemy.class));
    }

    @Test
    public void testLaunchHorizontalBullet() {
        Updater updater = new Updater();

        HauntedHouse house = new HauntedHouse(50, 30);

        int before = house.getBullets().size();

        updater.launchHorizontalBullet(house, 1);

        assertEquals(before + 1, house.getBullets().size());
        assertEquals(house.getPlayer().getPosition().getX() + 1, house.getBullets().get(0).getPosition().getX());
        assertEquals(house.getPlayer().getPosition().getY(), house.getBullets().get(0).getPosition().getY());
        assertEquals(1, house.getBullets().get(0).getDelta());
    }

    @Test
    public void testLaunchVerticalBullet() {
        Updater updater = new Updater();
        HauntedHouse house = new HauntedHouse(50, 30);

        int before = house.getBullets().size();

        updater.launchVerticalBullet(house, 1);

        assertEquals(before + 1, house.getBullets().size());
        assertEquals(house.getPlayer().getPosition().getX(), house.getBullets().get(0).getPosition().getX());
        assertEquals(house.getPlayer().getPosition().getY() + 1, house.getBullets().get(0).getPosition().getY());
        assertEquals(1, house.getBullets().get(0).getDelta());
    }

    @Test
    public void testBulletEvent() {
        Updater updater = new Updater();
        Event event = new Event(Event.TYPE.NO_EVENT);
        HauntedHouse house = new HauntedHouse(50, 30);

        int before = house.getBullets().size();

        event.setType(Event.TYPE.BULLET_UP);
        updater.processEvent(event, house);
        assertEquals(before + 1, house.getBullets().size());
        assertEquals(house.getPlayer().getPosition().getX(), house.getBullets().get(0).getPosition().getX());
        assertEquals(house.getPlayer().getPosition().getY() - 1, house.getBullets().get(0).getPosition().getY());
        assertEquals(-1, house.getBullets().get(0).getDelta());

        house.getPlayer().setLastFired(0);

        event.setType(Event.TYPE.BULLET_DOWN);
        updater.processEvent(event, house);
        assertEquals(before + 2, house.getBullets().size());
        assertEquals(house.getPlayer().getPosition().getX(), house.getBullets().get(1).getPosition().getX());
        assertEquals(house.getPlayer().getPosition().getY() + 1, house.getBullets().get(1).getPosition().getY());
        assertEquals(1, house.getBullets().get(1).getDelta());

        house.getPlayer().setLastFired(0);

        event.setType(Event.TYPE.BULLET_LEFT);
        updater.processEvent(event, house);
        assertEquals(before + 3, house.getBullets().size());
        assertEquals(house.getPlayer().getPosition().getX() - 1, house.getBullets().get(2).getPosition().getX());
        assertEquals(house.getPlayer().getPosition().getY(), house.getBullets().get(2).getPosition().getY());
        assertEquals(-1, house.getBullets().get(2).getDelta());

        house.getPlayer().setLastFired(0);

        event.setType(Event.TYPE.BULLET_RIGHT);
        updater.processEvent(event, house);
        assertEquals(before + 4, house.getBullets().size());
        assertEquals(house.getPlayer().getPosition().getX() + 1, house.getBullets().get(3).getPosition().getX());
        assertEquals(house.getPlayer().getPosition().getY(), house.getBullets().get(3).getPosition().getY());
        assertEquals(1, house.getBullets().get(3).getDelta());
    }

    @Test
    public void testBulletMovement() {
        Updater updater = new Updater();

        List<Bullet> bullets = new ArrayList<>();
        bullets.add(new HorizontalBullet(10, 10, 1));
        bullets.add(new HorizontalBullet(20, 20, -1));
        bullets.add(new VerticalBullet(10, 10, 1));
        bullets.add(new VerticalBullet(20, 20, -1));

        List<Bullet> expected = new ArrayList<>();
        expected.add(new HorizontalBullet(11, 10, 1));
        expected.add(new HorizontalBullet(19, 20, -1));
        expected.add(new VerticalBullet(10, 11, 1));
        expected.add(new VerticalBullet(20, 19, -1));

        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        Mockito.when(house.getBullets()).thenReturn(bullets);

        updater.moveBullets(house);

        for (int i = 0; i < bullets.size(); i++) {
            assertEquals(expected.get(i).getPosition(), bullets.get(i).getPosition());
        }
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

        List<Element> walls = new ArrayList<>();
        walls.add(new Element(15, 15));

        List<Bullet> bullets = new ArrayList<>();
        bullets.add(new HorizontalBullet(15, 15, 0));

        List<Enemy> enemies = new ArrayList<>();

        Mockito.when(house.getWalls()).thenReturn(walls);
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
        bullets.get(0).setKillFlag(true);
        bullets.get(3).setKillFlag(true);

        Mockito.when(house.getBullets()).thenReturn(bullets);

        updater.increaseScoreWithKills(house);

        Mockito.verify(house, times(2)).increaseScore(Updater.getScoreKillIncrease());
    }

}
