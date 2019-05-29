package com.aor.ghostrumble.menu.view.swing;

import com.aor.ghostrumble.menu.model.MenuModel;
import com.aor.ghostrumble.menu.view.ViewMenu;

import javax.swing.*;
import java.awt.*;

public class MenuComponent extends JPanel {

    private MenuModel model;
    private int width;
    private int height;

    public MenuComponent(int width, int height) {

        this.width = width;
        this.height = height;

    }

    protected void setModel(MenuModel model) {
        this.model = model;
    }

    private void drawMenu(Graphics g) {

        g.setColor(Color.ORANGE);

        g.setFont(new Font("Consolas", Font.BOLD, 40));

        g.drawString(ViewMenu.getText(), width / 3, height / 3);

        g.setFont(new Font("Consolas", Font.BOLD, 25));

        if (model.willPlay()) {
            g.setColor(Color.CYAN);
            g.drawRect(width / 3 - 12, (int) (1.5 * height) / 3 - 33, 80, 50);
        }
        g.drawString(ViewMenu.getFirst(), width / 3, (int) (1.5 * height) / 3);

        if (model.willPlay()) {
            g.setColor(Color.ORANGE);
        }
        else {
            g.setColor(Color.CYAN);
            g.drawRect(width / 3 - 12, 2 * height / 3 - 33, 80, 50);
        }
        g.drawString(ViewMenu.getSecond(), width / 3, 2 * height / 3);

    }

    @Override
    public Dimension getPreferredSize() { return new Dimension(width, height); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMenu(g);
    }
}
