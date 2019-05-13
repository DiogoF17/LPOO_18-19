package com.aor.ghostrumble.controller.Event;

import com.aor.ghostrumble.controller.Updater;
import com.aor.ghostrumble.model.HauntedHouse;

public interface EventType {

    void process(Updater updater, HauntedHouse house);
}
