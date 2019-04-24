package com.aor.ghostrumble.model;

public class Poltergeist extends Enemy {

    public Poltergeist(int x, int y) {
        super(x, y, 400);
    }

    @Override
    protected MovementStrategy createMovStrategy() {
        return new DVDMovement();
    }
}
