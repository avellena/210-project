package ui.searchformdisplay;

import model.Library;
import model.MyBook;
import ui.LibraryHome;
import ui.shelfdisplay.Shelf;
import ui.templates.MainPanel;
import ui.templates.MyHoverButton;
import ui.templates.MyIcon;
import ui.templates.MyTextField;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static ui.templates.ColorScheme.*;

/**
 * search icon credit:
 * <a target="_blank" href="https://icons8.com/icon/131/search">Search</a> icon by
 * <a target="_blank" href="https://icons8.com">Icons8</a>
 **/

// Represents a search form for searching book by title
public class SearchForm extends MainPanel {
    private static JPanel container;
    private final GridBagLayout gbl;
    private final GridBagConstraints gbc;
    protected String textSearched;
    protected JTextField text;
    protected JButton messageButton;
    protected JLabel titleLabel;

    // EFFECTS: constructs the search form
    public SearchForm(LibraryHome home) {
        super(home);
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        container = new JPanel(gbl);
        container.setBackground(MAIN_BACKGROUND);
        container.setBorder(new MatteBorder(0, 60, 100, 60, MAIN_BACKGROUND));
        addButtons();
        add(container);
        addHeader();
    }

    // MODIFIES: this
    // EFFECTS: adds header to search form
    protected void addHeader() {
        addHeader("    Search by title");
    }

    // MODIFIES: this
    // EFFECTS: adds JComponents to search form
    private void addButtons() {
        addSearchTitle();
        addSearchIcon();
        addText();
        messageButton = addMessage("");
    }

    // MODIFIES: this
    // EFFECTS: adds a panel that displays given message if search unsuccessful
    protected JButton addMessage(String message) {
        if (messageButton != null) {
            container.remove(messageButton);
        }

        JButton button = new JButton(message);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBorder(new MatteBorder(10,0,0,0, MAIN_BACKGROUND));
        button.setBackground(MAIN_BACKGROUND);
        button.setFont(new Font("", Font.PLAIN, 15));
        button.setForeground(TOOLBAR_BACKGROUND);
        GridBagConstraints messageGbc = new GridBagConstraints();
        messageGbc.gridx = 0;
        messageGbc.gridy = 2;
        messageGbc.gridheight = 1;
        messageGbc.gridwidth = 2;
        gbl.setConstraints(button, messageGbc);
        container.add(button);
        container.revalidate();
        container.repaint();
        return button;
    }

    // MODIFIES: this
    // EFFECTS: adds title caption above text field
    private void addSearchTitle() {
        titleLabel = new JLabel("SEARCH BY TITLE");
        titleLabel.setOpaque(true);
        titleLabel.setBackground(MAIN_BACKGROUND);
        titleLabel.setFont(new Font("Futura", Font.PLAIN, 20));
        titleLabel.setForeground(TOOLBAR_BACKGROUND);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbl.setConstraints(titleLabel, gbc);
        container.add(titleLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds a search button with magnifier icon to container
    private void addSearchIcon() {
        JButton searchButton = new MyHoverButton("");
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setBackground(TOOLBAR_BACKGROUND);
        JLabel searchLabel = new JLabel();
        ImageIcon searchIcon = MyIcon.getIcon("search.png", 15);
        searchLabel.setIcon(searchIcon);
        searchButton.add(searchLabel);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbl.setConstraints(searchButton, gbc);
        searchButton.setPreferredSize(new Dimension(30, 30));
        container.add(searchButton);
        searchButton.addActionListener(new SearchClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: adds text field for searching
    private void addText() {
        text = new MyTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbl.setConstraints(text, gbc);
        container.add(text);
    }

    // MODIFIES: this
    // EFFECTS: IF book found in library, display the book;
    //          OTHERWISE displays a book not found message.
    protected void search(String text) {
        ArrayList<MyBook> searchResult = new ArrayList<>();
        Library library = getHome().getLibrary();
        MyBook returned = library.findBookWithTitle(textSearched);
        if (returned != null) {
            searchResult.add(returned);
            displaySearchResult(searchResult);
        } else {
            addMessage("There is no \"" + textSearched + "\" in your library!");
        }
    }

    // handles click on search button
    private class SearchClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: searches book by string in text field
        public void actionPerformed(ActionEvent e) {
            textSearched = text.getText();
            search(textSearched);
        }
    }

    // MODIFIES: this
    // EFFECTS: displays search result on a new shelf and refreshes main panel
    public void displaySearchResult(ArrayList<MyBook> result) {
        Shelf newShelf = new Shelf(home);
        newShelf.loadBooks(result);
        home.refreshMainPanel(newShelf);
    }
}

