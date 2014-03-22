package project02;

import static java.lang.Math.pow;

public class IsingModel2 {

    private int L;
    private double temperature;
    private double J;
    private int s[][];
    private double boltzmannFactor[] = new double[5];
    private int tempEnergy = 0;
    private int tempMagnetization = 0;
    private int sumEnergy = 0;
    private int sumMagnetization = 0;
    private int sumEnergy2 = 0;
    private int sumMagnetization2 = 0;
    private double heatCapacity = 0;
    private double magneticSusceptibility = 0;
    private double orderParameter = 0;

    public IsingModel2(int L/*, double J*/) {
        this.L = L;

        s = new int[L + 1][L + 1];
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        for (int i = 0; i < 5; i++) {
            boltzmannFactor[i] = Math.exp(-4 * (i - 2) / temperature);
        }
    }

    public double getHeatCapacity() {
        return heatCapacity;
    }

    public double getOrderParameter() {
        return orderParameter;
    }

    public double getMagneticSusceptibility() {
        return magneticSusceptibility;
    }

    public void compute(int numberOfMonteCarloSteps) {
        for (int i = 0; i < numberOfMonteCarloSteps; i++) {
            for (int j = 0; j < L * L; j++) {
                change();
            }
            sumEnergy += tempEnergy;
            sumMagnetization += tempMagnetization;
            sumEnergy2 += pow(tempEnergy, 2);
            sumMagnetization2 += pow(tempMagnetization, 2);
        }

        orderParameter = ((double) sumMagnetization) / (numberOfMonteCarloSteps * L * L);
        heatCapacity = (((double) sumEnergy2 / numberOfMonteCarloSteps) - pow((double) sumEnergy / numberOfMonteCarloSteps, 2)) / pow(temperature, 2);
        magneticSusceptibility = (((double) sumMagnetization2 / numberOfMonteCarloSteps) - pow((double) sumMagnetization / numberOfMonteCarloSteps, 2)) / temperature;
    }

    private void change() {
        int i;
        int j;
        int eDiff;
        i = (int) (Math.random() * L + 1);
        j = (int) (Math.random() * L + 1);

        eDiff = deltaU(i, j);

        if (eDiff <= 0 || Math.random() < boltzmannFactor[eDiff / 4 + 2]) {
            s[i][j] = -s[i][j];
            tempEnergy += eDiff;
            tempMagnetization += 2 * s[i][j];
        }
    }

    public void initialize() {
        tempMagnetization = 0;
        sumMagnetization = 0;
        sumMagnetization2 = 0;
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= L; j++) {
                s[i][j] = Math.random() < 0.5 ? 1 : -1;
                tempMagnetization += s[i][j];
            }
        }
        setTotalEnergy();
    }

    private void setTotalEnergy() {
        tempEnergy = 0;
        sumEnergy = 0;
        sumEnergy2 = 0;
        for (int i = 1; i <= L; i++) {
            for (int j = 1; j <= L; j++) {
                int top = i == 1 ? s[L][j] : s[i - 1][j];
                int right = j == L ? s[i][1] : s[i][j + 1];
                tempEnergy += -s[i][j] * (top + right);
            }
        }
    }

    private int deltaU(int i, int j) {
        int top = i == 1 ? s[L][j] : s[i - 1][j];
        int bottom = i == L ? s[1][j] : s[i + 1][j];
        int left = j == 1 ? s[i][L] : s[i][j - 1];
        int right = j == L ? s[i][1] : s[i][j + 1];
        return 2 * s[i][j] * (top + bottom + right + left);
    }
}
