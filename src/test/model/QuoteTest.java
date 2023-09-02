package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuoteTest {
    Quote q;
    String content;

    @BeforeEach
    public void runBefore() {
        content = "This above all: to thine own self be true.";
        q = new Quote(content);
    }

    @Test
    public void testQuote() {
        assertEquals(content, q.getContent());
        q.setPageNum(10);
        assertEquals(10, q.getPageNum());

        q.setContent("some other content");
        assertEquals("some other content", q.getContent());
    }

    @Test
    public void testFullQuoteConstructor() {
        q = new Quote("some content", 97);
        assertEquals("some content", q.getContent());
        assertEquals(97, q.getPageNum());
    }
}
