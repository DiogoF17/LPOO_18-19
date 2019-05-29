package com.aor.ghostrumble.menu.event;
import com.aor.ghostrumble.menu.model.MenuModel;

public interface MenuEvent {

    boolean process(MenuModel model);

}
