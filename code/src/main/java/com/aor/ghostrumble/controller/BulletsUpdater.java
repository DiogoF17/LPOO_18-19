package com.aor.ghostrumble.controller;

import com.aor.ghostrumble.model.*;

import static java.lang.System.currentTimeMillis;

public class BulletsUpdater {

    private final static int FIRE_REFRESH_RATE = 500;

    public final static int getFireRefreshRate() { return FIRE_REFRESH_RATE; }


    public void launchHorizontalBullet(HauntedHouse house, int delta) {

        if(HauntedHouse.getMaxNumberBullets() > house.getBullets().size()) {

            Player player = house.getPlayer();

            if (currentTimeMillis() - player.getLastFired() > FIRE_REFRESH_RATE) {
                house.addBullet(new HorizontalBullet(player.getPosition().getX() + delta, player.getPosition().getY(), delta));
                player.setLastFired(currentTimeMillis());
            }
        }
    }

    public void launchVerticalBullet(HauntedHouse house, int delta) {

        if(HauntedHouse.getMaxNumberBullets() > house.getBullets().size()) {

            Player player = house.getPlayer();

            if (currentTimeMillis() - player.getLastFired() > FIRE_REFRESH_RATE) {
                house.addBullet(new VerticalBullet(player.getPosition().getX() , player.getPosition().getY() + delta, delta));
                player.setLastFired(currentTimeMillis());
            }
        }
    }

    public void moveBullets(HauntedHouse house) {
        for (Bullet bullet : house.getBullets()) {
            if (currentTimeMillis() - bullet.getLastMoved() > bullet.getSpeed()) {
                bullet.setPosition(bullet.move());
                bullet.setLastMoved(currentTimeMillis());
            }
        }
    }
}
