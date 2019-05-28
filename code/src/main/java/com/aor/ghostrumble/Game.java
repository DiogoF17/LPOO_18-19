package com.aor.ghostrumble;

import com.aor.ghostrumble.play.controller.Updater;
import com.aor.ghostrumble.play.model.HauntedHouse;
import com.aor.ghostrumble.play.view.ViewGame;

public class Game {

    protected HauntedHouse house;
    private ViewGame gameView;
    private Updater updater;
    private boolean loop;

    public Game(int width, int height, ViewGame gameView) {
        this.house = new HauntedHouse(width, height - 5);
        this.gameView = gameView;
        this.updater = new Updater();
        this.loop = true;
    }


    public void run() {

        /**
         * 1 - INPUT
         * 2 - DRAW
         * 3 - LOGIC
         */


        new Thread() {

            @Override
            public void run() {
                while (loop) {
                    loop = gameView.handleInput();
                }
            }
        }.start();

        while (loop) {
            gameView.drawAll(house);
            updater.update(gameView.getQueue(), house);
        }
    }

}

