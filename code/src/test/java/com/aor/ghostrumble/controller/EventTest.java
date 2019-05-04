package com.aor.ghostrumble.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {

    @Test
    public void testInit() {
        Event event = new Event(Event.TYPE.EXIT);
        assertEquals(Event.TYPE.EXIT, event.getType());
    }

    @Test
    public void testSetType() {
        Event event = new Event(Event.TYPE.NO_EVENT);
        event.setType(Event.TYPE.EXIT);
        assertEquals(Event.TYPE.EXIT, event.getType());
    }

}
