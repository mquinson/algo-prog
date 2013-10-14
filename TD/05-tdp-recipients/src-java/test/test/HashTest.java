package test;

import container.Containers;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class HashTest {

    public HashTest() {
    }

    @Test
    public void testHashValue1_14_4() {
        Containers c = SetOfContainer.create().addCan("A", 1, 1).addCan("B", 14, 14).addCan("C", 4, 4);
        assertEquals(101, c.hashValueWrong());

        c = SetOfContainer.create().addCan("X", 17, 1).addCan("Y", 12, 14).addCan("Z", 8, 4);
        assertEquals(101, c.hashValueWrong());
    }

   @Test
    public void testStringValue1_14_4() {
        Containers c = SetOfContainer.create().addCan("A", 1, 1).addCan("B", 14, 14).addCan("C", 4, 4);
        assertEquals("1.14.4.", c.stringValue());

        c = SetOfContainer.create().addCan("X", 17, 1).addCan("Y", 12, 14).addCan("Z", 8, 4);
        assertEquals("1.14.4.", c.stringValue());
    }

   @Test
   public void testHashValue() {
        assertEquals(0, SetOfContainer.create().addCan("A", 2, 0).addCan("B", 3, 0).hashValue());
        assertEquals(1, SetOfContainer.create().addCan("A", 2, 1).addCan("B", 3, 0).hashValue());
        assertEquals(2, SetOfContainer.create().addCan("A", 2, 2).addCan("B", 3, 0).hashValue());

        assertEquals(3, SetOfContainer.create().addCan("A", 2, 0).addCan("B", 3, 1).hashValue());
        assertEquals(4, SetOfContainer.create().addCan("A", 2, 1).addCan("B", 3, 1).hashValue());
        assertEquals(5, SetOfContainer.create().addCan("A", 2, 2).addCan("B", 3, 1).hashValue());

        assertEquals(6, SetOfContainer.create().addCan("A", 2, 0).addCan("B", 3, 2).hashValue());
        assertEquals(7, SetOfContainer.create().addCan("A", 2, 1).addCan("B", 3, 2).hashValue());
        assertEquals(8, SetOfContainer.create().addCan("A", 2, 2).addCan("B", 3, 2).hashValue());

        assertEquals(9, SetOfContainer.create().addCan("A", 2, 0).addCan("B", 3, 3).hashValue());
        assertEquals(10, SetOfContainer.create().addCan("A", 2, 1).addCan("B", 3, 3).hashValue());
        assertEquals(11, SetOfContainer.create().addCan("A", 2, 2).addCan("B", 3, 3).hashValue());
   }
}
