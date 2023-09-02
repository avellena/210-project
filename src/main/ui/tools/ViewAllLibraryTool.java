package ui.tools;

import model.MyBook;
import ui.LibraryHome;
import ui.shelfdisplay.Shelf;
import ui.templates.ToolButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * <a target="_blank" href="https://icons8.com/icon/123401/view-all">View All</a> icon by
 * <a target="_blank" href="https://icons8.com">Icons8</a>
 */

// Handles command to display all books in the library
public class ViewAllLibraryTool extends LibraryTool {

    // EFFECTS: constructs tool button and adds to toolbar
    public ViewAllLibraryTool(LibraryHome home, JComponent parent) {
        super(home, parent);
    }


    @Override
    public ArrayList<MyBook> getBooks() {
        return home.getLibrary().getBooks();
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new ToolButton("VIEW ALL BOOKS", "view_all.png");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ViewAllClickHandler());
    }

    private class ViewAllClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Shelf newShelf = new Shelf(home);
            newShelf.loadBooks(getBooks());
            home.refreshMainPanel(newShelf);
        }
    }
}
