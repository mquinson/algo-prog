package container;

/**
 *
 * @author oster
 */
public interface Container {

    public int getInitialCapacity();

    public int getVolume();

    public int getRemainingVolume();

    public void fill(int volume);

    public void empty(int volume);

    public void empty();

    public int transferTo(Container target);

    public Container copy();

}
