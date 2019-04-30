package com.aor.ghostrumble.model;

// class that represents movable elements, that move on their own (on a time basis)

public abstract class AutoMovable extends Movable {

    private int speed;
    private long lastMoved;

    public AutoMovable(int x, int y, int speed) {
        super(x, y);
        this.speed = speed;
        this.lastMoved = 0;
    }

    public int getSpeed() {
        return speed;
    }

/*
    public void setSpeed(int speed) {
        this.speed = speed;
    }
*/

    public long getLastMoved() {
        return lastMoved;
    }

    public void setLastMoved(long lastMoved) {
        this.lastMoved = lastMoved;
    }
}
