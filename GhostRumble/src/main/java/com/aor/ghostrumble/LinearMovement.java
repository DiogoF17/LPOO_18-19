package com.aor.ghostrumble;

public class LinearMovement implements MovementStrategy {

    @Override
    public void move(Enemy enemy) {
        enemy.setPosition(enemy.moveLeft());
    }
}
