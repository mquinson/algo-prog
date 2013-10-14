package container;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oster
 */
public class EvaluableTransferSequence {

    private List<Transfer> sequence;

    private Containers containers;

    public EvaluableTransferSequence(Containers containers) {
        this.sequence = new ArrayList<Transfer>();
        this.containers = containers;
    }

    public EvaluableTransferSequence(EvaluableTransferSequence other) {
        this(other.containers);
        this.sequence = new ArrayList(other.sequence);
    }

    public void addTransfert(Container src, Container dst) {
        Transfer t = new Transfer(src,dst);
        this.sequence.add(t);
        //t.apply();
    }

    public Containers getContainers() {
        return this.containers;
    }

    /*
    public void removeLastTransfer() {
        Transfer t = this.sequence.remove(this.sequence.size()-1);
        //t.undo();
    }
     */

    public int size() {
        return this.sequence.size();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Transfer t : sequence) {
            res.append(t.toString());
            res.append("; ");
        }
        return res.toString();
    }

    public Containers evaluate() {
        containers.emptyAll();

        for (Transfer t : sequence)
            t.apply();

        return this.containers;
    }
}
