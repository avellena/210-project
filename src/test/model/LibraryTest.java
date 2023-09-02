package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    Library l;
    MyBook b1;
    MyBook b2;
    MyBook b3;
    MyBook b4;
    Tag a1;
    Tag a2;
    Tag g1;
    Tag g2;
    private String a1Name = "Gabriel Garcia Marquez";
    private String a2Name = "Dubravka Ugresic";

    @BeforeEach
    public void runBefore() {
        l = new Library();
        b1 = new MyBook("Love in the Time of Cholera");

        b1.setAuthorName(a1Name);
        b1.setGenreName("fiction");

        b2 = new MyBook("Living to Tell the Tale");
        b2.setAuthorName(a1Name);
        b2.setGenreName("memoir");

        b3 = new MyBook("The Ministry of Pain");
        b3.setAuthorName(a2Name);
        b3.setGenreName("fiction");

        b4 = new MyBook("A Book with Empty Tags");
        b4.setAuthorName("");
        b4.setGenreName("");

        a1 = new Tag(a1Name);
        a2 = new Tag(a2Name);
        g1 = new Tag("fiction");
        g2 = new Tag("memoir");
    }

    @Test
    public void testSimpleLibraryConstructor() {
        assertEquals(0, l.getAuthors().size());
        assertEquals(0, l.getGenres().size());
        assertEquals(0, l.getBooks().size());
    }

    @Test
    public void testFullLibraryConstructor() {
        ArrayList<MyBook> testBooks = new ArrayList<>();
        testBooks.add(b1);
        testBooks.add(b2);
        ArrayList<Tag> testAuthors = new ArrayList<>();
        testAuthors.add(a1);
        testAuthors.add(a2);
        ArrayList<Tag> testGenres = new ArrayList<>();
        testGenres.add(g1);
        testGenres.add(g2);

        l = new Library(testBooks, testAuthors, testGenres);
        assertEquals(testAuthors, l.getAuthors());
        assertEquals(testBooks, l.getBooks());
        assertEquals(testGenres, l.getGenres());
    }

    @Test
    public void testAddFirstBook() {
        l.addBook(b1);
        String firstTitle = l.getBooks().get(0).getTitle();
        assertEquals("Love in the Time of Cholera", firstTitle);
        String firstAuthorTag = l.getAuthors().get(0).getTagName();
        assertEquals(a1Name, firstAuthorTag);
        String firstGenreTag = l.getGenres().get(0).getTagName();
        assertEquals("fiction", firstGenreTag);
    }

    @Test
    public void testAddBookToExistingTag() {
        createLibrary();

        for (Tag author: l.getAuthors()) {
            if (!author.getTagName().equals(a1Name)  && !author.getTagName().equals(a2Name)) {
                fail();
            }
        }

        for (Tag genre: l.getGenres()) {
            if (!genre.getTagName().equals("fiction") && !genre.getTagName().equals("memoir")) {
                fail();
            }
        }

        assertEquals(2, l.getAuthors().size());
        assertEquals(2, l.getGenres().size());

    }

    @Test
    public void testRemoveBook() {
        createLibrary();

        l.removeBook(b1);
        int b1Id = b1.getId();
        assertEquals(2, l.getAuthors().size());
        assertEquals(2, l.getGenres().size());
        for (Tag author: l.getAuthors()) {
            if (author.getTagName().equals(a1Name)) {
                assertFalse(author.getWorks().contains(b1Id));
            }
        }
        for (Tag genre: l.getGenres()) {
            if (genre.getTagName().equals("fiction")) {
                assertFalse(genre.getWorks().contains(b1Id));
            }
        }

        l.removeBook(b2);
        ArrayList<Tag> authors = l.getAuthors();
        ArrayList<Tag> genres = l.getGenres();
        assertEquals(1, authors.size());
        assertEquals(a2Name, authors.get(0).getTagName());
        assertEquals(1, genres.size());
        assertEquals("fiction", genres.get(0).getTagName());

        l.removeBook(b4);
        l.removeBook(b4);
        l.removeBook(b3);
        assertEquals(0, l.getAuthors().size());
        assertEquals(0, l.getGenres().size());
    }

    @Test
    public void testFindBooksInTag() {
        createLibrary();
        ArrayList<MyBook> booksByA1 = l.findBooksInTag(a1Name, l.getAuthors());
        ArrayList<MyBook> booksByG1 = l.findBooksInTag("Fiction", l.getGenres());
        assertEquals(2, booksByA1.size());
        assertTrue(booksByA1.contains(b1));
        assertTrue(booksByA1.contains(b2));
        assertEquals(2, booksByG1.size());
        assertTrue(booksByG1.contains(b1));
        assertTrue(booksByG1.contains(b3));
    }

    @Test
    public void testFindBookWithTitle() {
        createLibrary();
        assertEquals(b1, l.findBookWithTitle("love in the Time of Cholera"));
    }

    @Test
    public void testCannotFind() {
        createLibrary();
        assertNull(l.findBooksInTag("Anonymous", l.getAuthors()));
        assertNull(l.findBookWithId(10000));
        assertNull(l.findBookWithTitle("This book does not exist"));
    }

    private void createLibrary() {
        l.addBook(b1);
        l.addBook(b2);
        l.addBook(b3);
        l.addBook(b4);
    }
}
