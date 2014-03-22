package project01.helium2;


import ex01.Coffee;
import static java.lang.Math.random;

public class HeliumMetropolisMethod {
    public static int nAccept=0;

    private int numberOfAcceptCoordinateTrial = 0;
    private double lamda;
    private double x10;
    private double y10;
    private double z10;
    private double x20;
    private double y20;
    private double z20;
    
    public static double[] getNextXByMetropolisMethod(double x1, double y1, double z1, double x2, double y2, double z2, double delta, double lamda) {
//        double xTrial = x + delta * (2 * random() - 1);
//        if (xTrial < 0) {
//            return xTrial;
//        }
        double retVal[] = new double[6];
        double x1Trial = x1 + delta * (2 * random() - 1);
        double y1Trial = y1 + delta * (2 * random() - 1);
        double z1Trial = z1 + delta * (2 * random() - 1);
        double x2Trial = x2 + delta * (2 * random() - 1);
        double y2Trial = y2 + delta * (2 * random() - 1);
        double z2Trial = z2 + delta * (2 * random() - 1);

        double w = HeliumAtom.getValueOfW(x1Trial, y1Trial, z1Trial, x2Trial, y2Trial, z2Trial, x1, y1, z1, x2, y2, z2, lamda);
        if (w >= 1 || random() <= w) {
            retVal[0] = x1Trial;
            retVal[1] = y1Trial;
            retVal[2] = z1Trial;
            retVal[3] = x2Trial;
            retVal[4] = y2Trial;
            retVal[5] = z2Trial;
            nAccept++;
        } else {
            retVal[0] = x1;
            retVal[1] = y1;
            retVal[2] = z1;
            retVal[3] = x2;
            retVal[4] = y2;
            retVal[5] = z2;
        }
        return retVal;
    }
    
    public HeliumMetropolisMethod(double x10, double y10, double z10, double x20, double y20, double z20, double lamda) {
        this.lamda = lamda;
        this.x10 = x10;
        this.y10 = y10;
        this.z10 = z10;
        this.x20 = x20;
        this.y20 = y20;
        this.z20 = z20;
    }

    public void setLamda(double lamda) {
        this.lamda = lamda;
    }

//    public void setX0(double x0) {
//        this.x0 = x0;
//    }
    public void reset() {
        this.numberOfAcceptCoordinateTrial = 0;
    }


    public double[] getNextXByMetropolisMethod(double x1, double y1, double z1, double x2, double y2, double z2, double delta) {
        double[] coordinate = getNextXByMetropolisMethod(x1, y1, z1, x2, y2, z2, delta, lamda);
        if (coordinate[0] != x1 ) {
            numberOfAcceptCoordinateTrial++;
        }
        return coordinate;
    }

    public double[] getCorrectDeltaRange(double deltaStep, int numberOfRepeat, double acceptedRatioInterval[]) {
        double coordinate[] = null;
        double x1 = x10;
        double y1 = y10;
        double z1 = z10;
        double x2 = x20;
        double y2 = y20;
        double z2 = z20;
        double ratio;
        double deltaInterval[] = {0, 0};
        int numberOfDecimalPlaces = Coffee.numberOfDecimalPlaces(String.valueOf(deltaStep));
        for (double delta = 0.1; delta <= 10; /*delta += deltaLamda*/ delta = Coffee.round(delta + deltaStep, numberOfDecimalPlaces)) {
            reset();
            for (int i = 0; i < numberOfRepeat; i++) {
            coordinate = this.getNextXByMetropolisMethod(x1, y1, z1, x2, y2, z2, delta);
            x1 = coordinate[0];
            y1 = coordinate[1];
            z1 = coordinate[2];
            x2 = coordinate[3];
            y2 = coordinate[4];
            z2 = coordinate[5];
            }
            ratio = (double) numberOfAcceptCoordinateTrial / numberOfRepeat;
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
        HeliumMetropolisMethod metropolisMethod = new HeliumMetropolisMethod(0.5, 0.5, 0.5, 0.7, 0.7, 0.7, 0.5);
//        double interval[];
//        interval = metropolisMethod.getCorrectDeltaRange(0.5, 0.1, 1000, acceptedRatioInterval);
//        System.out.println(interval[0] + ", " + interval[1]);
        System.out.println(metropolisMethod.getCorrectDelta(0.1, 1000, acceptedRatioInterval));
    }
}
