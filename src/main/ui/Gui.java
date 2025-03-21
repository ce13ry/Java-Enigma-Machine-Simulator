package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the graphical user interface for the Enigma Machine
public class Gui implements ActionListener {
    Enigma enigma = new Enigma();
    private static final String JSON_STORE = "./img/enigma.json";
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
    private JButton rotarButton1;
    private JButton rotarButton2;
    private JButton rotarButton3;
    private JButton rotarButton4;
    private JButton rotarButton5;
    private JButton rotar1up;
    private JButton rotar1down;
    private JButton rotar2up;
    private JButton rotar2down;
    private JButton rotar3up;
    private JButton rotar3down;
    private JButton rotar4up;
    private JButton rotar4down;
    private JButton rotar5up;
    private JButton rotar5down;
    private JLabel rotar1init;
    private JLabel rotar2init;
    private JLabel rotar3init;
    private JLabel rotar4init;
    private JLabel rotar5init;
    private int rotar1;
    private int rotar2;
    private int rotar3;
    private int rotar4;
    private int rotar5;
    private JButton resetButton;
    private JButton save;
    private JButton load;
    private JLabel rotars;
    private JTextArea output;
    private JLabel gregor;
    private JButton gregorButton;

    // EFFECTS: creates a new GUI
    public Gui() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        rotar1 = 0;
        rotar2 = 0;
        rotar3 = 0;
        rotar4 = 0;
        rotar5 = 0;
        saved = true;
        gregorOn = false;
        mainFrame();
    }

    // Effects: Creates the main frame for the GUI
    private void mainFrame() {
        frame = new JFrame("Engima Machine");
        mainPanel = new JPanel();

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);

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

        rotars = new JLabel("Rotors: " + settings());
        rotars.setBounds(100, 500, 800, 25);
        enigmaPanel.add(rotars);

        rotarLabel();

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
        rotarButtons();
        rotarInitLabel();
        rotarNumLabel();
    }

    // Effects: Creates the quit button
    private void quitButton() {
        quitButton = new JButton("Quit");
        quitButton.setBounds(0, 0, 80, 25);
        quitButton.addActionListener(this);
        mainPanel.add(quitButton);
    }

    // Effects: Creates the gregor button
    private void gregorButton() {
        gregorButton = new JButton("My pookie ❤️");
        gregorButton.setBounds(0, 25, 120, 25);
        gregorButton.addActionListener(this);
        mainPanel.add(gregorButton);
    }

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

    // Effects: Creates the up buttons for the rotars
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
        rotar1up = new JButton();
        rotar1up.setIcon(img);
        rotar1up.setBounds(90, 50, 50, 50);
        rotar1up.setBorderPainted(false);
        rotar1up.setContentAreaFilled(false);
        rotar1up.setFocusPainted(false);
        rotar1up.addActionListener(this);
        enigmaPanel.add(rotar1up);
    }

    private void upTwo(ImageIcon img) {
        rotar2up = new JButton();
        rotar2up.setIcon(img);
        rotar2up.setBounds(320, 50, 50, 50);
        rotar2up.setBorderPainted(false);
        rotar2up.setContentAreaFilled(false);
        rotar2up.setFocusPainted(false);
        rotar2up.addActionListener(this);
        enigmaPanel.add(rotar2up);
    }

    private void upThree(ImageIcon img) {
        rotar3up = new JButton();
        rotar3up.setIcon(img);
        rotar3up.setBounds(550, 50, 50, 50);
        rotar3up.setBorderPainted(false);
        rotar3up.setContentAreaFilled(false);
        rotar3up.setFocusPainted(false);
        rotar3up.addActionListener(this);
        enigmaPanel.add(rotar3up);
    }
        
    private void upFour(ImageIcon img) {
        rotar4up = new JButton();
        rotar4up.setIcon(img);
        rotar4up.setBounds(190, 275, 50, 50);
        rotar4up.setBorderPainted(false);
        rotar4up.setContentAreaFilled(false);
        rotar4up.setFocusPainted(false);
        rotar4up.addActionListener(this);
        enigmaPanel.add(rotar4up);
    }

    private void upFive(ImageIcon img) {
        rotar5up = new JButton();
        rotar5up.setIcon(img);
        rotar5up.setBounds(420, 275, 50, 50);
        rotar5up.setBorderPainted(false);
        rotar5up.setContentAreaFilled(false);
        rotar5up.setFocusPainted(false);
        rotar5up.addActionListener(this);
        enigmaPanel.add(rotar5up);
    }

    // Effects: Creates the down buttons for the rotars
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
        rotar1down = new JButton();
        rotar1down.setIcon(img);
        rotar1down.setBounds(90, 150, 50, 50);
        rotar1down.setBorderPainted(false);
        rotar1down.setContentAreaFilled(false);
        rotar1down.setFocusPainted(false);
        rotar1down.addActionListener(this);
        enigmaPanel.add(rotar1down);
    }
        
    // Effects: add down button 2
    private void downTwo(ImageIcon img) {
        rotar2down = new JButton();
        rotar2down.setIcon(img);
        rotar2down.setBounds(320, 150, 50, 50);
        rotar2down.setBorderPainted(false);
        rotar2down.setContentAreaFilled(false);
        rotar2down.setFocusPainted(false);
        rotar2down.addActionListener(this);
        enigmaPanel.add(rotar2down);
    }

    // Effects: add down button 3
    private void downThree(ImageIcon img) {
        rotar3down = new JButton();
        rotar3down.setIcon(img);
        rotar3down.setBounds(550, 150, 50, 50);
        rotar3down.setBorderPainted(false);
        rotar3down.setContentAreaFilled(false);
        rotar3down.setFocusPainted(false);
        rotar3down.addActionListener(this);
        enigmaPanel.add(rotar3down);
    }
        
    // Effects: add down button 4
    private void downFour(ImageIcon img) {
        rotar4down = new JButton();
        rotar4down.setIcon(img);
        rotar4down.setBounds(190, 375, 50, 50);
        rotar4down.setBorderPainted(false);
        rotar4down.setContentAreaFilled(false);
        rotar4down.setFocusPainted(false);
        rotar4down.addActionListener(this);
        enigmaPanel.add(rotar4down);
    }
    
    // Effects: add down button 5
    private void downFive(ImageIcon img) {
        rotar5down = new JButton();
        rotar5down.setIcon(img);
        rotar5down.setBounds(420, 375, 50, 50);
        rotar5down.setBorderPainted(false);
        rotar5down.setContentAreaFilled(false);
        rotar5down.setFocusPainted(false);
        rotar5down.addActionListener(this);
        enigmaPanel.add(rotar5down);
    }

    // Effects: Creates the rotar buttons
    private void rotarButtons() {
        ImageIcon rotarIcon = new ImageIcon("./img/rotar.png");
        Image rotarImage = rotarIcon.getImage();
        Image resized = rotarImage.getScaledInstance(150, 230, Image.SCALE_SMOOTH);
        ImageIcon resizedRotar = new ImageIcon(resized);

        rotarButtonOne(resizedRotar);
        rotarButtonTwo(resizedRotar);
        rotarButtonThree(resizedRotar);
        rotarButtonFour(resizedRotar);
        rotarButtonFive(resizedRotar);
    }

    // Effects: add rotar 1
    private void rotarButtonOne(ImageIcon img) {
        rotarButton1 = new JButton();
        rotarButton1.setIcon(img);
        rotarButton1.setBounds(140, 10, 150, 230);
        rotarButton1.setBorderPainted(false);
        rotarButton1.setContentAreaFilled(false);
        rotarButton1.setFocusPainted(false);
        rotarButton1.addActionListener(this);
        enigmaPanel.add(rotarButton1);
    }

    // Effects: add rotar 2
    private void rotarButtonTwo(ImageIcon img) {
        rotarButton2 = new JButton();
        rotarButton2.setIcon(img);
        rotarButton2.setBounds(370, 10, 150, 230);
        rotarButton2.setBorderPainted(false);
        rotarButton2.setContentAreaFilled(false);
        rotarButton2.setFocusPainted(false);
        rotarButton2.addActionListener(this);
        enigmaPanel.add(rotarButton2);
    }

    // Effects: add rotar 3 
    private void rotarButtonThree(ImageIcon img) {
        rotarButton3 = new JButton();
        rotarButton3.setIcon(img);
        rotarButton3.setBounds(600, 10, 150, 230);
        rotarButton3.setBorderPainted(false);
        rotarButton3.setContentAreaFilled(false);
        rotarButton3.setFocusPainted(false);
        rotarButton3.addActionListener(this);
        enigmaPanel.add(rotarButton3);
    }
        
    // Effects: add rotar 4
    private void rotarButtonFour(ImageIcon img) {
        rotarButton4 = new JButton();
        rotarButton4.setIcon(img);
        rotarButton4.setBounds(240, 235, 150, 230);
        rotarButton4.setBorderPainted(false);
        rotarButton4.setContentAreaFilled(false);
        rotarButton4.setFocusPainted(false);
        rotarButton4.addActionListener(this);
        enigmaPanel.add(rotarButton4);
    }
        
    // Effects: add rotar 5
    private void rotarButtonFive(ImageIcon img) {
        rotarButton5 = new JButton();
        rotarButton5.setIcon(img);
        rotarButton5.setBounds(470, 235, 150, 230);
        rotarButton5.setBorderPainted(false);
        rotarButton5.setContentAreaFilled(false);
        rotarButton5.setFocusPainted(false);
        rotarButton5.addActionListener(this);
        enigmaPanel.add(rotarButton5);
    }

    // Effects: Creates the initial position labels for the rotars
    private void rotarInitLabel() {
        rotar1init = new JLabel(Integer.toString(rotar1));
        rotar1init.setBounds(105, 110, 60, 30);
        rotar1init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotar1init);
        
        rotar2init = new JLabel(Integer.toString(rotar2));
        rotar2init.setBounds(335, 110, 60, 30);
        rotar2init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotar2init);
        
        rotar3init = new JLabel(Integer.toString(rotar3));
        rotar3init.setBounds(565, 110, 60, 30);
        rotar3init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotar3init);
        
        rotar4init = new JLabel(Integer.toString(rotar4));
        rotar4init.setBounds(205, 335, 60, 30);
        rotar4init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotar4init);
        
        rotar5init = new JLabel(Integer.toString(rotar5));
        rotar5init.setBounds(435, 335, 60, 30);
        rotar5init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotar5init);
    }

    // Effects: Creates the number labels for the rotars
    private void rotarNumLabel() {
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

    // Effects: Updates the rotar label
    private void rotarLabel() {
        rotars.setText("Rotors: " + settings());
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
        } else if (e.getSource() == rotarButton1) {
            addRotar(1, rotar1);
        } else if (e.getSource() == rotarButton2) {
            addRotar(2, rotar2);
        } else if (e.getSource() == rotarButton3) {
            addRotar(3, rotar3);
        } else if (e.getSource() == rotarButton4) {
            addRotar(4, rotar4);
        } else if (e.getSource() == rotarButton5) {
            addRotar(5, rotar5);
        } else if (e.getSource() == resetButton) {
            reset();
        } else if (e.getSource() == save) {
            save();
        } else if (e.getSource() == load) {
            load();
        } else if (e.getSource() == quitButton) {
            quit();
        } else if (e.getSource() == rotar1up) {
            rotar1up();
        } else if (e.getSource() == rotar1down) {
            rotar1down();
        } else if (e.getSource() == rotar2up) {
            rotar2up();
        } else if (e.getSource() == rotar2down) {
            rotar2down();
        } else if (e.getSource() == rotar3up) {
            rotar3up();
        } else if (e.getSource() == rotar3down) {
            rotar3down();
        } else if (e.getSource() == rotar4up) {
            rotar4up();
        } else if (e.getSource() == rotar4down) {
            rotar4down();
        } else if (e.getSource() == rotar5up) {
            rotar5up();
        } else if (e.getSource() == rotar5down) {
            rotar5down();
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
        enigma.getRotars().clear();
        rotarLabel();
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
        rotarLabel();
        saved = false;
    }

    // Effects: Quits the program
    private void quit() {
        if (!saved) {
            savePrompt();
        }
        System.exit(0);
    }

    // Effects: Increases rotar 1 inital position by one
    private void rotar1up() {
        if (rotar1 >= Rotar.getNumOfChars()) {
            rotar1 = 0;
        } else {
            rotar1++;
        }
        rotar1init.setText(Integer.toString(rotar1));
    }

    // Effects: Decreases rotar 1 inital position by one
    private void rotar1down() {
        if (rotar1 <= 0) {
            rotar1 = Rotar.getNumOfChars();
        } else {
            rotar1--;
        }
        rotar1init.setText(Integer.toString(rotar1));
    }

    // Effects: Increases rotar 2 inital position by one
    private void rotar2up() {
        if (rotar2 >= Rotar.getNumOfChars()) {
            rotar2 = 0;
        } else {
            rotar2++;
        }
        rotar2init.setText(Integer.toString(rotar2));
    }

    // Effects: Decreases rotar 2 inital position by one
    private void rotar2down() {
        if (rotar2 <= 0) {
            rotar2 = Rotar.getNumOfChars();
        } else {
            rotar2--;
        }
        rotar2init.setText(Integer.toString(rotar2));
    }

    // Effects: Increases rotar 3 inital position by one
    private void rotar3up() {
        if (rotar3 >= Rotar.getNumOfChars()) {
            rotar3 = 0;
        } else {
            rotar3++;
        }
        rotar3init.setText(Integer.toString(rotar3));
    }

    // Effects: Decreases rotar 3 inital position by one
    private void rotar3down() {
        if (rotar3 <= 0) {
            rotar3 = Rotar.getNumOfChars();
        } else {
            rotar3--;
        }
        rotar3init.setText(Integer.toString(rotar3));
    }

    // Effects: Increases rotar 4 inital position by one
    private void rotar4up() {
        if (rotar4 >= Rotar.getNumOfChars()) {
            rotar4 = 0;
        } else {
            rotar4++;
        }
        rotar4init.setText(Integer.toString(rotar4));
    }

    // Effects: Decreases rotar 4 inital position by one
    private void rotar4down() {
        if (rotar4 <= 0) {
            rotar4 = Rotar.getNumOfChars();
        } else {
            rotar4--;
        }
        rotar4init.setText(Integer.toString(rotar4));
    }

    // Effects: Increases rotar 5 inital position by one
    private void rotar5up() {
        if (rotar5 >= Rotar.getNumOfChars()) {
            rotar5 = 0;
        } else {
            rotar5++;
        }
        rotar5init.setText(Integer.toString(rotar5));
    }

    // Effects: Decreases rotar 5 inital position by one
    private void rotar5down() {
        if (rotar5 <= 0) {
            rotar5 = Rotar.getNumOfChars();
        } else {
            rotar5--;
        }
        rotar5init.setText(Integer.toString(rotar5));
    }

    // Effects: Adds a rotar to the Enigma Machine
    // Modifies: enigma
    private void addRotar(int setting, int initial) {
        enigma.addSetting(setting, initial);
        rotarLabel();
        saved = false;
    }

    // Effects: Ciphers the text
    private String cipher(String text) {
        if (enigma.getRotars().isEmpty()) {
            System.out.println("No settings to cipher");
            return "";
        } else if (text.equals("")) {
            return "";
        }
        return enigma.cipher(text);
    }

    // Effects: Returns the settings of the rotars
    private String settings() {
        String settings = "";
        for (Rotar r : enigma.getRotars()) {
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