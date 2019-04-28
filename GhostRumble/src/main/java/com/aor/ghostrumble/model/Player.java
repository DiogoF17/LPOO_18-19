package com.aor.ghostrumble.model;


import java.util.ArrayList;
import java.util.List;

public class Player extends Movable {

    private final static int MAX_HEALTH = 20;

    private int currentHealth;
    private int maxHealth;
    private List<PlayerObserver> observers;

    public Player() { this(10, 10); }

    public Player(int x, int y) {
        super(x, y);
        this.maxHealth = MAX_HEALTH;
        this.currentHealth = maxHealth;
        this.observers = new ArrayList<>();
    }

    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public List<PlayerObserver> getObservers() { return observers; }

    public void damagePlayer(int damage) { currentHealth -= damage; }

    public void addObserver(PlayerObserver observer) { observers.add(observer); }

    public void removeObserver(PlayerObserver observer) { observers.remove(observer); }

    public void notifyObservers() {
        for(PlayerObserver observer : observers)
            observer.update(this);
    }

}
