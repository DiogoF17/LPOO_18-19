package com.aor.ghostrumble.model;

import java.util.Random;

public class DVDMovement extends MovementStrategy {

    public DVDMovement() {
        Random random = new Random();
        deltaX = random.nextInt(2);
        deltaY = random.nextInt(2);
    }

    @Override
    public void update(Enemy enemy) {

    }
}
