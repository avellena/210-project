package ui;

import model.Library;
import persistence.ILibraryFile;
import persistence.LibraryFile;
import ui.helperwindows.WelcomeWindow;
import ui.templates.MainPanel;
import ui.tools.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.io.IOException;

import static ui.templates.ColorScheme.*;

/**
 * referenced CPSC210 SimpleDrawingPlayer
 */

// Main display of the application
public class LibraryHome extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private final String libraryName;
    private Library library;
    private ILibraryFile libraryFile;
    private static final String LIBRARY_PATH = "./data";

    private MainPanel currentMainPanel;

    // EFFECTS: constructs LibraryHome and with the given library name
    public LibraryHome(String libraryName) {
        super(libraryName);
        this.libraryName = libraryName;
        initializeLibrary();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: loads library from json file
    //          creates new Library if file DNE
    private void initializeLibrary() {
        libraryFile = new LibraryFile();
        libraryFile.setFile(LIBRARY_PATH, libraryName + ".json");
        try {
            library = libraryFile.readLibrary();
            if (library == null) {
                library = new Library();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window of LibraryHome, and populates the tools in the toolbar
    private void initializeGraphics() {
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        createTools();
        setMainPanel(new Instruction(this));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // shows window centered on the screen
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: sets the main panel
    private void setMainPanel(MainPanel panel) {
        panel.setBounds(WIDTH / 4, 0, WIDTH * 3 / 4, HEIGHT);
        currentMainPanel = panel;
        add(currentMainPanel);
        currentMainPanel.showContent();
    }

    // MODIFIES: this
    // EFFECTS: removes the current main panel and sets new panel
    public void refreshMainPanel(MainPanel panel) {
        getContentPane().remove(currentMainPanel);
        setMainPanel(panel);
    }



    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
    private void createTools() {
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BorderLayout());
        sideBar.setBackground(TOOLBAR_BACKGROUND);
        sideBar.setBounds(0,0, WIDTH / 4, HEIGHT);
        this.add(sideBar);

        JPanel toolBar = new JPanel();
        toolBar.setBorder(new MatteBorder(30, 0, 0, 0, TOOLBAR_BACKGROUND));
        toolBar.setLayout(new GridLayout(0,1));
        toolBar.setSize(new Dimension(0, 0));
        sideBar.add(toolBar, BorderLayout.NORTH);


        new ViewAllLibraryTool(this, toolBar);
        new AddBooksLibraryTool(this, toolBar);
        new SearchBookLibraryTool(this, toolBar);
        new AuthorFilterLibraryTool(this, toolBar);
        new GenreFilterLibraryTool(this, toolBar);
        new CloseAppLibraryTool(this, toolBar);
    }

    // getters
    public Library getLibrary() {
        return library;
    }

    public ILibraryFile getLibraryFile() {
        return libraryFile;
    }

    public static void main(String[] args) {
        new WelcomeWindow();
    }

}
