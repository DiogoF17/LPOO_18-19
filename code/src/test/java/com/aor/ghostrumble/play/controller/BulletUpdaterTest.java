package com.aor.ghostrumble.play.controller;

import com.aor.ghostrumble.play.model.*;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertEquals;

public class BulletUpdaterTest {

    @Test
    public void testLaunchHorizontalBullet() {
        BulletsUpdater updater = new BulletsUpdater();
        HauntedHouse house = new HauntedHouse(50, 30);

        long canShootTime = currentTimeMillis() - 501;

        updater.launchHorizontalBullet(house, 1);


        assertEquals(1, house.getBullets().size());
        assertEquals(house.getPlayer().getPosition().getX() + 1, house.getBullets().get(0).getPosition().getX());
        assertEquals(house.getPlayer().getPosition().getY(), house.getBullets().get(0).getPosition().getY());
        assertEquals(1, house.getBullets().get(0).getDelta());

        house.getPlayer().setLastFired(canShootTime);
        updater.launchHorizontalBullet(house, 1);
        updater.launchHorizontalBullet(house, 1);        // won't fire because of player.setLastFired()
        house.getPlayer().setLastFired(canShootTime);
        updater.launchHorizontalBullet(house, 1);
        house.getPlayer().setLastFired(canShootTime + 1);     // won't fire because > and not >=
        updater.launchHorizontalBullet(house, 1);
        house.getPlayer().setLastFired(-(canShootTime));      // will fire but kill a mutation
        updater.launchHorizontalBullet(house, 1);
        house.getPlayer().setLastFired(canShootTime);
        updater.launchHorizontalBullet(house, 1);

        assertEquals(5, house.getBullets().size());

        house.getPlayer().setLastFired(canShootTime);         // won't fire from here on out because limit of 5
        updater.launchHorizontalBullet(house, 1);
        house.getPlayer().setLastFired(canShootTime);
        updater.launchHorizontalBullet(house, 1);

        assertEquals(5, house.getBullets().size());
    }

    @Test
    public void testLaunchVerticalBullet() {
        BulletsUpdater updater = new BulletsUpdater();
        HauntedHouse house = new HauntedHouse(50, 30);

        long canShootTime = currentTimeMillis() - 501;

        updater.launchVerticalBullet(house, 1);


        assertEquals(1, house.getBullets().size());
        assertEquals(house.getPlayer().getPosition().getX(), house.getBullets().get(0).getPosition().getX());
        assertEquals(house.getPlayer().getPosition().getY() + 1, house.getBullets().get(0).getPosition().getY());
        assertEquals(1, house.getBullets().get(0).getDelta());

        house.getPlayer().setLastFired(canShootTime);
        updater.launchVerticalBullet(house, 1);
        updater.launchVerticalBullet(house, 1);        // won't fire because of player.setLastFired()
        house.getPlayer().setLastFired(canShootTime);
        updater.launchVerticalBullet(house, 1);
        house.getPlayer().setLastFired(canShootTime + 1);   // won't fire because > and not >=
        updater.launchVerticalBullet(house, 1);
        house.getPlayer().setLastFired(-(canShootTime));    // will fire but kill a mutation
        updater.launchVerticalBullet(house, 1);
        house.getPlayer().setLastFired(canShootTime);
        updater.launchVerticalBullet(house, 1);

        assertEquals(5, house.getBullets().size());

        house.getPlayer().setLastFired(canShootTime);         // won't fire from here on out because limit of 5
        updater.launchVerticalBullet(house, 1);
        house.getPlayer().setLastFired(canShootTime);
        updater.launchVerticalBullet(house, 1);

        assertEquals(5, house.getBullets().size());
    }

