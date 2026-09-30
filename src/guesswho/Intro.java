
package guesswho;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Intro extends JFrame {
    public Intro() {
        setTitle("Guess Who - Intro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Welcome to Guess Who!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameManager gm = new GameManager();
                new Board(gm);
                dispose();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.pink);
        panel.add(title, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
