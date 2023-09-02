package ui.tools;

import ui.LibraryHome;
import ui.searchformdisplay.SearchForm;
import ui.templates.ToolButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// handles search book by title functionality
public class SearchBookLibraryTool extends LibraryTool {
    // EFFECTS: constructs tool button and adds to toolbar
    public SearchBookLibraryTool(LibraryHome home, JComponent parent) {
        super(home, parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: creates customized tool button
    protected void createButton(JComponent parent) {
        button = new ToolButton("SEARCH A BOOK", "search_book.png");
        addToParent(parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds action listener to button
    protected void addListener() {
        button.addActionListener(new SearchBookLibraryTool.SearchBookClickHandler());
    }

    // handles click on search book tool
    private class SearchBookClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: sets main panel to SearchForm
        public void actionPerformed(ActionEvent e) {
            SearchForm searchForm = new SearchForm(home);
            home.refreshMainPanel(searchForm);
        }
    }


}
