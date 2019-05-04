package com.aor.ghostrumble.model;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class PlayerTest {

    @Test
    public void testInitMaxHP() {
        Player player = new Player();
        assertEquals(player.getMaxHealth(), Player.getMaxHealthConstant());
    }

    @Test
    public void testInitFullHP() {
        Player player = new Player();
        assertEquals(player.getCurrentHealth(), player.getMaxHealth());
    }

    @Test
    public void testInitObservers() {
        Player player = new Player();
        assertTrue(player.getObservers().isEmpty());
    }

    @Test
    public void testAddObservers() {
        Player player = new Player();
        PlayerObserver observer = Mockito.mock(PlayerObserver.class);
        player.addObserver(observer);
        player.addObserver(observer);
        player.addObserver(observer);

        assertEquals(3, player.getObservers().size());
    }

    @Test
    public void testRemoveObservers() {
        Player player = new Player();
        PlayerObserver observer = Mockito.mock(PlayerObserver.class);

        player.addObserver(observer);
        player.addObserver(observer);
        player.addObserver(observer);

        player.removeObserver(player.getObservers().get(0));
        player.removeObserver(player.getObservers().get(0));

        assertEquals(1, player.getObservers().size());
    }

    @Test
    public void testNotifyObservers() {
        Player player = new Player();
        PlayerObserver observer = Mockito.mock(PlayerObserver.class);
        player.addObserver(observer);
        player.addObserver(observer);
        player.addObserver(observer);

        Mockito.doNothing().when(observer).update(any(Player.class));

        player.notifyObservers();

        Mockito.verify(observer, times(6)).update(any(Player.class));
    }

    @Test
    public void testPartlyDamagePlayer() {
        Player player = new Player();
        int damage = player.getMaxHealth() - 10;

        player.damagePlayer(damage);

        assertEquals(player.getMaxHealth() - damage, player.getCurrentHealth());
    }

    @Test
    public void testFullyDamagePlayer() {
        Player player = new Player();

        player.damagePlayer(player.getMaxHealth() + 20);

        assertEquals(0, player.getCurrentHealth());
    }
}
