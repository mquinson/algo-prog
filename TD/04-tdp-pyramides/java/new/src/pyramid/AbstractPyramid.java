package pyramid;

import java.util.Arrays;

/**
 *
 * @author oster
 */
public abstract class AbstractPyramid {

    private int height;
    protected int[] elements;

    public AbstractPyramid(int height) {
        this.height = height;
        this.elements = new int[getSize()];
    }

    public int getHeight() {
        return this.height;
    }

    public int getSize() {
        return (this.height * (this.height + 1)) / 2;
    }

    public int computeIndex(int diag, int line) {
        return (diag * (diag - 1)) / 2 + line - 1;
    }

    public int getValueAt(int diag, int line) {
        return this.elements[this.computeIndex(diag, line)];
    }

    public void setValueAt(int value, int diag, int line) {
        this.elements[this.computeIndex(diag, line)] = value;
    }

    public abstract boolean solve();

    @Override
    public String toString() {
        int valueMaxWidth = (int) Math.log10(getSize()) + 1;
        String valueSeparator = "";
        for (int i = 0; i < valueMaxWidth; i++) {
            valueSeparator += " ";
        }
        String format = "%0" + valueMaxWidth + "d";

        StringBuilder res = new StringBuilder("Pyramid height:");
        res.append(this.getHeight());
        res.append("\n");
        for (int line = 1; line <= this.getHeight(); line++) {
            for (int diag = line; diag <= this.getHeight(); diag++) {
                res.append(String.format(format + valueSeparator, getValueAt(diag, line)));
            }
            res.append("\n");
            for (int blankCount = 1; blankCount <= line; blankCount++) {
                res.append(valueSeparator);
            }
        }
        return res.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final AbstractPyramid other = (AbstractPyramid) obj;
        if (this.height == other.height && Arrays.equals(this.elements, other.elements)) {
            return true;
        }
        return false;
    }

    public void fillWithIndex() {
        for (int diag = 1; diag <= this.height; diag++) {
            for (int line = 1; line <= diag; line++) {
                int index = computeIndex(diag, line);
                setValueAt(index, diag, line);
            }
        }
    }

    public void setValues(int height, int[] values) {
        this.height = height;
        this.elements = values;
    }

    public int[] getValues() {
        return this.elements;
    }
}
