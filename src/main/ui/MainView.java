package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainView {

    private final JPanel panel;
    private final JButton enigmaButton;
    private final JButton quitButton;

    private JButton gregorButton; // optional
    private JLabel gregorLabel;   // optional

    private final JTextArea input;
    private final JTextArea output;

    public MainView(GuiApp controller) {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        enigmaButton = new JButton();
        enigmaButton.setIcon(Icons.scaled("./img/enigma.png", 300, 300));
        enigmaButton.setBounds(50, 150, 300, 300);
        enigmaButton.setBorderPainted(false);
        enigmaButton.setContentAreaFilled(false);
        enigmaButton.setFocusPainted(false);
        enigmaButton.addActionListener(controller);
        panel.add(enigmaButton);
        quitButton = new JButton("Quit");
        quitButton.setBounds(0, 0, 80, 25);
        quitButton.addActionListener(controller);
        panel.add(quitButton);

        JLabel notebook = new JLabel(Icons.scaled("./img/notebook.png", 400, 400));
        notebook.setBounds(350, 100, 400, 400);
        panel.add(notebook);

        input = new JTextArea(10, 10);
        input.setLineWrap(true);
        input.setWrapStyleWord(true);
        input.setBounds(390, 200, 150, 200);
        input.setOpaque(false);
        input.setBackground(new Color(0, 0, 0, 0));
        input.setFont(new Font("Arial", Font.PLAIN, 12));
        input.setCaretColor(Color.BLACK);
        input.setForeground(Color.BLACK);
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.onInputChanged();
            }
        });
        panel.add(input);
        panel.setComponentZOrder(input, 0);
        output = new JTextArea();
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setBounds(560, 200, 150, 300);
        output.setOpaque(false);
        output.setEditable(false);
        output.setFont(new Font("Arial", Font.PLAIN, 12));
        output.setForeground(Color.BLACK);
        panel.add(output);
        panel.setComponentZOrder(output, 0);

        panel.revalidate();
        panel.repaint();
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getEnigmaButton() {
        return enigmaButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

//    public JButton getGregorButton() {
//        return gregorButton;
//    }

    public String getInputText() {
        return input.getText();
    }

    public void setOutputText(String s) {
        output.setText(s);
        panel.revalidate();
        panel.repaint();
    }

    public void showGregor(ImageIcon icon) {
        if (gregorLabel == null) {
            gregorLabel = new JLabel();
            gregorLabel.setBounds(430, 0, 200, 200);
            panel.add(gregorLabel);
        }
        gregorLabel.setIcon(icon);
        panel.revalidate();
        panel.repaint();
    }

    public void hideGregor() {
        if (gregorLabel != null) {
            panel.remove(gregorLabel);
            gregorLabel = null;
            panel.revalidate();
            panel.repaint();
        }
    }
}
