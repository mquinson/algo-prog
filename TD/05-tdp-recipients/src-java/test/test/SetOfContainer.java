package test;

import container.Can;
import container.Containers;
import container.Fountain;

/**
 *
 * @author oster
 */
public class SetOfContainer extends Containers {

    /*
     * A Small DSL
     */

    public static SetOfContainer create() {
        return new SetOfContainer();
    }

    public SetOfContainer addCan(String name, int capacity) {
        this.containers.add(new Can(name, capacity));
        return this;
    }

    public SetOfContainer addCan(String name, int initialCapacity, int volume) {
        Can c = new Can(name, initialCapacity);
        c.fill(volume);
        this.containers.add(c);
        return this;
    }

    public SetOfContainer addFountain() {
        this.containers.add(new Fountain());
        return this;
    }
}
