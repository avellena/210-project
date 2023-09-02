package ui.templates;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static ui.templates.ColorScheme.TOOLBAR_BACKGROUND;
import static ui.templates.ColorScheme.CLICK_COLOR;

// Represents a customized TextField
public class MyTextField extends JTextField {

    // Constructs customized text field with default colorscheme
    public MyTextField() {

        setPreferredSize(new Dimension(300, 30));

        setFont(new Font("", Font.PLAIN, 14));
        setForeground(TOOLBAR_BACKGROUND);
        setCaretColor(CLICK_COLOR);
        setMargin(new Insets(0,20,0,0));

        setBorderColor(TOOLBAR_BACKGROUND);

    }

    // MODIFIES; this
    // EFFECTS: sets a compound border on text field
    public void setBorderColor(Color color) {
        Border lineBorder = new MatteBorder(2,2,2,2,color);
        Border emptyBorder = new EmptyBorder(0, 10, 0, 0);
        CompoundBorder border = new CompoundBorder(lineBorder, emptyBorder);
        setBorder(border);
    }

}
