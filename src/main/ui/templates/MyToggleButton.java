package ui.templates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static ui.templates.ColorScheme.MAIN_BACKGROUND;

// Represents a customized icon button that's only visible when mouse over
public class MyToggleButton extends JButton {

    private final ImageIcon light;
    private final ImageIcon dark;
    private final JLabel label;

    // EFFECTS: constructs MyDeleteButton with default color scheme
    public MyToggleButton(String light, String dark) {
        this.dark = MyIcon.getIcon(dark, 17);
        this.light = MyIcon.getIcon(light, 17);
        setPreferredSize(new Dimension(23,23));
        setLayout(new BorderLayout());
        setBorder(null);
        setBorderPainted(false);
        setBackground(MAIN_BACKGROUND);
        label = new JLabel();
        label.setHorizontalAlignment(0);
        add(label);
        addHoverEffect();
    }

    // MODIFIES: this
    // EFFECTS: adds hover effect to the button
    private void addHoverEffect() {
        this.addMouseListener(new MouseAdapter() {

            // MODIFIES: this
            // EFFECTS: turns button to darker color when clicked
            @Override
            public void mousePressed(MouseEvent e) {
                label.setIcon(dark);
            }

            // MODIFIES: this
            // EFFECTS: turns button to lighter color when click event finished
            @Override
            public void mouseReleased(MouseEvent e) {
                label.setIcon(light);
            }

            // MODIFIES: this
            // EFFECTS: turns button to darker color when mouse over
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setIcon(light);
            }

            // MODIFIES: this
            // EFFECTS: removes icon (turns button invisible) when mouse leaves button area
            @Override
            public void mouseExited(MouseEvent e) {
                label.setIcon(null);
            }
        });
    }
}
