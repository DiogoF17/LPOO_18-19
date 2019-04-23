package com.aor.ghostrumble;

import static java.lang.System.currentTimeMillis;

public abstract class Enemy extends Movable {

    // tem aqui um atributo de uma classe MovementStrategy, que dizia
    // como e que um determinado inimigo se moveria; ia ser instanciado
    // pelas subclasses, que iriam ter os seus metodos especificos de
    // se movimentarem (diagonal, em frente, etc)
    // --
    // Factory Method e Strategy?

    private MovementStrategy movStrategy;
    private int speed; // numero de frames que e preciso para ele se mover
    private long lastMoved;

    public Enemy(int x, int y, int speed) {
        super(x, y);
        this.movStrategy = createMovStrategy();
        this.speed = speed;
    }

    protected abstract MovementStrategy createMovStrategy();

    protected void move() {
        if (currentTimeMillis() - lastMoved > speed) {
            movStrategy.move(this);
            lastMoved = currentTimeMillis();
        }
    }

}
