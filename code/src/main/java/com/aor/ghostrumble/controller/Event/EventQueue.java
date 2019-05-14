package com.aor.ghostrumble.controller.Event;

import com.aor.ghostrumble.controller.Updater;
import com.aor.ghostrumble.model.HauntedHouse;

import java.util.LinkedList;
import java.util.Queue;

public class EventQueue {

    private Queue<Event> eventQueue = new LinkedList<>();
    private boolean close;
    private boolean exit;

    public void executeEvents(Updater updater, HauntedHouse house) {

        while(!eventQueue.isEmpty()) {

            Event currentEvent = eventQueue.remove();
            currentEvent.process(updater, house);

        }

    }

    public void raiseEvent(Event event) {
        eventQueue.add(event);
    }

    public boolean close() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public boolean exit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public Queue<Event> getEventQueue() {
        return eventQueue;
    }
}
