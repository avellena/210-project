package ui.shelfdisplay;

import model.MyBook;
import ui.LibraryHome;
import ui.templates.MainPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

import static ui.templates.ColorScheme.*;

// Represents a shelf that displays given list of books
public class Shelf extends MainPanel {

    private ArrayList<MyBook> books;
    private final JPanel listContainer;
    private final ShelfManager manager;

    // EFFECTS: constructs a Shelf
    public Shelf(LibraryHome home) {
        super(home);
        manager = new ShelfManager(home);
        books = new ArrayList<>();
        listContainer = new JPanel(new GridLayout(0, 1,0, 0));
        listContainer.setBackground(MAIN_BACKGROUND);
        listContainer.setBorder(new MatteBorder(30, 0, 0, 0, MAIN_BACKGROUND));
        add(listContainer);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: loads the list of books to display on this shelf
    public void loadBooks(ArrayList<MyBook> books) {
        this.books = books;
    }

    @Override
    public void showContent() {
        displayBooks();
        super.showContent();
    }

    // MODIFIES: this
    // EFFECTS: displays list of books on shelf
    public void displayBooks() {
        clearBooks();
        for (MyBook book : books) {
            BookDisplay bookDisplay = new BookDisplay(book, this);
            listContainer.add(bookDisplay);
        }
        listContainer.revalidate();
        listContainer.repaint();
    }

    // MODIFIES: this
    // EFFECTS: clears the shelf by removing all JComponents in listContainer
    private void clearBooks() {
        Component[] bookList = listContainer.getComponents();

        for (Component b : bookList) {
            listContainer.remove(b);
        }
    }

    // getters
    public ShelfManager getManager() {
        return manager;
    }



}
