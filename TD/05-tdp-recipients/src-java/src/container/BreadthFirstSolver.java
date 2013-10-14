package container;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author oster
 */
public class BreadthFirstSolver {

    private Containers containers;
    private int expectedCapacity;
    private EvaluableTransferSequence shortestSequence;
    //private List<Integer> alreadyChecked = new ArrayList<Integer>();
    private Set<Integer> alreadyChecked = new HashSet<Integer>();

    public BreadthFirstSolver(Containers containers, int expectedCapacity) {
        this.containers = containers;
        this.expectedCapacity = expectedCapacity;
    }

    public EvaluableTransferSequence getSequence() {
        return this.shortestSequence;
    }

//    int maxSize = 0;

   public boolean solve() {
        Deque<EvaluableTransferSequence>  sequencesToEvaluate = new ArrayDeque<EvaluableTransferSequence>();

        EvaluableTransferSequence sequence = new EvaluableTransferSequence(containers);
        sequencesToEvaluate.add(sequence);

        
        Containers c = null;
        Integer hashValue = null;
        EvaluableTransferSequence origSequence = null;

        while (!sequencesToEvaluate.isEmpty()) {
            sequence = sequencesToEvaluate.removeFirst();
/*
·            if (sequence.size() > maxSize) {
                maxSize = sequence.size();
                System.out.println(maxSize);
            }
*/
            if (shortestSequence != null && sequence.size() > shortestSequence.size()) {
                continue; // or break?
            }

            
            c = sequence.evaluate();
            hashValue = c.hashValue();
            
            if (alreadyChecked.contains(hashValue))
                continue;
            alreadyChecked.add(hashValue);


            if (c.hasContainerWithCapacity(expectedCapacity)) {
                this.shortestSequence = sequence;
                continue;
            }

            origSequence = sequence;
            for (Container src : containers) {
                for (Container dst : containers) {
                    if (src != dst) {
                        sequence = new EvaluableTransferSequence(origSequence);
                        sequence.addTransfert(src, dst);
                        sequencesToEvaluate.addLast(sequence);
                    }
                }
            }
        }

        return (shortestSequence != null);
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
