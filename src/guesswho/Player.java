package guesswho;

import java.util.ArrayList;

public class Player {
    private Character mysteryCharacter;
    private ArrayList<Character> possibleCharacters;

    public Player(ArrayList<Character> allCharacters) {
        // Copy all characters into possibleCharacters
        possibleCharacters = new ArrayList<Character>();
        for (int i = 0; i < allCharacters.size(); i++) {
            possibleCharacters.add(allCharacters.get(i));
        }
    }

    public void chooseMysteryCharacter() {
        int index = (int) (Math.random() * possibleCharacters.size());
        this.mysteryCharacter = possibleCharacters.get(index);
    }

    public Character getMysteryCharacter() {
        return mysteryCharacter;
    }

    public void updatePossibleCharacters(Question question, boolean answer) {
        for (int i = possibleCharacters.size() - 1; i >= 0; i--) {
            Character c = possibleCharacters.get(i);
            if (question.matches(c) != answer) {
                possibleCharacters.remove(i);
            }
        }
    }

    public int getRemainingCount() {
        return possibleCharacters.size();
    }

    public Character getBestGuess() {
        if (possibleCharacters.size() == 1) {
            return possibleCharacters.get(0);
        }
        return null;
    }

    // Used by opponent (AI or another player) to ask a question
    public boolean answerQuestion(Question question) {
        return question.matches(mysteryCharacter);
    }

    public ArrayList<Character> getPossibleCharacters() {
        return possibleCharacters;
    }
}
