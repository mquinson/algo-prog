package container;

/**
 *
 * @author oster
 */
public class DepthFirstSolver {

    private Containers containers;
    private int expectedCapacity;
    private TransferSequence sequence;
    private int maxDepth;

    public DepthFirstSolver(Containers containers, int expectedCapacity, int maxDepth) {
        this.containers = containers;
        this.expectedCapacity = expectedCapacity;
        this.maxDepth = maxDepth;
        this.sequence = new TransferSequence();
    }

    public TransferSequence getSequence() {
        return this.sequence;
    }

    public boolean solve() {
        return transvase(1);
    }

    public boolean transvase(int depth) {
        if (containers.hasContainerWithCapacity(expectedCapacity)) {
            return true;
        }
        
        if (depth > maxDepth) {
            return false;
        }

        for (Container src : containers) {
            for (Container dst : containers) {
                if (src != dst) {
                    sequence.addTransfert(src, dst);
                    if (transvase(depth + 1)) {
                        return true;
                    }
                    sequence.removeLastTransfer();
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(this.containers);
        res.append("\n");
        res.append("Expected volume: ");
        res.append(expectedCapacity);
        return res.toString();
    }
}
