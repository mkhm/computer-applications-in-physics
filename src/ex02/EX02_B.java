package ex02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EX02_B {

    public static void main(String[] args) throws FileNotFoundException {
        double v0 = 10;
        double c = 1;
        double deltat = 0.001;
        double step = (Math.PI / 2) / 100;

        double maxRange = 0;
        double maxTheta = 0;

        PrintWriter file = new PrintWriter("EX02_B__Projectile_Euler_Method_Theta_Max.txt");

        for (double theta = 0; theta <= Math.PI / 2; theta += step) {
            Projectile projectile = new Projectile(deltat, deltat, v0 * Math.cos(theta), v0 * Math.sin(theta), c);
            double range = projectile.getFinalXByEulerMethod(false);
            if (range > maxRange) {
                maxTheta = theta;
                maxRange = range;
            }
            file.println(Projectile.toDegree(theta) + "    " + range);

        }
        file.flush();
        System.out.println("Max Range: " + maxRange);
        System.out.println("Theta (Max): " + Projectile.toDegree(maxTheta));
        System.out.println("Max Range c=0: " + new Projectile(deltat, deltat, v0 * Math.cos(Projectile.toRad(45)), v0 * Math.sin(Projectile.toRad(45)), c).getFinalXWithCEqualsTo0());
    }
}
