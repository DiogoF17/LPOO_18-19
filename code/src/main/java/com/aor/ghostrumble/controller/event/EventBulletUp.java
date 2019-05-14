package com.aor.ghostrumble.controller.event;

import com.aor.ghostrumble.controller.Updater;
import com.aor.ghostrumble.model.HauntedHouse;

public class EventBulletUp implements Event {

    @Override
    public void process(Updater updater, HauntedHouse house) {
        updater.getBulletsUpdater().launchVerticalBullet(house, -1);
    }
}
