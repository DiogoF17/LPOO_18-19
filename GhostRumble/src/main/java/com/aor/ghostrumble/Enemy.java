package com.aor.ghostrumble;

public abstract class Enemy extends Element {

    // tem aqui um atributo de uma classe Movement, que dizia
    // como e que um determinado inimigo se moveria; ia ser instanciado
    // pelas subclasses, que iriam ter os seus metodos especificos de
    // se movimentarem (diagonal, em frente, etc)
    // --
    // Factory Method e Strategy?

    private Movement movStrategy;
    private int speed; // numero de frames que e preciso para ele se mover

    public Enemy(int x, int y, int speed) {
        super(x, y);
        this.movStrategy = createMovStrategy();
        this.speed = speed;
    }

    protected abstract Movement createMovStrategy();
}
