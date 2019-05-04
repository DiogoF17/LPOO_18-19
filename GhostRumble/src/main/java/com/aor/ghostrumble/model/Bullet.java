package com.aor.ghostrumble.model;

public abstract class Bullet extends AutoMovable {

    protected int delta;


    public Bullet(int x, int y, int delta, int speed) {
        super(x, y, speed);
        this.delta = delta;
    }

    public int getDelta() { return delta; }
}
