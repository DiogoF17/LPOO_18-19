package com.aor.ghostrumble.model;

import static java.lang.System.currentTimeMillis;

public abstract class Enemy extends AutoMovable {

    // tem aqui um atributo de uma classe MovementStrategy, que dizia
    // como e que um determinado inimigo se moveria; ia ser instanciado
    // pelas subclasses, que iriam ter os seus metodos especificos de
    // se movimentarem (diagonal, em frente, etc)
    // --
    // Factory Method e Strategy?

    private MovementStrategy movStrategy;
    private int damage;

    public Enemy(int x, int y, int speed, int damage) {
        super(x, y, speed);
        this.damage = damage;
        this.movStrategy = createMovStrategy();
    }

    protected abstract MovementStrategy createMovStrategy();

    public Position move() {
        return movStrategy.move(this);

        /*
        if (currentTimeMillis() - lastMoved > speed) {
            lastMoved = currentTimeMillis();
            return movStrategy.move(this);
        }
        */
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
