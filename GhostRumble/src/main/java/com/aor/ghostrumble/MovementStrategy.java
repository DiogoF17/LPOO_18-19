package com.aor.ghostrumble;

public abstract class MovementStrategy {
    protected int deltaX;
    protected int deltaY;

    public MovementStrategy() {
        deltaX = 0;
        deltaY = 0;
    }

    public abstract void update(Enemy enemy);

    public Position move(Enemy enemy) {
        return enemy.moveCustom(deltaX, deltaY);
    }
}
