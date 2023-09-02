package ui.quoteshelfdisplay;

import model.MyBook;
import model.Quote;
import ui.LibraryHome;

// A form for user to fill out and edit an existing quote
public class EditQuoteForm extends AddQuoteForm {

    // EFFECTS: constructs EditBookForm for the quote
    public EditQuoteForm(LibraryHome home, Quote quote, MyBook book) {
        super(home, quote, book);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Adds header description of the book being edited
    protected void addHeader() {
        addHeader("    Editing quote from \"" + book.getTitle() + "\"");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds content and pageNumber buttons, each text field has original info as placeholders
    public void addFields() {
        super.addFields();

        contentField.getTextArea().setText(getQuote().getContent());
        pageNumField.getTextField().setText(Integer.toString(getQuote().getPageNum()));

    }
}
