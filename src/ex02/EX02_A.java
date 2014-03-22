/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ex02;

import java.io.FileNotFoundException;

// Projectile
public class EX02_A {

    public static void main(String[] args) throws FileNotFoundException {
//        Projectile projectile = new Projectile(true);
        Projectile projectile = new Projectile(0.01, 0.01, 10, 10, 0);
        projectile.setFilename("EX02_A__Projectile_Euler_Method.txt");
        System.out.println(projectile.getFinalXByEulerMethod(true));
//        System.out.println(projectile.getError());
        System.out.println(projectile.getFinalXWithCEqualsTo0());
    }
}
