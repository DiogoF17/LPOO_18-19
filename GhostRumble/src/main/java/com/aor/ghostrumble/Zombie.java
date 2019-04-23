package com.aor.ghostrumble;

public class Zombie extends Enemy {

    public Zombie(int x, int y) {
        super(x, y, 2000);
    }

    @Override
    protected MovementStrategy createMovStrategy() {
        return new LinearMovement();
    }

}
