package com.aor.ghostrumble.menu.view;

import com.aor.ghostrumble.menu.event.MenuEvent;
import com.aor.ghostrumble.menu.event.NullEvent;
import com.aor.ghostrumble.menu.model.MenuModel;

public abstract class ViewMenu {

    private final static String TEXT = "Ghost Rumble (GR)";
    private final static String FIRST = "Play";
    private final static String SECOND = "Exit";

    protected MenuEvent event;

    public ViewMenu() {
        event = new NullEvent();
    }

    public static String getText() {
        return TEXT;
    }

    public static String getFirst() {
        return FIRST;
    }

    public static String getSecond() {
        return SECOND;
    }

    public MenuEvent getEvent() {
        return event;
    }

    public void setEvent(MenuEvent event) {
        this.event = event;
    }

    public abstract void prepareStateChange();

    public abstract void handleInput();

    public abstract void drawAll(MenuModel model);


}
