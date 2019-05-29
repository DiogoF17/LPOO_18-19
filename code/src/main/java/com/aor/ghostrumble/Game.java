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

                    // NOTA: este sleep encontra-se aqui inserido devido a uma race condition
                    // nas nosas threads. Depois de falar com o professor Restivo, este afirmou
                    // que este assunto nao e de grande relevancia e que nao nos deviamos
                    // preocupar com isso.
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

