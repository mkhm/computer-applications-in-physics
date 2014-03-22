package ex05;

import ex01.Coffee;
import static java.lang.Math.random;

public class Percolation {

    private int xLength;
    private int yLength;
    private double p;
    private double deltaP;
    private int[][] lattice;
    private boolean compeletlyBurned = false;
    private boolean noDeadlock;

    public Percolation(int xLength, int yLength, double deltaP) {
        this.xLength = xLength;
        this.yLength = yLength;
        this.deltaP = deltaP;
        lattice = new int[xLength][yLength];
    }

    public void setUp() {
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (random() < p) {
                    lattice[i][j] = 1;
                } else {
                    lattice[i][j] = 0;
                }
            }
        }
    }

    public double getAveragePc(double p0, int numberOfRepeat) {
        double sum = 0;
        for (int i = 0; i < numberOfRepeat; i++) {
            sum += getNaturalPc(p0);
        }

        return sum / numberOfRepeat;
    }

    public double getNaturalPc(double p0) {
        int t = -1;
        double p = p0;
//        int numberOfDecimalPlaces = Coffee.numberOfDecimalPlaces(String.valueOf(deltaP));
        while (t < 0) {
            t = getPc(p);
//            p = Coffee.round(p + deltaP, numberOfDecimalPlaces);
            p +=deltaP;
        }
        return p;
    }

    public int getPc(double p) {
        this.p = p;
        setUp();
        return getPc();
    }

    public int getPc() {
        compeletlyBurned = false;
        int t = 2;
//        boolean noDeadlock;

        for (int i = 0; i < xLength; i++) {
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
//                        noDeadlock = true;
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
            noDeadlock = true;
            lattice[i][j] = t + 1;
            if (j == yLength - 1) {
                compeletlyBurned = true;
            }
        }
    }
}
