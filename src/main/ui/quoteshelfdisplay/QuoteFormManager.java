package ui.quoteshelfdisplay;

import model.MyBook;
import model.Quote;
import ui.LibraryHome;

// Handles save/cancel click in AddQuoteForm
public class QuoteFormManager {

    private final AddQuoteForm form;

    // EFFECTS: constructs a QuoteFormManager
    public QuoteFormManager(AddQuoteForm form) {
        this.form = form;
    }

    // REQUIRES: pageNumField contains integer string
    // MODIFIES: this
    // EFFECTS: removes the old quote entry from book and adds new quote entry
    //          with information in text fields, returns to updated QuoteShelf
    public void handleSaveClick() {
        LibraryHome home = form.getHome();
        MyBook book = form.getBook();
        Quote quote = form.getQuote();
        book.removeQuote(quote);
        quote = new Quote(form.getContentField().getText());
        quote.setPageNum(Integer.parseInt(form.getPageNumField().getText()));
        book.addQuote(quote);
        QuoteShelf newShelf = new QuoteShelf(home, book);
        home.refreshMainPanel(newShelf);
    }

    // MODIFIES: this
    // EFFECTS: returns to QuoteShelf
    public void handleCancelClick() {
        LibraryHome home = form.getHome();
        QuoteShelf newShelf = new QuoteShelf(home, form.getBook());
        home.refreshMainPanel(newShelf);
    }
}
