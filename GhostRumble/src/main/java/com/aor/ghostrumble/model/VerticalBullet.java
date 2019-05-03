package com.aor.ghostrumble.model;

public class VerticalBullet extends Bullet {

    public VerticalBullet(int x, int y, int delta) { super(x, y, delta); }

    @Override
    public Position move() { return moveCustom(0, delta); }
}
