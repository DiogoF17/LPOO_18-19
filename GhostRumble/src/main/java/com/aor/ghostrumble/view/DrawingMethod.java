package com.aor.ghostrumble.view;

import com.aor.ghostrumble.model.HauntedHouse;

import java.io.IOException;

public interface DrawingMethod {

    void drawAll(HauntedHouse house) throws IOException;
}
