package test;

import container.TransferSequence;
import container.Can;
import container.Fountain;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class BasicTest {


    public BasicTest() {
    }

    @Test
    public void testSequenceA() {
        Can a = new Can("A", 5);
        Can b = new Can("B", 3);
        Fountain f = new Fountain();

        f.transferTo(a);
        a.transferTo(b);
        b.transferTo(f);
        a.transferTo(b);
        f.transferTo(a);
        a.transferTo(b);

        assertTrue(a.getVolume() == 4);
    }

    @Test
    public void testSequenceB() {
        Can a = new Can("A", 5);
        Can b = new Can("B", 3);
        Fountain f = new Fountain();

        TransferSequence seq = new TransferSequence();
        seq.addTransfert(f, a);
        seq.addTransfert(a, b);
        seq.addTransfert(b, f);
        seq.addTransfert(a, b);
        seq.addTransfert(f, a);
        seq.addTransfert(a, b);

        assertTrue(a.getVolume() == 4);
    }

}
