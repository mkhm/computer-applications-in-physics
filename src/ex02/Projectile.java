package ex02;

import ex01.Coffee;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Projectile {

    public final static int Euler_approximationMethod = 1001;
    public final static int Euler_Richardson_approximationMethod = 1002;
    public final static double g = 9.8;
    private String filename;
    private double deltat;
    private double v0x;
    private double v0y;
    private double theta;
    private double c = 1;
    private double periodTime_min = deltat;
    private int numberOfSteps = 0;

    public Projectile(double deltat, double periodTime_min, double v0x, double v0y, double c) {
        this.deltat = deltat;
        this.v0x = v0x;
        this.v0y = v0y;
        this.c = c;
        this.periodTime_min = periodTime_min;
        this.filename = "projectile.txt";
    }

    public Projectile(boolean deltatAsk) {
        initializeVariableFromInput(deltatAsk);
    }

    public void initializeVariableFromInput(boolean deltatAsk) {
        Scanner scanner = new Scanner(System.in);
        String read;
        System.out.println("Please put number:");

//      Start INPUT
        if (deltatAsk) {
            System.out.println("delta t:");
            read = scanner.next();
            deltat = Coffee.round(Double.valueOf(read), Coffee.numberOfDecimalPlaces(read));
        }

        System.out.println("Time period for save data into file (min):");
        read = scanner.next();
        periodTime_min = Coffee.round(Double.valueOf(read), Coffee.numberOfDecimalPlaces(read));

//        System.out.println("Total Time (min):");
//        read = scanner.next();
//        totalTime_min = Coffee.round(Double.valueOf(read), Coffee.numberOfDecimalPlaces(read));

        if (deltatAsk && deltat > periodTime_min) {
            System.out.println("deltat > periodTime_min");
            return;
        }

//        if (periodTime_min > totalTime_min) {
//            System.out.println("periodTime_min > totalTime_min");
//            return;
//        }

        System.out.println("v0x :");
        read = scanner.next();
        v0x = Coffee.round(Double.valueOf(read), Coffee.numberOfDecimalPlaces(read));

        System.out.println("v0y :");
        read = scanner.next();
        v0y = Coffee.round(Double.valueOf(read), Coffee.numberOfDecimalPlaces(read));

        System.out.println("c :");
        read = scanner.next();
        c = Coffee.round(Double.valueOf(read), Coffee.numberOfDecimalPlaces(read));

//      End INPUT    
    }

    public double getFinalXByEulerMethod(boolean writeFile) throws FileNotFoundException {
        return getFinalXByApproximationMethod(writeFile, Projectile.Euler_approximationMethod);
    }

    public double getFinalXByEulerRichardsonMethod(boolean writeFile) throws FileNotFoundException {
        return getFinalXByApproximationMethod(writeFile, Projectile.Euler_Richardson_approximationMethod);
    }

    public double getFinalXWithCEqualsTo0() throws FileNotFoundException {
        return getFinalXWithCEqualsTo0(v0x, v0y);
    }

    //getFinalXByOylerMethod
    public double getFinalXByApproximationMethod(boolean writeFile, int approximationMethod) throws FileNotFoundException {
        numberOfSteps = 0;
        PrintWriter file = null;
        if (writeFile) {
            file = new PrintWriter(filename);
            file.println(0 + "    " + 0);
        }

        int numberOfDecimalPlaces = Coffee.numberOfDecimalPlaces(String.valueOf(deltat));
        double vx = v0x;
        double vy = v0y;
        double vxTodt2;
        double vyTodt2;
        double totalTime_min = 5000000;
        double x = 0;
        double y = 0;
        double lastx = x;
        int n = 0;

        for (double t = 0; t <= totalTime_min; t = Coffee.round(t + deltat, numberOfDecimalPlaces)/*t + deltat*/) {
            lastx = x;
            double ax = -(c * Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)) * vx);
            double ay = -(g + c * Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)) * vy);
            if (approximationMethod == Projectile.Euler_approximationMethod) {
                vx = vx + ax * deltat;
                vy = vy + ay * deltat;
                x = x + vx * deltat;
                y = y + vy * deltat;
            } else if (approximationMethod == Projectile.Euler_Richardson_approximationMethod) {
                vxTodt2 = vx + ax * deltat / 2;
                vyTodt2 = vy + ay * deltat / 2;
                vx = vx + ax * deltat;
                vy = vy + ay * deltat;
                x = x + vxTodt2 * deltat;
                y = y + vyTodt2 * deltat;
            }
            if (y < 0) {
                break;
            }
            numberOfSteps++;
            if (writeFile && t >= Coffee.round(n * periodTime_min, numberOfDecimalPlaces)) {
                n++;
                file.println(x + "    " + y);
            }
        }

        if (writeFile) {
            file.flush();
        }

        return lastx;
    }

    public double getError() {
        return numberOfSteps * deltat;
    }

    public double getError(int approximationMethod) throws FileNotFoundException {
        getFinalXByApproximationMethod(false, approximationMethod);
        return numberOfSteps * deltat;
    }

    public static double getError(int approximationMethod, double deltat, double periodTime_min/*, double totalTime_min*/, double v0x, double v0y, double c) throws FileNotFoundException {
        return new Projectile(deltat, periodTime_min, v0x, v0y, c).getNumberOfSteps() * deltat;
    }

    public static double getFinalXByApproximationMethod(boolean writeFile, int approximationMethod, double deltat, double periodTime_min/*, double totalTime_min*/, double v0x, double v0y, double c) throws FileNotFoundException {
        return new Projectile(deltat, periodTime_min, v0x, v0y, c).getFinalXByApproximationMethod(writeFile, approximationMethod);
    }

    public static double getFinalXWithCEqualsTo0(/*double totalTime_min, */double v0x, double v0y) {
        return 2 * v0x * v0y / g;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setDeltat(double deltat) {
        if (deltat > periodTime_min) {
            System.out.println("deltat > periodTime_min - default value set 0.01");
            return;
        }

        this.deltat = deltat;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public static double toRad(double degree) {
        return degree * Math.PI / 180;
    }

    public static double toDegree(double rad) {
        return rad * 180 / Math.PI;
    }
}
