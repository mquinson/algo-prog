package pyramid;

/**
 *
 * @author oster
 */
public class RecursiveSolvingPyramid extends AbstractPyramid {

    public RecursiveSolvingPyramid(int height) {
        super(height);
    }

    public boolean solve() {
        return fill(1);
    }

    public boolean fill(int diag) {
        if (diag > getHeight()) {
            return true;
        }

        for (int value = 1; value <= getSize(); value++) {
            if (!contains(value, diag)) {
                if (propagate(value, diag)) {
                    if (fill(diag + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean contains(int value, int diag) {
        return contains(value, diag, diag);
    }

    public boolean contains(int value, int diag, int line) {
        for (int d = 1; d <= diag - 1; d++) {
            for (int l = 1; l <= diag; l++) {
                if (value == getValueAt(d, l)) {
                    return true;
                }
            }
        }

        for (int l = 1; l <= line; l++) {
            if (value == getValueAt(diag, l)) {
                return true;
            }
        }

        return false;
    }

    public boolean propagate(int value, int diag) {
        int v = value;
        setValueAt(v, diag, 1);

        for (int line = 2; line <= diag; line++) {
            v = Math.abs(v - getValueAt(diag - 1, line - 1));
            if (contains(v, diag, line - 1)) {
                return false;
            }
            setValueAt(v, diag, line);
        }

        return true;
    }
}
