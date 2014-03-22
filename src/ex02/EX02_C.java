package ex02;

import java.io.FileNotFoundException;

public class EX02_C {

    public static void main(String[] args) throws FileNotFoundException {
//        Projectile projectile = new Projectile(true);
        Projectile projectile = new Projectile(0.0001, 0.01, 10, 10, 0.1);
        projectile.setFilename("EX02_C__Projectile_Euler-Richardson_Method.txt");
        System.out.println(projectile.getFinalXByEulerRichardsonMethod(true));
//        System.out.println(projectile.getFinalXByEulerMethod(true));
//        System.out.println(projectile.getError());
        System.out.println(projectile.getFinalXWithCEqualsTo0());
    }
}
