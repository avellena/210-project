package ui.helperwindows;

import ui.LibraryHome;
import ui.templates.PopUpWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// A PopUpWindow that confirms with user if user wants to load library
public class LoadLibraryWindow extends PopUpWindow {
    private static final String LIBRARY_PATH = "./data";
    private final String libraryName;
    private final boolean hasLibrary;

    // EFFECTS: constructs LoadLibraryWindow with given libraryName;
    //          sets hasLibrary to true if library exists, false otherwise
    public LoadLibraryWindow(String name) {
        super();
        libraryName = name;
        File file = new File(LIBRARY_PATH, libraryName + ".json");
        hasLibrary = file.exists();
        addPrompt();
        addButtons();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: initializes prompt panel according to hasLibrary:
    //        - IF hasLibrary, asks if user wants to load existing library;
    //        - OTHERWISE, asks if user wants to create new library with given name.
    protected void addPrompt() {
        super.addPrompt();
        String promptText;
        if (hasLibrary) {
            promptText = "Load library \"" + libraryName + "\" ?";
        } else {
            promptText = "Create new library \"" + libraryName + "\" ?";
        }

        promptPanel.setText(promptText);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: initializes go button and cancel button
    protected void addButtons() {
        JButton goButton = new PopUpButton("LET'S GO!");
        JButton cancelButton = new PopUpButton("CANCEL");
        goButton.addActionListener(new GoClickHandler());
        cancelButton.addActionListener(new CancelClickHandler());
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(goButton, gbc);
        container.add(goButton);

        gbc.gridx = 1;
        gbl.setConstraints(cancelButton, gbc);
        container.add(cancelButton);
    }

    // Handles click on go button
    private class GoClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: dispose the frame and creates new LibraryHome with confirmed libraryName
        public void actionPerformed(ActionEvent e) {
            new LibraryHome(libraryName);
            dispose();
        }
    }

    // Handles click on cancel button
    private class CancelClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: dispose the frame and go back to WelcomeWindow
        public void actionPerformed(ActionEvent e) {
            new WelcomeWindow();
            dispose();
        }
    }
}
