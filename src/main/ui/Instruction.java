package ui;

import ui.templates.MainPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static ui.templates.ColorScheme.TOOLBAR_BACKGROUND;

// Represents the instruction page after loading the library
public class Instruction extends MainPanel {
    private JPanel container;

    // EFFECTS: constructs Instruction main panel
    public Instruction(LibraryHome home) {
        super(home);
        setLayout(new BorderLayout());
        addContainer();
    }

    // MODIFIES: this
    // EFFECTS: adds content container to main panel
    private void addContainer() {
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(true);
        container.setBackground(backgroundColor);
        container.setBorder(new MatteBorder(30,30,30,30,backgroundColor));

        Font titleFont = new Font("Futura", Font.BOLD + Font.ITALIC, 20);

        String text = "Please use the side bar to navigate the library and happy grading!";
        Font textFont = new Font("", Font.ITALIC, 17);
        addLabel("WELCOME :)", titleFont);
        addLabel(text, textFont);
        add(container);
    }

    // MODIFIES: this
    // EFFECTS: adds a text label with given text and font
    private void addLabel(String text, Font font) {
        JLabel label = new JLabel();
        label.setText(text);
        label.setForeground(TOOLBAR_BACKGROUND);
        label.setFont(font);
        label.setBorder(new MatteBorder(0,0,6,0,backgroundColor));
        container.add(label);
    }

}
