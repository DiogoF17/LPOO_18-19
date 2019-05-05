package com.aor.ghostrumble.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.*;

public class HauntedHouseTest {

    private int randomWidth;
    private int randomHeight;
    private HauntedHouse house;

    @Before
    public void init() {
        Random random = new Random();
        this.randomWidth = random.nextInt(100 - 10) + 10;
        this.randomHeight = random.nextInt(40 - 10) + 10;
        this.house = new HauntedHouse(randomWidth, randomHeight);
    }

    @Test
    public void testInitWidth() {
        assertEquals(randomWidth, house.getWidth());
    }

    @Test
    public void testInitHeight() { assertEquals(randomHeight, house.getHeight()); }

    @Test
    public void testInitWalls() {
        assertEquals(2 * randomWidth + 2 * (randomHeight - 7), house.getWalls().size());
    }

    @Test
    public void testWallsYPos() {
        assertEquals(randomHeight - 1, house.getWalls().get(1).getPosition().getY());
    }

    @Test
    public void testWallsXPos() {
        assertEquals(randomWidth - 1, house.getWalls().get(2 * randomWidth + 1).getPosition().getX());
    }


    @Test
    public void testInitEnemies() {
        assertTrue(house.getEnemies().isEmpty());
    }

    @Test
    public void testAddEnemies() {
        house.addEnemy(new Zombie(10, 10));
        house.addEnemy(new Poltergeist(10, 20));
        house.addEnemy(new Ghost(40, 30));

        assertEquals(3, house.getEnemies().size());
    }

    @Test
    public void testAddEnemiesObs() {
        house.addEnemy(new Zombie(10, 10));
        house.addEnemy(new Poltergeist(10, 20));
        house.addEnemy(new Ghost(40, 30));

        assertEquals(3, house.getPlayer().getObservers().size());
    }

    @Test
    public void testMonsterInPositionTrue() {
        house.addEnemy(new Zombie(10, 10));

        assertTrue(house.checkMonsterInPosition(10, 10));
    }

    @Test
    public void testMonsterInPositionFalse() {
        house.addEnemy(new Zombie(20, 10));

        assertFalse(house.checkMonsterInPosition(10, 10));
    }

    @Test
    public void testLastSpawned() {
        long value = 1000;
        house.setLastSpawned(value);

        assertEquals(value, house.getLastSpawned());
    }

    @Test
    public void testLastIncrementedScore() {
        long value = 1000;
        house.setLastIncrementedScore(value);

        assertEquals(value, house.getLastIncrementedScore());
    }

    @Test
    public void testScore() {
        assertEquals(0, house.getScore());
    }

    @Test
    public void testIncreaseScore() {
        house.increaseScore(20);
        house.increaseScore(30);

        assertEquals(50, house.getScore());
    }

}
