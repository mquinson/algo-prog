package container;

/**
 *
 * @author oster
 */
public class Transfer {

    private Container source;
    
    private Container target;

    private int volumeTransfered;

    public Transfer(Container source, Container target) {
        this.source = source;
        this.target = target;
    }

    public void apply() {
        volumeTransfered = this.source.transferTo(this.target);
    }

    public void undo() {
        this.target.empty(volumeTransfered);
        this.source.fill(volumeTransfered);
    }

    @Override
    public String toString() {
        return this.source.toString()+"-("+this.volumeTransfered+")->"+this.target.toString();
    }

}
