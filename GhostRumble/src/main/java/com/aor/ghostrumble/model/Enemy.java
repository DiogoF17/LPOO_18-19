package com.aor.ghostrumble.model;

public abstract class Enemy extends AutoMovable implements PlayerObserver {

    private MovementStrategy movStrategy;
    private int damage;

    public Enemy(int x, int y, int speed, int damage) {
        super(x, y, speed);
        this.damage = damage;
        this.movStrategy = createMovStrategy();
    }

    protected abstract MovementStrategy createMovStrategy();

    public Position move() { return movStrategy.move(this); }

    public int getDamage() { return damage; }

    public void setDamage(int damage) { this.damage = damage; }

    @Override
    public void updateDirection(Position position) { movStrategy.updateDirection(position, this.position); }
}
