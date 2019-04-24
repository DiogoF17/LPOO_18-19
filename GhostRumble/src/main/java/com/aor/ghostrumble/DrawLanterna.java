package com.aor.ghostrumble;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.List;

public class DrawLanterna implements DrawingMethod {

    private Screen screen;

    public DrawLanterna(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void drawAll(HauntedHouse house) throws IOException {

        TextGraphics graphics = screen.newTextGraphics();

        this.drawHouse(house.getWidth(), house.getHeight(), graphics);
        this.drawPlayerHP(house.getPlayer(), graphics);
        this.drawPlayer(house.getPlayer(), graphics);
        this.drawWalls(house.getWalls(), graphics);
        this.drawEnemies(house.getEnemies(), graphics);

        this.screen.refresh();
    }

    private void drawHouse(int width, int height, TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#2D1694"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height + 5), ' ');
    }

    private void drawPlayerHP(Player player, TextGraphics graphics) {

        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(new TerminalPosition(5, 2), "HP: ");

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));

        if (player.getCurrentHealth() > 2) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#77FF77"));
        }
        else {
            graphics.enableModifiers(SGR.BLINK);
            graphics.setForegroundColor(TextColor.Factory.fromString("#FF7777"));
        }

        graphics.fillRectangle(
                new TerminalPosition(9, 2),
                new TerminalSize(player.getCurrentHealth(), 1),
                '█');

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));

        graphics.fillRectangle(
                new TerminalPosition(9 + player.getCurrentHealth(), 2),
                new TerminalSize(player.getMaxHealth() - player.getCurrentHealth(), 1),
                ' ');

        graphics.disableModifiers(SGR.BLINK);
    }


    private void drawPlayer(Player player, TextGraphics graphics) {
        graphics.enableModifiers(SGR.BOLD);

        graphics.setBackgroundColor(TextColor.Factory.fromString("#2D1694"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#CEC20F"));
        graphics.putString(new TerminalPosition(player.getPosition().getX(), player.getPosition().getY()), "X");
    }


    private void drawWalls(List<Position> walls, TextGraphics graphics) {

        for(Position wall : walls) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#9A3324"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(wall.getX(), wall.getY()), "#");
        }
    }

    private void drawEnemies(List<Enemy> enemies, TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#2D1694"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(5, screen.getTerminalSize().getRows() - 4), "Enemies left: " + enemies.size());

        for(Enemy enemy : enemies) {
            graphics.enableModifiers(SGR.BOLD);

            if (enemy instanceof Zombie) {
                graphics.setForegroundColor(TextColor.Factory.fromString("#55831C"));
                graphics.putString(new TerminalPosition(enemy.getPosition().getX(), enemy.getPosition().getY()), "Z");
            }
            else if (enemy instanceof Ghost) {
                graphics.setForegroundColor(TextColor.Factory.fromString("#AAAAAA"));
                graphics.putString(new TerminalPosition(enemy.getPosition().getX(), enemy.getPosition().getY()), "G");
            }
            else if(enemy instanceof Poltergeist) {
                graphics.setForegroundColor(TextColor.Factory.fromString("#B7F072"));
                graphics.putString(new TerminalPosition(enemy.getPosition().getX(), enemy.getPosition().getY()), "P");
            }
        }

    }

}
