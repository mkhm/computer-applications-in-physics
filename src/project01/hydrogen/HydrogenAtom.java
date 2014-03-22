package project01.hydrogen;

import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class HydrogenAtom {

    public static double r(double x, double y, double z) {
        return sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));
    }

    public static double getValueOfEL(double r, double lamda) {
        return -0.5 * (2 - 2 * lamda + r * pow(lamda, 2)) / r;
    }

    public static double getValueOfP(double r, double lamda) {
        return pow(r, 2) * exp(-2 * lamda * r);
    }

    public static double getValueOfW(double rTrial, double r, double lamda) {
        return getValueOfP(rTrial, lamda) / getValueOfP(r, lamda);
    }

    public static double getValueOfPSI(double r, double lamda) {
        return r * exp(-lamda * r);
    }
//    public static double r(double x, double y, double z) {
//        return sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));
//    }
//
//    public static double getValueOfEL(double r, double lamda) {
//        return -0.5 * lamda * (lamda - 2 / r) - 1 / r;
//    }
//
//    public static double getValueOfP(double r, double lamda) {
//        return exp(-2 * lamda * r);
//    }
//
//    public static double getValueOfW(double rTrial, double r, double lamda) {
//        return exp(-2 * lamda * r) / exp(-2 * lamda * r);
//    }
//
//    public static double getValueOfPSI(double r, double lamda) {
//        return exp(-lamda * r);
//    }
////    public static double getValueOfEL(double x, double y, double z, double lamda) {
////        return -0.5 * lamda * (lamda - 2 / r(x, y, z)) - 1 / r(x, y, z);
////    }
////
////    public static double getValueOfP(double x, double y, double z, double lamda) {
////        return exp(-2 * lamda * r(x, y, z));
////    }
////
////    public static double getValueOfW(double xTrial, double yTrial, double zTrial, double x, double y, double z, double lamda) {
////        return exp(-2 * lamda * r(xTrial, yTrial, zTrial)) / exp(-2 * lamda * r(x, y, z));
////    }
////
////    public static double getValueOfPSI(double x, double y, double z, double lamda) {
////        return exp(-lamda * r(x, y, z));
////    }
}
