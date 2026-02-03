package ui;

import javax.swing.*;
import java.awt.*;

public class EnigmaView {

    private final JPanel panel;

    private final JButton backButton;
    private final JButton resetButton;
    private final JButton saveButton;
    private final JButton loadButton;

    private final JLabel rotorsLabel;

    private final JButton[] rotorButtons = new JButton[6]; // 1..5
    private final JButton[] upButtons = new JButton[6];
    private final JButton[] downButtons = new JButton[6];
    private final JLabel[] rotorValueLabels = new JLabel[6];

    public EnigmaView(GuiApp controller) {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        backButton = new JButton("Back");
        backButton.setBounds(0, 0, 80, 25);
        backButton.addActionListener(controller);
        panel.add(backButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(0, 500, 80, 25);
        resetButton.addActionListener(controller);
        panel.add(resetButton);

        saveButton = new JButton("Save");
        saveButton.setBounds(0, 450, 80, 25);
        saveButton.addActionListener(controller);
        panel.add(saveButton);

        loadButton = new JButton("Load");
        loadButton.setBounds(0, 475, 80, 25);
        loadButton.addActionListener(controller);
        panel.add(loadButton);

        rotorsLabel = new JLabel("Rotors: ");
        rotorsLabel.setBounds(100, 500, 800, 25);
        panel.add(rotorsLabel);

        buildRotorUI(controller);
        buildRomanLabels();

        panel.revalidate();
        panel.repaint();
    }

    private void buildRotorUI(GuiApp controller) {
        ImageIcon rotorIcon = Icons.scaled("./img/rotor.png", 150, 230);
        ImageIcon upIcon = Icons.scaled("./img/up.png", 50, 50);
        ImageIcon downIcon = Icons.scaled("./img/down.png", 50, 50);

        // Rotor button bounds
        addRotorButton(1, rotorIcon, 140, 10, controller);
        addRotorButton(2, rotorIcon, 370, 10, controller);
        addRotorButton(3, rotorIcon, 600, 10, controller);
        addRotorButton(4, rotorIcon, 240, 235, controller);
        addRotorButton(5, rotorIcon, 470, 235, controller);

        // Up buttons bounds
        addUpButton(1, upIcon, 90, 50, controller);
        addUpButton(2, upIcon, 320, 50, controller);
        addUpButton(3, upIcon, 550, 50, controller);
        addUpButton(4, upIcon, 190, 275, controller);
        addUpButton(5, upIcon, 420, 275, controller);

        // Down buttons bounds
        addDownButton(1, downIcon, 90, 150, controller);
        addDownButton(2, downIcon, 320, 150, controller);
        addDownButton(3, downIcon, 550, 150, controller);
        addDownButton(4, downIcon, 190, 375, controller);
        addDownButton(5, downIcon, 420, 375, controller);

        // Rotor value labels bounds
        addRotorValueLabel(1, 105, 110);
        addRotorValueLabel(2, 335, 110);
        addRotorValueLabel(3, 565, 110);
        addRotorValueLabel(4, 205, 335);
        addRotorValueLabel(5, 435, 335);
    }

    private void addRotorButton(int idx, ImageIcon icon, int x, int y, GuiApp controller) {
        JButton b = new JButton();
        b.setIcon(icon);
        b.setBounds(x, y, 150, 230);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.addActionListener(controller);
        rotorButtons[idx] = b;
        panel.add(b);
    }

    private void addUpButton(int idx, ImageIcon icon, int x, int y, GuiApp controller) {
        JButton b = new JButton();
        b.setIcon(icon);
        b.setBounds(x, y, 50, 50);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.addActionListener(controller);
        upButtons[idx] = b;
        panel.add(b);
    }

    private void addDownButton(int idx, ImageIcon icon, int x, int y, GuiApp controller) {
        JButton b = new JButton();
        b.setIcon(icon);
        b.setBounds(x, y, 50, 50);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.addActionListener(controller);
        downButtons[idx] = b;
        panel.add(b);
    }

    private void addRotorValueLabel(int idx, int x, int y) {
        JLabel l = new JLabel("0");
        l.setBounds(x, y, 60, 30);
        l.setFont(new Font("Arial", Font.PLAIN, 35));
        rotorValueLabels[idx] = l;
        panel.add(l);
    }

    private void buildRomanLabels() {
        addRoman("I", 210, 0);
        addRoman("II", 440, 0);
        addRoman("III", 670, 0);
        addRoman("IV", 310, 225);
        addRoman("V", 540, 225);
    }

    private void addRoman(String s, int x, int y) {
        JLabel l = new JLabel(s);
        l.setBounds(x, y, 40, 40);
        l.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(l);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getRotorButton(int idx) {
        return rotorButtons[idx];
    }

    public JButton getUpButton(int idx) {
        return upButtons[idx];
    }

    public JButton getDownButton(int idx) {
        return downButtons[idx];
    }

    public void setRotorValue(int idx, int value) {
        rotorValueLabels[idx].setText(Integer.toString(value));
        panel.revalidate();
        panel.repaint();
    }

    public void setRotorsLabel(String text) {
        rotorsLabel.setText(text);
        panel.revalidate();
        panel.repaint();
    }
}
