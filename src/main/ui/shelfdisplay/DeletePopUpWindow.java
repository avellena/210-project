package ui.shelfdisplay;

import ui.templates.PopUpWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A PopUpWindow that confirms with user if they want to delete the book
public class DeletePopUpWindow extends PopUpWindow {
    private final String bookTitle;
    private final ShelfManager shelfManager;

    // EFFECTS: constructs a DeletePopUpWindow for the shelf manager
    public DeletePopUpWindow(ShelfManager shelfManager) {
        super();
        this.shelfManager = shelfManager;
        bookTitle = shelfManager.getBook().getTitle();
        addPrompt();
        addButtons();
        container.revalidate();
        container.repaint();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds a prompt panel with bookTitle in caption
    protected void addPrompt() {
        super.addPrompt();
        promptPanel.setText("Delete \"" + bookTitle + "\" from your Library?");
    }

    @Override
    // MODIFIES: this
    // EFFECTS: adds save and donot save buttons to container
    protected void addButtons() {
        JButton save = new PopUpWindow.PopUpButton("DELETE");
        JButton notSave = new PopUpWindow.PopUpButton("CANCEL");
        save.addActionListener(new DeletePopUpWindow.DeleteClickHandler());
        notSave.addActionListener(new DeletePopUpWindow.CancelClickHandler());
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbl.setConstraints(save, gbc);
        container.add(save);

        gbc.gridx = 1;
        gbl.setConstraints(notSave, gbc);
        container.add(notSave);
    }

    // handles save and quit
    private class DeleteClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            shelfManager.deleteBook();
            dispose();
        }
    }

    // handles quit and do not save
    private class CancelClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent t) {
            dispose();
        }
    }
}
