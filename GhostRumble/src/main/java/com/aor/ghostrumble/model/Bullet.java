package com.aor.ghostrumble.model;

public abstract class Bullet extends AutoMovable {

    private final static int BULLET_SPEED = 100;
    protected int delta;


    public Bullet(int x, int y, int delta) {
        super(x, y, BULLET_SPEED);
        this.delta = delta;
    }

    public final static int getBulletSpeed() { return BULLET_SPEED; }

    public int getDelta() { return delta; }
}
