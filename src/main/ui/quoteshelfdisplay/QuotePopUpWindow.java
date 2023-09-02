package ui.quoteshelfdisplay;

import ui.templates.PopUpWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a QuotePopUpWindow that confirms with user if they want to delete or edit the quote
public class QuotePopUpWindow extends PopUpWindow {
    private final QuoteShelfManager manager;

    // EFFECTS: constructs QuotePopUpWindow for QuoteShelfManager
    public QuotePopUpWindow(QuoteShelfManager manager) {
        super();
        this.manager = manager;
        addPrompt();
        addButtons();
        container.revalidate();
        container.repaint();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds prompt button to container
    protected void addPrompt() {
        super.addPrompt();
        promptPanel.setText("DELETE or EDIT this quote?");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds delete and edit save buttons to container
    protected void addButtons() {
        JButton delete = new PopUpButton("DELETE");
        JButton edit = new PopUpButton("EDIT");
        delete.addActionListener(new QuotePopUpWindow.DeleteClickHandler());
        edit.addActionListener(new QuotePopUpWindow.EditClickHandler());
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(delete, gbc);
        container.add(edit);

        gbc.gridx = 1;
        gbl.setConstraints(edit, gbc);
        container.add(delete);
    }

    // handles click on delete button
    private class DeleteClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: passes command to delete quote to manager and dispose PopUpWindow
        public void actionPerformed(ActionEvent e) {
            manager.deleteQuote();
            dispose();
        }
    }

    // handles click on edit button
    private class EditClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: passes command to edit to manager and dispose PopUpWindow
        public void actionPerformed(ActionEvent e) {
            manager.editQuote();
            dispose();
        }
    }
}
