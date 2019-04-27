package com.aor.ghostrumble.model;

public abstract class MovementStrategy {
    protected int deltaX;
    protected int deltaY;

    public MovementStrategy() {
        this.deltaX = 0;
        this.deltaY = 0;
    }

    public abstract void updateDirection(Position position1, Position position2);

    public Position move(Enemy enemy) { return enemy.moveCustom(deltaX, deltaY); }
}
