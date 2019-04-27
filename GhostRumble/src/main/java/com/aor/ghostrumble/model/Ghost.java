package com.aor.ghostrumble.model;

public class Ghost extends Enemy {

    private final static int GHOST_SPEED = 700;
    private final static int GHOST_DAMAGE = 3;

    public Ghost(int x, int y) {
        super(x, y, GHOST_SPEED, GHOST_DAMAGE);
    }

    @Override
    protected MovementStrategy createMovStrategy() {
        return new DiagonalMovement();
    }

}
