package ui.quoteshelfdisplay;

import model.Quote;
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
 * Edit icon credit:
 * <a target="_blank" href="https://icons8.com/icon/9fYfwBJNoMpV/create">Edit</a> icon by
 * <a target="_blank" href="https://icons8.com">Icons8</a>*
 * Quotation mark icon credit:
 * <a target="_blank" href="https://icons8.com/icon/lO7AfSDCWFBX/get-quote">Quotation Mark</a> icon by
 * <a target="_blank" href="https://icons8.com">Icons8</a>
 */

// A JPanel for one quote entry
public class QuoteDisplay extends JPanel {
    private final String content;
    private final QuoteShelf shelf;
    private final Quote quote;
    private final int pageNum;
    private final GridBagLayout gbl = new GridBagLayout();
    private final GridBagConstraints gbc;

    // EFFECTS: constructs a quote entry display on the QuoteShelf
    public QuoteDisplay(Quote quote, QuoteShelf shelf) {
        setLayout(gbl);
        this.shelf = shelf;
        this.quote = quote;
        content = quote.getContent();
        pageNum = quote.getPageNum();
        setMinimumSize(new Dimension(680, 100));
        setOpaque(true);
        setBackground(MAIN_BACKGROUND);
        Border lineBorder = new MatteBorder(0,0,2,0, DIVIDER_COLOR);
        Border emptyBorder = new EmptyBorder(6,0,6,0);
        CompoundBorder border = new CompoundBorder(lineBorder, emptyBorder);
        setBorder(border);
        gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addContent();
        addPageNumber();
        addQuotationMark("open");
        addQuotationMark("close");
        addEditButton();
    }

    // MODIFIES: this
    // EFFECTS: adds content text area
    private void addContent() {
        JTextArea contentArea = new JTextArea();
        contentArea.setBackground(MAIN_BACKGROUND);
        contentArea.setBorder(new EmptyBorder(10,5,10,5));
        contentArea.setText(content);
        contentArea.setFont(new Font("Palatino", Font.ITALIC, 18));
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbl.setConstraints(contentArea, gbc);
        add(contentArea);
    }

    // MODIFIES: this
    // EFFECTS: adds page number label
    private void addPageNumber() {
        JLabel pageNumLabel = new JLabel();
        pageNumLabel.setText("------ page " + pageNum);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbl.setConstraints(pageNumLabel, gbc);
        pageNumLabel.setBackground(MAIN_BACKGROUND);
        pageNumLabel.setOpaque(true);
        pageNumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(pageNumLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds edit button to bottom left corner
    private void addEditButton() {
        JButton editButton = new MyToggleButton("light_edit.png", "dark_edit.png");
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbl.setConstraints(editButton, gbc);
        editButton.addActionListener(new QuoteDisplay.EditClickHandler());
        add(editButton);
    }

    // handles click on edit button
    private class EditClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: passes command to edit quote to QuoteShelfManager
        public void actionPerformed(ActionEvent e) {
            shelf.getManager().handleEditQuote(quote);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds opening or closing quotation mark icons to panel based on the key given
    private void addQuotationMark(String key) {
        JLabel quotationMark = new JLabel();
        quotationMark.setLayout(new BorderLayout());
        ImageIcon quoteIcon = MyIcon.getIcon(key + "_quote.png", 20);
        quotationMark.setIcon(quoteIcon);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridy = 0;
        if (key.equals("open")) {
            gbc.gridx = 0;
            quotationMark.setVerticalAlignment(JLabel.TOP);
        } else {
            gbc.gridx = 2;
            quotationMark.setVerticalAlignment(JLabel.BOTTOM);
        }
        gbl.setConstraints(quotationMark, gbc);
        quotationMark.setBackground(MAIN_BACKGROUND);
        quotationMark.setBorder(new MatteBorder(5,3,5,3, MAIN_BACKGROUND));
        quotationMark.setOpaque(true);
        add(quotationMark);
    }
}
