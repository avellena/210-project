package ui.templates;

import ui.LibraryHome;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import static ui.templates.ColorScheme.*;

// Represents a custom button with hover effects
public class ToolButton extends MyHoverButton {
    private final String icon;

    // EFFECTS: constructs a ToolButton with given name and icon file name
    public ToolButton(String text, String icon) {
        super(text);
        this.icon = icon;
        setLayout(new BorderLayout());
        customizeText();
        setPreferredSize(new Dimension(0, LibraryHome.HEIGHT / 10));
        addIcon();
    }

    // MODIFIES: this
    // EFFECTS: adds icon to button
    public void addIcon() {
        JLabel label = new JLabel();
        ImageIcon labelIcon = MyIcon.getIcon(icon, 25);
        label.setIcon(labelIcon);
        label.setBorder(new EmptyBorder(0,7,0,0));
        label.setPreferredSize(new Dimension(30,30));
        add(label);
    }

    // MODIFIES: this
    // EFFECTS: customize text on the button
    private void customizeText() {
        setFont(new Font("Futura", Font.PLAIN, 14));
        setForeground(MAIN_BACKGROUND);
    }


}
