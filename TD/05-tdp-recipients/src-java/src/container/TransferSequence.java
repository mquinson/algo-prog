package container;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oster
 */
public class TransferSequence {

    private List<Transfer> sequence;

    public TransferSequence() {
        this.sequence = new ArrayList<Transfer>();
    }

    public TransferSequence(TransferSequence other) {
        this();

        for (Transfer t : other.sequence) {
            this.sequence.add(t); // TODO: should we go deeper?
        }
    }

    public void addTransfert(Container src, Container dst) {
        Transfer t = new Transfer(src,dst);
        this.sequence.add(t);
        t.apply();
    }

    public void removeLastTransfer() {
        Transfer t = this.sequence.remove(this.sequence.size()-1);
        t.undo();
    }

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

    public TransferSequence copy() {
        return new TransferSequence(this);
    }
}
