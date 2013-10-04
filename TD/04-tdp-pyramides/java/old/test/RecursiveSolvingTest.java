package test;

import org.junit.Test;

import pyramide.Pyramide;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class RecursiveSolvingTest {

    public RecursiveSolvingTest() {
    }

    @Test
    public void testFullyremplird1() {
        Pyramide p = new Pyramide(1);
        assertTrue(p.remplir());
    }

    @Test
    public void testFullyremplird2() {
    	Pyramide p = new Pyramide(2);
        assertTrue(p.remplir());
    }

    @Test
    public void testFullyremplird3() {
    	Pyramide p = new Pyramide(3);
        assertTrue(p.remplir());
    }

    @Test
    public void testFullyremplird4() {
    	Pyramide p = new Pyramide(4);
        assertTrue(p.remplir());
    }

    @Test
    public void testFullyremplird5() {
    	Pyramide p = new Pyramide(5);
        assertTrue(p.remplir());
    }

    @Test
    public void testPartiallyremplird6() {
    	Pyramide p = new Pyramide(6);
        assertFalse(p.remplir());
        assertEquals(20, filledCount(p));
    }

    @Test
    public void testPartiallyremplird7() {
    	Pyramide p = new Pyramide(7);
        assertFalse(p.remplir());
        System.out.println(filledCount(p));
        //assertEquals(24, filledCount(p)); // and not 25 !
    }

    @Test
    public void testPartiallyFilled8() {
    	Pyramide p = new Pyramide(8);
        assertFalse(p.remplir());
        assertEquals(31, filledCount(p));
    }

    public static int filledCount(Pyramide p) {
        int size = p.count();
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
