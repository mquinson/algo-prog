package test;

import pyramid.BruteForceSolvingPyramid;
import org.junit.Test;
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
        BruteForceSolvingPyramid p = new BruteForceSolvingPyramid(1);
        p.setValues(1, new int[]{1});
        assertTrue(p.isCorrect());

        p.setValues(1, new int[]{0});
        assertFalse(p.isCorrect());

        p.setValues(1, new int[]{2});
        assertFalse(p.isCorrect());
    }

    @Test
    public void testCorrect2() {
        BruteForceSolvingPyramid p = new BruteForceSolvingPyramid(2);
        p.setValues(2, new int[]{3, 2, 1});
        assertTrue(p.isCorrect());
    }

    @Test
    public void testCorrect3() {
        BruteForceSolvingPyramid p = new BruteForceSolvingPyramid(3);
        p.setValues(3, new int[]{1, 6, 5, 4, 2, 3});
        assertTrue(p.isCorrect());
    }

    @Test
    public void testCorrect4() {
        BruteForceSolvingPyramid p = new BruteForceSolvingPyramid(4);
        p.setValues(4, new int[]{6, 1, 5, 10, 9, 4, 8, 2, 7, 3});
        assertTrue(p.isCorrect());
    }
}
