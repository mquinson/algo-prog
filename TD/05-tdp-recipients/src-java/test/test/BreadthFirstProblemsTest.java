package test;

import container.BreadthFirstSolver;
import container.ShortestSequenceSolver;
import container.Containers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oster
 */
public class BreadthFirstProblemsTest {

    public BreadthFirstProblemsTest() {
    }

    @Test
    public void testSolveA() {
        Containers containers = SetOfContainer.create().addCan("A", 5).addCan("B", 3).addFountain();

        BreadthFirstSolver s = new BreadthFirstSolver(containers, 4);
        assertTrue(s.solve());
        //assertEquals("F-(5)->A; A-(3)->B; B-(3)->F; A-(2)->B; F-(5)->A; A-(1)->B; ", s.getSequence().toString());
        System.out.println(s.getSequence().size());
        System.out.println(s.getSequence());
        assertEquals(6, s.getSequence().size());
    }

    @Test
    public void testSolveB() {
        Containers containers = SetOfContainer.create().addCan("A", 8).addCan("B", 5).addCan("C", 3).addFountain();

        BreadthFirstSolver s = new BreadthFirstSolver(containers, 6);
        assertTrue(s.solve());
        System.out.println(s.getSequence().size());
        System.out.println(s.getSequence());
        //assertEquals("F-(8)->A; A-(5)->B; B-(3)->C; C-(3)->A; ", s.getSequence().toString());
        assertEquals(4, s.getSequence().size());
    }

    @Test
    public void testSolveOswald() {
        Containers containers = SetOfContainer.create().addCan("A", 100).addCan("B", 25).addCan("C", 24).addFountain();

        BreadthFirstSolver s = new BreadthFirstSolver(containers, 42);
        assertTrue(s.solve());
        //System.out.println(s.alreadyChecked.size());
        System.out.println(s.getSequence().size());
        System.out.println(s.getSequence());
        assertEquals(21, s.getSequence().size());
    }

    @Test
    public void testSolve752() {
        //Containers containers = SetOfContainer.create().addCan("A", 1597).addCan("B", 2584).addCan("C", 4181).addFountain();
        Containers containers = SetOfContainer.create().addCan("A", 377).addCan("B", 610).addFountain();

        BreadthFirstSolver s = new BreadthFirstSolver(containers, 1);
        assertTrue(s.solve());
        //System.out.println(s.alreadyChecked.size());
        System.out.println(s.getSequence().size());
        System.out.println(s.getSequence());
        assertEquals(752, s.getSequence().size());
    }

    @Test
    public void testSolve1218() {
        //Containers containers = SetOfContainer.create().addCan("A", 1597).addCan("B", 2584).addCan("C", 4181).addFountain();
        Containers containers = SetOfContainer.create().addCan("A", 610).addCan("B", 987).addFountain();

        BreadthFirstSolver s = new BreadthFirstSolver(containers, 1);
        assertTrue(s.solve());
        //System.out.println(s.alreadyChecked.size());
        System.out.println(s.getSequence().size());
        System.out.println(s.getSequence());
        assertEquals(1218, s.getSequence().size());
    }

    @Test
    public void testSolve1972() {
        //Containers containers = SetOfContainer.create().addCan("A", 1597).addCan("B", 2584).addCan("C", 4181).addFountain();
        Containers containers = SetOfContainer.create().addCan("A", 987).addCan("B", 1597).addFountain();

        BreadthFirstSolver s = new BreadthFirstSolver(containers, 1);
        assertTrue(s.solve());
        //System.out.println(s.alreadyChecked.size());
        System.out.println(s.getSequence().size());
        System.out.println(s.getSequence());
        assertEquals(1972, s.getSequence().size());
    }

    @Test
    public void testSolveBrongniart() {
        //Containers containers = SetOfContainer.create().addCan("A", 1597).addCan("B", 2584).addCan("C", 4181).addFountain();
        Containers containers = SetOfContainer.create().addCan("A", 1597).addCan("B", 2584).addFountain();

        BreadthFirstSolver s = new BreadthFirstSolver(containers, 1);
        assertTrue(s.solve());
        //System.out.println(s.alreadyChecked.size());
        System.out.println(s.getSequence().size());
        System.out.println(s.getSequence());
        assertEquals(3192, s.getSequence().size());
    }
}
