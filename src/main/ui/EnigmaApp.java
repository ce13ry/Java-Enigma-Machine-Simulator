package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the Enigma application
public class EnigmaApp {
    private static final String JSON_STORE = "./data/enigma.json";
    private Scanner input = new Scanner(System.in);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Enigma enigma;
    private Boolean saved = true;

    // EFFECTS: runs the Enigma application
    public EnigmaApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runEnigma();
    }

    // EFFECTS: user interaction with the Enigma machine
    // MODIFIES: enigma
    private void runEnigma() {
        boolean running = true;

        System.out.println("Welcome to the Enigma Machine!");

        enigma = new Enigma();

        while (running) {
            
            displayOptions();

            String command = input.nextLine();

            if (command.equals("q")) {
                if (!saved) {
                    System.out.println("Would you like to save your settings? (y/n)");
                    String save = input.nextLine();
                    if (save.equals("y")) {
                        uploadEnigma();
                    }
                }
                running = false;
            } else {
                processCommand(command);
            }

            System.out.println("");
        }
        System.out.println("Ended");
    }

    // EFFECTS: displays the options for the user
    private void displayOptions() {
        if (options().equals("")) {
            System.out.println("Settings: Empty");
        } else {
            System.out.println("Settings: " + options());
        }
        System.out.println("Enter 's' to add a setting");
        System.out.println("Enter 'r' to remove a rotar");
        System.out.println("Enter 'e' to encrypt/decrypt a string");
        System.out.println("Enter 'c' to clear settings");
        System.out.println("Enter 'u' to upload settings");
        System.out.println("Enter 'l' to load settings");
        System.out.println("Enter 'q' to quit");
    }

    // EFFECTS: processes the user command
    @SuppressWarnings("methodlength")
    private void processCommand(String command) { 
        switch (command) {
            case "s":
                saved = false;
                setting();
                break;
            case "r":
                saved = false;
                remove();
                break;
            case "e":
                cipher();
                break;
            case "c":
                saved = false;
                clear();
                break;
            case "u":
                saved = true;
                uploadEnigma();
                break;
            case "l":
                loadEnigma();
                break;
            default:
                fail();
        }
    }

    // EFFECTS: processes the user command for adding a setting
    // MODIFIES: enigma
    // MODIFIES: rotar
    private void setting() {
        System.out.println("Enter the setting number (1-5): ");
        int setting = input.nextInt();
        System.out.println("Enter the initial position: ");
        int initial = input.nextInt();
        input.nextLine();
        if (setting < 1 || setting > 5) {
            fail();
            return;
        }
        addSetting(setting, initial);
    }

    // EFFECTS: processes the user command for removing a rotar
    // MODIFIES: enigma
    private void remove() {
        System.out.println("Enter position of the rotar desired to be removed " + options() + ": ");
        int remove = input.nextInt();
        input.nextLine();
        if (remove < 1 || remove > enigma.getRotars().size()) {
            fail();
            return;
        }
        enigma.remove(remove);
    }

    // EFFECTS: processes the user command for encrypting/decrypting a string
    // MODIFIES: enigma
    // MODIFIES: rotar
    private void cipher() {
        if (enigma.getRotars().isEmpty()) {
            System.out.println("No settings to cipher");
            return;
        }
        System.out.println("Enter the string to be ciphered: ");
        String cipher = input.nextLine();
        System.out.println(enigma.cipher(cipher));
    }

    // EFFECTS: clears all settings
    // MODIFIES: enigma
    private void clear() {
        enigma.getRotars().clear();
    }

    // EFFECTS: returns the options for the user
    private String options() {
        String options = "";
        for (Rotar r : enigma.getRotars()) {
            options += r.getSettingNum() + " (" + r.getInitialPosition() + ")" + ", ";
        }
        return options;
    }

    // EFFECTS: adds a setting to the Enigma machine
    // MODIFIES: enigma
    private void addSetting(int setting, int initial) {
        enigma.addSetting(setting, initial);
    }

    // EFFECTS: saves enigma to file
    private void uploadEnigma() {
        try {
            jsonWriter.open();
            jsonWriter.write(enigma);
            jsonWriter.close();
            System.out.println("Saved settings to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadEnigma() {
        try {
            enigma = jsonReader.read();
            System.out.println("Loaded settings from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void fail() {
        System.out.println("Invalid input");
    }

}