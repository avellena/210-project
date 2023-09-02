package ui.templates;

import model.MyBook;
import ui.LibraryHome;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

import static ui.templates.ColorScheme.*;

/**
 <a target="_blank" href="https://icons8.com/icon/98971/next-page">arrow</a> icon by
 <a target="_blank" href="https://icons8.com">Icons8</a>
 **/

// Main display area in JFrame next to side panel
public abstract class MainPanel extends JPanel {

    protected static Color backgroundColor = MAIN_BACKGROUND;
    protected LibraryHome home;

    // EFFECTS: constructs main panel
    public MainPanel(LibraryHome home) {
        setBackground(backgroundColor);
        this.home = home;
    }

    // MODIFIES: this
    // EFFECTS: refreshes main panel
    public void showContent() {
        this.validate();
        this.repaint();
    }

    // EFFECTS: default behaviour of loading books to main panel
    public void loadBooks(ArrayList<MyBook> books) {

    }

    // MODIFIES: this
    // EFFECTS: adds description header to main panel
    protected void addHeader(String text) {
        JButton header = new JButton(text);
        addArrowIcon(header);
        header.setOpaque(true);
        header.setBorderPainted(false);
        header.setBorder(new EmptyBorder(5,30,0,0));
        header.setFont(new Font("", Font.PLAIN, 15));
        header.setBackground(MAIN_BACKGROUND);
        header.setForeground(TOOLBAR_BACKGROUND);
        header.setHorizontalAlignment(SwingConstants.LEFT);
        header.setPreferredSize(new Dimension(230, 30));
        add(header, BorderLayout.NORTH);
    }

    // MODIFIES: header
    // EFFECTS: adds arrow icon to header
    private void addArrowIcon(JButton header) {
        ImageIcon arrow = MyIcon.getIcon("arrow.png", 16);
        JLabel arrowLabel = new JLabel();
        arrowLabel.setIcon(arrow);
        header.add(arrowLabel);
    }

    // getters
    public LibraryHome getHome() {
        return home;
    }
}
