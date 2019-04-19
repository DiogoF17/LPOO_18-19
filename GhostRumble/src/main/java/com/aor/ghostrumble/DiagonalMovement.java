package com.aor.ghostrumble;

public class DiagonalMovement implements MovementStrategy {

    @Override
    public void move(Enemy enemy) {
        enemy.setPosition(enemy.moveDown());
        enemy.setPosition(enemy.moveRight());
    }
}
