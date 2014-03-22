package ex07;

import ex01.Coffee;
import static java.lang.Math.random;

public class MetropolisMethod {

    private int numberOfAcceptXTrial = 0;
    private double lamda;
    private double x0;

    public MetropolisMethod(double x0, double lamda) {
        this.lamda = lamda;
        this.x0 = x0;
    }

    public void setLamda(double lamda) {
        this.lamda = lamda;
    }

    public void setX0(double x0) {
        this.x0 = x0;
    }

    public void reset() {
        this.numberOfAcceptXTrial = 0;
    }

    public static double getNextXByMetropolisMethod(double x, double delta, double lamda) {
        double xTrial = x + delta * (2 * random() - 1);
//        double w = HarmonicOscillator.getValueOfP(xTrial, lamda) / HarmonicOscillator.getValueOfP(x, lamda);
        double w = HarmonicOscillator.getValueOfW(xTrial, x, lamda);
        if (w >= 1 || random() <= w) {
            return xTrial;
        } else {
            return x;
        }
    }

    public double getNextXByMetropolisMethod(double x, double delta) {
        double xTrial = x + delta * (2 * random() - 1);
//        double w = HarmonicOscillator.getValueOfP(xTrial, lamda) / HarmonicOscillator.getValueOfP(x, lamda);
        double w = HarmonicOscillator.getValueOfW(xTrial, x, lamda);
        if (w >= 1 || random() <= w) {
            numberOfAcceptXTrial++;
            return xTrial;
        } else {
            return x;
        }
    }

    public double[] getCorrectDeltaRange(double deltaStep, int numberOfRepeat, double acceptedRatioInterval[]) {
        double x = x0;
        double ratio;
        double deltaInterval[] = {0, 0};
        int numberOfDecimalPlaces = Coffee.numberOfDecimalPlaces(String.valueOf(deltaStep));
        for (double delta = 0.1; delta <= 10; /*delta += deltaLamda*/ delta = Coffee.round(delta + deltaStep, numberOfDecimalPlaces)) {
            reset();
            for (int i = 0; i < numberOfRepeat; i++) {
                x = getNextXByMetropolisMethod(x, delta);
            }
            ratio = (double) numberOfAcceptXTrial / numberOfRepeat;
            if (deltaInterval[0] == 0 && ratio < acceptedRatioInterval[0]) {
//            if (deltaInterval[0] == 0 && ratio >= acceptedRatioInterval[0]) {
//                deltaInterval[0] = delta;
                deltaInterval[0] = Coffee.round(delta - deltaStep, numberOfDecimalPlaces);
            }
            if (deltaInterval[1] == 0 && ratio <= acceptedRatioInterval[1]) {
//            if (deltaInterval[1] == 0 && ratio > acceptedRatioInterval[1]) {
//                deltaInterval[1] = Coffee.round(delta - deltaLamda, numberOfDecimalPlaces);
                deltaInterval[1] = delta;
            }
//            System.out.println(delta + " :    " + ratio);
        }

        return deltaInterval;
    }

    public double getCorrectDelta(double deltaStep, int numberOfRepeat, double acceptedRatioInterval[]) {
        double interval[];
        interval = getCorrectDeltaRange(deltaStep, numberOfRepeat, acceptedRatioInterval);
        return (interval[0] + interval[1]) / 2;
    }

    public static void main(String[] args) {
        double acceptedRatioInterval[] = {0.3, 0.5};
        MetropolisMethod metropolisMethod = new MetropolisMethod(0.5, 0.5);
//        double interval[];
//        interval = metropolisMethod.getCorrectDeltaRange(0.5, 0.1, 1000, acceptedRatioInterval);
//        System.out.println(interval[0] + ", " + interval[1]);
        System.out.println(metropolisMethod.getCorrectDelta(0.1, 1000, acceptedRatioInterval));
    }
}
