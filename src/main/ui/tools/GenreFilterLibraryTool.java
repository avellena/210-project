package ui.tools;

import ui.LibraryHome;
import ui.searchformdisplay.SearchForm;
import ui.searchformdisplay.SearchFormGenre;
import ui.templates.ToolButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Quit icon credit:
 * <a target="_blank" href="https://icons8.com/icon/26218/login">Quit</a> icon by
 * <a target="_blank" href="https://icons8.com">Icons8</a>
 */

// Handles search by genre functionality
public class GenreFilterLibraryTool extends LibraryTool {

    // EFFECTS: constructs tool button and adds to toolbar
    public GenreFilterLibraryTool(LibraryHome home, JComponent parent) {
        super(home, parent);
    }


    @Override
    // MODIFIES: this
    // EFFECTS: creates customized tool button
    protected void createButton(JComponent parent) {
        button = new ToolButton("FILTER BY GENRE", "genre.png");
        addToParent(parent);
    }


    @Override
    // MODIFIES: this
    // EFFECTS: adds action listener to button
    protected void addListener() {
        button.addActionListener(new GenreFilterClickHandler());
    }

    // handles click on genre filter tool
    private class GenreFilterClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: sets main panel to SearchFormGenre
        public void actionPerformed(ActionEvent e) {
            SearchForm searchFormGenre = new SearchFormGenre(home);
            home.refreshMainPanel(searchFormGenre);
        }
    }
}
