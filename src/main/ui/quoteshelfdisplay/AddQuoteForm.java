package ui.quoteshelfdisplay;

import model.MyBook;
import model.Quote;
import ui.templates.Form;
import ui.LibraryHome;
import ui.editor.OneLineField;

import javax.swing.border.MatteBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.templates.ColorScheme.*;

// A form for user to fill out and add a new quote. Has a coupling QuoteShelfManager
public class AddQuoteForm extends Form {

    protected Quote quote;
    protected MyBook book;
    private final QuoteFormManager manager;
    protected ContentField contentField;
    protected OneLineField pageNumField;

    // EFFECTS: constructs an AddQuoteForm with a coupling QuoteFormManager
    public AddQuoteForm(LibraryHome home, Quote quote, MyBook book) {
        super(home);
        this.quote = quote;
        this.book = book;
        manager = new QuoteFormManager(this);
        addFields();
        addHeader();
    }

    // MODIFIES: this
    // EFFECTS: adds header description to AddQuoteForm
    protected void addHeader() {
        addHeader("    Adding a new quote to \"" + book.getTitle() + "\"");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: initializes actionListeners with the private handler classes in AddQuoteForm
    protected void initializeListeners() {
        saveClickHandler = new SaveClickHandler();
        cancelClickHandler = new CancelClickHandler();
    }

    // MODIFIES: this
    // EFFECTS: adds content and pageNumber text fields to container
    protected void addFields() {
        initializeContentField();
        initializePageNumField();
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbl.setConstraints(contentField, gbc);
        container.add(contentField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbl.setConstraints(pageNumField, gbc);
        container.add(pageNumField);
    }

    // MODIFIES: this
    // EFFECTS: initializes the content field
    private void initializeContentField() {
        contentField = new ContentField("CONTENT");
        contentField.setBorder(new MatteBorder(0,0,5,0, MAIN_BACKGROUND));
    }

    // MODIFIES: this
    // EFFECTS: initializes the pageNumField as a OneLineField with label "Page"
    private void initializePageNumField() {
        pageNumField = new OneLineField("Page");
    }


    // Handles click on saveButton by passing the command to QuoteShelfManager
    private class SaveClickHandler implements ActionListener {

        @Override
        // EFFECTS: passing the command to add a new quote to QuoteShelfManager
        public void actionPerformed(ActionEvent e) {
            manager.handleSaveClick();
        }
    }

    // Handles click on cancelButton by passing the command to QuoteShelfManager
    private class CancelClickHandler implements ActionListener {

        @Override
        // EFFECTS: passing the command to cancel adding a quote to QuoteShelfManager
        public void actionPerformed(ActionEvent e) {
            manager.handleCancelClick();
        }
    }


    // getters
    public MyBook getBook() {
        return book;
    }

    public Quote getQuote() {
        return quote;
    }

    public ContentField getContentField() {
        return contentField;
    }

    public OneLineField getPageNumField() {
        return pageNumField;
    }

}
