package ex04;

import static java.lang.Math.*;

public class RandomWalk2D {

    private int ax;
    private int ay;
    private double px;
    private double py;
    private int[] locationCoordinate = {0, 0};

    public RandomWalk2D(double px, double py, int ax, int ay) {
        this.px = px;
        this.ax = ax;
        this.py = py;
        this.ay = ay;
    }

    public void walk(int n) {
        for (int i = 0; i < n; i++) {
            if (random() < px) {
                locationCoordinate[0] += ax;
            } else {
                locationCoordinate[0] -= ax;
            }
            if (random() < py) {
                locationCoordinate[1] += ay;
            } else {
                locationCoordinate[1] -= ay;
            }
        }
    }

    public double getRAmplitude() {
        return sqrt(pow(locationCoordinate[0], 2) + pow(locationCoordinate[1], 2));
    }

    public int[] getRVector() {
        return locationCoordinate;
    }

    public int getX() {
        return locationCoordinate[0];
    }

    public int getY() {
        return locationCoordinate[1];
    }

    public double[] getRVectorAvarage(int numberOfRepeat, int n) {

        int sumX = 0;
        int sumY = 0;

        for (int i = 0; i < numberOfRepeat; i++) {
            RandomWalk2D rw2d = new RandomWalk2D(px, py, ax, ay);
            rw2d.walk(n);
            sumX += rw2d.getX();
            sumY += rw2d.getY();
        }
        double[] vectorAverage = {(double) sumX / numberOfRepeat, (double) sumY / numberOfRepeat};

        return vectorAverage;
    }

    public double getDeltaR2(int numberOfRepeat, int n) {

        double[] sumRVector = {0, 0};
        double[] RVectorAverage = {0, 0};
        double RVectorAverage2;
        int sumR2 = 0;

        for (int i = 0; i < numberOfRepeat; i++) {
            RandomWalk2D rw2d = new RandomWalk2D(px, py, ax, ay);
            rw2d.walk(n);
            int r[] = rw2d.getRVector();
            sumRVector[0] += r[0];
            sumRVector[1] += r[1];
            sumR2 += pow(r[0], 2) + pow(r[1], 2);
        }

        RVectorAverage[0] = (double) sumRVector[0] / numberOfRepeat;
        RVectorAverage[1] = (double) sumRVector[1] / numberOfRepeat;
        RVectorAverage2 = RVectorAverage[0] * RVectorAverage[0] + RVectorAverage[1] * RVectorAverage[1];

        return (double) sumR2 / numberOfRepeat - RVectorAverage2;
    }
}
