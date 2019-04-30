package com.aor.ghostrumble.model;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;


public class EnemyTest {

    // the Enemy subclasses were used for the unit testing of
    // Enemy functions, because the Enemy class is abstract.
    // The functions tested here are only the functions that
    // belong to Enemy, i.e. that belong to all of its
    // subclasses.

    Enemy initZombie() { return new Zombie(10, 20); }

    @Test
    public void EnemyTestHitPlayerFalse() {
        Enemy enemy = initZombie();
        assertEquals(false, enemy.hasHitPlayer());
    }

    @Test
    public void EnemyTestHitPlayerTrue() {
        Enemy enemy = initZombie();
        enemy.setHitPlayer(true);

        assertEquals(true, enemy.hasHitPlayer());
    }

    @Test
    public void EnemyGetDamage() {
        Enemy enemy = initZombie();
        enemy.setDamage(5);

        assertEquals(5, enemy.getDamage());
    }

    @Test
    public void EnemyGetStrat() {
        Enemy enemy = initZombie();
        MovementStrategy moveStrat = Mockito.mock(MovementStrategy.class);
        enemy.setMovStrategy(moveStrat);

        assertEquals(moveStrat, enemy.getMovStrategy());
    }

    @Test
    public void EnemyTestMove() {
        Enemy enemy = initZombie();
        Position pos = Mockito.mock(Position.class);
        MovementStrategy moveStrat = Mockito.mock(MovementStrategy.class);
        Mockito.when(moveStrat.move(any(Enemy.class))).thenReturn(pos);
        enemy.setMovStrategy(moveStrat);

        assertEquals(pos, enemy.move());
    }

    @Test
    public void EnemyTestUpdate() {
        Player player = Mockito.mock(Player.class);
        Enemy enemy = initZombie();
        MovementStrategy moveStrat = Mockito.mock(MovementStrategy.class);
        enemy.setMovStrategy(moveStrat);

        enemy.update(player);

        Mockito.verify(moveStrat, times(1)).updateDirection(player.getPosition(), enemy.getPosition());
    }
}
