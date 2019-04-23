package com.aor.ghostrumble;


public abstract class Movable {
    private Position position;

    public Movable(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    protected Position moveCustom(int x, int y) {
        return new Position(position.getX() + x, position.getY() + y);
    }

    protected Position moveUp() {
        return moveCustom(0, -1);
    }

    protected Position moveDown() {
        return moveCustom(0, 1);
    }

    protected Position moveLeft() {
        return moveCustom(-1, 0);
    }

    protected Position moveRight() {
        return moveCustom(1, 0);
    }

}
