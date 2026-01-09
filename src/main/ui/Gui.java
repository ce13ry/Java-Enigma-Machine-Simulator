package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Represents the graphical user interface for the Enigma Machine
public class Gui implements ActionListener {
    Enigma enigma = new Enigma();
    private static final String JSON_STORE = "./data/enigma.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Boolean saved;
    private Boolean gregorOn;
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel enigmaPanel;
    private JLabel notebook;
    private JLabel one;
    private JLabel two;
    private JLabel three;
    private JLabel four;
    private JLabel five;
    private JButton enigmaButton;
    private JTextArea textInput;
    private JButton quitButton;
    private JButton backButton;
    private JButton rotorButton1;
    private JButton rotorButton2;
    private JButton rotorButton3;
    private JButton rotorButton4;
    private JButton rotorButton5;
    private JButton rotor1up;
    private JButton rotor1down;
    private JButton rotor2up;
    private JButton rotor2down;
    private JButton rotor3up;
    private JButton rotor3down;
    private JButton rotor4up;
    private JButton rotor4down;
    private JButton rotor5up;
    private JButton rotor5down;
    private JLabel rotor1init;
    private JLabel rotor2init;
    private JLabel rotor3init;
    private JLabel rotor4init;
    private JLabel rotor5init;
    private int rotor1;
    private int rotor2;
    private int rotor3;
    private int rotor4;
    private int rotor5;
    private JButton resetButton;
    private JButton save;
    private JButton load;
    private JLabel rotors;
    private JTextArea output;
    private JLabel gregor;
    private JButton gregorButton;

