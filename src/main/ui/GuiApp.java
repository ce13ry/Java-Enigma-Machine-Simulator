package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GuiApp implements ActionListener {

    private static final String JSON_STORE = "./data/enigma.json";

    private Enigma enigma = new Enigma();
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);

    private Boolean saved = true;
    private Boolean gregorOn = false;

    private int rotor1 = 0;
    private int rotor2 = 0;
    private int rotor3 = 0;
    private int rotor4 = 0;
    private int rotor5 = 0;

    private JFrame frame;
    private MainView mainView;
    private EnigmaView enigmaView;

    public GuiApp() {
        buildFrame();
        showMain();
    }

    private void buildFrame() {
        frame = new JFrame("Enigma Machine");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog();
                frame.dispose();
            }
        });

        mainView = new MainView(this);
        enigmaView = new EnigmaView(this);

        frame.setContentPane(mainView.getPanel());
        frame.setVisible(true);

        updateOutput();
    }

    private void showMain() {
        frame.setContentPane(mainView.getPanel());
        frame.revalidate();
        frame.repaint();
        updateOutput();
    }

    private void showEnigma() {
        frame.setContentPane(enigmaView.getPanel());
        frame.revalidate();
        frame.repaint();

        enigmaView.setRotorValue(1, rotor1);
        enigmaView.setRotorValue(2, rotor2);
        enigmaView.setRotorValue(3, rotor3);
        enigmaView.setRotorValue(4, rotor4);
        enigmaView.setRotorValue(5, rotor5);

        updateRotorLabel();
    }

    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == mainView.getEnigmaButton()) {
            showEnigma();
        } else if (src == mainView.getQuitButton()) {
            quit();
        // } else if (src == mainView.getGregorButton()) {
        //    toggleGregor();
        } else if (src == enigmaView.getBackButton()) {
            showMain();
        } else if (src == enigmaView.getResetButton()) {
            reset();
        } else if (src == enigmaView.getSaveButton()) {
            save();
        } else if (src == enigmaView.getLoadButton()) {
            load();
        } else if (src == enigmaView.getRotorButton(1)) {
            addRotor(1, rotor1);
        } else if (src == enigmaView.getRotorButton(2)) {
            addRotor(2, rotor2);
        } else if (src == enigmaView.getRotorButton(3)) {
            addRotor(3, rotor3);
        } else if (src == enigmaView.getRotorButton(4)) {
            addRotor(4, rotor4);
        } else if (src == enigmaView.getRotorButton(5)) {
            addRotor(5, rotor5);
        } else if (src == enigmaView.getUpButton(1)) {
            rotor1 = rotorUp(rotor1);
            enigmaView.setRotorValue(1, rotor1);
        } else if (src == enigmaView.getDownButton(1)) {
            rotor1 = rotorDown(rotor1);
            enigmaView.setRotorValue(1, rotor1);
        } else if (src == enigmaView.getUpButton(2)) {
            rotor2 = rotorUp(rotor2);
            enigmaView.setRotorValue(2, rotor2);
        } else if (src == enigmaView.getDownButton(2)) {
            rotor2 = rotorDown(rotor2);
            enigmaView.setRotorValue(2, rotor2);
        } else if (src == enigmaView.getUpButton(3)) {
            rotor3 = rotorUp(rotor3);
            enigmaView.setRotorValue(3, rotor3);
        } else if (src == enigmaView.getDownButton(3)) {
            rotor3 = rotorDown(rotor3);
            enigmaView.setRotorValue(3, rotor3);
        } else if (src == enigmaView.getUpButton(4)) {
            rotor4 = rotorUp(rotor4);
            enigmaView.setRotorValue(4, rotor4);
        } else if (src == enigmaView.getDownButton(4)) {
            rotor4 = rotorDown(rotor4);
            enigmaView.setRotorValue(4, rotor4);
        } else if (src == enigmaView.getUpButton(5)) {
            rotor5 = rotorUp(rotor5);
            enigmaView.setRotorValue(5, rotor5);
        } else if (src == enigmaView.getDownButton(5)) {
            rotor5 = rotorDown(rotor5);
            enigmaView.setRotorValue(5, rotor5);
        }
    }

    private void reset() {
        enigma.clear();
        updateRotorLabel();
        saved = false;
    }

    private void save() {
        saveEnigma();
        saved = true;
    }

    private void load() {
        loadEnigma();
        updateRotorLabel();
        saved = false;
    }

    private void quit() {
        if (!saved) {
            savePrompt();
        }
        printLog();
        System.exit(0);
    }

    private void toggleGregor() {
        if (!gregorOn) {
            mainView.showGregor(Icons.scaled("./img/gregor.png", 200, 200));
            gregorOn = true;
        } else {
            mainView.hideGregor();
            gregorOn = false;
        }
    }

    private int rotorUp(int r) {
        if (r >= Rotor.getNumOfChars()) {
            return 0;
        }
        return r + 1;
    }

    private int rotorDown(int r) {
        if (r <= 0) {
            return Rotor.getNumOfChars();
        }
        return r - 1;
    }

    private void addRotor(int setting, int initial) {
        enigma.addSetting(setting, initial);
        updateRotorLabel();
        saved = false;
    }

    private void updateOutput() {
        String text = mainView.getInputText();
        mainView.setOutputText(cipher(text));
    }

    void onInputChanged() {
        updateOutput();
    }

    private String cipher(String text) {
        if (enigma.getRotors().isEmpty()) {
            return "";
        } else if (text.equals("")) {
            return "";
        }
        return enigma.cipher(text);
    }

    private void updateRotorLabel() {
        enigmaView.setRotorsLabel("Rotors: " + settings());
    }

    private String settings() {
        String settings = "";
        for (Rotor r : enigma.getRotors()) {
            settings += r.getSettingNum() + " (" + r.getInitialPosition() + ")" + ", ";
        }
        return settings;
    }

    private void savePrompt() {
        int result = JOptionPane.showConfirmDialog(
                frame,
                "Do you want to save your settings?",
                "Save",
                JOptionPane.YES_NO_OPTION
        );
        if (result == JOptionPane.YES_OPTION) {
            saveEnigma();
        }
    }

    private void printLog() {
        for (model.Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
    }

    private void saveEnigma() {
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
}
