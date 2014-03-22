package project01.helium2;

import ex01.Coffee;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Project01_C {

    private static double groundStateEnergy;
    private static double groundStateEnergyError;
    private static double lamdaOfGroundStateEnergy;
    private static double minEnergyError;
    private static double lamdaOfMinEnergyError;
    private static double energyOfMinEnergyError;

    public static void ApproximateOfGroundStateEnergy(double x10, double y10, double z10, double x20, double y20, double z20, double delta, double lamdaStep, int numberOfRepeat, PrintWriter file) {
        HeliumAtomEnergyComputation energyComputation = new HeliumAtomEnergyComputation(x10, y10, z10, x20, y20, z20, delta);
        energyComputation.compute(0.1, numberOfRepeat);
        groundStateEnergy = energyComputation.getAverageEnergy();
        minEnergyError = energyComputation.getAverageEnergyError();
        lamdaOfGroundStateEnergy = 0.1;
        int numberOfDecimalPlaces = Coffee.numberOfDecimalPlaces(String.valueOf(lamdaStep));

        for (double lamda = lamdaStep; lamda <= 1; /*lamda += deltaLamda*/ lamda = Coffee.round(lamda + lamdaStep, numberOfDecimalPlaces)) {
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
                energyOfMinEnergyError = energyComputation.getAverageEnergy();
            }
        }
        file.flush();
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("Project01_C__Average_Energy_of_Helium_Atom.txt");
        ApproximateOfGroundStateEnergy(0.5, 0.5, 0.5, 0.7, 0.7, 0.7, 1, 0.01, 100000, file);
        System.out.println("Lamda of Ground State Energy       :  " + lamdaOfGroundStateEnergy);
        System.out.println("Ground State Energy                :  " + groundStateEnergy);
        System.out.println("Ground State Energy Error          :  " + groundStateEnergyError);
        System.out.println("Minimum of Energy Error            :  " + minEnergyError);
        System.out.println("Lamda of Minimum of Energy Error   :  " + lamdaOfMinEnergyError);
        System.out.println("Energy of Minimum of Energy Error :  " + energyOfMinEnergyError);
    }
}
