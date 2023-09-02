package ui.tools;

import model.Event;
import model.EventLog;
import model.Library;
import ui.LibraryHome;
import ui.helperwindows.QuitPopUpWindow;
import ui.templates.ToolButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Quit icon credit:
 * <a target="_blank" href="https://icons8.com/icon/26211/login">Quit</a> icon by
 * <a target="_blank" href="https://icons8.com">Icons8</a>
 **/

// Handles close app functionality
public class CloseAppLibraryTool extends LibraryTool {

    // EFFECTS: constructs tool button and adds to toolbar
    public CloseAppLibraryTool(LibraryHome home, JComponent parent) {
        super(home, parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: creates customized tool button
    protected void createButton(JComponent parent) {
        button = new ToolButton("QUIT", "quit.png");
        addToParent(parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds action listener to button
    protected void addListener() {
        button.addActionListener(new CloseAppClickHandler());
    }

    // handles click on click app tool
    private class CloseAppClickHandler implements ActionListener {

        @Override
        // EFFECTS: creates QuitPopUpWindow
        public void actionPerformed(ActionEvent e) {
            new QuitPopUpWindow(CloseAppLibraryTool.this);
        }
    }

    // MODIFIES: this
    // EFFECTS: quit the app and save session; prints EventLog to console
    public void saveAndQuit() {
        Library activeLibrary = home.getLibrary();
        home.getLibraryFile().writeLibrary(activeLibrary);
        home.dispose();
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.getDate() + ": " + e.getDescription());
        }
    }

    // MODIFIES: this
    // EFFECTS: quit the app without saving; prints EventLog to console
    public void quit() {
        home.dispose();
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.getDate() + ": " + e.getDescription());
        }
    }
}
