package guesswho;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private ArrayList<Character> allCharacters;
    private Player player;
    private AI ai;

    public GameManager() {
        this.allCharacters = new ArrayList<>();
        loadCharacters();
        this.player = new Player(allCharacters);
        this.ai = new AI(allCharacters);
        player.chooseMysteryCharacter();
    }

    public void loadCharacters() {
        allCharacters.clear();
        allCharacters.add(new Character("Basil", "black", "brown", "male", "none", "short", "blue", false));
        allCharacters.add(new Character("Melvin", "brown", "blue", "male", "none", "short", "black", true));
        allCharacters.add(new Character("Hannah", "brown", "brown", "female", "none", "straight", "green", false));
        allCharacters.add(new Character("Simone", "brown", "green", "female", "none", "straight", "yellow", false));
        allCharacters.add(new Character("Ian", "white", "brown", "male", "mustache", "short", "purple", true));
        allCharacters.add(new Character("Isla", "red", "green", "female", "none", "wavy", "purple", false));
        allCharacters.add(new Character("Rupert", "brown", "green", "male", "mustache", "bald", "white", true));
        allCharacters.add(new Character("Maggie", "brown", "brown", "female", "none", "wavy", "blue", false));
        allCharacters.add(new Character("Susan", "brown", "brown", "female", "none", "curly", "purple", false));
        allCharacters.add(new Character("Natalie", "blonde", "green", "female", "none", "straight", "green", false));
        allCharacters.add(new Character("Kim", "brown", "brown", "female", "none", "tied", "grey", true));
        allCharacters.add(new Character("Stephen", "black", "brown", "male", "none", "buzz", "yellow", false));
        allCharacters.add(new Character("Joshua", "brown", "brown", "male", "none", "short", "green", false));
        allCharacters.add(new Character("Xiao Mei", "black", "brown", "female", "none", "straight", "green", false));
        allCharacters.add(new Character("Jennifer", "red", "brown", "female", "none", "tied", "brown", false));
        allCharacters.add(new Character("Brian", "brown", "blue", "male", "beard", "buzz", "white", true));
        allCharacters.add(new Character("Gary", "brown", "green", "male", "none", "short", "green", false));
        allCharacters.add(new Character("Martine", "brown", "brown", "female", "none", "straight", "green", false));
        allCharacters.add(new Character("Bill", "brown", "brown", "male", "beard", "short", "blue", false));
        allCharacters.add(new Character("Roy", "brown", "brown", "male", "none", "short", "purple", true));
        allCharacters.add(new Character("Edna", "white", "blue", "female", "none", "tied", "green", true));
        allCharacters.add(new Character("Mo", "black", "brown", "male", "beard", "covered", "white", false));
        allCharacters.add(new Character("Kelly", "blonde", "green", "female", "none", "tied", "red", false));
        allCharacters.add(new Character("Pete", "brown", "brown", "male", "beard", "curly", "green", false));
    }

    public Player getPlayer() {
        return player;
    }

    public AI getAI() {
        return ai;
    }

    public ArrayList<Character> getAllCharacters() {
        return allCharacters;
    }
}
