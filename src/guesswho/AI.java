package guesswho;

import java.util.ArrayList;
import java.util.Random;

public class AI {
    private ArrayList<Character> possibleCharacters;
    private Character mysteryCharacter;
    private ArrayList<String> askedQuestions;

    public AI(ArrayList<Character> allCharacters) {
        possibleCharacters = new ArrayList<>(allCharacters);
        Random rand = new Random();
        mysteryCharacter = allCharacters.get(rand.nextInt(allCharacters.size()));
        askedQuestions = new ArrayList<>();
    }

    public Character getMysteryCharacter() {
        return mysteryCharacter;
    }

    public void updatePossibleCharacters(Question q, boolean answer) {
        ArrayList<Character> updatedList = new ArrayList<>();

        for (Character c : possibleCharacters) {
            boolean matches = false;
            String attr = q.getAttribute().toLowerCase();
            String val = q.getValue().toString().toLowerCase();

            if (attr.equals("name")) {
                matches = c.getName().equalsIgnoreCase(val);
            } else if (attr.equals("hair colour")) {
                matches = c.getHairColour().equalsIgnoreCase(val);
            } else if (attr.equals("eye colour")) {
                matches = c.getEyeColour().equalsIgnoreCase(val);
            } else if (attr.equals("gender")) {
                matches = c.getGender().equalsIgnoreCase(val);
            } else if (attr.equals("facial hair")) {
                matches = c.getFacialHair().equalsIgnoreCase(val);
            } else if (attr.equals("hair style")) {
                matches = c.getHairStyle().equalsIgnoreCase(val);
            } else if (attr.equals("clothing")) {
                matches = c.getClothing().equalsIgnoreCase(val);
            } else if (attr.equals("glasses")) {
                matches = Boolean.toString(c.hasGlasses()).equalsIgnoreCase(val);
            }

            if ((answer && matches) || (!answer && !matches)) {
                updatedList.add(c);
            }
        }

        possibleCharacters = updatedList;
    }

    public Question findBestQuestion() {
        if (possibleCharacters.isEmpty())
            return null;

        String[] attributes = { "Hair Colour", "Eye Colour", "Gender", "Facial Hair", "Hair Style", "Clothing",
                "Glasses" };
        ArrayList<Question> potentialQuestions = new ArrayList<>();

        for (String attr : attributes) {
            ArrayList<String> used = new ArrayList<>();

            for (Character c : possibleCharacters) {
                String value = "";
                if (attr.equals("Hair Colour")) {
                    value = c.getHairColour();
                } else if (attr.equals("Eye Colour")) {
                    value = c.getEyeColour();
                } else if (attr.equals("Gender")) {
                    value = c.getGender();
                } else if (attr.equals("Facial Hair")) {
                    value = c.getFacialHair();
                } else if (attr.equals("Hair Style")) {
                    value = c.getHairStyle();
                } else if (attr.equals("Clothing")) {
                    value = c.getClothing();
                } else if (attr.equals("Glasses")) {
                    value = Boolean.toString(c.hasGlasses());
                }

                String questionKey = attr.toLowerCase() + ":" + value.toLowerCase();
                if (!used.contains(value) && !askedQuestions.contains(questionKey)) {
                    potentialQuestions.add(new Question(attr, value));
                    used.add(value);
                }
            }
        }

        if (potentialQuestions.isEmpty())
            return null;

        Random rand = new Random();
        Question selected = potentialQuestions.get(rand.nextInt(potentialQuestions.size()));
        String selectedKey = selected.getAttribute().toLowerCase() + ":" + selected.getValue().toString().toLowerCase();
        askedQuestions.add(selectedKey);
        return selected;
    }

    public ArrayList<Character> getPossibleCharacters() {
        return possibleCharacters;
    }
}
