package test;

import pyramid.AbstractPyramid;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class BasicTest {

    private AbstractPyramid p;

    public BasicTest() {
    }

    @Before
    public void setUp() {
        p = new DummyPyramid(3);
        p.setValues(3, new int[]{0, 1, 2, 3, 4, 5});
    }

    @Test
    public void testGetHeight() {
        AbstractPyramid p2 = new DummyPyramid(1);
        assertEquals(1, p2.getHeight());

        p2 = new DummyPyramid(3);
        assertEquals(3, p2.getHeight());

        p2 = new DummyPyramid(5);
        assertEquals(5, p2.getHeight());
    }

    @Test
    public void testGetSize() {
        AbstractPyramid p2 = new DummyPyramid(1);
        assertEquals(1, p2.getSize());

        p2 = new DummyPyramid(2);
        assertEquals(3, p2.getSize());

        p2 = new DummyPyramid(3);
        assertEquals(6, p2.getSize());

        p2 = new DummyPyramid(5);
        assertEquals(15, p2.getSize());
    }

    @Test
    public void testEquals() {
        AbstractPyramid p2 = new DummyPyramid(3);
        p2.setValues(3, new int[]{0, 1, 2, 3, 4, 5});
        assertEquals(p, p2);

        p2.setValues(2, new int[]{0, 1, 2});
        assertFalse(p2.equals(p));

        p2.setValues(3, new int[]{5, 1, 2, 3, 4, 5});
        assertFalse(p2.equals(p));

        p2.setValues(3, new int[]{0, 1, 2, 3, 4, 0});
        assertFalse(p2.equals(p));
    }

    @Test
    public void testComputeIndex() {
        assertEquals(0, p.computeIndex(1,1));
        assertEquals(1, p.computeIndex(2,1));
        assertEquals(2, p.computeIndex(2,2));
        assertEquals(3, p.computeIndex(3,1));
        assertEquals(4, p.computeIndex(3,2));
        assertEquals(5, p.computeIndex(3,3));
    }


    @Test
    public void testFillWithIndex() {
        AbstractPyramid p2 = new DummyPyramid(3);
        p2.fillWithIndex();
        assertEquals(p, p2);
    }

    class DummyPyramid extends AbstractPyramid {

        public DummyPyramid(int height) {
            super(height);
        }

        public boolean solve() {
            return true;
        }
    }
}
