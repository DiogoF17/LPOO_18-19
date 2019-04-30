package com.aor.ghostrumble.model;

import java.util.Random;

public class Poltergeist extends Enemy {

    private final static int POLTERGEIST_SPEED = 400;
    private final static int POLTERGEIST_DAMAGE = 5;

    public Poltergeist(int x, int y) {
        super(x, y, POLTERGEIST_SPEED, POLTERGEIST_DAMAGE);
    }

    public static int getPoltergeistDamage() { return POLTERGEIST_DAMAGE; }
    public static int getPoltergeistSpeed() { return POLTERGEIST_SPEED; }

    @Override
    protected MovementStrategy createMovStrategy() {

        // poltergeists can have either strategy

        Random random = new Random();

        if(random.nextBoolean())
            return new LinearMovement();
        else return new FreeMovement();
    }
}
