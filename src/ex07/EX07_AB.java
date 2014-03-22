package ex07;

import ex01.Coffee;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EX07_AB {

    private static double groundStateEnergy;
    private static double groundStateEnergyError;
    private static double lamdaOfGroundStateEnergy;
    private static double minEnergyError;

    public static void ApproximateOfGroundStateEnergy(double x0, double delta, double lamdaStep, int numberOfRepeat, PrintWriter file) {
        HarmonicOscillatorEnergyComputation energyComputation = new HarmonicOscillatorEnergyComputation(x0, delta);
//        MetropolisMethod metropolisMethod = new MetropolisMethod(x0, 0.5);
//        double acceptedRatioInterval[] = {0.3, 0.4};
        energyComputation.compute(0.1, numberOfRepeat);
        groundStateEnergy = energyComputation.getAverageEnergy();
        minEnergyError = energyComputation.getAverageEnergyError();
        lamdaOfGroundStateEnergy = 0.1;
        int numberOfDecimalPlaces = Coffee.numberOfDecimalPlaces(String.valueOf(lamdaStep));

        for (double lamda = 0.1; lamda <= 1; /*lamda += deltaLamda*/ lamda = Coffee.round(lamda + lamdaStep, numberOfDecimalPlaces)) {
//            metropolisMethod.setLamda(lamda);
//            delta = metropolisMethod.getCorrectDelta(0.1, 1000, acceptedRatioInterval);
            energyComputation.compute(lamda, numberOfRepeat);
            file.println(lamda + "      " + energyComputation.getAverageEnergy() + "      " + energyComputation.getAverageEnergyError());
            if (energyComputation.getAverageEnergy() < groundStateEnergy) {
                groundStateEnergy = energyComputation.getAverageEnergy();
                groundStateEnergyError = energyComputation.getAverageEnergyError();
                lamdaOfGroundStateEnergy = lamda;
            }
            if (energyComputation.getAverageEnergyError() < minEnergyError) {
                minEnergyError = energyComputation.getAverageEnergyError();
            }
        }
        file.flush();
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("EX07_AB__Average_Energy_of_Harmonic_Oscillator.txt");
//        ApproximateOfGroundStateEnergy(0.5, 2.7, 0.1, 100000, file);
        ApproximateOfGroundStateEnergy(0.5, 4, 0.1, 1000, file);
        System.out.println("Lamda of Ground State Energy :  " + lamdaOfGroundStateEnergy);
        System.out.println("Ground State Energy          :  " + groundStateEnergy);
        System.out.println("Ground State Energy Error    :  " + groundStateEnergyError);
        System.out.println("Minimum of Energy Error      :  " + minEnergyError);
    }
}
