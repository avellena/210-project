package ui.quoteshelfdisplay;

import model.MyBook;
import model.Quote;
import ui.LibraryHome;
import ui.templates.MainPanel;
import ui.templates.MyHoverButton;
import ui.templates.MyIcon;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ui.templates.ColorScheme.TOOLBAR_BACKGROUND;
import static ui.templates.ColorScheme.MAIN_BACKGROUND;

/**
 * Add icon credit:
 * <a target="_blank" href="https://icons8.com/icon/95744/add-new">Add</a> icon by
 * <a target="_blank" href="https://icons8.com">Icons8</a>
 */

// Represents a QuoteShelf that displays all quotes from the book
public class QuoteShelf extends MainPanel {
    private final MyBook book;
    private final JPanel listContainer;
    private final QuoteShelfManager manager;

    // EFFECTS: constructs a QuoteShelf for book
    public QuoteShelf(LibraryHome home, MyBook book) {
        super(home);
        manager = new QuoteShelfManager(this);
        this.book = book;
        setBackground(backgroundColor);
        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        listContainer.setBackground(MAIN_BACKGROUND);
        listContainer.setBorder(new EmptyBorder(30, 0, 0, 0));
        add(listContainer);
    }


    @Override
    // EFFECTS: refresh QuoteShelf and shows all quotes
    public void showContent() {
        displayQuotes();
        super.showContent();
    }

    // MODIFIES: this
    // EFFECTS: displays all quotes in book on QuoteShelf
    private void displayQuotes() {
        clearQuotes();
        addQuoteHeader();
        for (Quote quote: book.getQuotes()) {
            JPanel quoteDisplay = new QuoteDisplay(quote, this);
            listContainer.add(quoteDisplay);
        }
        listContainer.revalidate();
        listContainer.repaint();
    }

    // MODIFIES: this
    // EFFECTS: adds a header panel with a description and a button for adding new quote
    private void addQuoteHeader() {
        JPanel quoteHeader = new JPanel();
        JButton headerText = new JButton("Viewing quotes from \"" + book.getTitle() + "\"");
        headerText.setFont(new Font("", Font.PLAIN, 15));
        headerText.setForeground(TOOLBAR_BACKGROUND);
        headerText.setBorderPainted(false);
        headerText.setBackground(MAIN_BACKGROUND);
        quoteHeader.add(headerText);
        addQuoteButton(quoteHeader);
        quoteHeader.setBorder(new EmptyBorder(0,10,10,0));
        quoteHeader.setPreferredSize(new Dimension(680, 40));
        quoteHeader.setBackground(MAIN_BACKGROUND);
        listContainer.add(quoteHeader);
    }

    // MODIFIES: header
    // EFFECTS: adds new quote button to header
    private void addQuoteButton(JPanel header) {
        JButton addQuote = new MyHoverButton("     ADD NEW QUOTE");
        addQuote.setPreferredSize(new Dimension(160, 30));
        ImageIcon addIcon = MyIcon.getIcon("add.png", 20);
        JLabel addLabel = new JLabel();
        addLabel.setIcon(addIcon);
        addQuote.add(addLabel);
        header.add(addQuote);
        addQuote.addActionListener(new QuoteShelf.NewQuoteClickHandler());
    }

    // handles click on new quote button
    private class NewQuoteClickHandler implements ActionListener {

        @Override
        // EFFECTS: Passes command to add a new quote to QuoteShelfManager
        public void actionPerformed(ActionEvent e) {
            manager.handleNewQuote(new Quote(""));
        }
    }

    // MODIFIES: this
    // EFFECTS: remove all quotes from the listContainer
    private void clearQuotes() {
        Component[] quotesList = listContainer.getComponents();

        for (Component b : quotesList) {
            listContainer.remove(b);
        }
    }

    // getters
    public QuoteShelfManager getManager() {
        return manager;
    }

    public MyBook getBook() {
        return book;
    }
}
