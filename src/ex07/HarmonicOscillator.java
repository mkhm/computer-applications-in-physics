package ex07;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class HarmonicOscillator {

    public static double getValueOfEL(double x, double lamda) {
        return lamda + pow(x, 2) * (0.5 - 2 * pow(lamda, 2));
    }

    public static double getValueOfP(double x, double lamda) {
        return exp(-2 * lamda * pow(x, 2));
    }

    public static double getValueOfW(double xTrial, double x, double lamda) {
        return getValueOfP(xTrial, lamda) / getValueOfP(x, lamda);
    }

    public static double getValueOfPSI(double x, double lamda) {
        return exp(-lamda * pow(x, 2));
    }
}
