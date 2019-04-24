package com.aor.ghostrumble.controller;

public class Event {

    public enum TYPE { PLAYER_LEFT, PLAYER_RIGHT, PLAYER_UP, PLAYER_DOWN, CLOSE, EXIT, NO_EVENT }

    private TYPE type;

    public Event(TYPE type) {
        this.type = type;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
