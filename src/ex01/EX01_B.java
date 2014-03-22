package ex01;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EX01_B {

    public static void main(String[] args) throws FileNotFoundException {

//        double deltats[] = {0.1, 0.05, 0.025, 0.01, 0.005, 0.001};
        double deltats[] = {0.00001, 0.00002, 0.00003, 0.00004};
        PrintWriter file = new PrintWriter("EX01_B__Cooling_Euler_Method_Error.txt");

//        Coffee coffee = new Coffee(false);
        Coffee coffee = new Coffee(0.01, 0.1, 5, 25, 1000, 1);

        for (int i = 0; i < deltats.length; i++) {
            coffee.setDeltat(deltats[i]);
            double deltaT = coffee.getFinalTemprature() - coffee.getFinalTempratureByEulerMethod(false);
            file.println(deltats[i] + "   " + deltaT);
        }
        file.flush();
    }
}
