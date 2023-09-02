package ui.searchformdisplay;

import model.Library;
import model.MyBook;
import ui.LibraryHome;

import java.util.ArrayList;

// Represents a search form for searching books by genre
public class SearchFormGenre extends SearchForm {

    // EFFECTS: constructs a SearchFormGenre with caption
    public SearchFormGenre(LibraryHome home) {
        super(home);
        titleLabel.setText("SEARCH BY GENRE");
    }

    @Override
    // MODIFIES; this
    // EFFECTS: adds header to search form
    protected void addHeader() {
        addHeader("    Filter by genre tag");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: IF genre found in library, displays all books under that genre;
    //          OTHERWISE displays a genre not found message.
    protected void search(String text) {
        Library library = getHome().getLibrary();
        ArrayList<MyBook> returned = library.findBooksInTag(text, library.getGenres());
        if (returned != null) {
            displaySearchResult(returned);
        } else {
            addMessage("There is no such genre in your library!");
        }
    }
}
