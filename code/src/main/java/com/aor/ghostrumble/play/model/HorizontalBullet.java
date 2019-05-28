package com.aor.ghostrumble.play.model;

public class HorizontalBullet extends Bullet {

    private final static int HOR_BULLET_SPEED = 25;

    public HorizontalBullet(int x, int y, int delta) { super(x, y, delta, HOR_BULLET_SPEED); }

    public final static int getHorBulletSpeed() { return HOR_BULLET_SPEED; }

    @Override
    public Position move() { return moveCustom(delta, 0); }
}

