package test;

import org.junit.Before;
import org.junit.Test;

import pyramide.Pyramide;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class BasicTest {

    private Pyramide p;

    public BasicTest() {
    }

    @Before
    public void setUp() {
        p = new Pyramide(3);
        p.setValues(3, new int[]{0, 1, 2, 3, 4, 5});
    }

    @Test
    public void testhauteur() {
    	Pyramide p2 = new Pyramide(1);
        assertEquals(1, p2.hauteur());

        p2 = new Pyramide(3);
        assertEquals(3, p2.hauteur());

        p2 = new Pyramide(5);
        assertEquals(5, p2.hauteur());
    }

    @Test
    public void testcount() {
    	Pyramide p2 = new Pyramide(1);
        assertEquals(1, p2.count());

        p2 = new Pyramide(2);
        assertEquals(3, p2.count());

        p2 = new Pyramide(3);
        assertEquals(6, p2.count());

        p2 = new Pyramide(5);
        assertEquals(15, p2.count());
    }
/*
    @Test
    public void testEquals() {
    	Pyramide p2 = new Pyramide(3);
        p2.setValues(3, new int[]{0, 1, 2, 3, 4, 5});
        assertEquals(p, p2);

        p2.setValues(2, new int[]{0, 1, 2});
        assertFalse(p2.equals(p));

        p2.setValues(3, new int[]{5, 1, 2, 3, 4, 5});
        assertFalse(p2.equals(p));

        p2.setValues(3, new int[]{0, 1, 2, 3, 4, 0});
        assertFalse(p2.equals(p));
    }
    */

    @Test
    public void testindice() {
        assertEquals(0, p.indice(1,1));
        assertEquals(1, p.indice(1,2));
        assertEquals(2, p.indice(2,2));
        assertEquals(3, p.indice(1,3));
        assertEquals(4, p.indice(2,3));
        assertEquals(5, p.indice(3,3));
    }

/*
    @Test
    public void testFillWithIndex() {
        Pyramide p2 = new Pyramide(3);
        p2.fillWithIndex();
        assertEquals(p, p2);
    }
 */
 }
