package ui.templates;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static ui.templates.ColorScheme.TOOLBAR_BACKGROUND;
import static ui.templates.ColorScheme.MAIN_BACKGROUND;

// Represents a pop-up window that has a prompt and some buttons
public abstract class PopUpWindow extends JFrame {

    public static final int WIDTH = 350;
    public static final int HEIGHT = 150;
    protected JPanel container;
    protected JLabel promptPanel;
    protected GridBagLayout gbl;
    protected GridBagConstraints gbc;

    //EFFECTS: constructs PopUpWindow for CloseAppLibraryTool
    public PopUpWindow() {
        addContainer();
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // shows window centered on the screen
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds content container to this JFrame
    private void addContainer() {
        container = new JPanel();
        container.setOpaque(true);
        container.setBackground(MAIN_BACKGROUND);
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 8, 8, 0);
        container.setLayout(gbl);
        container.setBorder(new EmptyBorder(20,20,10,20));
        getContentPane().add(container, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds prompt button to container
    protected void addPrompt() {
        promptPanel = new JLabel();
        promptPanel.setFont(new Font("", Font.PLAIN, 15));
        promptPanel.setHorizontalAlignment(SwingConstants.CENTER);
        promptPanel.setOpaque(true);
        promptPanel.setBackground(MAIN_BACKGROUND);
        promptPanel.setForeground(TOOLBAR_BACKGROUND);
        promptPanel.setPreferredSize(new Dimension(300, 110));
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbl.setConstraints(promptPanel, gbc);
        container.add(promptPanel);
    }

    // default stub for adding buttons
    protected abstract void addButtons();

    // Customized button o PopUpWindow
    protected static class PopUpButton extends MyHoverButton {

        // EFFECTS: constructs a PopUpButton
        public PopUpButton(String text) {
            super(text);
            setPreferredSize(new Dimension(100, 40));
        }
    }
}
