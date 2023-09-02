package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class TagTest {
    Tag a1;
    Tag a2;
    Tag a3;
    HashSet<Integer> w1;


    @BeforeEach
    public void runBefore() {
        a1 = new Tag("Gabriel Garcia Marquez");
        a2 = new Tag("J.R.R. Tolkien");
    }

    @Test
    public void testFullTagConstructor() {
        w1 = new HashSet<>();
        w1.add(6);
        a3 = new Tag("Gabriel Garcia Marquez", w1);
        assertEquals(w1, a3.getWorks());
    }

    @Test
    public void testTag() {
        assertEquals(0, a1.getWorks().size());
        assertEquals("Gabriel Garcia Marquez", a1.getTagName());
    }

    @Test
    public void testSetTagName() {
        a2.setTagName("another tag");
        assertEquals("another tag", a2.getTagName());
    }

    @Test
    public void testAddWorks() {
        a1.addWork(23);
        assertEquals(1, a1.getWorks().size());
        assertTrue(a1.getWorks().contains(23));

        a1.addWork(12);
        a1.addWork(11);
        assertEquals(3, a1.getWorks().size());
        assertTrue(a1.getWorks().contains(12));
        assertTrue(a1.getWorks().contains(11));
    }

    @Test
    public void testRemoveWorks() {
        a1.addWork(23);
        a1.addWork(12);
        a1.addWork(11);

        a1.removeWork(12);
        a1.removeWork(99);
        assertEquals(2, a1.getWorks().size());
        assertFalse(a1.getWorks().contains(12));

        a1.removeWork(23);
        a1.removeWork(11);
        assertEquals(0, a1.getWorks().size());
    }
}
