package ui;

import model.Enigma;

import model.Rotar;

import java.util.Scanner;

public class EnigmaApp {
    private Scanner input = new Scanner(System.in);
    
    // EFFECTS: runs the Enigma application
    public EnigmaApp() {
        runEnigma();
    }

    // EFFECTS: user interaction with the Enigma machine
    // MODIFIES: enigma
    private void runEnigma() {
        boolean running = true;

        System.out.println("Welcome to the Enigma Machine!");

        Enigma enigma = new Enigma();

        while (running) {
            
            displayOptions(enigma);

            String command = input.nextLine();

            if (command.equals("q")) {
                running = false;
            } else {
                processCommand(command, enigma);
            }

            System.out.println("");
        }
        System.out.println("Ended");
    }

    // EFFECTS: displays the options for the user
    private void displayOptions(Enigma enigma) {
        if (options(enigma).equals("")) {
            System.out.println("Settings: Empty");
        } else {
            System.out.println("Settings: " + options(enigma));
        }
        System.out.println("Enter 's' to add a setting");
        System.out.println("Enter 'r' to remove a rotar");
        System.out.println("Enter 'e' to encrypt/decrypt a string");
        System.out.println("Enter 'c' to clear settings");
        System.out.println("Enter 'q' to quit");
    }

    // EFFECTS: processes the user command
    private void processCommand(String command, Enigma enigma) { 
        switch (command) {
            case "s":
                setting(enigma);
                break;
            case "r":
                remove(enigma);
                break;
            case "e":
                cipher(enigma);
                break;
            case "c":
                clear(enigma);
                break;
            default:
                fail();
        }
    }

    // EFFECTS: processes the user command for adding a setting
    // MODIFIES: enigma
    // MODIFIES: rotar
    private void setting(Enigma enigma) {
        System.out.println("Enter the setting number (1-5): ");
        int setting = input.nextInt();
        input.nextLine();
        if (setting < 1 || setting > 5) {
            fail();
            return;
        }
        addSetting(setting, enigma);
    }

    // EFFECTS: processes the user command for removing a rotar
    // MODIFIES: enigma
    private void remove(Enigma enigma) {
        System.out.println("Enter position of the rotar desired to be removed " + options(enigma) + ": ");
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
    private void cipher(Enigma enigma) {
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
    private void clear(Enigma enigma) {
        enigma.getRotars().clear();
    }

    // EFFECTS: returns the options for the user
    private String options(Enigma enigma) {
        String options = "";
        for (Rotar r : enigma.getRotars()) {
            options += r.getSettingNum() + ", ";
        }
        return options;
    }

    // EFFECTS: adds a setting to the Enigma machine
    // MODIFIES: enigma
    private void addSetting(int setting, Enigma enigma) {
        switch (setting) {
            case 1:
                enigma.addSetting1();
                break;
            case 2:
                enigma.addSetting2();
                break;
            case 3:
                enigma.addSetting3();
                break;
            case 4:
                enigma.addSetting4();
                break;
            case 5:
                enigma.addSetting5();
                break;
            default:
                fail();
        }
    }

    private void fail() {
        System.out.println("Invalid input");
    }

}