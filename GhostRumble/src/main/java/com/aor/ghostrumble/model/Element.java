package com.aor.ghostrumble.model;

public class Element {
    protected Position position;
    private boolean removeFlag;

    public Element(int x, int y) {
        position = new Position(x, y);
        removeFlag = false;
    }

    public boolean flaggedForRemoval() {
        return removeFlag;
    }

    public void setRemoveFlag(boolean removeFlag) {
        this.removeFlag = removeFlag;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
