package ex01;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Coffee {

    private double deltat = 0.01;
    private double Ts = 25d;
    private double r = 1d;
    private double T0 = 1000d;
    private double periodTime_min = 0.05;
    private double totalTime_min = 5d;
    private String filename;

    public Coffee(double deltat, double periodTime_min, double totalTime_min, double Ts, double T0, double r) {
        this.deltat = deltat;
        this.Ts = Ts;
        this.r = r;
        this.T0 = T0;
        this.periodTime_min = periodTime_min;
        this.totalTime_min = totalTime_min;
        this.filename = "EX01_A__Cooling_Euler_Method.txt";
    }

    public Coffee(boolean deltatAsk) {
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
            deltat = round(Double.valueOf(read), numberOfDecimalPlaces(read));
        }

        System.out.println("Time period for save data into file (min):");
        read = scanner.next();
        periodTime_min = round(Double.valueOf(read), numberOfDecimalPlaces(read));

        System.out.println("Total Time (min):");
        read = scanner.next();
        totalTime_min = round(Double.valueOf(read), numberOfDecimalPlaces(read));

        if (deltatAsk && deltat > periodTime_min) {
            System.out.println("deltat > periodTime_min");
            return;
        }

        if (periodTime_min > totalTime_min) {
            System.out.println("periodTime_min > totalTime_min");
            return;
        }

        System.out.println("Ts :");
        read = scanner.next();
        Ts = round(Double.valueOf(read), numberOfDecimalPlaces(read));

        System.out.println("T0 :");
        read = scanner.next();
        T0 = round(Double.valueOf(read), numberOfDecimalPlaces(read));

        System.out.println("r :");
        read = scanner.next();
        r = round(Double.valueOf(read), numberOfDecimalPlaces(read));

//      End INPUT    
    }

    public double getFinalTempratureByEulerMethod(boolean writeFile) throws FileNotFoundException {
        PrintWriter file = null;
        if (writeFile) {
            file = new PrintWriter(filename);
        }
        int numberOfDecimalPlaces = numberOfDecimalPlaces(String.valueOf(deltat));
        double T = T0;
//        T = T - r * (T - Ts) * deltat;
        int n = 0;

        for (double t = 0; t <= totalTime_min; t = round(t + deltat, numberOfDecimalPlaces)) {
            T = T - r * (T - Ts) * deltat;
            if (writeFile && t >= round(n * periodTime_min, numberOfDecimalPlaces)) {
                n++;
//                System.out.println(t + "    " + T + "      " + getFinalTemprature(t, Ts, T0, r));
                file.println(t + "    " + T + "      " + getFinalTemprature(t, Ts, T0, r));
            }
        }
//        for (int i = 1; i <= numberOfPeriod; i++) {
//            for (float t = deltat; t <= periodTime_min; t += deltat) {
//                T = T - r * (T - Ts) * deltat;
//            }
//            currentTime_min = i * periodTime_min;
//            file.println(currentTime_min + "    " + T + "      " + (Ts - (Ts - T0) * Math.pow(Math.E, -r * currentTime_min)));
//        }EX1_A


        if (writeFile) {
            file.flush();
        }

        return T;
    }

    public double getFinalTemprature() throws FileNotFoundException {
        return getFinalTemprature(totalTime_min, Ts, T0, r);
    }

    public static double getFinalTempratureByEulerMethod(boolean writeFile, double deltat, double periodTime_min, double totalTime_min, double Ts, double T0, double r) throws FileNotFoundException {
        return new Coffee(deltat, periodTime_min, totalTime_min, Ts, T0, r).getFinalTempratureByEulerMethod(writeFile);
    }

    public static double getFinalTemprature(double totalTime_min, double Ts, double T0, double r) {
        return Ts - (Ts - T0) * Math.pow(Math.E, -r * totalTime_min);
    }

    public static double round(double valueToRound, int numberOfDecimalPlaces) {
        double multipicationFactor = Math.pow(10, numberOfDecimalPlaces);
        double interestedInZeroDPs = valueToRound * multipicationFactor;
        return Math.round(interestedInZeroDPs) / multipicationFactor;
    }

    public static int numberOfDecimalPlaces(String str) {
        int index = str.indexOf(".");
        if (str.toLowerCase().indexOf("e") != -1) {
            return Integer.valueOf(str.substring(str.toLowerCase().indexOf("e") + 2));
        } else if (index != -1) {
            return str.length() - str.indexOf(".") - 1;
        } else {
            return 0;
        }
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
}
