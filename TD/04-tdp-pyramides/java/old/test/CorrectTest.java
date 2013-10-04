package test;

import org.junit.Test;

import pyramide.Pyramide;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class CorrectTest {

    public CorrectTest() {
    }

    @Test
    public void testCorrect1() {
        Pyramide p = new Pyramide(1);
        p.setValues(1, new int[]{1});
        assertTrue(p.correcte());

        p.setValues(1, new int[]{0});
        assertFalse(p.correcte());

        p.setValues(1, new int[]{2});
        assertFalse(p.correcte());
    }

    @Test
    public void testCorrect2() {
    	Pyramide p = new Pyramide(2);
        p.setValues(2, new int[]{3, 2, 1});
        assertTrue(p.correcte());
    }

    @Test
    public void testCorrect3() {
    	Pyramide p = new Pyramide(3);
        p.setValues(3, new int[]{1, 6, 5, 4, 2, 3});
        assertTrue(p.correcte());
    }

    @Test
    public void testCorrect4() {
        Pyramide p = new Pyramide(4);
        p.setValues(4, new int[]{6, 1, 5, 10, 9, 4, 8, 2, 7, 3});
        assertTrue(p.correcte());
    }
}
