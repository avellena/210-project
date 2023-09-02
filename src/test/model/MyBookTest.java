package model;

import exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MyBookTest {
    MyBook b1;
    MyBook b2;
    ArrayList<Quote> q1;

    @BeforeEach
    public void runBefore() {
        b1 = new MyBook("Love in the Time of Cholera");
        b2 = new MyBook("No One Writes to the Colonel");
    }

    @Test
    public void testBookConstructor() {
        assertEquals("Love in the Time of Cholera", b1.getTitle());
        assertEquals(0, b1.getQuotes().size());
        assertFalse(b1.getId() == b2.getId());
        MyBook.setNextBookId(5);
        MyBook b3 = new MyBook("");
        assertEquals(5, b3.getId());
    }

    @Test
    public void testFullBookConstructor() {
        q1 = new ArrayList<>();
        q1.add(new Quote("The only regret I will have in dying is if it is not for love."));
        b1 = new MyBook(99, "Love in the Time of Cholera", "Gabriel Garcia Marquez", "2020-09-15",
                "fiction", 10,
                "Amazing!",q1);
        assertEquals("Love in the Time of Cholera", b1.getTitle());
        assertEquals("Gabriel Garcia Marquez", b1.getAuthor());
        assertEquals(q1, b1.getQuotes());
        assertEquals("2020-09-15", b1.getDateFinishedReading());
        assertEquals("fiction", b1.getGenre());
        assertEquals("Amazing!", b1.getComment());
        assertTrue(b1.getId() == 99);
    }


    @Test
    public void testSimpleSetters() {
        b1.setTitle("News of a Kidnapping");
        b1.setDateFinishedReading("2020-09-01");
        b1.setRating(9);
        b1.setComment("Good!");
        b1.setGenreName("fiction");
        b1.setAuthorName("Gabriel Garcia Marquez");
        assertEquals("News of a Kidnapping", b1.getTitle());
        assertEquals("2020-09-01", b1.getDateFinishedReading());
        assertEquals("Good!", b1.getComment());
        assertEquals(9, b1.getRating());
        assertEquals("fiction", b1.getGenre());
        assertEquals("Gabriel Garcia Marquez", b1.getAuthor());
    }

    @Test
    public void testEditQuote() {
        Quote quote1 = new Quote("He was still too young to know that the heart's memory eliminates the bad " +
                "and magnifies the good, and that thanks to this artifice we manage to endure the burden of the past.");
        Quote quote2 = new Quote("wisdom comes to us when it can no longer do any good.");
        b1.addQuote(quote1);
        b1.addQuote(quote2);
        assertEquals(2, b1.getQuotes().size());
        assertTrue(b1.getQuotes().contains(quote1));

        b1.removeQuote(quote1);
        assertFalse(b1.getQuotes().contains(quote1));
    }

    @Test
    public void testValidateRating() {
        try {
            b1.validateRating("1");
            b1.validateRating("2");
            b1.validateRating("10");
            b1.validateRating("");
        } catch (Exception e) {
            fail("Should not have thrown exception!");
        }
    }

    @Test
    public void testInvalidRating() {
        try {
            b1.validateRating("11");
            fail("Should have thrown exception!");
        } catch (InvalidInputException e) {
            //expected
            assertEquals("Please input a rating between 1 and 10!", e.getMessage());
        }

        try {
            b1.validateRating("0");
            fail("Should have thrown exception!");
        } catch (InvalidInputException e) {
            //expected
            assertEquals("Please input a rating between 1 and 10!", e.getMessage());
        }

        try {
            b1.validateRating("string");
            fail("Should have thrown exception!");
        } catch (InvalidInputException e) {
            //expected
            assertEquals("Please input a rating between 1 and 10!", e.getMessage());
        }
    }

    @Test
    public void testValidateTitle() {
        try {
            b1.validateTitle("title");
        } catch (InvalidInputException e) {
            fail("Should not have thrown exception!");
        }

        try {
            b1.validateTitle("");
            fail("Should have thrown exception!");
        } catch (InvalidInputException e) {
            //expected
            assertEquals("Title cannot be empty!", e.getMessage());
        }
    }

    @Test
    public void testValidateDate() {
        try {
            b1.validateDate("2023-07-30");
            b1.validateDate("");
        } catch (InvalidInputException e) {
            fail("Should not have thrown exception!");
        }

        try {
            b1.validateDate("20230730");
            fail("Should have thrown exception!");
        } catch (InvalidInputException e) {
            //expected
            assertEquals("Please follow the format yyyy-MM-dd!", e.getMessage());
        }
    }

}