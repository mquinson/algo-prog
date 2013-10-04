package test;

import java.util.Arrays;

import org.junit.Test;

import pyramide.Pyramide;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class PropagationTest {

    public PropagationTest() {
    }

    @Test
    public void testpropager() {
        // diag 1
        Pyramide p = new Pyramide(3);
        p.setValues(3, new int[]{0, 0, 0, 0, 0, 0});
        assertTrue(p.propager(1, 1));
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0}, p.getValues());


        // diag 2
        p.setValues(3, new int[]{1, 0, 0, 0, 0, 0});
        assertFalse(p.propager(2, 2));

        // diag 2
        p.setValues(3, new int[]{1, 0, 0, 0, 0, 0});
        assertTrue(p.propager(2, 3));
//        System.out.println(p.propager(2, 3));
//        System.out.println(Arrays.toString(p.getValues()));
        assertArrayEquals(new int[]{1, 3, 2, 0, 0, 0}, p.getValues());

        // diag 3
        p.setValues(3, new int[]{1, 3, 2, 0, 0, 0});
        assertFalse(p.propager(3, 4));

        p.setValues(3, new int[]{1, 3, 2, 0, 0, 0});
        assertFalse(p.propager(3, 5));

        p.setValues(3, new int[]{1, 3, 2, 0, 0, 0});
        assertFalse(p.propager(3, 6));

        p.setValues(3, new int[]{1, 6, 5, 0, 0, 0});
        assertTrue(p.propager(3, 4));
        assertArrayEquals(new int[]{1, 6, 5, 4, 2, 3}, p.getValues());
    }
}
