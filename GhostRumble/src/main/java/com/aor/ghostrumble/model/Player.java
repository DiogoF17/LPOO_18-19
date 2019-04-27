package com.aor.ghostrumble.model;


public class Player extends Movable {

    private final static int MAX_HEALTH = 20;

    private int currentHealth;
    private int maxHealth;

    public Player() { this(10, 10); }

    public Player(int x, int y) {
        super(x, y);
        maxHealth = MAX_HEALTH;
        currentHealth = maxHealth;
    }

    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }

    public void damagePlayer(int damage) { currentHealth -= damage; }

}
