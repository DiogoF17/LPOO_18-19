package com.aor.ghostrumble.play.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class PlayerTest {

    private Player player;

    @Before
    public void playerInit() { player = new Player(); }

    @Test
    public void testInitMaxHP() {
        assertEquals(player.getMaxHealth(), Player.getMaxHealthConstant());
    }

    @Test
    public void testInitFullHP() {
        assertEquals(player.getCurrentHealth(), player.getMaxHealth());
    }

    @Test
    public void testInitLastFired() {
        assertEquals(0, player.getLastFired());
    }

    @Test
    public void testInitObservers() {
        assertTrue(player.getObservers().isEmpty());
    }

    @Test
    public void testSetLastFired() {

        long time = 1000;
        player.setLastFired(time);
        assertEquals(1000, player.getLastFired());

    }

    @Test
    public void testAddObservers() {

        PlayerObserver observer = Mockito.mock(PlayerObserver.class);
        player.addObserver(observer);
        player.addObserver(observer);
        player.addObserver(observer);

        assertEquals(3, player.getObservers().size());

    }

    @Test
    public void testRemoveObservers() {

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

        int damage = player.getMaxHealth() - 10;

        player.damagePlayer(damage);

        assertEquals(player.getMaxHealth() - damage, player.getCurrentHealth());

    }

    @Test
    public void testFullyDamagePlayer() {

        player.damagePlayer(player.getMaxHealth() + 20);

        assertEquals(0, player.getCurrentHealth());

    }

}
