package ui;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener {
    Enigma enigma = new Enigma();
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
    private JButton cipherButton;
    private JButton quitButton;
    private JButton backButton;
    private JButton rotarButton1;
    private JButton rotarButton2;
    private JButton rotarButton3;
    private JButton rotarButton4;
    private JButton rotarButton5;
    private JButton resetButton;
    private JLabel rotars;

    public Gui() {
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
        int buttonWidth = 300;
        int buttonHeight = 300;
        Image resized = enigmaImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
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
        mainPanel.setLayout(null); // Ensure absolute positioning
    
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
    
        mainPanel.add(textInput);
        mainPanel.setComponentZOrder(textInput, 0);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void addMainButtons() {
        cipherButton = new JButton("Cipher");
        cipherButton.setBounds(390, 425, 80, 25);
        cipherButton.addActionListener(this);
        mainPanel.add(cipherButton);

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

        ImageIcon rotarIcon = new ImageIcon("./data/rotar.png");
        Image rotarImage = rotarIcon.getImage();
        int buttonWidth = 150;
        int buttonHeight = 230;
        Image resized = rotarImage.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedRotar = new ImageIcon(resized);

        rotarButton1 = new JButton();
        rotarButton1.setIcon(resizedRotar);
        rotarButton1.setBounds(140, 10, 150, 230);
        rotarButton1.setBorderPainted(false);
        rotarButton1.setContentAreaFilled(false);
        rotarButton1.setFocusPainted(false);
        rotarButton1.addActionListener(this);
        enigmaPanel.add(rotarButton1);

        one = new JLabel();
        one.setText("I");
        one.setBounds(210, 0, 40, 40);
        enigmaPanel.add(one);

        rotarButton2 = new JButton();
        rotarButton2.setIcon(resizedRotar);
        rotarButton2.setBounds(350, 10, 150, 230);
        rotarButton2.setBorderPainted(false);
        rotarButton2.setContentAreaFilled(false);
        rotarButton2.setFocusPainted(false);
        rotarButton2.addActionListener(this);
        enigmaPanel.add(rotarButton2);

        two = new JLabel();
        two.setText("II");
        two.setBounds(420, 0, 40, 40);
        enigmaPanel.add(two);

        rotarButton3 = new JButton();
        rotarButton3.setIcon(resizedRotar);
        rotarButton3.setBounds(550, 10, 150, 230);
        rotarButton3.setBorderPainted(false);
        rotarButton3.setContentAreaFilled(false);
        rotarButton3.setFocusPainted(false);
        rotarButton3.addActionListener(this);
        enigmaPanel.add(rotarButton3);

        three = new JLabel();
        three.setText("III");
        three.setBounds(620, 0, 40, 40);
        enigmaPanel.add(three);

        rotarButton4 = new JButton();
        rotarButton4.setIcon(resizedRotar);
        rotarButton4.setBounds(250, 235, 150, 230);
        rotarButton4.setBorderPainted(false);
        rotarButton4.setContentAreaFilled(false);
        rotarButton4.setFocusPainted(false);
        rotarButton4.addActionListener(this);
        enigmaPanel.add(rotarButton4);

        four = new JLabel();
        four.setText("IV");
        four.setBounds(320, 225, 40, 40);
        enigmaPanel.add(four);

        rotarButton5 = new JButton();
        rotarButton5.setIcon(resizedRotar);
        rotarButton5.setBounds(450, 235, 150, 230);
        rotarButton5.setBorderPainted(false);
        rotarButton5.setContentAreaFilled(false);
        rotarButton5.setFocusPainted(false);
        rotarButton5.addActionListener(this);
        enigmaPanel.add(rotarButton5);

        five = new JLabel();
        five.setText("V");
        five.setBounds(520, 225, 40, 40);
        enigmaPanel.add(five);
    }

    private void rotarLabel() {
        rotars.setText("Rotors: " + settings());
        enigmaPanel.revalidate();
        enigmaPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cipherButton) {
            String text = textInput.getText();
            cipher(text);
        } else if (e.getSource() == enigmaButton) {
            enigmaPanel();
        } else if (e.getSource() == backButton) {
            mainPanel.setVisible(true);
            enigmaPanel.setVisible(false);
        } else if (e.getSource() == rotarButton1) {
            enigma.addSetting(1, 0);
            rotarLabel();
        } else if (e.getSource() == rotarButton2) {
            enigma.addSetting(2, 0);
            rotarLabel();
        } else if (e.getSource() == rotarButton3) {
            enigma.addSetting(3, 0);
            rotarLabel();
        } else if (e.getSource() == rotarButton4) {
            enigma.addSetting(4, 0);
            rotarLabel();
        } else if (e.getSource() == rotarButton5) {
            enigma.addSetting(5, 0);
            rotarLabel();
        } else if (e.getSource() == resetButton) {
            enigma.getRotars().clear();
            rotarLabel();
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        }
    }

    private void cipher(String text) {
        if (enigma.getRotars().isEmpty()) {
            System.out.println("No settings to cipher");
            return;
        }
        System.out.println(enigma.cipher(text));
    }

    private String settings() {
        String settings = "";
        for (Rotar r : enigma.getRotars()) {
            settings += r.getSettingNum() + " (" + r.getInitialPosition() + ")" + ", ";
        }
        return settings;
    }

}