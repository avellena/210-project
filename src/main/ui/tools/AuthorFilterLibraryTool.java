package ui.tools;

import ui.LibraryHome;
import ui.searchformdisplay.SearchForm;
import ui.searchformdisplay.SearchFormAuthor;
import ui.templates.ToolButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <a target="_blank" href="https://icons8.com/icon/hc9G1IfYOEcA/writer-male">Author</a> icon by
 * <a target="_blank" href="https://icons8.com">Icons8</a>
 */

// Handles search by author functionality
public class AuthorFilterLibraryTool extends LibraryTool {

    // EFFECTS: constructs tool button and adds to toolbar
    public AuthorFilterLibraryTool(LibraryHome home, JComponent parent) {
        super(home, parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: creates customized tool button
    protected void createButton(JComponent parent) {
        button = new ToolButton("FILTER BY AUTHOR", "author.png");
        addToParent(parent);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds action listener to button
    protected void addListener() {
        button.addActionListener(new AuthorFilterClickHandler());
    }

    // handles click on author filter tool
    private class AuthorFilterClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: sets main panel to SearchHomeAuthor
        public void actionPerformed(ActionEvent e) {
            SearchForm searchFormAuthor = new SearchFormAuthor(home);
            home.refreshMainPanel(searchFormAuthor);
        }
    }
}
