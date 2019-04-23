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

    protected Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    protected Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    protected Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    protected Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

}
