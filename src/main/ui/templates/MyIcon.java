package ui.templates;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// a helper class that reads image from data folder and produces ImageIcon of desired size
public abstract class MyIcon {

    private static final String IMG_PATH = "./data/img";

    // MODIFIES: this
    // EFFECTS: gets image file from ./data/img and returns ImageIcon with given size
    public static ImageIcon getIcon(String fileName, int size) {
        Image scaledImage = null;

        try {
            File f = new File(IMG_PATH, fileName);
            BufferedImage image = ImageIO.read(f);
            scaledImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.out.println("No such file in ./data/img !");
            e.printStackTrace();
        }

        return new ImageIcon(scaledImage);
    }


}
