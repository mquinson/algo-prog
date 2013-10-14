package container;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oster
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        int[] capacities = new int[]{5, 3};
        int expected = 4;
        int depth = 6;
         */
        int[] capacities = new int[]{8, 5, 3};
        int expected = 6;
        int depth = 4;

        Containers containers = Containers.fromCapacityArray(capacities);
        containers.add(new Fountain());

        DepthFirstSolver s = new DepthFirstSolver(containers, expected, depth);
        System.out.println(s);
        if (s.solve()) {
            System.out.println("Found a solustion:");
            System.out.println(s.getSequence());
        } else {
            System.out.println("No solution found.");
        }
    }
}
