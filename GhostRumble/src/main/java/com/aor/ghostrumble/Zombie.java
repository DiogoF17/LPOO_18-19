package com.aor.ghostrumble;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Zombie extends Enemy {

    public Zombie(int x, int y) {
        super(x, y, 10);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#79CF1E"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "Z");
    }

    @Override
    protected Movement createMovStrategy() {
        return new LinearMovement();
    }
}
