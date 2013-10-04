package pyramid;

/**
 *
 * @author oster
 */
public class BruteForceSolvingPyramid extends AbstractPyramid {

    public BruteForceSolvingPyramid(int height) {
        super(height);
    }

    public boolean solve() {
        return fill(1);
    }

    public boolean isCorrect() {
        return isCorrect(getHeight());
    }

    public boolean isCorrect(int diag) {
        if (diag == 0) {
            return true;
        }

        for (int line = 1; line <= diag; line++) {
            if (!isCellCorrect(diag, line)) {
                return false;
            }
        }

        return isCorrect(diag - 1);
    }

    public boolean isCellCorrect(int diag, int line) {

        int value = getValueAt(diag, line);

        // value in [1..size]
        if (value <= 0 || value > getSize()) {
            return false;
        }

        // first cell is special
        if (diag == 1 && line == 1) {
            return true;
        }

        // no need to check uniqueness of value (permutation)

        if (line > 1) {
            int n1 = getValueAt(diag - 1, line - 1);
            int n2 = getValueAt(diag, line -1);

            if (value != Math.abs(n1 - n2)) {
                return false;
            }
        }

        return true;
    }

    public boolean fill(int pos) {
        if (pos > getSize()) {
            return isCorrect();
        }

        for (int v = 1; v <= getSize(); v++) {
            if (!containsValue(this.elements, v, pos - 1)) {
                this.elements[pos - 1] = v;
                if (fill(pos + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsValue(int[] values, int value, int end) {
        for (int i = 0; i <= end; i++) {
            if (values[i] == value) {
                return true;
            }
        }
        return false;
    }
}
