package ui.searchformdisplay;

import model.Library;
import model.MyBook;
import ui.LibraryHome;

import java.util.ArrayList;

// a search form to search for books by author name
public class SearchFormAuthor extends SearchForm {

    // EFFECTS: constructs SearchFormAuthor with caption
    public SearchFormAuthor(LibraryHome home) {
        super(home);
        titleLabel.setText("SEARCH BY AUTHOR");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds header
    protected void addHeader() {
        addHeader("    Filter by author tag");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: IF genre found in library, displays all books under that author;
    //          OTHERWISE displays a author not found message.
    protected void search(String text) {
        Library library = getHome().getLibrary();
        ArrayList<MyBook> returned = library.findBooksInTag(text, library.getAuthors());
        if (returned != null) {
            displaySearchResult(returned);
        } else {
            addMessage("There is no such author in your library!");
        }
    }
}
