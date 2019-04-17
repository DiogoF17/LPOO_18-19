package com.aor.ghostrumble;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Player extends Element {
    private int currentHealth;
    private int maxHealth;

    public Player() { this(10, 10); }
    public Player(int x, int y) {
        super(x, y);
        currentHealth = 5;
        maxHealth = 10;
    }

    public void draw(TextGraphics graphics) {
        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(new TerminalPosition(5, 2), "HP: ");
        graphics.setBackgroundColor(TextColor.Factory.fromString("#77FF77"));
        graphics.fillRectangle(
                new TerminalPosition(9, 2),
                new TerminalSize(currentHealth, 1),
                ' ');

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#77FF77"));

        graphics.fillRectangle(
                new TerminalPosition(9 + currentHealth, 2),
                new TerminalSize(maxHealth - currentHealth, 1),
                '_');

        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "X");
    }
}
