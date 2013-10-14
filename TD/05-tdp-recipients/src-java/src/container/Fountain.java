package container;

/**
 *
 * @author oster
 */
public class Fountain implements Container {

    public Fountain() {
    }

    public int getInitialCapacity() {
        return 0;
    }

    public int getVolume() {
        return 0;
    }

    public int getRemainingVolume() {
        return Integer.MAX_VALUE;
    }

    public void fill(int volume) {
    }

    public void empty(int volume) {
    }

    public void empty() {
    }
    
    public int transferTo(Container target) {
        int volumeToTransfer = target.getRemainingVolume();
        target.fill(volumeToTransfer);
        return volumeToTransfer;
    }

    public Fountain copy() {
        return new Fountain();
    }

    @Override
    public String toString() {
        return "F";
    }
}
