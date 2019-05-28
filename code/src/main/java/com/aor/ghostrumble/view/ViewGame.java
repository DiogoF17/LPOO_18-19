package com.aor.ghostrumble.view;

import com.aor.ghostrumble.controller.event.EventQueue;
import com.aor.ghostrumble.model.HauntedHouse;

public abstract class ViewGame {

    protected EventQueue queue = new EventQueue();

    public EventQueue getQueue() {
        return queue;
    }

    public void setQueue(EventQueue queue) {
        this.queue = queue;
    }

    public abstract boolean handleInput();

    public abstract void drawAll(HauntedHouse house);
}
