package guesswho;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserQuestion extends JFrame {
    private JComboBox<String> attributeBox;
    private JComboBox<String> valueBox;
    private String[] attributes;
    private String[][] values;
    private GameManager gameManager;
    private Board board;

    public UserQuestion(String[] attributes, String[][] values, GameManager gm, Board board) {
        this.attributes = attributes;
        this.values = values;
        this.gameManager = gm;
        this.board = board;

        setTitle("Ask a Question");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        panel.add(new JLabel("Select Attribute:"));
        attributeBox = new JComboBox<>(attributes);
        panel.add(attributeBox);

        panel.add(new JLabel("Select Value:"));
        valueBox = new JComboBox<>();
        panel.add(valueBox);

        JButton askButton = new JButton("Ask");
        panel.add(askButton);

        attributeBox.addActionListener(e -> updateValueBox());
        updateValueBox();

        askButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String attribute = attributeBox.getSelectedItem().toString();
                    String value = valueBox.getSelectedItem().toString();
                    Question q = new Question(attribute, value);

                    Character mysteryCharacter = gameManager.getAI().getMysteryCharacter();
                    boolean answer = mysteryCharacter != null && mysteryCharacter.matches(q);

                    JOptionPane.showMessageDialog(null, "Answer: " + (answer ? "Yes" : "No"));
                    dispose();
                    board.triggerAITurn();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred while processing your question.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                    dispose();
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private void updateValueBox() {
        int selectedIndex = attributeBox.getSelectedIndex();
        valueBox.removeAllItems();
        for (String val : values[selectedIndex]) {
            valueBox.addItem(val);
        }
    }
}
