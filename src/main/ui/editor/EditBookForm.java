package ui.editor;

import model.MyBook;
import ui.LibraryHome;

// A form for user to fill out and edit a book. Has a coupling BookFormManager that handles saving and canceling.
public class EditBookForm extends AddBookForm {

    // EFFECTS: constructs the book editor form
    public EditBookForm(LibraryHome home, MyBook book) {
        super(home, book);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: Adds header description of the book being edited
    protected void addHeader() {
        addHeader("    Editing \"" + book.getTitle() + "\"");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds buttons to book editor form, each text field has original info as placeholders
    public void addFields() {
        super.addFields();
        titleField.getTextField().setText(book.getTitle());

        authorField.getTextField().setText(book.getAuthor());

        dateField.getTextField().setText(book.getDateFinishedReading());

        genreField.getTextField().setText(book.getGenre());

        ratingField.getTextField().setText(Integer.toString(book.getRating()));

    }

    // getter
    public MyBook getBook() {
        return book;
    }

}
