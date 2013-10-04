package test;

import pyramid.RecursiveSolvingPyramid;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class ContainsTest {

    public ContainsTest() {
    }

    @Test
    public void testContainsInSubPyramid() {
        RecursiveSolvingPyramid p = new RecursiveSolvingPyramid(3);
        p.setValues(3, new int[]{0, 1, 2, 3, 4, 5});

        // diag 1
        assertTrue(p.contains(0, 1));
        assertFalse(p.contains(1, 1)); // loop is going too far

        // diag 2
        assertTrue(p.contains(0, 2));
        assertTrue(p.contains(1, 2));
        assertTrue(p.contains(2, 2));
        assertFalse(p.contains(3, 2)); // loop is going too far

        // diag 3
        assertTrue(p.contains(0, 3));
        assertTrue(p.contains(1, 3));
        assertTrue(p.contains(2, 3));
        assertTrue(p.contains(3, 3));
        assertTrue(p.contains(4, 3));
        assertTrue(p.contains(5, 3));
    }

    @Test
    public void testContainsInPartialSubPyramid() {
        RecursiveSolvingPyramid p = new RecursiveSolvingPyramid(3);
        p.setValues(3, new int[]{0, 1, 2, 3, 4, 5});

        // diag 1
        assertTrue(p.contains(0, 1, 1));
        assertFalse(p.contains(1, 1, 1)); // loop is going too far

        // diag 2
        assertTrue(p.contains(0, 2, 1));
        assertTrue(p.contains(1, 2, 1));
        assertFalse(p.contains(2, 2, 1)); // loop is going too far

        assertTrue(p.contains(0, 2, 2));
        assertTrue(p.contains(1, 2, 2));
        assertTrue(p.contains(2, 2, 2));
        assertFalse(p.contains(3, 2, 2)); // loop is going too far

        // diag 3
        assertTrue(p.contains(0, 3, 1));
        assertTrue(p.contains(1, 3, 1));
        assertTrue(p.contains(2, 3, 1));
        assertTrue(p.contains(3, 3, 1));
        assertFalse(p.contains(4, 3, 1)); // loop is going too far

        assertTrue(p.contains(0, 3, 2));
        assertTrue(p.contains(1, 3, 2));
        assertTrue(p.contains(2, 3, 2));
        assertTrue(p.contains(3, 3, 2));
        assertTrue(p.contains(4, 3, 2));
        assertFalse(p.contains(5, 3, 2)); // loop is going too far

        assertTrue(p.contains(0, 3, 3));
        assertTrue(p.contains(1, 3, 3));
        assertTrue(p.contains(2, 3, 3));
        assertTrue(p.contains(3, 3, 3));
        assertTrue(p.contains(4, 3, 3));
        assertTrue(p.contains(5, 3, 3)); // loop is going too far
    }
}
