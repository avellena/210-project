package ui.quoteshelfdisplay;

import model.MyBook;
import model.Quote;
import ui.LibraryHome;

// Handles click events on QuoteShelf
public class QuoteShelfManager {
    private final QuoteShelf shelf;
    private Quote quote; //target quote to make edits on

    // EFFECTS: constructs a QuoteShelfManager
    public QuoteShelfManager(QuoteShelf shelf) {
        this.shelf = shelf;
    }

    // MODIFIES: this
    // EFFECTS: sets main panel to AddQuoteForm
    public void handleNewQuote(Quote quote) {
        this.quote = quote;
        LibraryHome home = shelf.getHome();
        AddQuoteForm addQuoteForm = new AddQuoteForm(home, new Quote(""), shelf.getBook());
        addQuoteForm.showContent();
        home.refreshMainPanel(addQuoteForm);
    }

    // MODIFIES: this
    // EFFECTS: creates QuotePopUpWindow to ask if author want to EDIT/DELETE the quote
    public void handleEditQuote(Quote quote) {
        this.quote = quote;
        new QuotePopUpWindow(this);

    }

    // MODIFIES: this
    // EFFECTS: sets main panel to EditBookForm
    public void editQuote() {
        LibraryHome home = shelf.getHome();
        EditQuoteForm editQuoteForm = new EditQuoteForm(home, quote, shelf.getBook());
        editQuoteForm.showContent();
        home.refreshMainPanel(editQuoteForm);
    }

    // MODIFIES: this
    // EFFECTS: removes quote from book and returns main panel to QuoteShelf
    public void deleteQuote() {
        MyBook book = shelf.getBook();
        LibraryHome home = shelf.getHome();
        book.removeQuote(quote);
        QuoteShelf newQuoteShelf = new QuoteShelf(home, book);
        newQuoteShelf.showContent();
        home.refreshMainPanel(newQuoteShelf);
    }
}
