package com.aor.ghostrumble.play.controller.event;

import com.aor.ghostrumble.play.controller.Updater;
import com.aor.ghostrumble.play.model.HauntedHouse;

public class EventBulletDown implements Event {

    @Override
    public void process(Updater updater, HauntedHouse house) {
        updater.getBulletsUpdater().launchVerticalBullet(house, 1);
    }
}
