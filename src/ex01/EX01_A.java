package ex01;

import java.io.FileNotFoundException;

public class EX01_A {

    public static void main(String[] args) throws FileNotFoundException {
//        Coffee coffee = new Coffee(true);
        Coffee coffee = new Coffee(0.00001, 0.01, 5, 25, 1000, 1);
        coffee.setFilename("EX01_A__Cooling_Euler_Method.txt");
        System.out.println(coffee.getFinalTempratureByEulerMethod(true));
        System.out.println(coffee.getFinalTemprature());

    }
}
