package project01.helium1;

import ex01.Coffee;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Project01_B {

    private static double groundStateEnergy;
    private static double groundStateEnergyError;
    private static double lamdaOfGroundStateEnergy;
    private static double minEnergyError;
    private static double lamdaOfMinEnergyError;
    private static double energyOfMinEnergyError;

    public static void ApproximateOfGroundStateEnergy(double r10, double r20, double theta0, double delta, double lamdaStep, int numberOfRepeat, PrintWriter file) {
        HeliumAtomEnergyComputation energyComputation = new HeliumAtomEnergyComputation(r10, r20, theta0, delta);
//        MetropolisMethod metropolisMethod = new MetropolisMethod(x0, 0.5);
//        double acceptedRatioInterval[] = {0.3, 0.4};
        energyComputation.compute(0.1, numberOfRepeat);
        groundStateEnergy = energyComputation.getAverageEnergy();
        minEnergyError = energyComputation.getAverageEnergyError();
        lamdaOfGroundStateEnergy = 0.1;
        int numberOfDecimalPlaces = Coffee.numberOfDecimalPlaces(String.valueOf(lamdaStep));

        for (double lamda = 0.1; lamda <= 3; /*lamda += deltaLamda*/ lamda = Coffee.round(lamda + lamdaStep, numberOfDecimalPlaces)) {
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
                lamdaOfMinEnergyError = lamda;
                energyOfMinEnergyError =energyComputation.getAverageEnergy();
            }
        }
        file.flush();
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("Project01_B__Average_Energy_of_Helium_Atom.txt");
        ApproximateOfGroundStateEnergy(0.5,0.5,Math.PI/6, 0.1, 0.01, 10000, file);
        System.out.println("Lamda of Ground State Energy       :  " + lamdaOfGroundStateEnergy);
        System.out.println("Ground State Energy                :  " + groundStateEnergy);
        System.out.println("Ground State Energy Error          :  " + groundStateEnergyError);
        System.out.println("Minimum of Energy Error            :  " + minEnergyError);
        System.out.println("Lamda of Minimum of Energy Error   :  " + lamdaOfMinEnergyError);
        System.out.println("Energy of Minimum of Energy Error :  " + energyOfMinEnergyError);
    }
}
