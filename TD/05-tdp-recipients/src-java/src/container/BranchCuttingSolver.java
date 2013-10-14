package container;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oster
 */
public class BranchCuttingSolver {

    private Containers containers;
    private int expectedCapacity;
    private TransferSequence sequence;
    public List<String> alreadyChecked = new ArrayList<String>();


    public BranchCuttingSolver(Containers containers, int expectedCapacity) {
        this.containers = containers;
        this.expectedCapacity = expectedCapacity;
        this.sequence = new TransferSequence();
    }

    public TransferSequence getSequence() {
        return this.sequence;
    }

    public boolean solve() {
        return transvase(1);
    }

    public boolean transvase(int depth) {
        String hashValue = containers.stringValue();
        if (alreadyChecked.contains(hashValue))
            return false;

        if (containers.hasContainerWithCapacity(expectedCapacity)) {
            return true;
        } else {
            alreadyChecked.add(hashValue);
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
