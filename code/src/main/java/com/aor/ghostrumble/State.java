package com.aor.ghostrumble;

import com.aor.ghostrumble.factory.ViewAbstractFactory;

public abstract class State {

    protected ViewAbstractFactory factory;
    protected StateObserver observer;

    public State(ViewAbstractFactory factory) {
        this.factory = factory;
    }

    public boolean keepGoing() {
        return true;
    }

    public StateObserver getObserver() {
        return observer;
    }

    public void setObserver(StateObserver observer) {
        this.observer = observer;
    }

    public ViewAbstractFactory getFactory() {
        return factory;
    }

    public abstract void handleInput();

    public abstract void draw();

    public abstract void update();

}

