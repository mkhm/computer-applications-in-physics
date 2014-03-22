package ex04;

import static java.lang.Math.random;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class RandomWalk {

    private int[] a;
    private double[] p;
    private int x = 0;
    private int y = 0;
    private int dimension;
    private int[] locationCoordinate;

    public RandomWalk(int dimension, double p[], int a[]) {
        this.dimension = dimension;
        locationCoordinate = new int[dimension];
        this.p = p;
        this.a = a;
    }

    public RandomWalk(int dimension, double p[], int a) {
        this.dimension = dimension;
        locationCoordinate = new int[dimension];
        this.p = p;
        this.a = new int[dimension];
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = a;
        }
    }

    public void walk(int n) {
        for (int i = 0; i < n; i++) {
            for (int d = 0; d < dimension; d++) {
                if (random() < p[d]) {
                    locationCoordinate[d] += a[d];
                } else {
                    locationCoordinate[d] -= a[d];
                }
            }
        }
    }

    public double getRAmplitude() {
        int sumCoordinate2 = 0;
        for (int d = 0; d < dimension; d++) {
            sumCoordinate2 += pow(locationCoordinate[d], 2);
        }
        return sqrt(sumCoordinate2);
    }

    public int[] getRVector() {
        return locationCoordinate;
    }

    public int getRCoordinate(int coordinateNumber) {
        return locationCoordinate[coordinateNumber];
    }

    public double[] getRVectorAvarage(int numberOfRepeat, int n) {

        int[] sumRVector = new int[dimension];

        for (int i = 0; i < numberOfRepeat; i++) {
            RandomWalk rw = new RandomWalk(dimension, p, a);
            rw.walk(n);
            for (int d = 0; d < dimension; d++) {
                sumRVector[d] += rw.getRCoordinate(d);
            }
        }
        double[] vectorAverage = new double[dimension];
        for (int d = 0; d < dimension; d++) {
            vectorAverage[d] = (double) sumRVector[d] / numberOfRepeat;
        }

        return vectorAverage;
    }

    public double getDeltaR2(int numberOfRepeat, int n) {

        int[] sumRVector = new int[dimension];
        double[] RVectorAverage = new double[dimension];
        double RVectorAverage2 = 0;
        int sumR2 = 0;

        for (int i = 0; i < numberOfRepeat; i++) {
            RandomWalk rw = new RandomWalk(dimension, p, a);
            rw.walk(n);
            int r[] = rw.getRVector();
            for (int d = 0; d < dimension; d++) {
                sumRVector[d] += r[d];
                sumR2 += pow(r[d], 2);
            }
        }

        for (int d = 0; d < dimension; d++) {
            RVectorAverage[d] = (double) sumRVector[d] / numberOfRepeat;
            RVectorAverage2 += RVectorAverage[d] * RVectorAverage[d];
        }

        return (double) sumR2 / numberOfRepeat - RVectorAverage2;
    }
}
