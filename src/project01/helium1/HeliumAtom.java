package project01.helium1;

import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class HeliumAtom {

//    public static double r(double x, double y, double z) {
//        return sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));
//    }
    public static double getValueOfEL(double r1, double r2, double theta, double lamda) {
        return -pow(lamda, 2) + (lamda - 2) / r1 + (lamda - 2) / r2 + 1 / sqrt(pow(r1, 2) + pow(r2, 2) - 2 * r1 * r2 * cos(theta));
    }

    public static double getValueOfP(double r1, double r2, double theta, double lamda) {
        return pow(r1, 2) * pow(r2, 2) * sin(theta) * exp(-2 * lamda * (r1 + r2));
    }

    public static double getValueOfW(double r1Trial, double r2Trial, double thetaTrial, double r1, double r2, double theta, double lamda) {
        return getValueOfP(r1Trial, r2Trial, thetaTrial, lamda) / getValueOfP(r1, r2, theta, lamda);
    }
//
//    public static double getValueOfPSI(double r, double lamda) {
//        return r * exp(-lamda * r);
//    }
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
