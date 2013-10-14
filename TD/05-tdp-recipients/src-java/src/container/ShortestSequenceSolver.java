package container;

import java.util.HashMap;

/**
 *
 * @author oster
 */
public class ShortestSequenceSolver {

    private Containers containers;
    private int expectedCapacity;
    private TransferSequence shortestSequence;
    private TransferSequence sequence;
    private int maxDepth;
    public HashMap<String,Integer> alreadyChecked = new HashMap<String,Integer>();


    public ShortestSequenceSolver(Containers containers, int expectedCapacity) {
        this.containers = containers;
        this.expectedCapacity = expectedCapacity;
        this.sequence = new TransferSequence();
        this.maxDepth = 100;
    }

    public TransferSequence getSequence() {
        return this.shortestSequence;
    }

    public boolean solve() {
        transvase(1);
        return (this.shortestSequence != null);
    }

    public boolean transvase(int depth) {
        String hashValue = containers.stringValue();
        if (alreadyChecked.containsKey(hashValue) && alreadyChecked.get(hashValue) <= sequence.size())
            return false;

        if (depth > maxDepth)
            return false;

        if (containers.hasContainerWithCapacity(expectedCapacity)) {
            if (shortestSequence == null || shortestSequence.size() > sequence.size()) {
                shortestSequence = new TransferSequence(sequence);
            }
            return false;
        } else {
            alreadyChecked.put(hashValue, sequence.size());
        }
        hashValue = null;

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
