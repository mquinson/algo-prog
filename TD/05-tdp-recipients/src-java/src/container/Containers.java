package container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author oster
 */
public class Containers implements Iterable<Container> {

    protected List<Container> containers;

    public Containers() {
        this.containers = new ArrayList<Container>();
    }

    public Containers(Containers containers) {
        this();
        for (Container c : containers)
            this.containers.add(c.copy());
    }

    public Iterator<Container> iterator() {
        return this.containers.iterator();
    }

    public void add(Container c) {
        this.containers.add(c);
    }

    public boolean hasContainerWithCapacity(int expectedCapacity) {
        for (Container c : containers) {
            if (c.getVolume() == expectedCapacity) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Container c : this.containers) {
            res.append(c.toString());
            res.append("[/");
            res.append(c.getInitialCapacity());
            res.append("]; ");
        }
        return res.toString();
    }

    public static Containers fromCapacityArray(int[] capacities) {
        Containers cs = new Containers();
        int id = 0;
        for (int capacity : capacities) {
            cs.add(new Can("C" + (++id), capacity));
        }
        return cs;
    }


    // do not call hashCode() since it does not fulfill hashCode contract.
    // It does not take into account initialCapacity and name properties.
    // FIXME: wrong hashValue, we should implements some kind of linearization
    // of n dimensions arrays.

    public int hashValueWrong() {
        int hash = 0;
        int i = 0;
        for (Container c : containers) {
            if (!(c instanceof Fountain)) {
                hash += PRIME_NUMBERS[i++] * c.getVolume();
            }
        }

        return hash;
    }

    private final static byte[] PRIME_NUMBERS = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};


    public int hashValue() {
        int coef = 1;
        int res = 0;

        for (Container c : containers) {
            if (!(c instanceof Fountain)) {
                res += coef*c.getVolume();
                coef *= (c.getInitialCapacity()+1);
            }
        }

        return res;
    }

    public String stringValue() {
        StringBuilder s = new StringBuilder();
        for (Container c : containers) {
            if (!(c instanceof Fountain)) {
                s.append(c.getVolume());
                s.append(".");
            }
        }
        return s.toString();
    }

    public Containers copy() {
        return new Containers(this);
    }

    public void emptyAll() {
        for (Container c : containers) {
            if (!(c instanceof Fountain)) {
                c.empty();
            }
        }
    }

}
