package com.aor.ghostrumble.play.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;


public class EnemyTest {

    private Enemy enemy;

    // the Enemy subclasses were used for the unit testing of
    // Enemy functions, because the Enemy class is abstract.
    // The functions tested here are only the functions that
    // belong to Enemy, i.e. that belong to all of its
    // subclasses.

    @Before
    public void initZombie() { enemy = new Zombie(10, 20); }

    @Test
    public void testHitPlayerFalse() {
        assertFalse(enemy.hasHitPlayer());
    }

    @Test
    public void testHitPlayerTrue() {
        enemy.setHitPlayer(true);
        assertTrue(enemy.hasHitPlayer());
    }

    @Test
    public void testGetDamage() {
        assertEquals(Zombie.getZombieDamage(), enemy.getDamage());
    }

    @Test
    public void testGetStrat() {
        MovementStrategy moveStrat = Mockito.mock(MovementStrategy.class);
        enemy.setMovStrategy(moveStrat);

        assertEquals(moveStrat, enemy.getMovStrategy());
    }

    @Test
    public void testMove() {
        Position pos = Mockito.mock(Position.class);
        MovementStrategy moveStrat = Mockito.mock(MovementStrategy.class);
        Mockito.when(moveStrat.move(enemy)).thenReturn(pos);
        enemy.setMovStrategy(moveStrat);

        assertEquals(pos, enemy.move());
    }

    @Test
    public void testUpdate() {
        Player player = Mockito.mock(Player.class);
        MovementStrategy moveStrat = Mockito.mock(MovementStrategy.class);
        enemy.setMovStrategy(moveStrat);

        enemy.update(player);

        Mockito.verify(moveStrat, times(1)).updateDirection(player.getPosition(), enemy.getPosition());
    }
}
