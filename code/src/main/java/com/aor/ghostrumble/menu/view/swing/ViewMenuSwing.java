package com.aor.ghostrumble.menu.view.swing;

import com.aor.ghostrumble.menu.event.EventChangeOption;
import com.aor.ghostrumble.menu.event.EventConfirmOption;
import com.aor.ghostrumble.menu.model.MenuModel;
import com.aor.ghostrumble.menu.view.ViewMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class ViewMenuSwing extends ViewMenu implements KeyListener {

    private JFrame frame;
    private MenuComponent component;

    public ViewMenuSwing(JFrame frame, int width, int height, int tileSize, int borderOffset) {

        this.frame = frame;
        int compWidth = width * tileSize + borderOffset;
        int compHeight = height * tileSize;
        component = new MenuComponent(compWidth, compHeight);
        component.setPreferredSize(new Dimension(compWidth, compHeight));
        component.setBackground(Color.DARK_GRAY);

        this.frame.add(component);
        this.frame.addKeyListener(this);

        this.frame.revalidate();

    }

    public void prepareStateChange() {
        this.frame.remove(component);
    }

    @Override
    public void handleInput() {}

    @Override
    public void drawAll(MenuModel model) {

        component.setModel(model);
        component.repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {

            case VK_UP: case VK_DOWN:
                event = new EventChangeOption();
                break;

            case VK_ENTER:
                event = new EventConfirmOption();
                break;

            default:
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

}
