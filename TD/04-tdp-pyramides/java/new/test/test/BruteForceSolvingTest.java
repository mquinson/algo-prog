package test;

import pyramid.BruteForceSolvingPyramid;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class BruteForceSolvingTest {

    public BruteForceSolvingTest() {
    }

    @Test
    public void testFullySolved1() {
        BruteForceSolvingPyramid p = new BruteForceSolvingPyramid(2);
        assertTrue(p.solve());
    }

    @Test
    public void testFullySolved2() {
        BruteForceSolvingPyramid p = new BruteForceSolvingPyramid(2);
        assertTrue(p.solve());
    }

    @Test
    public void testFullySolved3() {
        BruteForceSolvingPyramid p = new BruteForceSolvingPyramid(3);
        assertTrue(p.solve());
    }

    @Test
    public void testFullySolved4() {
        BruteForceSolvingPyramid p = new BruteForceSolvingPyramid(4);
        assertTrue(p.solve());
    }
/*
    @Test
    public void testFullySolved5() {
        BruteForceSolvingPyramid p = new BruteForceSolvingPyramid(5);
        assertTrue(p.solve());
    }
*/
 }