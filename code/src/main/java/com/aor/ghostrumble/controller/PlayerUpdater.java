package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.Enemy;
import com.aor.ghostrumble.model.HauntedHouse;
import com.aor.ghostrumble.model.Player;
import com.aor.ghostrumble.model.Position;

public class PlayerUpdater {

    public boolean movePlayer(Player player, Position position, HauntedHouse house) {
        if (!house.hitsWall(position)) {
            player.setPosition(position);
            player.notifyObservers();
            return true;
        }
        return false;
    }


    public void damagePlayer(HauntedHouse house) {
        for (Enemy enemy : house.getEnemies()) {
            if (enemy.hasHitPlayer()) {
                house.getPlayer().damagePlayer(enemy.getDamage());
                enemy.setHitPlayer(false);
            }
        }
    }
}
