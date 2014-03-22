package ex05;

import ex01.Coffee;
import static java.lang.Math.random;

public class ConstraintPercolation {

    private int xLength;
    private int yLength;
    private double n;
    private int deltaN;
    private int[][] lattice;
    private boolean compeletlyBurned = false;

    public ConstraintPercolation(int xLength, int yLength, int deltaN) {
        this.xLength = xLength;
        this.yLength = yLength;
        this.deltaN = deltaN;
        lattice = new int[xLength][yLength];
    }

    public void setUp(int n) {
        int x;
        int y;
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                lattice[i][j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            do {
                x = (int) (random() * xLength);
                y = (int) (random() * yLength);
            } while (lattice[x][y] == 1);
            lattice[x][y] = 1;
        }
    }

    public double getAveragePc(int n0, int numberOfRepeat) {
        int sum = 0;
        for (int i = 0; i < numberOfRepeat; i++) {
            sum += getNaturalPc(n0);
        }

        return ((double) sum / numberOfRepeat) / (xLength * yLength);
    }

    public int getNaturalPc(int n0) {
        int t = -1;
        int n = n0;
        while (t < 0) {
            t = getPc(n);
            n += deltaN;
        }
        return n;
    }

    public int getPc(int n) {
        this.n = n;
        setUp(n);
        return getPc();
    }

    public int getPc() {
        compeletlyBurned = false;
        int t = 2;
        boolean noDeadlock;

        for (int i = 0; i < yLength; i++) {
            if (lattice[i][0] == 1) {
                lattice[i][0] = t;
            }
        }

        loopt:
        while (true) {
            noDeadlock = false;
            for (int j = 0; j < yLength; j++) {
                for (int i = 0; i < xLength; i++) {
                    if (lattice[i][j] == t) {
                        noDeadlock = true;
                        burnAround(i, j, t);
                    }
                }
            }
            if (compeletlyBurned) {
                return t;
            }
            if (!noDeadlock) {
                break loopt;
            }

            t++;
        }
        return -1;
    }

    private void burnAround(int i, int j, int t) {
        burn(i - 1, j, t);
        burn(i + 1, j, t);
        burn(i, j - 1, t);
        burn(i, j + 1, t);
    }

    private void burn(int i, int j, int t) {
        if (i < 0 || j < 0 || i >= xLength || j >= yLength) {
            return;
        }
        if (lattice[i][j] == 1) {
            lattice[i][j] = t + 1;
            if (j == yLength - 1) {
                compeletlyBurned = true;
            }
        }
    }
}
