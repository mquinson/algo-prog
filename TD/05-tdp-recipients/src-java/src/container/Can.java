package container;

/**
 *
 * @author oster
 */
public class Can implements Container {

    private String name;

    private int initialCapacity;

    private int volume;

    public Can(String name, int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.volume = 0;
        this.name = name;
    }

    public int getInitialCapacity() {
        return this.initialCapacity;
    }

    public int getVolume() {
        return this.volume;
    }

    public int getRemainingVolume() {
        return this.initialCapacity - this.volume;
    }

    public void fill(int volumeToFill) {
        //if (volumeToFill > getRemainingVolume())
        //    throw new RuntimeException("Try to fill with an overestimated volume");
        this.volume += volumeToFill;
    }

    public void empty(int volumeToEmpty) {
        //if (volumeToEmpty > this.volume)
        //    throw new RuntimeException("Try to empty an overestimated volume");
        this.volume -= volumeToEmpty;
    }

    public void empty() {
        this.volume = 0;
    }

    public int transferTo(Container target) {
        int volumeToTransfer =  Math.min(getVolume(), target.getRemainingVolume());
        empty(volumeToTransfer);
        target.fill(volumeToTransfer);
        return volumeToTransfer;
    }

    public Can copy() {
        Can c = new Can(this.name, this.initialCapacity);
        c.volume = this.volume;
        return c;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
