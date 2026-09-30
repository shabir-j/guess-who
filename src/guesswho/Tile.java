package guesswho;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Tile {
    JButton button;
    JPanel cover;

    public Tile(int x, int y, int w, int h, ActionListener listener) {
        button = new JButton();
        button.setBounds(x, y, w, h);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.addActionListener(listener);

        cover = new JPanel();
        cover.setBounds(x, y, w, h);
        cover.setBackground(new Color(0, 0, 0, 195));
        cover.setOpaque(false);
    }

    public void toggleCover() {
        cover.setOpaque(!cover.isOpaque());
        cover.repaint();
    }

    public JButton getButton() {
        return button;
    }

    public JPanel getCover() {
        return cover;
    }
}
