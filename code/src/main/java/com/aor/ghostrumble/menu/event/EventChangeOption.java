package com.aor.ghostrumble.menu.event;

import com.aor.ghostrumble.menu.model.MenuModel;

public class EventChangeOption implements MenuEvent {

    @Override
    public boolean process(MenuModel model) {
        model.changeOption();
        return false;
    }
}
