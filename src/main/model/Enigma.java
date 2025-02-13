package model;

import java.util.*;

public class Enigma {

    private ArrayList<Rotar> rotars;

    private String[] reflector = "SWJEDVZYQCXPOTMLIUANRFBKHGswjedvz qcxpotmliuanrfbkygh9876543210,.?!".split("");
    static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 0123456789.,!?";

    // Effect: Creates a machine with no rotars
    public Enigma() {
        rotars = new ArrayList<>();
    }

    public void addSetting1() {
        rotars.add(new Rotar(1));
    }

    public void addSetting2() {
        rotars.add(new Rotar(2));
    }

    public void addSetting3() {
        rotars.add(new Rotar(3));
    }

    public void addSetting4() {
        rotars.add(new Rotar(4));
    }

    public void addSetting5() {
        rotars.add(new Rotar(5));
    }

    // Effect: Removes the rotar at the given position
    // Modifies: this
    // Requires: i is a valid position
    public void remove(int i) {
        rotars.remove(rotars.get(i - 1));
    }

    // Requires: Characters in the input string are in the alphabet
    // Effect: Encrypts/Decrypts the input string
    // Modifies: this
    // Modifies: rotars
    public String cipher(String in) {

        // reset all rotars
        for (Rotar r : rotars) {
            Rotar.reset(r);
        }

        String[] input = in.split("");

        if (invalid(input)) {
            return "**INVALID CHARACTER**";
        }

        int pos = 0;

        for (String c : input) {
            int character = defaultPosition(c);

            // forward direction
            for (Rotar r : rotars) {
                input[pos] = r.getLetter(character);
                character = defaultPosition(input[pos]);
            }

            // reflector
            input[pos] = reflector[character];
            character = defaultPosition(input[pos]);

            // backward direction
            for (int i = rotars.size() - 1; i >= 0; i--) {
                input[pos] = getDefaultLetter(getLetterPosition(input[pos], rotars.get(i)));
                character = defaultPosition(input[pos]);

            }

            // rotates
            update(0);

            pos++;

        }

        return toString(input);
    }

    // Effect: Rotates nth rotar a whole loop before rotating n+1th rotar
    // Modifies rotars
    public void update(int n) {
        
        getRotars().get(n).rotate();

        if (getRotars().get(n).getRotatePosition() == 0) {
            if (n < getRotars().size() - 1) {
                update(n + 1);
            }
        }
    }

    // Effect: Returns the position of the given letter in the given rotar
    public int getLetterPosition(String letter, Rotar rotar) {
        for (int i = 0; i < Rotar.NUMOFCHARS; i++) {
            if (rotar.getLetter(i).equals(letter)) {
                return i;
            }
        }
        return -1;
    }



    // Effect: Returns the default position of the given letter
    public int defaultPosition(String letter) {
        return ALPHABET.indexOf(letter);
    }

    // Effect: Returns the default letter at the given position
    public String getDefaultLetter(int i) {
        return ALPHABET.substring(i, i + 1);
    }

    // Effect: Array -> String
    public String toString(String[] arr) {
        String output = "";
        for (String s : arr) {
            output += s;
        }
        return output;
    }

    public ArrayList<Rotar> getRotars() {
        return rotars;
    }

    public boolean invalid(String[] input) {
        for (String s : input) {
            if (!ALPHABET.contains(s)) {
                return true;
            }
        } 
        return false;
    }
}