    // EFFECTS: creates a new GUI
    public Gui() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        rotor1 = 0;
        rotor2 = 0;
        rotor3 = 0;
        rotor4 = 0;
        rotor5 = 0;
        saved = true;
        gregorOn = false;
        mainFrame();
    }

    // Effects: Creates the main frame for the GUI
    private void mainFrame() {
        frame = new JFrame("Enigma Machine");
        mainPanel = new JPanel();

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.add(mainPanel);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog();
                frame.dispose();
            }
        });

        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        enigmaButton();

        notebook();

        addMainButtons();

        frame.setVisible(true);
    }

    // Effects: Creates the panel for the Enigma Machine
    private void enigmaPanel() {
        enigmaPanel = new JPanel();

        enigmaPanel.setLayout(null);
        enigmaPanel.setBackground(Color.WHITE);

        mainPanel.setVisible(false);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        frame.add(enigmaPanel);

        addEnigmaButtons();

        rotors = new JLabel("Rotors: " + settings());
        rotors.setBounds(100, 500, 800, 25);
        enigmaPanel.add(rotors);

        rotorLabel();

        frame.setVisible(true);
    }

    // Effects: Creates the button for the Enigma settings
    private void enigmaButton() {
        ImageIcon enigmaIcon = new ImageIcon("./img/enigma.png");
        Image enigmaImage = enigmaIcon.getImage();
        Image resized = enigmaImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedEnigma = new ImageIcon(resized);

        enigmaButton = new JButton();
        enigmaButton.setIcon(resizedEnigma);
        enigmaButton.setBounds(50, 150, 300, 300);
        enigmaButton.setBorderPainted(false);
        enigmaButton.setContentAreaFilled(false);
        enigmaButton.setFocusPainted(false);
        enigmaButton.addActionListener(this);
        mainPanel.add(enigmaButton);
    }

    // Effects: Creates the notebook and input/output text areas
    private void notebook() {
        mainPanel.setLayout(null);
    
        ImageIcon notebookIcon = new ImageIcon("./img/notebook.png");
        Image notebookImage = notebookIcon.getImage();
        Image resizedNotebook = notebookImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        ImageIcon resizedNotebookIcon = new ImageIcon(resizedNotebook);
    
        notebook = new JLabel(resizedNotebookIcon);
        notebook.setBounds(350, 100, 400, 400);
        mainPanel.add(notebook);
    
        addInput();
        addOutput();
    }

    // Effects: Creates the input text area
    private void addInput() {
        textInput = new JTextArea(10, 10);
        textInput.setLineWrap(true);
        textInput.setWrapStyleWord(true);
        textInput.setBounds(390, 200, 150, 200);
        textInput.setOpaque(false);
        textInput.setBackground(new Color(0, 0, 0, 0));
        textInput.setFont(new Font("Arial", Font.PLAIN, 12));
        textInput.setCaretColor(Color.BLACK);
        textInput.setForeground(Color.BLACK);
        textInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                output();
            }
        });
    
        mainPanel.add(textInput);
        mainPanel.setComponentZOrder(textInput, 0);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Effects: Creates the output text area
    private void addOutput() {
        output = new JTextArea();
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setBounds(560, 200, 150, 300);
        output.setOpaque(false);
        output.setEditable(false);
        output.setFont(new Font("Arial", Font.PLAIN, 12));
        output.setForeground(Color.BLACK);
        mainPanel.add(output);
        mainPanel.setComponentZOrder(output, 0);

        output();
    }

    // Effects: Creates the main buttons for the GUI
    private void addMainButtons() {
        quitButton();
        gregorButton();
    }

    // Effects: Creates the buttons for the Enigma Machine
    private void addEnigmaButtons() {
        
        backButton();
        resetButton();
        saveButton();
        loadButton();

        upButtons();
        downButtons();
        rotorButtons();
        rotorInitLabel();
        rotorNumLabel();
    }

    // Effects: Creates the quit button
    private void quitButton() {
        quitButton = new JButton("Quit");
        quitButton.setBounds(0, 0, 80, 25);
        quitButton.addActionListener(this);
        mainPanel.add(quitButton);
    }

    // Effects: Creates the gregor button
    // private void gregorButton() {
    // gregorButton = new JButton("My pookie ❤️");
    //  gregorButton.setBounds(0, 25, 120, 25);
    //  gregorButton.addActionListener(this);
    // mainPanel.add(gregorButton);
    // }

    // Effects: Creates the back button
    private void backButton() {
        backButton = new JButton("Back");
        backButton.setBounds(0, 0, 80, 25);
        backButton.addActionListener(this);
        enigmaPanel.add(backButton);
    }

    // Effects: Creates the reset button
    private void resetButton() {
        resetButton = new JButton("Reset");
        resetButton.setBounds(0, 500, 80, 25);
        resetButton.addActionListener(this);
        enigmaPanel.add(resetButton);
    }

    // Effects: Creates the save button
    private void saveButton() {
        save = new JButton("Save");
        save.setBounds(0, 450, 80, 25);
        save.addActionListener(this);
        enigmaPanel.add(save);
    }

    // Effects: Creates the load button
    private void loadButton() {
        load = new JButton("Load");
        load.setBounds(0, 475, 80, 25);
        load.addActionListener(this);
        enigmaPanel.add(load);
    }

    // Effects: Creates the up buttons for the rotors
    private void upButtons() {
        ImageIcon upIcon = new ImageIcon("./img/up.png");
        Image upImage = upIcon.getImage();
        Image resizedUp = upImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedUpIcon = new ImageIcon(resizedUp);

        upOne(resizedUpIcon);
        upTwo(resizedUpIcon);
        upThree(resizedUpIcon);
        upFour(resizedUpIcon);
        upFive(resizedUpIcon);
    }

    private void upOne(ImageIcon img) {
        rotor1up = new JButton();
        rotor1up.setIcon(img);
        rotor1up.setBounds(90, 50, 50, 50);
        rotor1up.setBorderPainted(false);
        rotor1up.setContentAreaFilled(false);
        rotor1up.setFocusPainted(false);
        rotor1up.addActionListener(this);
        enigmaPanel.add(rotor1up);
    }

    private void upTwo(ImageIcon img) {
        rotor2up = new JButton();
        rotor2up.setIcon(img);
        rotor2up.setBounds(320, 50, 50, 50);
        rotor2up.setBorderPainted(false);
        rotor2up.setContentAreaFilled(false);
        rotor2up.setFocusPainted(false);
        rotor2up.addActionListener(this);
        enigmaPanel.add(rotor2up);
    }

    private void upThree(ImageIcon img) {
        rotor3up = new JButton();
        rotor3up.setIcon(img);
        rotor3up.setBounds(550, 50, 50, 50);
        rotor3up.setBorderPainted(false);
        rotor3up.setContentAreaFilled(false);
        rotor3up.setFocusPainted(false);
        rotor3up.addActionListener(this);
        enigmaPanel.add(rotor3up);
    }
        
    private void upFour(ImageIcon img) {
        rotor4up = new JButton();
        rotor4up.setIcon(img);
        rotor4up.setBounds(190, 275, 50, 50);
        rotor4up.setBorderPainted(false);
        rotor4up.setContentAreaFilled(false);
        rotor4up.setFocusPainted(false);
        rotor4up.addActionListener(this);
        enigmaPanel.add(rotor4up);
    }

    private void upFive(ImageIcon img) {
        rotor5up = new JButton();
        rotor5up.setIcon(img);
        rotor5up.setBounds(420, 275, 50, 50);
        rotor5up.setBorderPainted(false);
        rotor5up.setContentAreaFilled(false);
        rotor5up.setFocusPainted(false);
        rotor5up.addActionListener(this);
        enigmaPanel.add(rotor5up);
    }

    // Effects: Creates the down buttons for the rotors
    private void downButtons() {
        ImageIcon downIcon = new ImageIcon("./img/down.png");
        Image downImage = downIcon.getImage();
        Image resizedDown = downImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedDownIcon = new ImageIcon(resizedDown);

        downOne(resizedDownIcon);
        downTwo(resizedDownIcon);
        downThree(resizedDownIcon);
        downFour(resizedDownIcon);
        downFive(resizedDownIcon);
    }

    // Effects: add down button 1
    private void downOne(ImageIcon img) {
        rotor1down = new JButton();
        rotor1down.setIcon(img);
        rotor1down.setBounds(90, 150, 50, 50);
        rotor1down.setBorderPainted(false);
        rotor1down.setContentAreaFilled(false);
        rotor1down.setFocusPainted(false);
        rotor1down.addActionListener(this);
        enigmaPanel.add(rotor1down);
    }
        
    // Effects: add down button 2
    private void downTwo(ImageIcon img) {
        rotor2down = new JButton();
        rotor2down.setIcon(img);
        rotor2down.setBounds(320, 150, 50, 50);
        rotor2down.setBorderPainted(false);
        rotor2down.setContentAreaFilled(false);
        rotor2down.setFocusPainted(false);
        rotor2down.addActionListener(this);
        enigmaPanel.add(rotor2down);
    }

    // Effects: add down button 3
    private void downThree(ImageIcon img) {
        rotor3down = new JButton();
        rotor3down.setIcon(img);
        rotor3down.setBounds(550, 150, 50, 50);
        rotor3down.setBorderPainted(false);
        rotor3down.setContentAreaFilled(false);
        rotor3down.setFocusPainted(false);
        rotor3down.addActionListener(this);
        enigmaPanel.add(rotor3down);
    }
        
    // Effects: add down button 4
    private void downFour(ImageIcon img) {
        rotor4down = new JButton();
        rotor4down.setIcon(img);
        rotor4down.setBounds(190, 375, 50, 50);
        rotor4down.setBorderPainted(false);
        rotor4down.setContentAreaFilled(false);
        rotor4down.setFocusPainted(false);
        rotor4down.addActionListener(this);
        enigmaPanel.add(rotor4down);
    }
    
    // Effects: add down button 5
    private void downFive(ImageIcon img) {
        rotor5down = new JButton();
        rotor5down.setIcon(img);
        rotor5down.setBounds(420, 375, 50, 50);
        rotor5down.setBorderPainted(false);
        rotor5down.setContentAreaFilled(false);
        rotor5down.setFocusPainted(false);
        rotor5down.addActionListener(this);
        enigmaPanel.add(rotor5down);
    }

    // Effects: Creates the rotor buttons
    private void rotorButtons() {
        ImageIcon rotorIcon = new ImageIcon("./img/rotor.png");
        Image rotorImage = rotorIcon.getImage();
        Image resized = rotorImage.getScaledInstance(150, 230, Image.SCALE_SMOOTH);
        ImageIcon resizedrotor = new ImageIcon(resized);

        rotorButtonOne(resizedrotor);
        rotorButtonTwo(resizedrotor);
        rotorButtonThree(resizedrotor);
        rotorButtonFour(resizedrotor);
        rotorButtonFive(resizedrotor);
    }

    // Effects: add rotor 1
    private void rotorButtonOne(ImageIcon img) {
        rotorButton1 = new JButton();
        rotorButton1.setIcon(img);
        rotorButton1.setBounds(140, 10, 150, 230);
        rotorButton1.setBorderPainted(false);
        rotorButton1.setContentAreaFilled(false);
        rotorButton1.setFocusPainted(false);
        rotorButton1.addActionListener(this);
        enigmaPanel.add(rotorButton1);
    }

    // Effects: add rotor 2
    private void rotorButtonTwo(ImageIcon img) {
        rotorButton2 = new JButton();
        rotorButton2.setIcon(img);
        rotorButton2.setBounds(370, 10, 150, 230);
        rotorButton2.setBorderPainted(false);
        rotorButton2.setContentAreaFilled(false);
        rotorButton2.setFocusPainted(false);
        rotorButton2.addActionListener(this);
        enigmaPanel.add(rotorButton2);
    }

    // Effects: add rotor 3 
    private void rotorButtonThree(ImageIcon img) {
        rotorButton3 = new JButton();
        rotorButton3.setIcon(img);
        rotorButton3.setBounds(600, 10, 150, 230);
        rotorButton3.setBorderPainted(false);
        rotorButton3.setContentAreaFilled(false);
        rotorButton3.setFocusPainted(false);
        rotorButton3.addActionListener(this);
        enigmaPanel.add(rotorButton3);
    }
        
    // Effects: add rotor 4
    private void rotorButtonFour(ImageIcon img) {
        rotorButton4 = new JButton();
        rotorButton4.setIcon(img);
        rotorButton4.setBounds(240, 235, 150, 230);
        rotorButton4.setBorderPainted(false);
        rotorButton4.setContentAreaFilled(false);
        rotorButton4.setFocusPainted(false);
        rotorButton4.addActionListener(this);
        enigmaPanel.add(rotorButton4);
    }
        
    // Effects: add rotor 5
    private void rotorButtonFive(ImageIcon img) {
        rotorButton5 = new JButton();
        rotorButton5.setIcon(img);
        rotorButton5.setBounds(470, 235, 150, 230);
        rotorButton5.setBorderPainted(false);
        rotorButton5.setContentAreaFilled(false);
        rotorButton5.setFocusPainted(false);
        rotorButton5.addActionListener(this);
        enigmaPanel.add(rotorButton5);
    }

    // Effects: Creates the initial position labels for the rotors
    private void rotorInitLabel() {
        rotor1init = new JLabel(Integer.toString(rotor1));
        rotor1init.setBounds(105, 110, 60, 30);
        rotor1init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotor1init);
        
        rotor2init = new JLabel(Integer.toString(rotor2));
        rotor2init.setBounds(335, 110, 60, 30);
        rotor2init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotor2init);
        
        rotor3init = new JLabel(Integer.toString(rotor3));
        rotor3init.setBounds(565, 110, 60, 30);
        rotor3init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotor3init);
        
        rotor4init = new JLabel(Integer.toString(rotor4));
        rotor4init.setBounds(205, 335, 60, 30);
        rotor4init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotor4init);
        
        rotor5init = new JLabel(Integer.toString(rotor5));
        rotor5init.setBounds(435, 335, 60, 30);
        rotor5init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotor5init);
    }

    // Effects: Creates the number labels for the rotors
    private void rotorNumLabel() {
        one();
        two();
        three();
        four();
        five();
    }

    // Effects: add I label
    private void one() {
        one = new JLabel();
        one.setText("I");
        one.setBounds(210, 0, 40, 40);
        one.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        enigmaPanel.add(one);
    }

    // Effects: add II label
    private void two() { 
        two = new JLabel();
        two.setText("II");
        two.setBounds(440, 0, 40, 40);
        two.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        enigmaPanel.add(two);
    }

    // Effects: add III label
    private void three() {   
        three = new JLabel();
        three.setText("III");
        three.setBounds(670, 0, 40, 40);
        three.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        enigmaPanel.add(three);
    }

    // Effects: add IV label
    private void four() {
        four = new JLabel();
        four.setText("IV");
        four.setBounds(310, 225, 40, 40);
        four.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        enigmaPanel.add(four);
    }

    // Effects: add V label
    private void five() {
        five = new JLabel();
        five.setText("V");
        five.setBounds(540, 225, 40, 40);
        five.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        enigmaPanel.add(five);
    }

    // Effects: Updates the output text area
    private void output() {
        String text = textInput.getText();
        output.setText(cipher(text));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    // Effects: Updates the rotor label
    private void rotorLabel() {
        rotors.setText("Rotors: " + settings());
        enigmaPanel.revalidate();
        enigmaPanel.repaint();
    }

    // Effects: Handles the actions of the buttons
    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enigmaButton) {
            enigmaPanel();
        } else if (e.getSource() == backButton) {
            back();
        } else if (e.getSource() == rotorButton1) {
            addrotor(1, rotor1);
        } else if (e.getSource() == rotorButton2) {
            addrotor(2, rotor2);
        } else if (e.getSource() == rotorButton3) {
            addrotor(3, rotor3);
        } else if (e.getSource() == rotorButton4) {
            addrotor(4, rotor4);
        } else if (e.getSource() == rotorButton5) {
            addrotor(5, rotor5);
        } else if (e.getSource() == resetButton) {
            reset();
        } else if (e.getSource() == save) {
            save();
        } else if (e.getSource() == load) {
            load();
        } else if (e.getSource() == quitButton) {
            quit();
        } else if (e.getSource() == rotor1up) {
            rotor1up();
        } else if (e.getSource() == rotor1down) {
            rotor1down();
        } else if (e.getSource() == rotor2up) {
            rotor2up();
        } else if (e.getSource() == rotor2down) {
            rotor2down();
        } else if (e.getSource() == rotor3up) {
            rotor3up();
        } else if (e.getSource() == rotor3down) {
            rotor3down();
        } else if (e.getSource() == rotor4up) {
            rotor4up();
        } else if (e.getSource() == rotor4down) {
            rotor4down();
        } else if (e.getSource() == rotor5up) {
            rotor5up();
        } else if (e.getSource() == rotor5down) {
            rotor5down();
        } else if (e.getSource() == gregorButton) {
            gregor();
        }
    }

    // Effects: Returns to main panel
    private void back() {
        mainPanel.setVisible(true);
        enigmaPanel.setVisible(false);
        output();
    }

    // Effects: Resets the Enigma Machine
    // Modifies: enigma
    private void reset() {
        enigma.clear();
        rotorLabel();
        saved = false;
    }

    // Effects: Saves the Enigma Machine
    private void save() {
        saveEnigma();
        saved = true;
    }

    // Effects: Loads the Enigma Machine
    private void load() {
        loadEnigma();
        rotorLabel();
        saved = false;
    }

    // Effects: Quits the program
    private void quit() {
        if (!saved) {
            savePrompt();
        }
        printLog();
        System.exit(0);
    }

    // Effects: Increases rotor 1 inital position by one
    private void rotor1up() {
        if (rotor1 >= rotor.getNumOfChars()) {
            rotor1 = 0;
        } else {
            rotor1++;
        }
        rotor1init.setText(Integer.toString(rotor1));
    }

    // Effects: Decreases rotor 1 inital position by one
    private void rotor1down() {
        if (rotor1 <= 0) {
            rotor1 = rotor.getNumOfChars();
        } else {
            rotor1--;
        }
        rotor1init.setText(Integer.toString(rotor1));
    }

    // Effects: Increases rotor 2 inital position by one
    private void rotor2up() {
        if (rotor2 >= rotor.getNumOfChars()) {
            rotor2 = 0;
        } else {
            rotor2++;
        }
        rotor2init.setText(Integer.toString(rotor2));
    }

    // Effects: Decreases rotor 2 inital position by one
    private void rotor2down() {
        if (rotor2 <= 0) {
            rotor2 = rotor.getNumOfChars();
        } else {
            rotor2--;
        }
        rotor2init.setText(Integer.toString(rotor2));
    }

    // Effects: Increases rotor 3 inital position by one
    private void rotor3up() {
        if (rotor3 >= rotor.getNumOfChars()) {
            rotor3 = 0;
        } else {
            rotor3++;
        }
        rotor3init.setText(Integer.toString(rotor3));
    }

    // Effects: Decreases rotor 3 inital position by one
    private void rotor3down() {
        if (rotor3 <= 0) {
            rotor3 = rotor.getNumOfChars();
        } else {
            rotor3--;
        }
        rotor3init.setText(Integer.toString(rotor3));
    }

    // Effects: Increases rotor 4 inital position by one
    private void rotor4up() {
        if (rotor4 >= rotor.getNumOfChars()) {
            rotor4 = 0;
        } else {
            rotor4++;
        }
        rotor4init.setText(Integer.toString(rotor4));
    }

    // Effects: Decreases rotor 4 inital position by one
    private void rotor4down() {
        if (rotor4 <= 0) {
            rotor4 = rotor.getNumOfChars();
        } else {
            rotor4--;
        }
        rotor4init.setText(Integer.toString(rotor4));
    }

    // Effects: Increases rotor 5 inital position by one
    private void rotor5up() {
        if (rotor5 >= rotor.getNumOfChars()) {
            rotor5 = 0;
        } else {
            rotor5++;
        }
        rotor5init.setText(Integer.toString(rotor5));
    }

    // Effects: Decreases rotor 5 inital position by one
    private void rotor5down() {
        if (rotor5 <= 0) {
            rotor5 = rotor.getNumOfChars();
        } else {
            rotor5--;
        }
        rotor5init.setText(Integer.toString(rotor5));
    }

    // Effects: Adds a rotor to the Enigma Machine
    // Modifies: enigma
    private void addrotor(int setting, int initial) {
        enigma.addSetting(setting, initial);
        rotorLabel();
        saved = false;
    }

    // Effects: Ciphers the text
    private String cipher(String text) {
        if (enigma.getrotors().isEmpty()) {
            return "";
        } else if (text.equals("")) {
            return "";
        }
        return enigma.cipher(text);
    }

    // Effects: Returns the settings of the rotors
    private String settings() {
        String settings = "";
        for (rotor r : enigma.getrotors()) {
            settings += r.getSettingNum() + " (" + r.getInitialPosition() + ")" + ", ";
        }
        return settings;
    }

    // Effects: Prompts the user to save their settings
    private void savePrompt() {
        int result = JOptionPane.showConfirmDialog(frame, "Do you want to save your settings?", "Save",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            saveEnigma();
        }
    }

    // Effects: MY KINGGGGGG
    private void gregor() {
        if (!gregorOn) {
            ImageIcon gregorIcon = new ImageIcon("./img/gregor.png");
            Image gregorImage = gregorIcon.getImage();
            Image resized = gregorImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedGregor = new ImageIcon(resized);

            gregor = new JLabel();
            gregor.setIcon(resizedGregor);
            gregor.setBounds(430, 0,200, 200);
            mainPanel.add(gregor);

            mainPanel.revalidate();
            mainPanel.repaint();

            gregorOn = true;
        } else {
            gregorOff();
        }
    }

    private void gregorOff() {
        mainPanel.remove(gregor);
        mainPanel.revalidate();
        mainPanel.repaint();
        gregorOn = false;
    }

    private void printLog() {
        for (model.Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
    }

    // Effects: Saves the Enigma Machine to file
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

    // Effects: Loads the Enigma Machine from file
    private void loadEnigma() {
        try {
            enigma = jsonReader.read();
            System.out.println("Loaded settings from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
