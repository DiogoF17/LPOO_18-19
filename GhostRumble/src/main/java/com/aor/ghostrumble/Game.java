package com.aor.ghostrumble;

import java.io.IOException;

public abstract class Game {

    protected HauntedHouse house;
    private DrawingMethod drawingMethod;
    private Updater updater;
    private boolean loop;

    protected void init(int width, int height) {
        this.house = new HauntedHouse(width, height - 5);
        this.drawingMethod = createDrawingMethod();
        this.updater = new Updater();
        this.loop = true;
    }

    protected abstract DrawingMethod createDrawingMethod();

    protected abstract boolean handleInput(Event event) throws IOException;

    public void run() throws IOException {

        /**
         * 1 - INPUT
         * 2 - LOGIC
         * 3 - DRAW
         */

        Event event = new Event(Event.TYPE.NO_EVENT);

        while (loop) {
            loop = handleInput(event);
            updater.update(event, house);
            drawingMethod.drawAll(house);
        }
    }

}

