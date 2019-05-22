package com.aor.ghostrumble.view;

import com.aor.ghostrumble.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private List<ImageComponent> zombieBuffer;
    private List<ImageComponent> ghostBuffer;
    private List<ImageComponent> poltergeistBuffer;
    private List<ImageComponent> bulletUpBuffer;
    private List<ImageComponent> bulletLeftBuffer;
    private List<ImageComponent> bulletDownBuffer;
    private List<ImageComponent> bulletRightBuffer;

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

        gamePanel.add(playerSprite);
        gamePanel.add(backgroundSprite);

        zombieBuffer = new ArrayList<>();
        ghostBuffer = new ArrayList<>();
        poltergeistBuffer = new ArrayList<>();

        for (int i = 0; i < HauntedHouse.getMaxNumberEnemies(); i++) {
            ImageComponent zombie = zombieSprite.clone();
            zombieBuffer.add(zombie);
            gamePanel.add(zombie);

            ImageComponent ghost = ghostSprite.clone();
            ghostBuffer.add(ghost);
            gamePanel.add(ghost);

            ImageComponent poltergeist = poltergeistSprite.clone();
            poltergeistBuffer.add(poltergeist);
            gamePanel.add(poltergeist);
        }

        bulletUpBuffer = new ArrayList<>();
        bulletLeftBuffer = new ArrayList<>();
        bulletDownBuffer = new ArrayList<>();
        bulletRightBuffer = new ArrayList<>();

        for (int i = 0; i < HauntedHouse.getMaxNumberBullets(); i++) {

            ImageComponent bulletUp = bulletUpSprite.clone();
            bulletUpBuffer.add(bulletUp);
            gamePanel.add(bulletUp);

            ImageComponent bulletLeft = bulletLeftSprite.clone();
            bulletLeftBuffer.add(bulletLeft);
            gamePanel.add(bulletLeft);

            ImageComponent bulletDown = bulletDownSprite.clone();
            bulletDownBuffer.add(bulletDown);
            gamePanel.add(bulletDown);

            ImageComponent bulletRight = bulletRightSprite.clone();
            bulletRightBuffer.add(bulletRight);
            gamePanel.add(bulletRight);

        }

    }


    private ImageComponent loadImage(String imageName) throws IOException {
        return new ImageComponent(ImageIO.read(getClass().getResource("/" + imageName + ".png")));
    }

    private void drawFixed(ImageComponent image, int x, int y) {
        image.setPosition(x, y);
    }

    private void eraseMovable(ImageComponent image) {
        image.setPosition(-1, -1);
    }

    private void drawMovable(ImageComponent image, int x, int y) {
        image.setPosition(x, y);
    }

    private void drawInit(HauntedHouse house) {
        drawHouse(house.getWidth(), house.getHeight());
        drawWalls(house.getWalls());
        this.firstDraw = false;
    }

    @Override
    public void drawAll(HauntedHouse house) {

        if (this.firstDraw)
            drawInit(house);

        drawEnemies(house.getEnemies());
        drawBullets(house.getBullets());
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

        int zombieCount = 0;
        int ghostCount = 0;
        int poltergeistCount = 0;


        for(Enemy enemy : enemies) {

            if (enemy instanceof Zombie) {
                drawMovable(zombieBuffer.get(zombieCount++), enemy.getPosition().getX(), enemy.getPosition().getY());
            }
            else if (enemy instanceof Ghost) {
                drawMovable(ghostBuffer.get(ghostCount++), enemy.getPosition().getX(), enemy.getPosition().getY());
            }
            else if(enemy instanceof Poltergeist) {
                drawMovable(poltergeistBuffer.get(poltergeistCount++), enemy.getPosition().getX(), enemy.getPosition().getY());
            }

        }


        for (int i = zombieCount; i < HauntedHouse.getMaxNumberEnemies(); i++) {
            eraseMovable(zombieBuffer.get(i));
        }

        for (int i = ghostCount; i < HauntedHouse.getMaxNumberEnemies(); i++) {
            eraseMovable(ghostBuffer.get(i));
        }

        for (int i = poltergeistCount; i < HauntedHouse.getMaxNumberEnemies(); i++) {
            eraseMovable(poltergeistBuffer.get(i));
        }

    }

    private void drawBullets(List<Bullet> bullets) {
        // igual ao drawEnemies mas com bullets
    }

    private void drawPlayer(Player player) {
        drawMovable(playerSprite, player.getPosition().getX(), player.getPosition().getY());
    }
}