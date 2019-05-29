package com.aor.ghostrumble.play.controller.event;

import com.aor.ghostrumble.play.controller.Updater;
import com.aor.ghostrumble.play.model.HauntedHouse;

import java.util.LinkedList;
import java.util.Queue;

public class EventQueue {

    private Queue<GameEvent> eventQueue = new LinkedList<>();
    private boolean close;

    public void executeEvents(Updater updater, HauntedHouse house) {

        while(!eventQueue.isEmpty()) {

            GameEvent currentGameEvent = eventQueue.remove();
            currentGameEvent.process(updater, house);

        }

    }

    public void raiseEvent(GameEvent event) {
        eventQueue.add(event);
    }

    public boolean close() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public Queue<GameEvent> getEventQueue() {
        return eventQueue;
    }
}
