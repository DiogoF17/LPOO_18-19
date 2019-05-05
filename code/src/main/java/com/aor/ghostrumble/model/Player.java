package com.aor.ghostrumble.model;


import java.util.ArrayList;
import java.util.List;

public class Player extends Movable {

    private final static int MAX_HEALTH = 20;

    private int currentHealth;
    private int maxHealth;
    private long lastFired;
    private List<PlayerObserver> observers;

    public Player() { this(10, 10); }

    public Player(int x, int y) {
        super(x, y);
        this.maxHealth = MAX_HEALTH;
        this.currentHealth = maxHealth;
        this.lastFired = 0;
        this.observers = new ArrayList<>();
    }

    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public long getLastFired() { return lastFired; }
    public List<PlayerObserver> getObservers() { return observers; }
    public final static int getMaxHealthConstant() { return MAX_HEALTH; }

    public void setLastFired(long lastFired) { this.lastFired = lastFired; }

    public void damagePlayer(int damage) {
        currentHealth -= damage;
        assertCurrentHealth();
    }

    private void assertCurrentHealth() {
        if (currentHealth <= 0)
            currentHealth = 0;
    }

    public void addObserver(PlayerObserver observer) {
        observers.add(observer);
        observer.update(this);
    }

    public void removeObserver(PlayerObserver observer) { observers.remove(observer); }

    public void notifyObservers() {
        for(PlayerObserver observer : observers)
            observer.update(this);
    }

}
