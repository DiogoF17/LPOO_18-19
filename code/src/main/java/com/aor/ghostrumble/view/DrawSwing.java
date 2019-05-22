package com.aor.ghostrumble.view;

import com.aor.ghostrumble.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

import static com.aor.ghostrumble.view.GameSwing.TILE_SIZE;

public class DrawSwing implements DrawingMethod {

    private JPanel gamePanel;

    private ImageComponent backgroundSprite;
    private ImageComponent wallSprite;
    private ImageComponent playerSprite;
    private ImageComponent bulletRightSprite;
    private ImageComponent bulletUpSprite;
    private ImageComponent bulletDownSprite;
    private ImageComponent bulletLeftSprite;
    private ImageComponent zombieSprite;
    private ImageComponent ghostSprite;
    private ImageComponent poltergeistSprite;

    private boolean firstDraw;

    public DrawSwing(JPanel gamePanel) {

        this.gamePanel = gamePanel;

        try {

            this.playerSprite = loadImage("guy");
            this.bulletUpSprite = loadImage("bulletUp");
            this.bulletLeftSprite = loadImage("bulletLeft");
            this.bulletDownSprite = loadImage("bulletDown");
            this.bulletRightSprite = loadImage("bulletRight");
            this.zombieSprite = loadImage("zombie");
            this.ghostSprite = loadImage("ghost");
            this.backgroundSprite = loadImage("floor");
            this.wallSprite = loadImage("wall");
            this.poltergeistSprite = loadImage("poltergeist");

        } catch(IOException e) {
            e.printStackTrace();
        }

        this.firstDraw = true;

        gamePanel.add(bulletUpSprite);
        gamePanel.add(bulletLeftSprite);
        gamePanel.add(bulletDownSprite);
        gamePanel.add(bulletRightSprite);
        gamePanel.add(playerSprite);
        gamePanel.add(zombieSprite);
        gamePanel.add(ghostSprite);
        gamePanel.add(backgroundSprite);
        gamePanel.add(wallSprite);
        gamePanel.add(poltergeistSprite);
    }


    private ImageComponent loadImage(String imageName) throws IOException {
        return new ImageComponent(ImageIO.read(getClass().getResource("/" + imageName + ".png")));
    }

    private void drawFixed(ImageComponent image, int x, int y) {
        image.setPosition(x, y);
        image.repaint();
    }

    private void drawMovable(ImageComponent image, int x, int y) {
        backgroundSprite.setPosition(image.getX(), image.getY());
        backgroundSprite.repaint();
        image.setPosition(x, y);
        gamePanel.repaint( x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public void drawInit(HauntedHouse house) {
        drawHouse(house.getWidth(), house.getHeight());
        drawWalls(house.getWalls());
        gamePanel.repaint();
        this.firstDraw = false;
    }

    @Override
    public void drawAll(HauntedHouse house) {

        if (this.firstDraw)
            drawInit(house);

        drawEnemies(house.getEnemies());
        drawPlayer(house.getPlayer());

        gamePanel.repaint();

    }


    private void drawHouse(int width, int height) {

        for (int i = 6; i < height - 1; i++) {

            for (int j = 1; j < width - 1; j++) {

                ImageComponent tile = backgroundSprite.clone();
                gamePanel.add(tile);
                drawFixed(tile, j, i);

            }

        }

    }

    private void drawWalls(List<Element> walls) {

        for (Element wall : walls) {

            ImageComponent tile = wallSprite.clone();
            gamePanel.add(tile);
            drawFixed(tile, wall.getPosition().getX(), wall.getPosition().getY());

        }

    }

    private void drawEnemies(List<Enemy> enemies) {

        for(Enemy enemy : enemies) {

            if (enemy instanceof Zombie) {
                drawMovable(zombieSprite, enemy.getPosition().getX(), enemy.getPosition().getY());
            }
            else if (enemy instanceof Ghost) {
                drawMovable(ghostSprite, enemy.getPosition().getX(), enemy.getPosition().getY());
            }
            else if(enemy instanceof Poltergeist) {
                drawMovable(poltergeistSprite, enemy.getPosition().getX(), enemy.getPosition().getY());
            }

        }

    }

    private void drawPlayer(Player player) {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();
        playerSprite.setPosition(x, y);
        gamePanel.repaint((x-1) * TILE_SIZE, (y-1) * TILE_SIZE, 3 * TILE_SIZE, 3 * TILE_SIZE);
    }
}