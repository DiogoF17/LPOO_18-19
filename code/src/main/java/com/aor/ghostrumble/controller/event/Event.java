package com.aor.ghostrumble.controller.event;

import com.aor.ghostrumble.controller.Updater;
import com.aor.ghostrumble.model.HauntedHouse;

public interface Event {

    void process(Updater updater, HauntedHouse house);

}
