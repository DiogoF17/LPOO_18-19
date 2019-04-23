package com.aor.ghostrumble;

import java.io.IOException;

public abstract class Game {

    protected HauntedHouse house;
    private DrawingMethod drawingMethod;

    protected void init(int width, int height) {
        this.house = new HauntedHouse(width, height - 5);
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

            loop = handleInput();
            house.clockTick();
            this.draw();
        }

    }

}