    @Test
    public void testFireRefreshRate() {
        BulletsUpdater updater = new BulletsUpdater();
        HauntedHouse house = Mockito.spy(new HauntedHouse(50, 30));
        Player player = Mockito.spy(new Player());

        Mockito.when(house.getPlayer()).thenReturn(player);

        long canShootTime = currentTimeMillis() - BulletsUpdater.getFireRefreshRate() - 1;

        Answer<Long> answer = new Answer<Long>() {
            @Override
            public Long answer(InvocationOnMock invocation) throws Throwable {
                return currentTimeMillis() - BulletsUpdater.getFireRefreshRate();
            }
        };

        player.setLastFired(canShootTime);

        updater.launchVerticalBullet(house, 1);
        updater.launchVerticalBullet(house, 1);

        Mockito.doAnswer(answer).when(player).getLastFired();

        updater.launchVerticalBullet(house, 1);

        Mockito.when(player.getLastFired()).thenCallRealMethod();

        player.setLastFired(canShootTime);

        updater.launchHorizontalBullet(house, 1);
        updater.launchHorizontalBullet(house, 1);

        Mockito.doAnswer(answer).when(player).getLastFired();
        updater.launchHorizontalBullet(house, 1);

        assertEquals(2, house.getBullets().size());
    }

    @Test
    public void testBulletMovement() {
        BulletsUpdater updater = new BulletsUpdater();
        Bullet horizontalBulletFront = Mockito.spy(new HorizontalBullet(10, 10, 1));
        Bullet horizontalBulletBack = Mockito.spy(new HorizontalBullet(20, 20, -1));
        Bullet verticalBulletFront = Mockito.spy(new VerticalBullet(10, 10, 1));
        Bullet verticalBulletBack = Mockito.spy(new VerticalBullet(20, 20, -1));

        List<Bullet> bullets = new ArrayList<>();
        bullets.add(horizontalBulletFront);
        bullets.add(horizontalBulletBack);
        bullets.add(verticalBulletFront);
        bullets.add(verticalBulletBack);

        List<Bullet> expected = new ArrayList<>();
        expected.add(new HorizontalBullet(11, 10, 1));
        expected.add(new HorizontalBullet(19, 20, -1));
        expected.add(new VerticalBullet(10, 11, 1));
        expected.add(new VerticalBullet(20, 19, -1));

        HauntedHouse house = Mockito.mock(HauntedHouse.class);

        Mockito.when(house.getBullets()).thenReturn(bullets);

        for (Bullet bullet : bullets) {
            Mockito.doAnswer(invocation -> currentTimeMillis() - bullet.getSpeed()).when(bullet).getLastMoved();
        }

        updater.moveBullets(house);

        for (Bullet bullet : bullets) {
            Mockito.doAnswer(invocation -> bullet.getSpeed() - currentTimeMillis()).when(bullet).getLastMoved();
        }

        updater.moveBullets(house);

        for (int i = 0; i < bullets.size(); i++) {
            assertEquals(expected.get(i).getPosition(), bullets.get(i).getPosition());
        }
    }

    @Test
    public void testBulletSpeed() {
        BulletsUpdater updater = new BulletsUpdater();
        Bullet horizontalBulletFront = Mockito.spy(new HorizontalBullet(10, 10, 1));

        List<Bullet> bullets = new ArrayList<>();
        bullets.add(horizontalBulletFront);

        List<Bullet> expected = new ArrayList<>();
        expected.add(new HorizontalBullet(12, 10, 1));

        HauntedHouse house = Mockito.mock(HauntedHouse.class);
        Mockito.when(house.getBullets()).thenReturn(bullets);

        updater.moveBullets(house);
        updater.moveBullets(house);

        Mockito.doAnswer(
                invocation -> currentTimeMillis() - horizontalBulletFront.getSpeed()
        ).when(horizontalBulletFront).getLastMoved();

        updater.moveBullets(house);

        Mockito.doAnswer(
                invocation -> horizontalBulletFront.getSpeed() - currentTimeMillis()
        ).when(horizontalBulletFront).getLastMoved();

        updater.moveBullets(house);

        assertEquals(bullets.get(0).getPosition(), expected.get(0).getPosition());
    }

}
