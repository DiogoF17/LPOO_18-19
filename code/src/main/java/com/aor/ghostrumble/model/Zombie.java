package com.aor.ghostrumble.model;

public class Zombie extends Enemy {

    private final static int ZOMBIE_SPEED = 350;
    private final static int ZOMBIE_DAMAGE = 2;

    public Zombie(int x, int y) {
        super(x, y, ZOMBIE_SPEED, ZOMBIE_DAMAGE);
    }

    public final static int getZombieSpeed() { return ZOMBIE_SPEED; }
    public final static int getZombieDamage() { return ZOMBIE_DAMAGE; }

    @Override
    protected MovementStrategy createMovStrategy() { return new LinearMovement(); }

}
