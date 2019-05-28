package com.aor.ghostrumble.play.controller.event;

import com.aor.ghostrumble.play.controller.Updater;
import com.aor.ghostrumble.play.model.HauntedHouse;

public class EventBulletRight implements Event {

    @Override
    public void process(Updater updater, HauntedHouse house) {
        updater.getBulletsUpdater().launchHorizontalBullet(house, 1);
    }
}
