package com.aor.ghostrumble.model;

public class Poltergeist extends Enemy {

    private final static int POLTERGEIST_SPEED = 400;
    private final static int POLTERGEIST_DAMAGE = 5;

    public Poltergeist(int x, int y) {
        super(x, y, POLTERGEIST_SPEED, POLTERGEIST_DAMAGE);
    }

    @Override
    protected MovementStrategy createMovStrategy() {
        return new DVDMovement();
    }
}
