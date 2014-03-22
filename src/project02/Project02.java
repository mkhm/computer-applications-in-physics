package project02;

import ex01.Coffee;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Project02 {

    private static double groundStateEnergy;
    private static double groundStateEnergyError;
    private static double lamdaOfGroundStateEnergy;
    private static double minEnergyError;
    private static double lamdaOfMinEnergyError;
    private static double energyOfMinEnergyError;

    public static void ApproximateOfGroundStateEnergy(int L, double J, int numberOfMonteCarloSteps, int numberOfEquilibrationSteps, double start_T, double final_T, double delta_T, PrintWriter file) {
        IsingModel isingModel = new IsingModel(L, J);
//        energyComputation.compute(0.1, numberOfRepeat);
//        groundStateEnergy = energyComputation.getAverageEnergy();
//        minEnergyError = energyComputation.getAverageEnergyError();
//        lamdaOfGroundStateEnergy = 0.1;
        int numberOfDecimalPlaces = Coffee.numberOfDecimalPlaces(String.valueOf(delta_T));

        for (double T = start_T; T <= final_T; /*lamda += deltaLamda*/ T = Coffee.round(T + delta_T, numberOfDecimalPlaces)) {
            isingModel.reset();
            isingModel.initialize();
            isingModel.setTemperature(T);
            isingModel.compute(numberOfMonteCarloSteps, numberOfEquilibrationSteps);
            file.println(T + "      " + isingModel.getOrderParameter() + "      " + isingModel.getHeatCapacity() + "      " + isingModel.getMagneticSusceptibility());
//            if (energyComputation.getAverageEnergy() < groundStateEnergy) {
//                groundStateEnergy = energyComputation.getAverageEnergy();
//                groundStateEnergyError = energyComputation.getAverageEnergyError();
//                lamdaOfGroundStateEnergy = T;
//            }
//            if (energyComputation.getAverageEnergyError() < minEnergyError) {
//                minEnergyError = energyComputation.getAverageEnergyError();
//                lamdaOfMinEnergyError = T;
//                energyOfMinEnergyError = energyComputation.getAverageEnergy();
//            }
        }
        file.flush();
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("Project02__Ising_Model.txt");
        ApproximateOfGroundStateEnergy(20, 1, 10000, 5000, 0.9, 3, 0.01, file);
//        ApproximateOfGroundStateEnergy(20, 1, 50000, 45000, 0.9, 3, 0.05, file);
//        ApproximateOfGroundStateEnergy(20, 1, 200000, 195000, 0.1, 0.9, 0.1, file);
//        System.out.println("Lamda of Ground State Energy       :  " + lamdaOfGroundStateEnergy);
//        System.out.println("Ground State Energy                :  " + groundStateEnergy);
//        System.out.println("Ground State Energy Error          :  " + groundStateEnergyError);
//        System.out.println("Minimum of Energy Error            :  " + minEnergyError);
//        System.out.println("Lamda of Minimum of Energy Error   :  " + lamdaOfMinEnergyError);
//        System.out.println("EEnergy of Minimum of Energy Error :  " + energyOfMinEnergyError);
    }
}
