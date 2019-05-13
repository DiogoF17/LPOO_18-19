package com.aor.ghostrumble.controller.Event;

import com.aor.ghostrumble.controller.Updater;
import com.aor.ghostrumble.model.HauntedHouse;
import com.aor.ghostrumble.model.Player;

public class EventPlayerLeft implements Event {

    @Override
    public void process(Updater updater, HauntedHouse house) {
        Player player = house.getPlayer();

        updater.getPlayerUpdater().movePlayer(player, player.moveLeft(), house);
    }
}
