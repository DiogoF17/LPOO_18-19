package com.aor.ghostrumble.controller.Event;

import com.aor.ghostrumble.controller.Updater;
import com.aor.ghostrumble.model.HauntedHouse;

public class EventBulletLeft implements EventType {

    @Override
    public void process(Updater updater, HauntedHouse house) {
        updater.getBulletsUpdater().launchHorizontalBullet(house, -1);
    }
}
