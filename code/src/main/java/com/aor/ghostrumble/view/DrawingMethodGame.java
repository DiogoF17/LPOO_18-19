package com.aor.ghostrumble.view;

import com.aor.ghostrumble.model.HauntedHouse;

import java.io.IOException;


// por agora n e necessario; se se fizer fac method com as views, usa se

public interface DrawingMethodGame {

    void drawAll(HauntedHouse house) throws IOException;
}
