package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class PlayerUpdaterTest {

    @Test
    public void testDamagePlayer() {

        PlayerUpdater updater = new PlayerUpdater();
        Player player = new Player();

        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Poltergeist(0, 30));
        enemies.get(0).setHitPlayer(true);

        enemies.add(new Ghost(20, 20));
        enemies.get(1).setHitPlayer(false);

        Mockito.when(house.getEnemies()).thenReturn(enemies);
        Mockito.when(house.getPlayer()).thenReturn(player);

        assertEquals(Player.getMaxHealthConstant(), player.getCurrentHealth());

        updater.damagePlayer(house);
        updater.damagePlayer(house);

        assertEquals(Player.getMaxHealthConstant() - Poltergeist.getPoltergeistDamage(), player.getCurrentHealth());
        assertEquals(false, enemies.get(0).hasHitPlayer());
    }

    @Test
    public void testMovePlayer() {
        PlayerUpdater playerUpdater = new PlayerUpdater();
        Player player = new Player();
        Position position = new Position(10, 10);

        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        player.setPosition(position);

        Mockito.when(house.getEnemies()).thenReturn(new ArrayList<>());
        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(house.hitsWall(any(Position.class))).thenReturn(false);

        playerUpdater.movePlayer(player, player.moveUp(), house);
        assertEquals(new Position(10, 9), player.getPosition());

        playerUpdater.movePlayer(player, player.moveDown(), house);
        assertEquals(position, player.getPosition());

        playerUpdater.movePlayer(player, player.moveLeft(), house);
        assertEquals(new Position(9, 10), player.getPosition());

        playerUpdater.movePlayer(player, player.moveRight(), house);
        assertEquals(position, player.getPosition());


        Mockito.when(house.hitsWall(any(Position.class))).thenReturn(true);


        playerUpdater.movePlayer(player, player.moveUp(), house);
        assertEquals(position, player.getPosition());

        playerUpdater.movePlayer(player, player.moveDown(), house);
        assertEquals(position, player.getPosition());

        playerUpdater.movePlayer(player, player.moveLeft(), house);
        assertEquals(position, player.getPosition());

        playerUpdater.movePlayer(player, player.moveRight(), house);
        assertEquals(position, player.getPosition());
    }

    @Test
    public void testPlayerNotify() {
        PlayerUpdater playerUpdater = new PlayerUpdater();
        HauntedHouse house = Mockito.spy(new HauntedHouse(50, 30));
        Player player = Mockito.spy(new Player());
        player.setPosition(new Position(11, 15));

        Mockito.when(house.getPlayer()).thenReturn(player);

        Enemy enemy = new Zombie(10, 10);
        player.addObserver(enemy);

        playerUpdater.movePlayer(player, player.moveLeft(), house);

        Position relativePosition = new Position(0, 1);
        Position expectedRelativePosition = new Position(enemy.getMovStrategy().getDeltaX(), enemy.getMovStrategy().getDeltaY());

        assertEquals(expectedRelativePosition, relativePosition);
    }

    @Test
    public void testMoveFalse() {
        PlayerUpdater playerUpdater = new PlayerUpdater();
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Player player = Mockito.mock(Player.class);
        List<Element> walls = new ArrayList<>();
        walls.add(new Element(10, 10));

        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(house.getWalls()).thenReturn(walls);
        Mockito.when(house.hitsWall(any(Position.class))).thenReturn(true);

        assertFalse(playerUpdater.movePlayer(player, new Position(10, 10), house));
    }

    @Test
    public void testMoveTrue() {
        PlayerUpdater playerUpdater = new PlayerUpdater();
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Player player = Mockito.mock(Player.class);
        List<Element> walls = new ArrayList<>();
        walls.add(new Element(10, 10));

        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(house.getWalls()).thenReturn(walls);
        Mockito.when(house.hitsWall(any(Position.class))).thenReturn(false);

        assertTrue(playerUpdater.movePlayer(player, new Position(10, 10), house));
    }

}
