package project01.helium.pade_jastrow;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class HeliumAtomEnergyComputation {

    private double x10;
    private double y10;
    private double z10;
    private double x20;
    private double y20;
    private double z20;
    private double delta;
    private double averageEnergy;
    private double averageEnergyError;
    private double groundStateEnergy;
    private double groundStateEnergyError;
    private double lamdaOfGroundStateEnergy;

    public HeliumAtomEnergyComputation(double x10, double y10, double z10, double x20, double y20, double z20, double delta) {
        this.x10 = x10;
        this.y10 = y10;
        this.z10 = z10;
        this.x20 = x20;
        this.y20 = y20;
        this.z20 = z20;
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
        int deltaAdjustInterval = (int) (numberOfRepeat * 0.01) + 1;

        double sumEnergy = 0;
        double sumEnergy2 = 0;
        double energy;
        double coordinate[];
        double x1 = x10;
        double y1 = y10;
        double z1 = z10;
        double x2 = x20;
        double y2 = y20;
        double z2 = z20;
        for (int i = 0; i < numberOfRepeat; i++) {
            coordinate = HeliumMetropolisMethod.getNextXByMetropolisMethod(x1, y1, z1, x2, y2, z2, delta, lamda);
            if ((i + 1) % deltaAdjustInterval == 0) {
                delta *= (double) HeliumMetropolisMethod.nAccept / (0.5 * deltaAdjustInterval);
//                System.out.println("adjust delta to : " + delta);
                HeliumMetropolisMethod.nAccept = 0;
            }

            x1 = coordinate[0];
            y1 = coordinate[1];
            z1 = coordinate[2];
            x2 = coordinate[3];
            y2 = coordinate[4];
            z2 = coordinate[5];
            energy = HeliumAtom.getValueOfEL(x1, y1, z1, x2, y2, z2, lamda);
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
