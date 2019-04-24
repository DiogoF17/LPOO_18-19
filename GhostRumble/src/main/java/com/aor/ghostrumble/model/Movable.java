package com.aor.ghostrumble.model;


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

    public Position moveUp() {
        return moveCustom(0, -1);
    }

    public Position moveDown() {
        return moveCustom(0, 1);
    }

    public Position moveLeft() {
        return moveCustom(-1, 0);
    }

    public Position moveRight() {
        return moveCustom(1, 0);
    }

}
