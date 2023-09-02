package ui.editor;

import model.MyBook;
import ui.templates.Form;
import ui.LibraryHome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A form for user to fill out and log a new book. Has a coupling BookFormManager that handles saving and canceling.
public class AddBookForm extends Form {

    protected MyBook book;
    private final BookFormManager manager;
    protected OneLineField titleField;
    protected OneLineField authorField;
    protected OneLineField genreField;
    protected OneLineField dateField;
    protected OneLineField ratingField;

    // EFFECTS: constructs a form with a coupling BookFormManager
    public AddBookForm(LibraryHome home, MyBook book) {
        super(home);
        this.book = book;
        manager = new BookFormManager(this);
        addFields();
        addHeader();
    }

    // MODIFIES: this
    // EFFECTS: adds header description to AddBookForm
    protected void addHeader() {
        addHeader("    Logging a new book");
    }

    // MODIFIES: this
    // EFFECTS: adds fields to be filled out in this form.
    //          Each book entry has 5 fields: title, author, date finished reading, and rating.
    public void addFields() {
        gbc.gridx = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;

        titleField = new OneLineField("Title");
        setFieldButton(titleField, 0);

        authorField = new OneLineField("Author");
        setFieldButton(authorField, 1);

        dateField = new OneLineField("Date");
        setFieldButton(dateField, 2);

        genreField = new OneLineField("Genre");
        setFieldButton(genreField, 3);

        ratingField = new OneLineField("Rating");
        setFieldButton(ratingField, 4);
    }

    // MODIFIES: this
    // EFFECTS: sets the GridBagLayout y constraint of a field and adds it to container
    private void setFieldButton(OneLineField field, int gridY) {
        gbc.gridy = gridY;
        gbl.setConstraints(field, gbc);
        container.add(field);
    }


    @Override
    // MODIFIES: this
    // EFFECTS: initializes actionListeners with the private handler classes in AddBookForm
    protected void initializeListeners() {
        saveClickHandler = new SaveClickHandler();
        cancelClickHandler = new CancelClickHandler();
    }

    // Handles click on saveButton by passing the command to BookFormManager
    private class SaveClickHandler implements ActionListener {

        @Override
        // EFFECTS: passing the command to save the book to BookFormManager
        public void actionPerformed(ActionEvent e) {
            manager.handleSaveClick();
        }
    }

    // Handles click on cancelButton by passing the command to BookFormManager
    private class CancelClickHandler implements ActionListener {

        @Override
        // EFFECTS: passing the command to cancel adding a book to BookFormManager
        public void actionPerformed(ActionEvent e) {
            manager.handleCancelClick();
        }
    }

    //getters
    public MyBook getBook() {
        return new MyBook("");
    }

    public OneLineField getTitleField() {
        return titleField;
    }

    public OneLineField getAuthorField() {
        return authorField;
    }

    public OneLineField getDateField() {
        return dateField;
    }

    public OneLineField getRatingField() {
        return ratingField;
    }

    public OneLineField getGenreField() {
        return genreField;
    }

}
