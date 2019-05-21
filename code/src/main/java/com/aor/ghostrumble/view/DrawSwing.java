package com.aor.ghostrumble.view;

import com.aor.ghostrumble.model.HauntedHouse;

import javax.swing.*;
import java.io.IOException;

public class DrawSwing implements DrawingMethod {

    JFrame frame;

    public DrawSwing(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void drawAll(HauntedHouse house) throws IOException {

    }
}
