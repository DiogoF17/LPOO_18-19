package com.aor.ghostrumble;

public class Ghost extends Enemy {

    public Ghost(int x, int y) {
        super(x, y, 10);
    }

    @Override
    protected Movement createMovStrategy() {
        return new DiagonalMovement();
    }
}
