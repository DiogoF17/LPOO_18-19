package com.aor.ghostrumble;

public interface State {

    void handleInput();

    void draw();

    void update();
}
