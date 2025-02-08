package model;
import java.util.*;

public class Enigma {
    int iterations = 0;

    public ArrayList<Rotar> rotars;

    private String[] reflector = "swjedvz qcxpotmliuanrfbkygh9876543210,.".split("");
    final String ALPHABET = "abcdefghijklmnopqrstuvwxyz 0123456789.,";
    
    // Effect: Creates a machine with no rotars
    public Enigma() {
        rotars  = new ArrayList<>();
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

    public void remove(int i){
        rotars.remove(rotars.get(i-1));
    }

    // Effect: Encrypts/Decrypts the input string
    // Modifies: this
    @SuppressWarnings("methodlength")
    public String cipher(String in){

        //reset all rotars
        for(Rotar r : rotars){
            Rotar.reset(r);
            iterations = 0;
        }
        
        String[] input = in.split("");
        int pos = 0;
        
        for(String c : input){
            int character = defaultPosition(c);

            //forward direction
            for(Rotar r : rotars){
                input[pos] = r.getLetter(character);
                character = defaultPosition(input[pos]);
            }
            
            //reflector
            input[pos] = reflector[character];
            character = defaultPosition(input[pos]);
            
            //backward direction
            for(int i = rotars.size() - 1; i >= 0; i--){
                input[pos] = getDefaultLetter(getLetterPosition(input[pos], rotars.get(i)));
                character = defaultPosition(input[pos]); 
                
            }

            //rotates
            update(iterations, 0);  

            pos++;
            iterations++;
            
        }
        
        return toString(input);
    }

    // Effect: Rotates nth rotar a whole loop before rotating n+1th rotar
    public void update(int iterations, int n){

        if(iterations < Rotar.NUMOFCHARS-1){
            for(int i = 0; i <= n; i++){
                rotars.get(i).rotate();
            }
        }else if(n < rotars.size() - 1){
            update(iterations - Rotar.NUMOFCHARS-1, n + 1);
        }else{
            update(iterations - Rotar.NUMOFCHARS-1, n);
        }
    }

    // Effect: Returns the position of the given letter in the given rotar
    public int getLetterPosition(String letter, Rotar rotar){
        for(int i = 0; i < Rotar.NUMOFCHARS; i++){
            if(rotar.getLetter(i).equals(letter)){
                return i;
            }
        }
        return -1;
    }

    // Effect: Returns the default position of the given letter
    public int defaultPosition(String letter){
        return ALPHABET.indexOf(letter);
    }

    // Effect: Returns the default letter at the given position
    public String getDefaultLetter(int i){
        return ALPHABET.substring(i, i + 1);
    }

    // Effect: Array -> String
    public String toString(String[] arr){
        String output = "";
        for(String s : arr){
            output += s;
        }
        return output;
    }
}

