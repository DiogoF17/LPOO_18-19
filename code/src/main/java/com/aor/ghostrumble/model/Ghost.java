package com.aor.ghostrumble.model;

public class Ghost extends Enemy {

    private final static int GHOST_SPEED = 200;
    private final static int GHOST_DAMAGE = 3;

    public Ghost(int x, int y) {
        super(x, y, GHOST_SPEED, GHOST_DAMAGE);
    }

    public static int getGhostDamage() { return GHOST_DAMAGE; }
    public static int getGhostSpeed() { return GHOST_SPEED; }

    @Override
    protected MovementStrategy createMovStrategy() {
        return new FreeMovement();
    }

}
