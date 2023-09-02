package ui.helperwindows;

import ui.templates.PopUpWindow;
import ui.tools.CloseAppLibraryTool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a QuitPopUpWindow separated from main frame that asks if user wants to save session
public class QuitPopUpWindow extends PopUpWindow {
    private final CloseAppLibraryTool tool;

    //EFFECTS: constructs QuitPopUpWindow for CloseAppLibraryTool
    public QuitPopUpWindow(CloseAppLibraryTool tool) {
        super();
        this.tool = tool;
        addPrompt();
        addButtons();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds prompt button to container
    protected void addPrompt() {
        super.addPrompt();
        promptPanel.setText("Save this session?");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds save and donot save buttons to container
    protected void addButtons() {
        JButton save = new PopUpButton("SAVE");
        JButton notSave = new PopUpButton("DO NOT SAVE");
        save.addActionListener(new QuitPopUpWindow.SaveClickHandler());
        notSave.addActionListener(new QuitPopUpWindow.DoNotSaveClickHandler());
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(save, gbc);
        container.add(save);

        gbc.gridx = 1;
        gbl.setConstraints(notSave, gbc);
        container.add(notSave);
    }

    // handles save and quit
    private class SaveClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: save the session and quit using CloseAppTool
        public void actionPerformed(ActionEvent e) {
            tool.saveAndQuit();
            dispose();
        }
    }

    // handles quit and do not save
    private class DoNotSaveClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: quit the application using CloseAppTool
        public void actionPerformed(ActionEvent t) {
            tool.quit();
            dispose();
        }
    }
}
