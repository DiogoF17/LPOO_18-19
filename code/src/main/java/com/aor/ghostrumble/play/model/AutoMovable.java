package com.aor.ghostrumble.play.model;

// class that represents movable elements, that move on their own (on a time basis)

public abstract class AutoMovable extends Movable {

    private int speed;
    private long lastMoved;
    private boolean removeFlag;

    public AutoMovable(int x, int y, int speed) {
        super(x, y);
        this.speed = speed;
        this.lastMoved = 0;
        this.removeFlag = false;
    }

    public boolean flaggedForRemoval() {
        return removeFlag;
    }

    public void setRemoveFlag(boolean removeFlag) {
        this.removeFlag = removeFlag;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public long getLastMoved() {
        return lastMoved;
    }

    public void setLastMoved(long lastMoved) {
        this.lastMoved = lastMoved;
    }

    public abstract Position move();
}
