package ui;

import javax.swing.*;
import java.awt.*;

public final class Icons {

    private Icons() { }

    public static ImageIcon scaled(String path, int w, int h) {
        ImageIcon src = new ImageIcon(path);
        Image img = src.getImage();
        Image resized = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }
}
