package com.aor.ghostrumble.model;

public class HorizontalBullet extends Bullet {

    public HorizontalBullet(int x, int y, int delta) { super(x, y, delta); }

    @Override
    public Position move() { return moveCustom(delta, 0); }
}

