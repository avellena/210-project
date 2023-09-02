package ui.quoteshelfdisplay;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static ui.templates.ColorScheme.ENTRY_BACKGROUND;
import static ui.templates.ColorScheme.TOOLBAR_BACKGROUND;

// A customized JPanel with a label and a text area
public class ContentField extends JPanel {
    private JTextArea textArea;
    private final String text;

    // EFFECTS: constructs a ContentField with text in name label
    public ContentField(String text) {
        this.text = text;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 180));
        addNameLabel();
        addTextArea();

    }

    // MODIFIES: this
    // EFFECTS: adds name label
    private void addNameLabel() {
        JLabel nameLabel = new JLabel(text);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(ENTRY_BACKGROUND);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setPreferredSize(new Dimension(400, 30));
        nameLabel.setFont(new Font("", Font.PLAIN, 15));
        nameLabel.setForeground(TOOLBAR_BACKGROUND);
        add(nameLabel, BorderLayout.NORTH);
    }

    // MODIFIES: THIS
    // EFFECTS: adds text area
    private void addTextArea() {
        textArea = new JTextArea();

        textArea.setFont(new Font("", Font.PLAIN, 16));
        textArea.setForeground(TOOLBAR_BACKGROUND);
        Border lineBorder = new MatteBorder(2,2,2,2, ENTRY_BACKGROUND);
        Border emptyBorder = new EmptyBorder(6, 10, 6, 10);
        CompoundBorder border = new CompoundBorder(lineBorder, emptyBorder);
        textArea.setBorder(border);
        textArea.setCaretColor(TOOLBAR_BACKGROUND);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        add(textArea);
    }

    // getters
    public JTextArea getTextArea() {
        return textArea;
    }

    public String getText() {
        return textArea.getText();
    }
}
