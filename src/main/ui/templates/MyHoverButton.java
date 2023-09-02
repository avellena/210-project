package ui.templates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static ui.templates.ColorScheme.*;

// Represents a customized button with hover effects
public class MyHoverButton extends JButton {

    private Color defaultColor = TOOLBAR_BACKGROUND;
    private Color hoverColor = HOVER_COLOR;
    private Color clickColor = CLICK_COLOR;

    // EFFECTS: constructs MyHoverButton with default color scheme
    public MyHoverButton(String text) {
        super(text);
        setOpaque(true);
        setBorderPainted(false);
        setForeground(MAIN_BACKGROUND);
        setBackground(defaultColor);
        addHoverEffect();
    }

    // EFFECTS: alternative constructor, a hover button with customized hover/click color
    public MyHoverButton(String text, Color hoverColor, Color clickColor, Color defaultColor) {
        super(text);
        setOpaque(true);
        setBorderPainted(false);
        this.hoverColor = hoverColor;
        this.clickColor = clickColor;
        this.defaultColor = defaultColor;
        setForeground(MAIN_BACKGROUND);
        setBackground(defaultColor);
        addHoverEffect();
    }

    // MODIFIES: this
    // EFFECTS: adds hover effect to the button
    private void addHoverEffect() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            // MODIFIES: this
            // EFFECTS: sets background color to CLICK_COLOR when mouse pressed
            public void mousePressed(MouseEvent e) {
                setBackground(clickColor);
            }

            @Override
            // MODIFIES: this
            // EFFECTS: sets background color to HOVER_COLOR when mouse released
            public void mouseReleased(MouseEvent e) {
                setBackground(hoverColor);
            }

            @Override
            // MODIFIES: this
            // EFFECTS: sets background color to HOVER_COLOR when mouse entered button area
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
            }

            @Override
            // MODIFIES: this
            // EFFECTS: sets background color to default when mouse leaves button area
            public void mouseExited(MouseEvent e) {
                setBackground(defaultColor);
            }
        });
    }
}
