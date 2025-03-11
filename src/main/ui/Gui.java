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

public class Gui implements ActionListener {
    Enigma enigma = new Enigma();
    private static final String JSON_STORE = "./data/enigma.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
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

    public Gui() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        rotar1 = 0;
        rotar2 = 0;
        rotar3 = 0;
        rotar4 = 0;
        rotar5 = 0;
        mainFrame();
    }

    private void mainFrame() {
        frame = new JFrame("Engima Machine");
        mainPanel = new JPanel();

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mainPanel);

        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        EnigmaButton();

        addNotebook();

        addMainButtons();

        frame.setVisible(true);
    }

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

    private void EnigmaButton() {
        ImageIcon enigmaIcon = new ImageIcon("./data/enigma.png");
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

    private void addNotebook() {
        mainPanel.setLayout(null);
    
        ImageIcon notebookIcon = new ImageIcon("./data/openNotebook.png");
        Image notebookImage = notebookIcon.getImage();
        int notebookWidth = 400;
        int notebookHeight = 400;
        Image resizedNotebook = notebookImage.getScaledInstance(notebookWidth, notebookHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedNotebookIcon = new ImageIcon(resizedNotebook);
    
        notebook = new JLabel(resizedNotebookIcon);
        notebook.setBounds(350, 100, 400, 400);
        mainPanel.add(notebook);
    
        textInput = new JTextArea(10, 10);
        textInput.setLineWrap(true);
        textInput.setWrapStyleWord(true);
        textInput.setBounds(390, 200, 150, 200);
        textInput.setOpaque(false);
        textInput.setBackground(new Color(0, 0, 0, 0));
        textInput.setFont(new Font("Arial", Font.PLAIN, 16));
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

        output = new JTextArea();
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setBounds(560, 200, 150, 300);
        output.setOpaque(false);  // Make background transparent
        output.setEditable(false); // Prevent user edits
        output.setFont(new Font("Arial", Font.PLAIN, 15));
        output.setForeground(Color.BLACK);
        mainPanel.add(output);
        mainPanel.setComponentZOrder(output, 0);

        output();
    }

    private void addMainButtons() {

        quitButton = new JButton("Quit");
        quitButton.setBounds(0, 0, 80, 25);
        quitButton.addActionListener(this);
        mainPanel.add(quitButton);
    }

    private void addEnigmaButtons() {
        backButton = new JButton("Back");
        backButton.setBounds(0, 0, 80, 25);
        backButton.addActionListener(this);
        enigmaPanel.add(backButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(0, 500, 80, 25);
        resetButton.addActionListener(this);
        enigmaPanel.add(resetButton);

        save = new JButton("Save");
        save.setBounds(0, 450, 80, 25);
        save.addActionListener(this);
        enigmaPanel.add(save);

        load = new JButton("Load");
        load.setBounds(0, 475, 80, 25);
        load.addActionListener(this);
        enigmaPanel.add(load);

        ImageIcon upIcon = new ImageIcon("./data/up.png");
        Image upImage = upIcon.getImage();
        Image resizedUp = upImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedUpIcon = new ImageIcon(resizedUp);

        ImageIcon downIcon = new ImageIcon("./data/down.png");
        Image downImage = downIcon.getImage();
        Image resizedDown = downImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedDownIcon = new ImageIcon(resizedDown);

        ImageIcon rotarIcon = new ImageIcon("./data/rotar.png");
        Image rotarImage = rotarIcon.getImage();
        Image resized = rotarImage.getScaledInstance(150, 230, Image.SCALE_SMOOTH);
        ImageIcon resizedRotar = new ImageIcon(resized);

        rotarButton1 = new JButton();
        rotarButton1.setIcon(resizedRotar);
        rotarButton1.setBounds(140, 10, 150, 230);
        rotarButton1.setBorderPainted(false);
        rotarButton1.setContentAreaFilled(false);
        rotarButton1.setFocusPainted(false);
        rotarButton1.addActionListener(this);
        enigmaPanel.add(rotarButton1);

        rotar1up = new JButton();
        rotar1up.setIcon(resizedUpIcon);
        rotar1up.setBounds(90, 50, 50, 50);
        rotar1up.setBorderPainted(false);
        rotar1up.setContentAreaFilled(false);
        rotar1up.setFocusPainted(false);
        rotar1up.addActionListener(this);
        enigmaPanel.add(rotar1up);

        rotar1down = new JButton();
        rotar1down.setIcon(resizedDownIcon);
        rotar1down.setBounds(90, 150, 50, 50);
        rotar1down.setBorderPainted(false);
        rotar1down.setContentAreaFilled(false);
        rotar1down.setFocusPainted(false);
        rotar1down.addActionListener(this);
        enigmaPanel.add(rotar1down);

        rotar1init = new JLabel(Integer.toString(rotar1));
        rotar1init.setBounds(105, 110, 60, 30);
        rotar1init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotar1init);

        one = new JLabel();
        one.setText("I");
        one.setBounds(210, 0, 40, 40);
        enigmaPanel.add(one);

        rotarButton2 = new JButton();
        rotarButton2.setIcon(resizedRotar);
        rotarButton2.setBounds(370, 10, 150, 230);
        rotarButton2.setBorderPainted(false);
        rotarButton2.setContentAreaFilled(false);
        rotarButton2.setFocusPainted(false);
        rotarButton2.addActionListener(this);
        enigmaPanel.add(rotarButton2);

        rotar2up = new JButton();
        rotar2up.setIcon(resizedUpIcon);
        rotar2up.setBounds(320, 50, 50, 50);
        rotar2up.setBorderPainted(false);
        rotar2up.setContentAreaFilled(false);
        rotar2up.setFocusPainted(false);
        rotar2up.addActionListener(this);
        enigmaPanel.add(rotar2up);

        rotar2down = new JButton();
        rotar2down.setIcon(resizedDownIcon);
        rotar2down.setBounds(320, 150, 50, 50);
        rotar2down.setBorderPainted(false);
        rotar2down.setContentAreaFilled(false);
        rotar2down.setFocusPainted(false);
        rotar2down.addActionListener(this);
        enigmaPanel.add(rotar2down);

        rotar2init = new JLabel(Integer.toString(rotar2));
        rotar2init.setBounds(335, 110, 60, 30);
        rotar2init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotar2init);

        two = new JLabel();
        two.setText("II");
        two.setBounds(440, 0, 40, 40);
        enigmaPanel.add(two);

        rotarButton3 = new JButton();
        rotarButton3.setIcon(resizedRotar);
        rotarButton3.setBounds(600, 10, 150, 230);
        rotarButton3.setBorderPainted(false);
        rotarButton3.setContentAreaFilled(false);
        rotarButton3.setFocusPainted(false);
        rotarButton3.addActionListener(this);
        enigmaPanel.add(rotarButton3);

        rotar3up = new JButton();
        rotar3up.setIcon(resizedUpIcon);
        rotar3up.setBounds(550, 50, 50, 50);
        rotar3up.setBorderPainted(false);
        rotar3up.setContentAreaFilled(false);
        rotar3up.setFocusPainted(false);
        rotar3up.addActionListener(this);
        enigmaPanel.add(rotar3up);

        rotar3down = new JButton();
        rotar3down.setIcon(resizedDownIcon);
        rotar3down.setBounds(550, 150, 50, 50);
        rotar3down.setBorderPainted(false);
        rotar3down.setContentAreaFilled(false);
        rotar3down.setFocusPainted(false);
        rotar3down.addActionListener(this);
        enigmaPanel.add(rotar3down);

        rotar3init = new JLabel(Integer.toString(rotar3));
        rotar3init.setBounds(565, 110, 60, 30);
        rotar3init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotar3init);

        three = new JLabel();
        three.setText("III");
        three.setBounds(670, 0, 40, 40);
        enigmaPanel.add(three);

        rotarButton4 = new JButton();
        rotarButton4.setIcon(resizedRotar);
        rotarButton4.setBounds(240, 235, 150, 230);
        rotarButton4.setBorderPainted(false);
        rotarButton4.setContentAreaFilled(false);
        rotarButton4.setFocusPainted(false);
        rotarButton4.addActionListener(this);
        enigmaPanel.add(rotarButton4);

        rotar4up = new JButton();
        rotar4up.setIcon(resizedUpIcon);
        rotar4up.setBounds(190, 275, 50, 50);
        rotar4up.setBorderPainted(false);
        rotar4up.setContentAreaFilled(false);
        rotar4up.setFocusPainted(false);
        rotar4up.addActionListener(this);
        enigmaPanel.add(rotar4up);

        rotar4down = new JButton();
        rotar4down.setIcon(resizedDownIcon);
        rotar4down.setBounds(190, 375, 50, 50);
        rotar4down.setBorderPainted(false);
        rotar4down.setContentAreaFilled(false);
        rotar4down.setFocusPainted(false);
        rotar4down.addActionListener(this);
        enigmaPanel.add(rotar4down);

        rotar4init = new JLabel(Integer.toString(rotar4));
        rotar4init.setBounds(205, 335, 60, 30);
        rotar4init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotar4init);

        four = new JLabel();
        four.setText("IV");
        four.setBounds(310, 225, 40, 40);
        enigmaPanel.add(four);

        rotarButton5 = new JButton();
        rotarButton5.setIcon(resizedRotar);
        rotarButton5.setBounds(470, 235, 150, 230);
        rotarButton5.setBorderPainted(false);
        rotarButton5.setContentAreaFilled(false);
        rotarButton5.setFocusPainted(false);
        rotarButton5.addActionListener(this);
        enigmaPanel.add(rotarButton5);

        rotar5up = new JButton();
        rotar5up.setIcon(resizedUpIcon);
        rotar5up.setBounds(420, 275, 50, 50);
        rotar5up.setBorderPainted(false);
        rotar5up.setContentAreaFilled(false);
        rotar5up.setFocusPainted(false);
        rotar5up.addActionListener(this);
        enigmaPanel.add(rotar5up);

        rotar5down = new JButton();
        rotar5down.setIcon(resizedDownIcon);
        rotar5down.setBounds(420, 375, 50, 50);
        rotar5down.setBorderPainted(false);
        rotar5down.setContentAreaFilled(false);
        rotar5down.setFocusPainted(false);
        rotar5down.addActionListener(this);
        enigmaPanel.add(rotar5down);

        rotar5init = new JLabel(Integer.toString(rotar5));
        rotar5init.setBounds(435, 335, 60, 30);
        rotar5init.setFont(new Font("Arial", Font.PLAIN, 35));
        enigmaPanel.add(rotar5init);

        five = new JLabel();
        five.setText("V");
        five.setBounds(540, 225, 40, 40);
        enigmaPanel.add(five);
    }

    private void output() {
        String text = textInput.getText();
        output.setText(cipher(text));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void rotarLabel() {
        rotars.setText("Rotors: " + settings());
        enigmaPanel.revalidate();
        enigmaPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enigmaButton) {
            enigmaPanel();
        } else if (e.getSource() == backButton) {
            mainPanel.setVisible(true);
            enigmaPanel.setVisible(false);
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
            enigma.getRotars().clear();
            rotarLabel();
        } else if (e.getSource() == save) {
            saveEnigma();
        } else if (e.getSource() == load) {
            loadEnigma();
            rotarLabel();
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        } else if (e.getSource() == rotar1up) {
            if (rotar1 >= Rotar.getNumOfChars()) {
                rotar1 = 0;
            } else {
                rotar1++;
            }
            rotar1init.setText(Integer.toString(rotar1));
        } else if (e.getSource() == rotar1down) {
            if (rotar1 <= 0) {
                rotar1 = Rotar.getNumOfChars();
            } else {
                rotar1--;
            }
            rotar1init.setText(Integer.toString(rotar1));
        } else if (e.getSource() == rotar2up) {
            if (rotar2 >= Rotar.getNumOfChars()) {
                rotar2 = 0;
            } else {
                rotar2++;
            }
            rotar2init.setText(Integer.toString(rotar2));
        } else if (e.getSource() == rotar2down) {
            if (rotar2 <= 0) {
                rotar2 = Rotar.getNumOfChars();
            } else {
                rotar2--;
            }
            rotar2init.setText(Integer.toString(rotar2));
        } else if (e.getSource() == rotar3up) {
            if (rotar3 >= Rotar.getNumOfChars()) {
                rotar3 = 0;
            } else {
                rotar3++;
            }
            rotar3init.setText(Integer.toString(rotar3));
        } else if (e.getSource() == rotar3down) {
            if (rotar3 <= 0) {
                rotar3 = Rotar.getNumOfChars();
            } else {
                rotar3--;
            }
            rotar3init.setText(Integer.toString(rotar3));
        } else if (e.getSource() == rotar4up) {
            if (rotar4 >= Rotar.getNumOfChars()) {
                rotar4 = 0;
            } else {
                rotar4++;
            }
            rotar4init.setText(Integer.toString(rotar4));
        } else if (e.getSource() == rotar4down) {
            if (rotar4 <= 0) {
                rotar4 = Rotar.getNumOfChars();
            } else {
                rotar4--;
            }
            rotar4init.setText(Integer.toString(rotar4));
        } else if (e.getSource() == rotar5up) {
            if (rotar5 >= Rotar.getNumOfChars()) {
                rotar5 = 0;
            } else {
                rotar5++;
            }
            rotar5init.setText(Integer.toString(rotar5));
        } else if (e.getSource() == rotar5down) {
            if (rotar5 <= 0) {
                rotar5 = Rotar.getNumOfChars();
            } else {
                rotar5--;
            }
            rotar5init.setText(Integer.toString(rotar5));
        }
    }

    private void addRotar(int setting, int initial) {
        enigma.addSetting(setting, initial);
        rotarLabel();
    }

    private String cipher(String text) {
        if (enigma.getRotars().isEmpty()) {
            System.out.println("No settings to cipher");
            return "";
        } else if (text.equals("")) {
            return "";
        }
        return enigma.cipher(text);
    }

    private String settings() {
        String settings = "";
        for (Rotar r : enigma.getRotars()) {
            settings += r.getSettingNum() + " (" + r.getInitialPosition() + ")" + ", ";
        }
        return settings;
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