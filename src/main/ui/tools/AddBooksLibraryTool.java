package ui.tools;

import model.MyBook;
import ui.LibraryHome;
import ui.editor.AddBookForm;
import ui.templates.ToolButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Book icon credit:
 * <a target="_blank" href="https://icons8.com/icon/41414/book">Book</a> icon
 * by <a target="_blank" href="https://icons8.com">Icons8</a>
 * */

// Handles add book functionality
public class AddBooksLibraryTool extends LibraryTool {
    // EFFECTS: constructs tool button and adds to toolbar
    public AddBooksLibraryTool(LibraryHome home, JComponent parent) {
        super(home, parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: creates customized tool button
    protected void createButton(JComponent parent) {
        button = new ToolButton("LOG A NEW BOOK", "book.png");
        addToParent(parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds action listener to button
    protected void addListener() {
        button.addActionListener(new AddBookClickHandler());
    }

    // handles click on author filter tool
    private class AddBookClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: sets main panel to AddBookForm
        public void actionPerformed(ActionEvent e) {
            AddBookForm addBookForm = new AddBookForm(home, new MyBook(""));
            home.refreshMainPanel(addBookForm);
        }
    }
}
