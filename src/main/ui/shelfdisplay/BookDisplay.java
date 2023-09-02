package ui.shelfdisplay;




import model.MyBook;
import ui.templates.MyHoverButton;
import ui.templates.MyIcon;
import ui.templates.MyToggleButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.templates.ColorScheme.*;

/**
 <a target="_blank" href="https://icons8.com/icon/78757/pencil-drawing">Pencil</a>
 icon by <a target="_blank" href="https://icons8.com">Icons8</a>

 <a target="_blank" href="https://icons8.com/icon/93143/quote">Quote</a> icon by
 <a target="_blank" href="https://icons8.com">Icons8</a>

 <a target="_blank" href="https://icons8.com/icon/zWBbi62CYIKH/star">Star</a> icon
 by <a target="_blank" href="https://icons8.com">Icons8</a>

 <a target="_blank" href="https://icons8.com/icon/79201/multiplication">Cross</a> icon
 by <a target="_blank" href="https://icons8.com">Icons8</a>
 **/

// Represents a JPanel of a single book entry with fixed width and height
public class BookDisplay extends JPanel {
    private static final int BOOK_ENTRY_HEIGHT = 90;
    private static final int BOOK_ENTRY_WIDTH = 680;
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints gbc;
    private final Shelf shelf;
    private final MyBook book;

    // EFFECTS: constructs a JPanel with book's title, author, date finished reading, rating, and genre
    public BookDisplay(MyBook book, Shelf shelf) {
        this.book = book;
        this.shelf = shelf;
        setBackground(MAIN_BACKGROUND);
        setLayout(gbl);
        Dimension entryDimension = new Dimension(BOOK_ENTRY_WIDTH, BOOK_ENTRY_HEIGHT);
        setPreferredSize(entryDimension);
        Border lineBorder = new MatteBorder(0,0,2,0, DIVIDER_COLOR);
        Border emptyBorder = new EmptyBorder(7,0,7,0);
        CompoundBorder border = new CompoundBorder(lineBorder, emptyBorder);
        setBorder(border);
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: adds title display
    private void addTitleButton() {
        InfoButton title = new InfoButton(book.getTitle());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbl.setConstraints(title, gbc);
        title.setMinimumSize(new Dimension(320, 38));
        title.setAlignmentY(0.5f);
        add(title);
    }

    // MODIFIES: this
    // EFFECTS: adds quote button that allows access to QuoteShelf
    private void addQuoteButton() {
        MyHoverButton quote = new MyHoverButton("", ENTRY_BACKGROUND, ENTRY_BACKGROUND, MAIN_BACKGROUND);
        quote.setLayout(new BorderLayout());
        ImageIcon quoteIcon = MyIcon.getIcon("quote.png", 20);
        JLabel quoteLabel = new JLabel();
        quoteLabel.setIcon(quoteIcon);
        quoteLabel.setText(Integer.toString(book.getQuotes().size()));
        quoteLabel.setFont(new Font("Baskerville", Font.BOLD, 15));
        quoteLabel.setForeground(TOOLBAR_BACKGROUND);
        quoteLabel.setHorizontalAlignment(0);
        quote.add(quoteLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbl.setConstraints(quote, gbc);
        addQuoteListener(quote);
        add(quote);
    }

    // MODIFIES: button
    // EFFECTS: adds listener to button
    private void addQuoteListener(JButton button) {
        button.addActionListener(new BookDisplay.QuoteClickHandler());
    }

    // transfers quote clicking events to handler
    private class QuoteClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            shelf.getManager().handleQuoteClick(book);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds date-finished-reading display
    private void addDateButton() {
        String dateString = "Finished on: " + book.getDateFinishedReading();
        InfoButton date = new InfoButton(dateString);
        date.smallerFontSize();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(date, gbc);
        date.setMinimumSize(new Dimension(250, 38));
        add(date);
    }

    // MODIFIES: this
    // EFFECTS: adds edit book button
    private void addEditButton() {
        JButton edit = new MyHoverButton("", ENTRY_BACKGROUND, MAIN_BACKGROUND, MAIN_BACKGROUND);

        edit.setLayout(new BorderLayout());
        ImageIcon pencil = MyIcon.getIcon("pencil.png", 30);
        JLabel editIcon = new JLabel();
        editIcon.setIcon(pencil);
        editIcon.setHorizontalAlignment(0);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        edit.add(editIcon);
        gbc.fill = GridBagConstraints.BOTH;
        gbl.setConstraints(edit, gbc);
        edit.setMaximumSize(new Dimension(70, 100));
        addEditListener(edit);
        add(edit);
    }

    // MODIFIES: button
    // EFFECTS: adds listener to button
    private void addEditListener(JButton button) {
        button.addActionListener(new BookDisplay.EditClickHandler());
    }

    // passes quote clicking events to handler
    private class EditClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            shelf.getManager().handleEditClick(book);
        }
    }


