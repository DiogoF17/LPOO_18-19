package com.aor.ghostrumble.play.model;

public abstract class MovementStrategy {

    int deltaX;
    int deltaY;

    public MovementStrategy() {
        this.deltaX = 0;
        this.deltaY = 0;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public abstract void updateDirection(Position position1, Position position2);

    public Position move(Movable movable) { return movable.moveCustom(deltaX, deltaY); }
}
