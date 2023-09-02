package ui.editor;

import exception.InvalidInputException;
import model.MyBook;
import ui.LibraryHome;
import ui.shelfdisplay.Shelf;

// Handles save/cancel click in AddBookForm
public class BookFormManager {

    private final AddBookForm form;

    // EFFECTS: constructs a BookFormManager
    public BookFormManager(AddBookForm form) {
        this.form = form;
    }

    // MODIFIES: this, home.getLibrary
    // EFFECTS: - IF user inputs for title, date, and rating are valid, remove the old entry from library and adds new
    //            book with information in text fields, returns to updated Shelf
    //          - IF any inputs are invalid, do nothing
    public void handleSaveClick() {
        MyBook book = form.getBook();
        if (validTitle(book) && validDate(book) && validRating(book) >= 0) {
            form.getHome().getLibrary().removeBook(book);
            book = new MyBook(form.getTitleField().getText());
            book.setAuthorName(form.getAuthorField().getText());
            book.setGenreName(form.getGenreField().getText());
            book.setRating(validRating(book));
            book.setDateFinishedReading(form.getDateField().getText());
            form.getHome().getLibrary().addBook(book);

            Shelf newShelf = new Shelf(form.getHome());
            newShelf.loadBooks(form.getHome().getLibrary().getBooks());
            form.getHome().refreshMainPanel(newShelf);
        }
    }

    // MODIFIES: this
    // EFFECTS: returns to view all page
    public void handleCancelClick() {
        LibraryHome home = form.getHome();
        Shelf newShelf = new Shelf(home);
        newShelf.loadBooks(home.getLibrary().getBooks());
        home.refreshMainPanel(newShelf);
    }

    // EFFECTS: returns true if date follows regex, false otherwise
    private boolean validDate(MyBook book) {
        OneLineField field = form.getDateField();
        try {
            book.validateDate(field.getText());
            hideMessage(field);
            return true;
        } catch (InvalidInputException e) {
            showMessage(field, e);
            return false;
        }
    }

    // EFFECTS: returns int rating if rating is an integer between 1 and 10;
    //          OTHERWISE returns -1;
    private int validRating(MyBook book) {
        OneLineField field = form.getRatingField();
        try {
            int rating = book.validateRating(field.getText());
            hideMessage(field);
            return rating;
        } catch (InvalidInputException e) {
            showMessage(field, e);
            return -1;
        }
    }

    // EFFECTS: returns true if title is non-empty string
    private boolean validTitle(MyBook book) {
        OneLineField field = form.getTitleField();
        try {
            book.validateTitle(field.getText());
            hideMessage(field);
            return true;
        } catch (InvalidInputException e) {
            showMessage(field, e);
            return false;
        }
    }

    // EFFECTS: sets invalid input message to invisible
    private void hideMessage(OneLineField field) {
        field.hideMessage();
        form.revalidate();
        form.repaint();
    }

    // EFFECTS: sets invalid input message to visible
    private void showMessage(OneLineField field, InvalidInputException e) {
        field.showMessage(e.getMessage());
        form.revalidate();
        form.repaint();
    }

}