    // MODIFIES: this
    // EFFECTS: adds author display
    private void addAuthorButton() {
        String authorString = "by " + book.getAuthor();
        InfoButton author = new InfoButton(authorString);
        author.setFont(new Font("Apple chancery", Font.BOLD + Font.ITALIC, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(author, gbc);
        add(author);
    }

    // MODIFIES: this
    // EFFECTS: adds genre display
    private void addGenreButton() {
        InfoButton genre = new InfoButton(book.getGenre());
        genre.smallerFontSize();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(genre, gbc);
        add(genre);
    }

    // MODIFIES: this
    // EFFECTS: adds rating display
    private void addRatingButton() {
        JButton rating = new InfoButton("");
        rating.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        addStars(rating);
        rating.validate();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(rating, gbc);
        add(rating);
    }

    // REQUIRES: 1 <= rating <= 10
    // MODIFIES: ratingButton
    // EFFECTS: adds correct number of stars to the rating button
    private void addStars(JButton ratingButton) {
        ImageIcon scaledStar = MyIcon.getIcon("star.png", 15);
        ImageIcon scaledHalfStar = MyIcon.getIcon("half_star.png", 15);

        int rating = book.getRating();
        while (rating > 0) {
            if (rating >= 2) {
                JLabel starLabel = new JLabel();
                starLabel.setIcon(scaledStar);
                ratingButton.add(starLabel);
            } else {
                JLabel halfStarLabel = new JLabel();
                halfStarLabel.setIcon(scaledHalfStar);
                ratingButton.add(halfStarLabel);
            }
            rating -= 2;

        }
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons to the book display
    private void addButtons() {
        addTitleButton();
        addQuoteButton();
        addDateButton();
        addAuthorButton();
        addGenreButton();
        addRatingButton();
        addEditButton();
        addDeleteButton();
    }

    // MODIFIES: this
    // EFFECTS: adds delete button
    private void addDeleteButton() {
        JButton delete = new MyToggleButton("light_cross.png", "dark_cross.png");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(delete, gbc);
        addDeleteListener(delete);
        add(delete);
    }

    // MODIFIES: button
    // EFFECTS: adds listener to button
    private void addDeleteListener(JButton button) {
        button.addActionListener(new BookDisplay.DeleteClickHandler());
    }

    // passes quote clicking events to handler
    private class DeleteClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            shelf.getManager().handleDeleteClick(book);
        }
    }


    // Represents a customized button for displaying a field in the book entry
    private static class InfoButton extends JButton {
        protected InfoButton(String text) {
            super(text);
            setFont(new Font("Palatino", Font.BOLD, 18));
            setOpaque(true);
            setBorderPainted(false);
            setBackground(MAIN_BACKGROUND);
        }

        // MODIFIES: this
        // EFFECTS: changes the font size of the button
        protected void smallerFontSize() {
            setFont(new Font("Palatino", Font.PLAIN, 14));
        }
    }


}
