package com.aor.ghostrumble.play.controller.event;

import com.aor.ghostrumble.play.controller.BulletsUpdater;
import com.aor.ghostrumble.play.controller.PlayerUpdater;
import com.aor.ghostrumble.play.controller.Updater;
import com.aor.ghostrumble.play.model.*;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class EventQueueTest {

    @Test
    public void testInitQueue() {
        EventQueue queue = new EventQueue();
        assertTrue(queue.getEventQueue().isEmpty());
    }

    @Test
    public void testInitClose() {
        EventQueue queue = new EventQueue();
        assertFalse(queue.close());
    }

    @Test
    public void testRaiseEvent() {
        EventQueue queue = new EventQueue();
        queue.raiseEvent(new EventBulletDown());
        queue.raiseEvent(new EventPlayerDown());

        assertEquals(2, queue.getEventQueue().size());
    }

    @Test
    public void testSetClose() {
        EventQueue queue = new EventQueue();
        queue.setClose(true);

        assertTrue(queue.close());
    }

    @Test
    public void testExecuteEvents() {
        GameEvent event = Mockito.mock(GameEvent.class);
        Updater updater = Mockito.mock(Updater.class);
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Mockito.doNothing().when(event).process(updater, house);

        EventQueue queue = new EventQueue();
        queue.raiseEvent(event);
        queue.raiseEvent(event);
        queue.executeEvents(updater, house);

        assertTrue(queue.getEventQueue().isEmpty());
    }

    @Test
    public void testProcessEvent() {
        Updater updater = Mockito.mock(Updater.class);
        PlayerUpdater playerUpdater = Mockito.mock(PlayerUpdater.class);
        BulletsUpdater bulletsUpdater = Mockito.mock(BulletsUpdater.class);
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Player player = Mockito.mock(Player.class);
        EventQueue queue = new EventQueue();

        // return random positions; doesn't really matter in this unit test
        Mockito.when(player.moveUp()).thenReturn(new Position(1, 1));
        Mockito.when(player.moveDown()).thenReturn(new Position(2, 1));
        Mockito.when(player.moveLeft()).thenReturn(new Position(3, 1));
        Mockito.when(player.moveRight()).thenReturn(new Position(4, 1));

        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(updater.getPlayerUpdater()).thenReturn(playerUpdater);
        Mockito.when(updater.getBulletsUpdater()).thenReturn(bulletsUpdater);

        queue.raiseEvent(new EventPlayerUp());
        queue.executeEvents(updater, house);
        Mockito.verify(playerUpdater, times(1)).movePlayer(player, player.moveUp(), house);

        queue.raiseEvent(new EventPlayerLeft());
        queue.executeEvents(updater, house);
        Mockito.verify(playerUpdater, times(1)).movePlayer(player, player.moveLeft(), house);

        queue.raiseEvent(new EventPlayerRight());
        queue.executeEvents(updater, house);
        Mockito.verify(playerUpdater, times(1)).movePlayer(player, player.moveRight(), house);

        queue.raiseEvent(new EventPlayerDown());
        queue.executeEvents(updater, house);
        Mockito.verify(playerUpdater, times(1)).movePlayer(player, player.moveDown(), house);

        queue.raiseEvent(new EventBulletUp());
        queue.executeEvents(updater, house);
        Mockito.verify(bulletsUpdater, times(1)).launchVerticalBullet(house, -1);

        queue.raiseEvent(new EventBulletRight());
        queue.executeEvents(updater, house);
        Mockito.verify(bulletsUpdater, times(1)).launchHorizontalBullet(house, 1);

        queue.raiseEvent(new EventBulletLeft());
        queue.executeEvents(updater, house);
        Mockito.verify(bulletsUpdater, times(1)).launchHorizontalBullet(house, -1);

        queue.raiseEvent(new EventBulletDown());
        queue.executeEvents(updater, house);
        Mockito.verify(bulletsUpdater, times(1)).launchVerticalBullet(house, 1);

    }

    @Test
    public void testCallCollision() {
        Updater updater = Mockito.mock(Updater.class);
        PlayerUpdater playerUpdater = Mockito.mock(PlayerUpdater.class);
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Player player = new Player(9, 10);
        EventQueue queue = new EventQueue();

        Mockito.when(house.getPlayer()).thenReturn(player);
        Mockito.when(updater.getPlayerUpdater()).thenReturn(playerUpdater);
        Mockito.when(
                playerUpdater.movePlayer(any(Player.class), any(Position.class), any(HauntedHouse.class))
        ).thenCallRealMethod();

        queue.raiseEvent(new EventPlayerRight());
        queue.raiseEvent(new EventPlayerRight());
        queue.executeEvents(updater, house);

        Mockito.verify(updater, times(2)).checkEnemyCollisions(house);
    }
}
