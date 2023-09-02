package ui.helperwindows;

import ui.templates.PopUpWindow;
import ui.editor.OneLineField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a welcome window that allows use to load existing library or start a new one
public class WelcomeWindow extends PopUpWindow {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 200;
    protected OneLineField textField;

    // EFFECTS: constructs a WelcomeWindow for loading library data
    public WelcomeWindow() {
        super();
        addTextField();
        addPrompt();
        addButtons();
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
    }

    // MODIFIES: this
    // EFFECTS: adds prompt button to container
    protected void addPrompt() {
        super.addPrompt();
        promptPanel.setText("Type in the name of the library to load:");
    }

    // MODIFIES: this
    // EFFECTS: adds a textField for use to input the library they want to load
    private void addTextField() {
        textField = new OneLineField("Library Name");
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbl.setConstraints(textField, gbc);
        container.add(textField);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds the "LOAD LIBRARY" button to JFrame
    protected void addButtons() {
        JButton load = new PopUpButton("LOAD LIBRARY");
        load.addActionListener(new LoadClickHandler());
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(load, gbc);
        container.add(load);
    }

    // handles load library
    private class LoadClickHandler implements ActionListener {

        @Override
        // REQUIRES: user input cannot be empty
        // MODIFIES: this
        // EFFECTS: passes user input to LoadLibraryWindow
        public void actionPerformed(ActionEvent e) {
            new LoadLibraryWindow(textField.getText());
            dispose();
        }
    }
}
