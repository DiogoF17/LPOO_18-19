package com.aor.ghostrumble.model;

public abstract class Enemy extends AutoMovable implements PlayerObserver {

    private MovementStrategy movStrategy;
    private int damage;
    private boolean hitPlayer;

    public Enemy(int x, int y, int speed, int damage) {
        super(x, y, speed);
        this.damage = damage;
        this.movStrategy = createMovStrategy();
        this.hitPlayer = false;
    }

    protected abstract MovementStrategy createMovStrategy();

    public Position move() { return movStrategy.move(this); }

    public int getDamage() { return damage; }

    public void setHitPlayer(boolean hitPlayer) {
        this.hitPlayer = hitPlayer;
    }

    public boolean hasHitPlayer() {
        return hitPlayer;
    }

    @Override
    public void update(Player player) { movStrategy.updateDirection(player.getPosition(), position); }
}
