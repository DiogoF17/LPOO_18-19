package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    }

    @Test
    public void testMovePlayer() {


    }
}
