package ui.editor;

import model.MyBook;
import ui.templates.MyTextField;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

import static ui.templates.ColorScheme.*;

// Represents a JPanel template with a field name label, a text field and an invisible message panel
public class OneLineField extends JPanel {
    protected String fieldName;
    protected MessagePanel messagePanel;
    protected MyTextField textField;
    protected JButton fieldNameButton;
    private final GridBagConstraints gbc;
    private final GridBagLayout gbl;
    private static final Dimension FIELD_DIMENSION = new Dimension(400,50);

    // EFFECTS: constructs a OneLineField
    public OneLineField(String fieldName) {
        this.fieldName = fieldName;
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        setLayout(gbl);
        setPreferredSize(FIELD_DIMENSION);
        addComponents();
    }

    // MODIFIES: this
    // EFFECTS: initializes GridBagConstraints and adds components to OneLineField
    protected void addComponents() {
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        addFieldName();
        addTextField();
        addMessage();
    }

    // MODIFIES: this
    // EFFECTS: initializes message panel
    protected void addMessage() {
        messagePanel = new MessagePanel("");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbl.setConstraints(messagePanel, gbc);
        add(messagePanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes field name panel
    protected void addFieldName() {
        fieldNameButton = new JButton(this.fieldName);
        fieldNameButton.setOpaque(true);
        fieldNameButton.setBorderPainted(false);
        fieldNameButton.setBackground(ENTRY_BACKGROUND);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbl.setConstraints(fieldNameButton, gbc);
        fieldNameButton.setPreferredSize(new Dimension(100, 30));
        fieldNameButton.setFont(new Font("", Font.PLAIN, 15));
        fieldNameButton.setForeground(TOOLBAR_BACKGROUND);
        add(fieldNameButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes text field
    protected void addTextField() {
        textField = new MyTextField();
        textField.setBorderColor(ENTRY_BACKGROUND);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbl.setConstraints(textField, gbc);
        add(textField);
    }

    // MODIFIES: this
    // EFFECTS: sets message panel to visible
    public void showMessage(String text) {
        messagePanel.setText(text);
    }

    // MODIFIES: this
    // EFFECTS: sets message panel to invisible
    public void hideMessage() {
        messagePanel.setText("");
    }

    // getters
    public JTextField getTextField() {
        return textField;
    }

    public String getText() {
        return textField.getText();
    }


    // template for message panel
    private static class MessagePanel extends JButton {

        // EFFECTS: construct an invisible message panel with string
        public MessagePanel(String message) {
            super(message);
            setOpaque(true);
            setBorderPainted(false);
            setBorder(new MatteBorder(5,0,0,0, MAIN_BACKGROUND));
            setBackground(MAIN_BACKGROUND);
            setFont(new Font("", Font.PLAIN, 11));
            setForeground(CLICK_COLOR);
            setPreferredSize(new Dimension(400, 20));
        }

    }



}
