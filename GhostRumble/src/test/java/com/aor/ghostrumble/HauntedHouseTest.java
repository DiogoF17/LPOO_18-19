package com.aor.ghostrumble;

import com.aor.ghostrumble.model.HauntedHouse;
import com.aor.ghostrumble.model.Player;
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
    public void testInitDimensions() {
        assertEquals(house.getWidth(), randomWidth);
        assertEquals(house.getHeight(), randomHeight);
    }

    @Test
    public void testInitPlayer() {
        assertEquals(house.getPlayer().getMaxHealth(), 20);
        assertEquals(house.getPlayer().getCurrentHealth(), house.getPlayer().getMaxHealth());
        assertEquals(house.getWalls().size(), 2 * randomWidth + 2 * (randomHeight - 7));
        assertEquals(house.getEnemies(), house.getPlayer().getObservers());
    }
}
