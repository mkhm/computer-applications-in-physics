package project01.helium1;

import ex01.Coffee;
import static java.lang.Math.random;

public class HeliumMetropolisMethod {

    private int numberOfAcceptXTrial = 0;
    private double lamda;
    private double r10;
    private double r20;
    private double theta0;

    public HeliumMetropolisMethod(double r10, double r20, double theta0, double lamda) {
        this.lamda = lamda;
        this.r10 = r10;
        this.r20 = r20;
        this.theta0 = theta0;
    }

    public void setLamda(double lamda) {
        this.lamda = lamda;
    }

//    public void setX0(double x0) {
//        this.x0 = x0;
//    }
    public void reset() {
        this.numberOfAcceptXTrial = 0;
    }

    public static double[] getNextXByMetropolisMethod(double r1, double r2, double theta, double delta, double lamda) {
//        double xTrial = x + delta * (2 * random() - 1);
//        if (xTrial < 0) {
//            return xTrial;
//        }
        double retVal[] = new double[3];
        double r1Trial;
        double r2Trial;
        double thetaTrial;
        do {
            r1Trial = r1 + delta * (2 * random() - 1);
        } while (r1Trial < 0);
        do {
            r2Trial = r2 + delta * (2 * random() - 1);
        } while (r2Trial < 0);
        do {
            thetaTrial = theta + delta * (2 * random() - 1);
        } while (thetaTrial < 0 || thetaTrial > Math.PI);

        double w = HeliumAtom.getValueOfW(r1Trial, r2Trial, thetaTrial, r1, r2, theta, lamda);
//        double w = HydrogenAtom.getValueOfP(xTrial, lamda) / HydrogenAtom.getValueOfP(x, lamda);
        if (w >= 1 || random() <= w) {
            retVal[0] = r1Trial;
            retVal[1] = r2Trial;
            retVal[2] = thetaTrial;
            return retVal;
        } else {
            retVal[0] = r1;
            retVal[1] = r2;
            retVal[2] = theta;
            return retVal;
        }
    }

    public double[] getNextXByMetropolisMethod(double r1, double r2, double theta, double delta) {
        double[] coordinate = getNextXByMetropolisMethod(r1, r2, theta, delta, lamda);
        if (coordinate[0] != r1 || coordinate[1] != r2 || coordinate[2] != theta) {
            numberOfAcceptXTrial++;
        }
        return coordinate;
    }

    public double[] getCorrectDeltaRange(double deltaStep, int numberOfRepeat, double acceptedRatioInterval[]) {
        double coordinate[];
        double r1 = r10;
        double r2 = r20;
        double theta = theta0;
        double ratio;
        double deltaInterval[] = {0, 0};
        int numberOfDecimalPlaces = Coffee.numberOfDecimalPlaces(String.valueOf(deltaStep));
        for (double delta = 0.1; delta <= 10; /*delta += deltaLamda*/ delta = Coffee.round(delta + deltaStep, numberOfDecimalPlaces)) {
            reset();
            for (int i = 0; i < numberOfRepeat; i++) {
                coordinate = this.getNextXByMetropolisMethod(r1, r2, theta, delta);
                r1 = coordinate[0];
                r2 = coordinate[1];
                theta = coordinate[2];
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
        HeliumMetropolisMethod metropolisMethod = new HeliumMetropolisMethod(0.5, 0.5, Math.PI / 6, 0.5);
//        double interval[];
//        interval = metropolisMethod.getCorrectDeltaRange(0.5, 0.1, 1000, acceptedRatioInterval);
//        System.out.println(interval[0] + ", " + interval[1]);
        System.out.println(metropolisMethod.getCorrectDelta(0.1, 1000, acceptedRatioInterval));
    }
}
