package com.aor.ghostrumble.model;

import org.junit.Before;
import org.junit.Test;

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
    public void testInitHeight() {
        assertEquals(randomHeight, house.getHeight());
    }

    @Test
    public void testInitWalls() {
        assertEquals(2 * randomWidth + 2 * (randomHeight - 7), house.getWalls().size());
    }

    @Test
    public void testInitEnemies() {
        assertEquals(3, house.getEnemies().size());
    }

}
