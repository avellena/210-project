package ui.templates;

import ui.LibraryHome;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static ui.templates.ColorScheme.MAIN_BACKGROUND;

// Represents a form with GridBagLayout and SAVE/CANCEL buttons
public abstract class Form extends MainPanel {

    protected GridBagConstraints gbc;
    protected GridBagLayout gbl;
    protected JPanel container;
    protected MyHoverButton saveButton;
    protected MyHoverButton cancelButton;
    protected ActionListener saveClickHandler;
    protected ActionListener cancelClickHandler;

    // EFFECTS: constructs a form
    public Form(LibraryHome home) {
        super(home);
        setLayout(new BorderLayout());
        setBackground(backgroundColor);
        gbc = new GridBagConstraints();
        gbl = new GridBagLayout();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,10,0,10);
        container = new JPanel(gbl);
        container.setBackground(MAIN_BACKGROUND);
        container.setBorder(new MatteBorder(0, 60, 100, 60, MAIN_BACKGROUND));
        initializeListeners();
        addConfirmationButtons();
        add(container);
    }

    // MODIFIES: this
    // EFFECTS: initializes actionListeners for saving and canceling
    protected abstract void initializeListeners();

    // MODIFIES: this
    // EFFECTS: adds SAVE and CANCEL buttons to form
    protected void addConfirmationButtons() {
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        cancelButton = new MyHoverButton("CANCEL");
        cancelButton.addActionListener(cancelClickHandler);
        saveButton = new MyHoverButton("SAVE");
        saveButton.addActionListener(saveClickHandler);

        gbc.gridx = 0;
        gbl.setConstraints(cancelButton, gbc);
        cancelButton.setPreferredSize(new Dimension(190, 40));
        container.add(cancelButton);

        gbc.gridx = 1;
        gbl.setConstraints(saveButton, gbc);
        saveButton.setPreferredSize(new Dimension(190, 40));
        container.add(saveButton);
    }


}
