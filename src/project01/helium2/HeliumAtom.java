package project01.helium2;

import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class HeliumAtom {

    public static double r(double x, double y, double z) {
        return sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));
    }

    public static double getValueOfEL(double x1, double y1, double z1, double x2, double y2, double z2, double lamda) {
        double r12 = r(x1 - x2, y1 - y2, z1 - z2);
        double b = 1 / (1 + lamda * r12);
        double h = 2 + lamda * b;
        double b2 = b * b;

//        return -8 + 1 / r12 - 2 * pow(lamda, 2) * b2 - 4 * lamda * b + 2 * h * (1 / r12 - getV(x1, y1, z1, x2, y2, z2));
        return -4 + 1 / r12 - (4 + 2 * lamda * (2 + lamda + 2 * r12 * (2 + lamda + r12 * lamda))) * b2 + 2 * h * (1 / r12 - getV(x1, y1, z1, x2, y2, z2));

//        double hh = 2 * (1 + (-1 + r12) * lamda) * b;
//        return -4 + 1 / r12 - (1 + 2 * (-1 + r12) * lamda + (2 + (-2 + r12) * r12) * pow(lamda, 2)) * b2 + hh * (getV(x1, y1, z1, x2, y2, z2) - 1 / r12);
    }

    public static double getValueOfP(double x1, double y1, double z1, double x2, double y2, double z2, double lamda) {
        double r12 = r(x1 - x2, y1 - y2, z1 - z2);
        return exp(2 * (-2 * (r(x1, y1, z1) + r(x2, y2, z2) + r12))) / pow(2 * (1 + lamda * r12), 2);


//        return exp(2 * (-2 * (r(x1, y1, z1) + r(x2, y2, z2)) + r12)) / pow(2 * (1 + lamda * r12), 2);
    }

    public static double getValueOfW(double x1Trial, double y1Trial, double z1Trial, double x2Trial, double y2Trial, double z2Trial,
            double x1, double y1, double z1, double x2, double y2, double z2, double lamda) {
        return getValueOfP(x1Trial, y1Trial, z1Trial, x2Trial, y2Trial, z2Trial, lamda) / getValueOfP(x1, y1, z1, x2, y2, z2, lamda);
    }

    public static double getV(double x1, double y1, double z1, double x2, double y2, double z2) {
        double r1 = r(x1, y1, z1);
        double r2 = r(x2, y2, z2);
        double r12 = r(x1 - x2, y1 - y2, z1 - z2);
//        return 1 / r12 * ((x1 - x2) * (x1 / r1 - x2 / r2) + (y1 - y2) * (y1 / r1 - y2 / r2) + (z1 - z2) * (z1 / r1 - z2 / r2));
        return ((x1 - x2) * (x1 / r1 - x2 / r2) + (y1 - y2) * (y1 / r1 - y2 / r2) + (z1 - z2) * (z1 / r1 - z2 / r2)) / r12;
    }
}
