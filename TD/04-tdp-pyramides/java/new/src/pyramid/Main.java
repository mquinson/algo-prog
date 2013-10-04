package pyramid;

/**
 *
 * @author oster
 */
public class Main {

    /**
     * @param height of the pyramid to solve.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: pyramid.Main <height>");
            System.exit(1);
        }
        int height = Integer.parseInt(args[0]);

        AbstractPyramid p = new RecursiveSolvingPyramid(height);

        long startTime = System.currentTimeMillis();
        boolean solved = p.solve();
        long endTime = System.currentTimeMillis();

        if (solved) {
            System.out.println("At least one solution exist.");
            System.out.println(p.toString());
        } else {
            System.out.println("There is no complete solution.");
        }

        System.out.println("Computation time: " + (endTime - startTime) + " ms");

        // compute partial completion
        int empty = 0;
        int[] values = p.getValues();
        for (int i = values.length - 1; i >= 0; i--) {
            if (values[i] == 0) {
                empty++;
            }
        }
        
        int size = p.getSize();
        int filled = size - empty;
        System.out.println("Filling: " + filled + "/" + size);
    }
}
