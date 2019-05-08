package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class EnemiesUpdaterTest {

    @Test
    public void testEnemyMovement() {
        EnemiesUpdater updater = new EnemiesUpdater();
        Enemy zombie1 = Mockito.spy(new Zombie(20, 10));
        Enemy zombie2 = Mockito.spy(new Zombie(16, 18));
        Enemy zombie3 = Mockito.spy(new Zombie(15, 14));
        Enemy ghost1 = Mockito.spy(new Ghost(12, 12));
        Enemy ghost2 = Mockito.spy(new Ghost(15, 12));
        Enemy ghost3 = Mockito.spy(new Ghost(10, 10));


        Player player = new Player();
        Position standard = new Position(15, 10);
        player.setPosition(standard);

        List<Element> walls = new ArrayList<>();

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(zombie1);
        enemies.add(zombie2);
        enemies.add(zombie3);
        enemies.add(ghost1);
        enemies.add(ghost2);
        enemies.add(ghost3);

        for (Enemy enemy : enemies) {
            player.addObserver(enemy);
        }

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

        for (Enemy enemy : enemies) {
            Mockito.doAnswer(invocation -> currentTimeMillis() - enemy.getSpeed()).when(enemy).getLastMoved();
        }

        updater.moveEnemies(house);

        for (Enemy enemy : enemies) {
            Mockito.doAnswer(invocation -> enemy.getSpeed() - currentTimeMillis()).when(enemy).getLastMoved();
        }

        updater.moveEnemies(house);

        for (int i = 0; i < 6; i++) {
            assertEquals(expected.get(i).getPosition(), enemies.get(i).getPosition());
        }
    }


    @Test
    public void testEnemySpeed() {
        EnemiesUpdater updater = new EnemiesUpdater();
        Enemy zombie = Mockito.spy(new Zombie(10, 10));
        Player player = new Player();
        Position prePosition = new Position(10, 15);
        Position postPosition = new Position(15, 10);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(zombie);
        player.setPosition(prePosition);
        player.addObserver(zombie);
        player.setPosition(postPosition);

        Position expected = new Position(12, 10);

        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getPlayer()).thenReturn(player);

        updater.moveEnemies(house);
        updater.moveEnemies(house);

        Mockito.doAnswer(
                invocation -> currentTimeMillis() - zombie.getSpeed()
        ).when(zombie).getLastMoved();

        updater.moveEnemies(house);

        Mockito.doAnswer(
                invocation -> zombie.getSpeed() - currentTimeMillis()
        ).when(zombie).getLastMoved();

        updater.moveEnemies(house);

        assertEquals(enemies.get(0).getPosition().getX(), expected.getX());
        assertEquals(enemies.get(0).getPosition().getY(), expected.getY());
    }

    @Test
    public void testEnemyCollisions() {
        EnemiesUpdater updater = new EnemiesUpdater();
        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Zombie(10, 10));
        enemies.add(new Zombie(11, 10));

        List<Enemy> expected = new ArrayList<>();
        expected.add(new Zombie(10, 10));
        expected.add(new Zombie(12, 10));

        Player player = new Player();
        Position standard = new Position(15, 10);
        player.setPosition(standard);

        for (Enemy enemy : enemies) {
            player.addObserver(enemy);
        }

        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getPlayer()).thenReturn(player);

        updater.moveEnemies(house);

        for (int i = 0; i < 2; i++) {
            assertEquals(expected.get(i).getPosition(), enemies.get(i).getPosition());
        }
    }

    @Test
    public void testSpawnEnemy() {
        EnemiesUpdater updater = new EnemiesUpdater();
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
        Mockito.when(house.hitsEnemies(any(Position.class))).thenReturn(false);

        updater.spawnEnemy(house);

        value = currentTimeMillis();
        Mockito.when(house.getLastSpawned()).thenReturn(value);
        updater.spawnEnemy(house);

        Answer<Long> noSpawnAnswer = new Answer<Long>() {
            public Long answer(InvocationOnMock invocation) throws Throwable {
                return currentTimeMillis() - EnemiesUpdater.getEnemySpawnRate();
            }
        };
        Mockito.when(house.getLastSpawned()).thenAnswer(noSpawnAnswer);

        updater.spawnEnemy(house);

        Mockito.verify(house, times(1)).addEnemy(any(Enemy.class));

        Answer<Long> spawnAnswer = new Answer<Long>() {
            public Long answer(InvocationOnMock invocation) throws Throwable {
                return currentTimeMillis() - EnemiesUpdater.getEnemySpawnRate() - 1;
            }
        };
        Mockito.when(house.getLastSpawned()).thenAnswer(spawnAnswer);

        enemies.add(new Ghost(15, 15));
        enemies.add(new Ghost(30, 30));
        enemies.add(new Ghost(21, 22));
        enemies.add(new Ghost(2, 2));
        enemies.add(new Ghost(5, 2));
        enemies.add(new Ghost(0, 10));
        enemies.add(new Ghost(15, 15));
        enemies.add(new Ghost(2, 5));
        enemies.add(new Ghost(10, 10));

        updater.spawnEnemy(house);

        Mockito.verify(house, times(1)).addEnemy(any(Enemy.class));
        Mockito.verify(house, times(1)).setLastSpawned(any(long.class));
    }


    @Test
    public void testEnemySpawnRate() {
        EnemiesUpdater updater = new EnemiesUpdater();
        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        List<Enemy> enemies = new ArrayList<>();

        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getWidth()).thenReturn(100);
        Mockito.when(house.getHeight()).thenReturn(100);
        Mockito.when(house.hitsEnemies(any(Position.class))).thenReturn(false);

        Mockito.doAnswer(
                invocation -> {
                    Enemy enemy = invocation.getArgument(0);
                    enemies.add(enemy);
                    return null;
                }).when(house).addEnemy(any(Enemy.class));
        // instead of adding enemy to house enemies, since house is a mock,
        // adds enemy to our list 'enemies'

        Answer<Long> noSpawnAnswer = new Answer<Long>() {
            public Long answer(InvocationOnMock invocation) throws Throwable {
                return currentTimeMillis() - EnemiesUpdater.getEnemySpawnRate();
            }
        };
        Mockito.doAnswer(noSpawnAnswer).when(house).getLastSpawned();

        updater.spawnEnemy(house);

        Mockito.verify(house, times(0)).addEnemy(any(Enemy.class));

        Answer<Long> spawnAnswer = new Answer<Long>() {
            public Long answer(InvocationOnMock invocation) throws Throwable {
                return currentTimeMillis() - 2 * EnemiesUpdater.getEnemySpawnRate();
            }
        };
        Mockito.doAnswer(spawnAnswer).when(house).getLastSpawned();

        updater.spawnEnemy(house);

        Mockito.verify(house, times(1)).addEnemy(any(Enemy.class));

        assertEquals(1, house.getEnemies().size());
    }
}
