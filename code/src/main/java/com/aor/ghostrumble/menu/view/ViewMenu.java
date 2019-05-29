package com.aor.ghostrumble.menu.view;

import com.aor.ghostrumble.menu.event.MenuEvent;
import com.aor.ghostrumble.menu.event.NullEvent;
import com.aor.ghostrumble.menu.model.MenuModel;

public abstract class ViewMenu {

    protected MenuEvent event;

    public ViewMenu() {
        event = new NullEvent();
    }

    public MenuEvent getEvent() {
        return event;
    }

    public void setEvent(MenuEvent event) {
        this.event = event;
    }

    public abstract void handleInput();

    public abstract void drawAll(MenuModel model);


}
