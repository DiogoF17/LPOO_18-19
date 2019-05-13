package com.aor.ghostrumble.controller.Event;

import com.aor.ghostrumble.controller.Updater;
import com.aor.ghostrumble.model.HauntedHouse;

public class Event {

    private EventType eventType;
    private boolean close;
    private boolean exit;

    public Event(EventType eventType) {
        this.eventType = eventType;
        this.close = false;
        this.exit = false;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public boolean eventClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public boolean eventExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }


    public void processEvent(Updater updater, HauntedHouse house) {
        eventType.process(updater, house);
    }
}
