package com.aor.ghostrumble;

import com.aor.ghostrumble.factory.ViewAbstractFactory;
import com.aor.ghostrumble.menu.MainMenuState;

public class Game implements StateObserver {

    private State state;

    public Game(ViewAbstractFactory factory) {
        changeState(new MainMenuState(factory));
    }

    public void changeState(State state) {
        this.state = state;
        this.state.setObserver(this);
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
                while (state.keepGoing()) {
                    state.handleInput();

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        }.start();

        while (state.keepGoing()) {
            state.draw();
            state.update();
        }

    }

}

