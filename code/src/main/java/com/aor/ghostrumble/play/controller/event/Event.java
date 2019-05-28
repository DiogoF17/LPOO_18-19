package com.aor.ghostrumble.play.controller.event;

import com.aor.ghostrumble.play.controller.Updater;
import com.aor.ghostrumble.play.model.HauntedHouse;

public interface Event {

    void process(Updater updater, HauntedHouse house);

}
