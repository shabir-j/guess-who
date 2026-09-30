package guesswho;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class Board extends JFrame {
    private GameManager gameManager;
    private JPanel gridPanel;
    private ArrayList<Character> characters;
    private JCheckBox eliminationMode;
    private boolean hasGuessed = false;
    private boolean hasAskedQuestion = false;
    private ArrayList<Character> eliminated = new ArrayList<>();
    private ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<Question> askedQuestions = new ArrayList<>();

    public Board(GameManager gm) {
        this.gameManager = gm;
        this.characters = gameManager.getAllCharacters();

        setTitle("Guess Who - Game Board");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4, 6, 10, 10));
        add(gridPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        eliminationMode = new JCheckBox("Elimination Mode");
        rightPanel.add(eliminationMode);

        JButton askButton = new JButton("Ask Question");
        askButton.addActionListener(e -> {
            if (!hasAskedQuestion) {
                hasAskedQuestion = true;
                openQuestionGUI();
            } else {
                JOptionPane.showMessageDialog(this, "You can only ask one question per turn.");
            }
        });
        rightPanel.add(askButton);

        bottomPanel.add(rightPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(180, 300));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Your Character", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(titleLabel);

        String charName = gameManager.getPlayer().getMysteryCharacter().getName().replace(" ", "");
        File imgFile = new File(charName + ".png");
        JLabel imageLabel;

        if (imgFile.exists()) {
            ImageIcon icon = new ImageIcon(
                    new ImageIcon(imgFile.getPath()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            imageLabel = new JLabel(icon);
        } else {
            imageLabel = new JLabel("No Image", SwingConstants.CENTER);
        }
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(imageLabel);

        Character mc = gameManager.getPlayer().getMysteryCharacter();
        JTextArea attributes = new JTextArea(
                "Name: " + mc.getName() + "\n" +
                        "Hair Colour: " + mc.getHairColour() + "\n" +
                        "Eye Colour: " + mc.getEyeColour() + "\n" +
                        "Gender: " + mc.getGender() + "\n" +
                        "Facial Hair: " + mc.getFacialHair() + "\n" +
                        "Hair Style: " + mc.getHairStyle() + "\n" +
                        "Clothing: " + mc.getClothing() + "\n" +
                        "Glasses: " + (mc.hasGlasses() ? "Yes" : "No"));
        attributes.setEditable(false);
        attributes.setFont(new Font("Monospaced", Font.PLAIN, 12));
        attributes.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidePanel.add(attributes);

        add(sidePanel, BorderLayout.EAST);

        drawCharacterTiles();
        setVisible(true);
    }

    private void drawCharacterTiles() {
        gridPanel.removeAll();
        buttons.clear();

        for (Character c : characters) {
            JButton tileButton = new JButton();
            tileButton.setLayout(new BorderLayout());

            String name = c.getName().replace(" ", "");
            String path = name + ".png";
            JLabel imageLabel;
            File file = new File(path);
            if (file.exists()) {
                ImageIcon icon = new ImageIcon(
                        new ImageIcon(path).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                imageLabel = new JLabel(icon);
            } else {
                imageLabel = new JLabel("No Image", SwingConstants.CENTER);
            }
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            tileButton.add(imageLabel, BorderLayout.CENTER);

            tileButton.setFocusable(false);

            if (eliminated.contains(c)) {
                tileButton.setBackground(Color.LIGHT_GRAY);
            }

            tileButton.addActionListener(e -> handleTileClick(c, tileButton));
            buttons.add(tileButton);

            gridPanel.add(tileButton);
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void handleTileClick(Character character, JButton button) {
        if (eliminationMode.isSelected()) {
            if (eliminated.contains(character)) {
                eliminated.remove(character);
                button.setBackground(null);
            } else {
                eliminated.add(character);
                button.setBackground(Color.LIGHT_GRAY);
            }
        } else if (!hasGuessed) {
            int choice = JOptionPane.showConfirmDialog(this, "Is this your guess?", "Confirm Guess",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                hasGuessed = true;
                checkGuess(character);
            }
        } else {
            JOptionPane.showMessageDialog(this, "You've already made your guess!");
        }
    }

    private void checkGuess(Character guess) {
        Character correct = gameManager.getAI().getMysteryCharacter();
        if (guess.equals(correct)) {
            JOptionPane.showMessageDialog(this, "Correct! You guessed the AI's character: " + correct.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Wrong! The AI's character was: " + correct.getName());
        }
    }

    private void aiTakeTurnWithUserInput() {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Character> possible = gameManager.getAI().getPossibleCharacters();
            if (possible.size() == 1) {
                Character guess = possible.get(0);
                Character correct = gameManager.getPlayer().getMysteryCharacter();
                if (guess.equals(correct)) {
                    JOptionPane.showMessageDialog(this,
                            "AI guessed your character correctly! It was: " + guess.getName());
                } else {
                    JOptionPane.showMessageDialog(this, "AI guessed wrong! It guessed: " + guess.getName());
                }
                return;
            }

            Question aiQuestion = gameManager.getAI().findBestQuestion();
            if (aiQuestion == null || askedQuestions.contains(aiQuestion))
                return;

            askedQuestions.add(aiQuestion);

            Object[] options = { "Yes", "No" };
            int result;
            do {
                result = JOptionPane.showOptionDialog(this,
                        "AI asks: Does your character have " + aiQuestion.getAttribute() + " = " + aiQuestion.getValue()
                                + "?",
                        "AI Question",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
            } while (result != 0 && result != 1);

            boolean answer = (result == 0);
            gameManager.getAI().updatePossibleCharacters(aiQuestion, answer);
            hasAskedQuestion = false;
        });
    }

    public void updateBoard(ArrayList<Character> updatedCharacters) {
        this.characters = updatedCharacters;
        drawCharacterTiles();
    }

    public void triggerAITurn() {
        aiTakeTurnWithUserInput();
    }

    private void openQuestionGUI() {
        String[] attributeNames = { "Hair Colour", "Eye Colour", "Gender", "Facial Hair", "Hair Style", "Clothing",
                "Glasses" };
        String[][] attributeValues = {
                { "black", "brown", "blonde", "white", "red" },
                { "brown", "blue", "green" },
                { "male", "female" },
                { "none", "mustache", "beard" },
                { "short", "straight", "curly", "wavy", "buzz", "tied", "covered", "bald" },
                { "blue", "black", "green", "yellow", "purple", "white", "grey", "red", "brown" },
                { "true", "false" }
        };
        new UserQuestion(attributeNames, attributeValues, gameManager, this);
    }
}
