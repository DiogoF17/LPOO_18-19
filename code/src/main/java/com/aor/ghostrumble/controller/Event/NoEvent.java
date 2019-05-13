package com.aor.ghostrumble.controller.Event;

import com.aor.ghostrumble.controller.Updater;
import com.aor.ghostrumble.model.HauntedHouse;

// the purpose of this class is to not repeat events after they were processed once.

public class NoEvent implements EventType {

    @Override
    public void process(Updater updater, HauntedHouse house) {

    }
}
