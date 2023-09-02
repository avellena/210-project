package persistence;

import model.Library;
import model.MyBook;
import model.Quote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryFileTest {
    private Library l;
    private MyBook rb1;
    private MyBook rb2;
    private MyBook rb3;
    private String a1;
    private String a2;
    private String g1;
    private String g2;
    private String t1;
    private String t2;
    private String t3;
    private LibraryFile files;

    @BeforeEach void runBefore() {
        files = new LibraryFile();
        files.setFile("./data","testFile.json");
        l = new Library();
        a1 = "C.S. Lewis";
        a2 = "Madeline Miller";
        g1 = "mythological novel";
        g2 = "high fantasy";
        Quote q1 = new Quote("quote one", 87);
        Quote q2 = new Quote("quote two", 99);
        t1 = "Till We Have Faces";
        t2 = "The Chronicles of Narnia";
        t3 = "The Song of Achilles";

        ArrayList<Quote> quotes1 = new ArrayList<>();
        quotes1.add(q1);
        quotes1.add(q2);
        ArrayList<Quote> quotes2 = new ArrayList<>();
        quotes2.add(q2);
        ArrayList<Quote> quotes3 = new ArrayList<>();

        MyBook b1 = new MyBook(1, t1, a1, "2021-08-06", g1, 9,
                "some comment", quotes1);
        MyBook b2 = new MyBook(2, t2, a1, "2017-04-30", g2, 8,
                "Childhood!!", quotes2);
        MyBook b3 = new MyBook(3, t3, a2, "2023-02-10", g1, 8,
                "Romance!!", quotes3);
        l.addBook(b1);
        l.addBook(b2);
        l.addBook(b3);
    }

    @Test
    void testBookTitles() {
        writeAndRead();
        Assertions.assertEquals(t1, rb1.getTitle());
        Assertions.assertEquals(t2, rb2.getTitle());
        Assertions.assertEquals(t3, rb3.getTitle());
    }

    @Test
    void testBookAuthors() {
        writeAndRead();
        Assertions.assertEquals(a1, rb1.getAuthor());
        Assertions.assertEquals(a2, rb3.getAuthor());
    }

    @Test
    void testBookDate() {
        writeAndRead();
        Assertions.assertEquals("2017-04-30", rb2.getDateFinishedReading());
        Assertions.assertEquals("2023-02-10", rb3.getDateFinishedReading());
    }

    @Test
    void testBookGenre() {
        writeAndRead();
        Assertions.assertEquals(g1, rb1.getGenre());
        Assertions.assertEquals(g2, rb2.getGenre());
    }

    @Test
    void testRating() {
        writeAndRead();
        Assertions.assertEquals(9, rb1.getRating());
        Assertions.assertEquals(8, rb2.getRating());
    }

    @Test
    void testComment() {
        writeAndRead();
        Assertions.assertEquals("Romance!!", rb3.getComment());
    }

    @Test
    void testQuotes() {
        writeAndRead();
        ArrayList<Quote> rquotes = rb1.getQuotes();
        Quote rq1 = rquotes.get(0);
        Quote rq2 = rquotes.get(1);
        Assertions.assertEquals("quote one", rq1.getContent());
        Assertions.assertEquals(87, rq1.getPageNum());
        Assertions.assertEquals(99, rq2.getPageNum());
        Assertions.assertEquals("quote two", rq2.getContent());
    }

    @Test
    void testCannotClearDisk() {
        writeAndRead();
        files.setFile("src/DNE", "DNE");
        files.clearDisk();
    }

    @Test
    void testClearDisk() {
        writeAndRead();
        try {
            files.clearDisk();
            Path path = Paths.get("src","Data","testFile.txt");
            Assertions.assertFalse(Files.exists(path));
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    void testWriteLibraryJsonExc() {
        files.setFile("src/DNE*", "DNE");
        files.writeLibrary(l);
        // expect to see error stack trace
    }



    @Test
    void testCannotRead() {
        files.setFile("src/DNE*", "DNE");
        Library nullLibrary = files.readLibrary();
        assertNull(nullLibrary);
    }

    private void writeAndRead() {
        files.writeLibrary(l);
        Library returnedLibrary;
        returnedLibrary = files.readLibrary();
        rb1 = returnedLibrary.findBookWithId(1);
        rb2 = returnedLibrary.findBookWithId(2);
        rb3 = returnedLibrary.findBookWithId(3);
    }

}
