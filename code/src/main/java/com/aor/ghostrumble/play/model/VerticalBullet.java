package com.aor.ghostrumble.play.model;

public class VerticalBullet extends Bullet {

    private final static int VERT_BULLET_SPEED = 50;

    public VerticalBullet(int x, int y, int delta) { super(x, y, delta, VERT_BULLET_SPEED); }

    public static int getVertBulletSpeed() { return VERT_BULLET_SPEED; }

    @Override
    public Position move() { return moveCustom(0, delta); }
}
