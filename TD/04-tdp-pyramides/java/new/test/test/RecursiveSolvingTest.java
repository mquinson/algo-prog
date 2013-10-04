package test;

import pyramid.RecursiveSolvingPyramid;
import pyramid.AbstractPyramid;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class RecursiveSolvingTest {

    public RecursiveSolvingTest() {
    }

    @Test
    public void testFullySolved1() {
        AbstractPyramid p = new RecursiveSolvingPyramid(1);
        assertTrue(p.solve());
    }

    @Test
    public void testFullySolved2() {
        AbstractPyramid p = new RecursiveSolvingPyramid(2);
        assertTrue(p.solve());
    }

    @Test
    public void testFullySolved3() {
        AbstractPyramid p = new RecursiveSolvingPyramid(3);
        assertTrue(p.solve());
    }

    @Test
    public void testFullySolved4() {
        AbstractPyramid p = new RecursiveSolvingPyramid(4);
        assertTrue(p.solve());
    }

    @Test
    public void testFullySolved5() {
        AbstractPyramid p = new RecursiveSolvingPyramid(5);
        assertTrue(p.solve());
    }

    @Test
    public void testPartiallySolved6() {
        AbstractPyramid p = new RecursiveSolvingPyramid(6);
        assertFalse(p.solve());
        assertEquals(20, filledCount(p));
    }

    @Test
    public void testPartiallySolved7() {
        AbstractPyramid p = new RecursiveSolvingPyramid(7);
        assertFalse(p.solve());
        assertEquals(24, filledCount(p)); // and not 25 !
    }

    @Test
    public void testPartiallyFilled8() {
        AbstractPyramid p = new RecursiveSolvingPyramid(8);
        assertFalse(p.solve());
        assertEquals(31, filledCount(p));
    }

    public static int filledCount(AbstractPyramid p) {
        int size = p.getSize();
        int[] values = p.getValues();
        int empty = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (values[i] == 0) {
                empty++;
            }
        }

        return size - empty;
    }
}
