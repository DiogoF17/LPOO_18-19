package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.controller.Event.Event;
import com.aor.ghostrumble.controller.Event.EventBulletDown;
import com.aor.ghostrumble.controller.Event.EventPlayerDown;
import com.aor.ghostrumble.controller.Event.EventQueue;
import com.aor.ghostrumble.model.HauntedHouse;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

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
    public void testInitExit() {
        EventQueue queue = new EventQueue();
        assertFalse(queue.exit());
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
    public void testSetExit() {
        EventQueue queue = new EventQueue();
        queue.setExit(true);

        assertTrue(queue.exit());
    }

    @Test
    public void testExecuteEvents() {
        Event event = Mockito.mock(Event.class);
        Updater updater = Mockito.mock(Updater.class);
        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Mockito.doNothing().when(event).process(updater, house);

        EventQueue queue = new EventQueue();
        queue.raiseEvent(event);
        queue.raiseEvent(event);
        queue.executeEvents(updater, house);

        assertTrue(queue.getEventQueue().isEmpty());
    }

}
