package com.aor.ghostrumble;

import com.aor.ghostrumble.menu.MenuState;

public class Game implements StateObserver {

    private State state;

    public Game(ViewAbstractFactory factory) {
        changeState(new MenuState(factory));
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
                }
            }
        }.start();

        while (state.keepGoing()) {
            state.draw();
            state.update();
        }

    }

}

