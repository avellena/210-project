package ui.shelfdisplay;

import model.MyBook;
import ui.editor.EditBookForm;
import ui.LibraryHome;
import ui.quoteshelfdisplay.QuoteShelf;

// Handles click events on Shelf
public class ShelfManager {
    private final LibraryHome home;
    private MyBook book;

    // EFFECTS: constructs a shelfManager
    public ShelfManager(LibraryHome home) {
        this.home = home;
    }

    // EFFECTS: handles click on quote button by setting main panel to QuoteShelf
    public void handleQuoteClick(MyBook book) {
        QuoteShelf quoteShelf = new QuoteShelf(home, book);
        quoteShelf.showContent();
        home.refreshMainPanel(quoteShelf);
    }

    // EFFECTS: handles click on edit button by setting main panel to editBookForm
    public void handleEditClick(MyBook book) {
        EditBookForm editBookForm = new EditBookForm(home, book);
        home.refreshMainPanel(editBookForm);
    }

    // MODIFIES: this
    // EFFECTS: handles click on delete button by showing a DeletePopUpWindow
    public void handleDeleteClick(MyBook book) {
        this.book = book;
        new DeletePopUpWindow(this);
    }

    // EFFECTS: delete the book from th library and sets main panel to Shelf
    public void deleteBook() {
        home.getLibrary().removeBook(book);
        Shelf newShelf = new Shelf(home);
        newShelf.loadBooks(home.getLibrary().getBooks());
        home.refreshMainPanel(newShelf);
    }

    // getter:
    public MyBook getBook() {
        return book;
    }
}
