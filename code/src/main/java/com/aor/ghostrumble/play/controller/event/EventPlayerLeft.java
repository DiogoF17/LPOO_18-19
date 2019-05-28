package com.aor.ghostrumble.play.controller.event;

import com.aor.ghostrumble.play.controller.Updater;
import com.aor.ghostrumble.play.model.HauntedHouse;
import com.aor.ghostrumble.play.model.Player;

public class EventPlayerLeft implements Event {

    @Override
    public void process(Updater updater, HauntedHouse house) {
        Player player = house.getPlayer();
        if (updater.getPlayerUpdater().movePlayer(player, player.moveLeft(), house))
            updater.checkEnemyCollisions(house);
    }
}
