package com.aor.ghostrumble;

import com.aor.ghostrumble.controller.event.Event;
import com.aor.ghostrumble.controller.event.EventQueue;
import com.aor.ghostrumble.controller.Updater;
import com.aor.ghostrumble.view.DrawingMethod;
import com.aor.ghostrumble.model.HauntedHouse;

import java.io.IOException;

public abstract class Game {

    protected HauntedHouse house;
    private DrawingMethod drawingMethod;
    private Updater updater;
    private boolean loop;

    private EventQueue eventQueue;

    protected void init(int width, int height) {
        this.house = new HauntedHouse(width, height - 5);
        this.drawingMethod = createDrawingMethod();
        this.updater = new Updater();
        this.loop = true;
    }

    public EventQueue getEventQueue() {
        return eventQueue;
    }

    public void setEventQueue(EventQueue queue) {
        this.eventQueue = queue;
    }

    protected abstract DrawingMethod createDrawingMethod();

    protected abstract boolean handleInput(EventQueue eventQueue) throws IOException;

    public void run() throws IOException {

        /**
         * 1 - INPUT
         * 2 - DRAW
         * 3 - LOGIC
         */

        eventQueue = new EventQueue();

        new Thread() {

            @Override
            public void run() {
                while (loop) {
                    try{
                        loop = handleInput(eventQueue);
                    }catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        while (loop) {

            drawingMethod.drawAll(house);
            updater.update(eventQueue, house);

        }
    }

}

