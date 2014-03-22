package ex02;

import ex01.Coffee;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EX02_D {

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("EX02_D__Find_Deltat_For_Min_Error_By_Euler_Method.txt");

        double range = Projectile.getFinalXWithCEqualsTo0(10, 10);
        double step = 0.0001;

        for (double deltat = step; deltat <= 1; deltat = Coffee.round(deltat + step, 4)) {
//            System.out.println(deltat + "     " + (range - Projectile.getFinalXByApproximationMethod(false, Projectile.Euler_approximationMethod, deltat, 0.01, 10, 10, 0)));
            file.println(deltat + "     " + (range - Projectile.getFinalXByApproximationMethod(false, Projectile.Euler_approximationMethod, deltat, 0.01, 10, 10, 0)));
        }
    }
}
