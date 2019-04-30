package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;
import static junit.framework.TestCase.assertEquals;

public class UpdaterTest {

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
            enemy.setLastMoved(currentTimeMillis() - enemy.getSpeed());
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

        updater.update(new Event(Event.TYPE.NO_EVENT), house);

        for (int i = 0; i < 6; i++) {
            assertEquals(expected.get(i).getPosition(), enemies.get(i).getPosition());
        }
    }

    @Test
    public void testDeleteEnemies() {

        Updater updater = new Updater();
        Position position = new Position(0, 30);

        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Event event = Mockito.mock(Event.class);
        Player player = Mockito.mock(Player.class);

        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            enemies.add(new Zombie(i, 10));
            enemies.add(new Ghost(i, 20));
        }

        Mockito.when(event.getType()).thenReturn(Event.TYPE.NO_EVENT);
        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(player.getPosition()).thenReturn(position);

        updater.update(event, house);
        assertEquals(20, enemies.size());

        for (Enemy enemy : enemies) {
            enemy.setRemoveFlag(true);
        }

        updater.update(event, house);
        assertEquals(0, enemies.size());
    }

    @Test
    public void testPlayerDamage() {
        Updater updater = new Updater();
        Player player = new Player();
        Position position = new Position(0, 30);

        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Event event = Mockito.mock(Event.class);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Poltergeist(0, 30));

        player.setPosition(position);

        Mockito.when(event.getType()).thenReturn(Event.TYPE.NO_EVENT);
        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getPlayer()).thenReturn(player);

        assertEquals(20, player.getCurrentHealth());

        updater.update(event, house);

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
        updater.update(event, house);
        assertEquals(new Position(10, 9), player.getPosition());

        event.setType(Event.TYPE.PLAYER_DOWN);
        updater.update(event, house);
        assertEquals(position, player.getPosition());

        event.setType(Event.TYPE.PLAYER_LEFT);
        updater.update(event, house);
        assertEquals(new Position(9, 10), player.getPosition());

        event.setType(Event.TYPE.PLAYER_RIGHT);
        updater.update(event, house);
        assertEquals(position, player.getPosition());

        walls.add(new Element(9, 10));
        walls.add(new Element(10, 11));
        walls.add(new Element(11, 10));
        walls.add(new Element(10, 9));

        event.setType(Event.TYPE.PLAYER_DOWN);
        updater.update(event, house);
        assertEquals(position, player.getPosition());

        event.setType(Event.TYPE.PLAYER_UP);
        updater.update(event, house);
        assertEquals(position, player.getPosition());

        event.setType(Event.TYPE.PLAYER_LEFT);
        updater.update(event, house);
        assertEquals(position, player.getPosition());

        event.setType(Event.TYPE.PLAYER_RIGHT);
        updater.update(event, house);
        assertEquals(position, player.getPosition());

    }
}
