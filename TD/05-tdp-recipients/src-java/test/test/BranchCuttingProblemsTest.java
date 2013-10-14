package test;

import container.BranchCuttingSolver;
import container.Containers;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class BranchCuttingProblemsTest {

    @Test
    public void testSolveA() {
        Containers containers = SetOfContainer.create().addCan("A", 5).addCan("B", 3).addFountain();

        BranchCuttingSolver s = new BranchCuttingSolver(containers, 4);
        assertTrue(s.solve());
        //assertEquals("F-(5)->A; A-(3)->B; B-(3)->F; A-(2)->B; F-(5)->A; A-(1)->B; ", s.getSequence().toString());
    }

    @Test
    public void testSolveB() {
        Containers containers = SetOfContainer.create().addCan("A", 8).addCan("B", 5).addCan("C", 3).addFountain();

        BranchCuttingSolver s = new BranchCuttingSolver(containers, 6);
        assertTrue(s.solve());
        //assertEquals("F-(8)->A; A-(5)->B; B-(3)->C; C-(3)->A; ", s.getSequence().toString());
    }

    @Test
    public void testSolveOswald() {
        Containers containers = SetOfContainer.create().addCan("A", 100).addCan("B", 25).addCan("C", 24).addFountain();

        BranchCuttingSolver s = new BranchCuttingSolver(containers, 42);
        assertTrue(s.solve());
        //System.out.println(s.alreadyChecked.size());
        //System.out.println(s.getSequence());
    }
}
