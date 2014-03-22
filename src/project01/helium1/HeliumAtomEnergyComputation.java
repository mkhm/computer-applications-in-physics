package project01.helium1;

import ex07.*;
import ex01.Coffee;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class HeliumAtomEnergyComputation {

    private double r10;
    private double r20;
    private double theta0;
    private double delta;
    private double averageEnergy;
    private double averageEnergyError;
    private double groundStateEnergy;
    private double groundStateEnergyError;
    private double lamdaOfGroundStateEnergy;

    public HeliumAtomEnergyComputation(double r10, double r20, double theta0, double delta) {
        this.r10 = r10;
        this.r20 = r20;
        this.theta0 = theta0;
        this.delta = delta;
    }

    public double getAverageEnergy() {
        return averageEnergy;
    }

    public double getAverageEnergyError() {
        return averageEnergyError;
    }

    public double getGroundStateEnergy() {
        return groundStateEnergy;
    }

    public double getGroundStateEnergyError() {
        return groundStateEnergyError;
    }

    public double getLamdaOfGroundStateEnergy() {
        return lamdaOfGroundStateEnergy;
    }

    public void compute(double lamda, int numberOfRepeat) {
        double sumEnergy = 0;
        double sumEnergy2 = 0;
        double energy;
        double coordinate[];
        double r1 = r10;
        double r2 = r20;
        double theta = theta0;
        for (int i = 0; i < numberOfRepeat; i++) {
            coordinate = HeliumMetropolisMethod.getNextXByMetropolisMethod(r1, r2, theta, delta, lamda);
            r1 = coordinate[0];
            r2 = coordinate[1];
            theta = coordinate[2];
            energy = HeliumAtom.getValueOfEL(r1, r2, theta, lamda);
            sumEnergy += energy;
            sumEnergy2 += pow(energy, 2);
        }

        averageEnergy = sumEnergy / numberOfRepeat;
        averageEnergyError = sqrt(sumEnergy2 / numberOfRepeat - pow(averageEnergy, 2)) / sqrt(numberOfRepeat);
    }
//    // [lamda, GroundStateEnergy]
//    public void ApproximateOfGroundStateEnergy(double lamdaStep, int numberOfRepeat) {
////        MetropolisMethod metropolisMethod = new MetropolisMethod(x0, 0.5);
////        double acceptedRatioInterval[] = {0.3, 0.4};
//        compute(0.1, numberOfRepeat);
//        groundStateEnergy = averageEnergy;
//        lamdaOfGroundStateEnergy = 0.1;
//        int numberOfDecimalPlaces = Coffee.numberOfDecimalPlaces(String.valueOf(lamdaStep));
//
//        for (double lamda = 0.1; lamda <= 1; /*lamda += deltaLamda*/ lamda = Coffee.round(lamda + lamdaStep, numberOfDecimalPlaces)) {
////            metropolisMethod.setLamda(lamda);
////            delta = metropolisMethod.getCorrectDelta(0.1, 1000, acceptedRatioInterval);
//            compute(lamda, numberOfRepeat);
//            if (averageEnergy < groundStateEnergy) {
//                groundStateEnergy = averageEnergy;
//                groundStateEnergyError = averageEnergyError;
//                lamdaOfGroundStateEnergy = lamda;
//            }
//        }
////        double[] retVal = {lamdaMin, minEnergy};
//////        System.out.println(lamdaMin + "      " + minEnergy);
////        return retVal;
//
//    }
//
//    public static void main(String[] args) {
//
//        int numberOfRepeat = 100000;
//        HydrogenAtomEnergyComputation energyComputation = new HydrogenAtomEnergyComputation(1, 2.7);
//
//        energyComputation.ApproximateOfGroundStateEnergy(0.1, numberOfRepeat);
//
//        System.out.println(energyComputation.getLamdaOfGroundStateEnergy());
//        System.out.println(energyComputation.getGroundStateEnergy());
//        System.out.println(energyComputation.getGroundStateEnergyError());
//    }
}
