package com.aor.ghostrumble.play.view;

import com.aor.ghostrumble.play.model.HauntedHouse;

import java.io.IOException;


// por agora n e necessario; se se fizer fac method com as views, usa se

public interface DrawingMethodGame {

    void drawAll(HauntedHouse house) throws IOException;
}
