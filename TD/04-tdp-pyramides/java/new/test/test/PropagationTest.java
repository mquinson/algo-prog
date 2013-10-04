package test;

import pyramid.RecursiveSolvingPyramid;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class PropagationTest {

    public PropagationTest() {
    }

    @Test
    public void testPropagate() {
        // diag 1
        RecursiveSolvingPyramid p = new RecursiveSolvingPyramid(3);
        p.setValues(3, new int[]{0, 0, 0, 0, 0, 0});
        assertTrue(p.propagate(1, 1));
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0}, p.getValues());


        // diag 2
        p.setValues(3, new int[]{1, 0, 0, 0, 0, 0});
        assertFalse(p.propagate(2, 2));

        // diag 2
        p.setValues(3, new int[]{1, 0, 0, 0, 0, 0});
        assertTrue(p.propagate(3, 2));
        assertArrayEquals(new int[]{1, 3, 2, 0, 0, 0}, p.getValues());

        // diag 3
        p.setValues(3, new int[]{1, 3, 2, 0, 0, 0});
        assertFalse(p.propagate(4, 3));

        p.setValues(3, new int[]{1, 3, 2, 0, 0, 0});
        assertFalse(p.propagate(5, 3));

        p.setValues(3, new int[]{1, 3, 2, 0, 0, 0});
        assertFalse(p.propagate(6, 3));

        p.setValues(3, new int[]{1, 6, 5, 0, 0, 0});
        assertTrue(p.propagate(4, 3));
        assertArrayEquals(new int[]{1, 6, 5, 4, 2, 3}, p.getValues());
    }
}
