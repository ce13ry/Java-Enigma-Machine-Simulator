package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

// represents an enigma machine with a collection of rotors
public class Enigma {

    private ArrayList<rotor> rotors;

    private String[] reflector = "SWJEDVZYQCXPOTMLIUANRFBKHGswjedvz qcxpotmliuanrfbkygh9876543210,.?!)(*#".split("");
    static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 0123456789.,!?()#*";

    // Effect: Creates a machine with no rotors
    public Enigma() {
        rotors = new ArrayList<>();
    }

    public void addSetting(int i, int initialPosition) {
        EventLog.getInstance().logEvent(new Event("Added rotor: " + i + " with initial position: " + initialPosition));
        rotors.add(new rotor(i, initialPosition));
    }

    // Effect: Removes the rotor at the given position
    // Modifies: this
    // Requires: i is a valid position
    public void remove(int i) {
        EventLog.getInstance().logEvent(new Event("Removed rotor: " + i));
        rotors.remove(rotors.get(i - 1));
    }

    // Effect: Encrypts/Decrypts the input string
    // Modifies: this
    // Modifies: rotors
    // Requires: Characters in the input string are in the alphabet
    @SuppressWarnings("methodlength") // 26 lines instead of 25
    public String cipher(String in) {

        // reset all rotors
        for (rotor r : rotors) {
            rotor.reset(r);
        }
        
        String[] input = in.split("");

        if (invalid(input)) {
            return "**INVALID CHARACTER**";
        }

        int pos = 0;

        for (String c : input) {

            int character = defaultPosition(c);

            // forward direction
            for (rotor r : rotors) {
                input[pos] = r.getLetter(character);
                character = defaultPosition(input[pos]);
            }

            // reflector
            input[pos] = reflector[character];
            character = defaultPosition(input[pos]);

            // backward direction
            for (int i = rotors.size() - 1; i >= 0; i--) {
                input[pos] = getDefaultLetter(getLetterPosition(input[pos], rotors.get(i)));
                character = defaultPosition(input[pos]);
            }

            // rotates
            update(0);
            pos++;
        }

        return toString(input);
    }

    // Effect: Rotates nth rotor a whole loop before rotating n+1th rotor once
    // Modifies rotors
    public void update(int n) {
        
        getrotors().get(n).rotate();

        if (getrotors().get(n).getRotatePosition() == 0) {
            if (n < getrotors().size() - 1) {
                update(n + 1);
            }
        }
    }

    // Effect: Returns the position of the given letter in the given rotor
    public int getLetterPosition(String letter, rotor rotor) {
        for (int i = 0; i < rotor.NUMOFCHARS; i++) {
            if (rotor.getLetter(i).equals(letter)) {
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

    // Effect: Clears all rotors
    // Modifies: this
    public void clear() {
        EventLog.getInstance().logEvent(new Event("Cleared all rotors"));
        rotors.clear();
    }

    // Effect: Array -> String
    public String toString(String[] arr) {
        String output = "";
        for (String s : arr) {
            output += s;
        }
        return output;
    }

    public ArrayList<rotor> getrotors() {
        return rotors;
    }

    // Effect: Returns true if the input contains invalid characters
    public boolean invalid(String[] input) {
        for (String s : input) {
            if (!ALPHABET.contains(s)) {
                return true;
            }
        } 
        return false;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("rotors", rotorsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray rotorsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (rotor r : rotors) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }
}
