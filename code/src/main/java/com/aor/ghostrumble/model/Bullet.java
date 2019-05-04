package com.aor.ghostrumble.model;

public abstract class Bullet extends AutoMovable {

    protected int delta;
    private boolean killFlag;

    public Bullet(int x, int y, int delta, int speed) {
        super(x, y, speed);
        this.delta = delta;
        this.killFlag = false;
    }

    public int getDelta() { return delta; }
    public boolean getKillFlag() { return killFlag; }

    public void setKillFlag(boolean killFlag) { this.killFlag = killFlag; }
}
