package ex04;

import static java.lang.Math.*;

public class RandomWalk1D {

    private int a;
    private double p;
    private int x = 0;

    public RandomWalk1D(double p, int a) {
        this.p = p;
        this.a = a;
    }

    public int getX(int n) {

        for (int i = 0; i < n; i++) {
            if (random() < p) {
                x += a;
            } else {
                x -= a;
            }
        }

        return x;
    }

    public double getAvarage(int numberOfRepeat, int n) {

        int sum = 0;

        for (int i = 0; i < numberOfRepeat; i++) {
            sum += new RandomWalk1D(p, a).getX(n);
        }

        return (double) sum / numberOfRepeat;

    }

    public double getDeltaX2(int numberOfRepeat, int n) {

        int sumX = 0;
        int sumX2 = 0;

        for (int i = 0; i < numberOfRepeat; i++) {
            int x = new RandomWalk1D(p, a).getX(n);
            sumX += x;
            sumX2 += pow(x, 2);
        }

        return (double) (sumX2 - sumX) / numberOfRepeat;

    }
}
