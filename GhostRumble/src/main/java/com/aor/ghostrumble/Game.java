package com.aor.ghostrumble;

import java.io.IOException;

public abstract class Game {

    private HauntedHouse house;
    private DrawingMethod drawingMethod;

    protected void init(int width, int height) {
        this.house = new HauntedHouse(width, height);
        this.drawingMethod = createDrawingMethod();
    }

    protected abstract DrawingMethod createDrawingMethod();


    private void draw() throws IOException {
            this.drawingMethod.drawAll(house);
    }

    protected abstract boolean handleInput() throws IOException;


    public void run() throws IOException {

        boolean loop = true;

        while (loop) {

            /**
             * 1 - INPUT
             * 2 - LOGIC
             * 3 - DRAW
             */

            // loop = handleInput();
            house.clockTick();
            this.draw();

        }

    }

}
