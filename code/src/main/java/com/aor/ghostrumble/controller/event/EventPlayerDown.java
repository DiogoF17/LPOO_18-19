package com.aor.ghostrumble.controller.event;

import com.aor.ghostrumble.controller.Updater;
import com.aor.ghostrumble.model.HauntedHouse;
import com.aor.ghostrumble.model.Player;

public class EventPlayerDown implements Event {

    @Override
    public void process(Updater updater, HauntedHouse house) {
        Player player = house.getPlayer();
        if (updater.getPlayerUpdater().movePlayer(player, player.moveDown(), house))
            updater.checkEnemyCollisions(house);
    }
}
