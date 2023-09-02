package ui.tools;

import model.MyBook;
import ui.LibraryHome;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

import static ui.templates.ColorScheme.MAIN_BACKGROUND;
import static ui.LibraryHome.*;

// handles functionality listed in the toolbar
public abstract class LibraryTool {

    protected JButton button;
    protected LibraryHome home;

    // EFFECTS: constructs a library tool
    public LibraryTool(LibraryHome home, JComponent parent) {
        this.home = home;
        createContainer(parent);
        addListener();
    }

    // EFFECTS: default behavior of getBooks for all library tools, returns empty list
    public ArrayList<MyBook> getBooks() {
        return new ArrayList<>();
    }

    // EFFECTS: creates a container and adds a button and a separator to it
    protected void createContainer(JComponent parent) {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(MAIN_BACKGROUND);
        container.setBorder(new MatteBorder(0,0,2,0, MAIN_BACKGROUND));
        createButton(container);
        parent.add(container);
    }


    // EFFECTS: creates button to activate tool
    protected void createButton(JComponent parent) {
    }

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

}
