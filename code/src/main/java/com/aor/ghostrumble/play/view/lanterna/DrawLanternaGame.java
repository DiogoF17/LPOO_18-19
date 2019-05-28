package com.aor.ghostrumble.play.view.lanterna;

import com.aor.ghostrumble.play.model.*;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.List;

public class DrawLanternaGame {

    private Screen screen;

    public DrawLanternaGame(Screen screen) {
        this.screen = screen;
    }

    public void drawAll(HauntedHouse house) throws IOException {

        TextGraphics graphics = screen.newTextGraphics();

        this.drawHouse(house.getWidth(), house.getHeight(), graphics);
        this.drawPlayerHP(house.getPlayer(), graphics);
        this.drawPlayer(house.getPlayer(), graphics);
        this.drawWalls(house.getWalls(), graphics);
        this.drawEnemies(house.getEnemies(), graphics);
        this.drawBullets(house.getBullets(), graphics);
        this.drawScore(house.getScore(), graphics);

        this.screen.refresh();
    }

    private void drawHouse(int width, int height, TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#32204E"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height + 5), ' ');
    }

    private void drawPlayerHP(Player player, TextGraphics graphics) {

        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(new TerminalPosition(5, 2), "HP: ");

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));

        if (player.getCurrentHealth() > 5) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#77FF77"));
        }
        else {
            graphics.enableModifiers(SGR.BLINK);
            graphics.setForegroundColor(TextColor.Factory.fromString("#FF7777"));
        }

        graphics.fillRectangle(
                new TerminalPosition(9, 2),
                new TerminalSize(player.getCurrentHealth(), 1), (char) 0x2588);

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));

        graphics.fillRectangle(
                new TerminalPosition(9 + player.getCurrentHealth(), 2),
                new TerminalSize(player.getMaxHealth() - player.getCurrentHealth(), 1),
                ' ');

        graphics.disableModifiers(SGR.BLINK);
    }


    private void drawPlayer(Player player, TextGraphics graphics) {
        graphics.enableModifiers(SGR.BOLD);

        graphics.setBackgroundColor(TextColor.Factory.fromString("#32204E"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#CEC20F"));
        graphics.putString(new TerminalPosition(player.getPosition().getX(), player.getPosition().getY()), "X");
    }


    private void drawWalls(List<Element> walls, TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#323232"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#9A3324"));
        graphics.enableModifiers(SGR.BOLD);

        for(Element wall : walls) {
            graphics.putString(new TerminalPosition(wall.getPosition().getX(), wall.getPosition().getY()), "#");
        }
    }

    private void drawEnemies(List<Enemy> enemies, TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#32204E"));
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

    private void drawBullets(List<Bullet> bullets, TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#32204E"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#D52209"));

        for(Bullet bullet : bullets) {

            if(bullet instanceof HorizontalBullet) {

                if(bullet.getDelta() > 0)
                    graphics.putString(new TerminalPosition(bullet.getPosition().getX(), bullet.getPosition().getY()), Character.toString((char) 0x25BA));
                else graphics.putString(new TerminalPosition(bullet.getPosition().getX(), bullet.getPosition().getY()), Character.toString((char) 0x25C4));

            }

            else if(bullet instanceof VerticalBullet) {

                if(bullet.getDelta() > 0)
                    graphics.putString(new TerminalPosition(bullet.getPosition().getX(), bullet.getPosition().getY()), Character.toString((char) 0x25BC));
                else graphics.putString(new TerminalPosition(bullet.getPosition().getX(), bullet.getPosition().getY()), Character.toString((char) 0x25B2));

            }

        }
    }

    private void drawScore(int score, TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#32204E"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#F6F0EF"));

        graphics.putString(new TerminalPosition(80, 2), "Score: " + score);
    }

}
