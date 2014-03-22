package ex04;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EX04_A {

    public static void main(String[] args) throws FileNotFoundException {
        int numberOfRepeat = 10000;
        int n = 100;
        PrintWriter file = new PrintWriter("EX04_A__Random_Walk_1D.txt");


        RandomWalk1D rw = new RandomWalk1D(0.5, 1);
        System.out.println(rw.getAvarage(numberOfRepeat, n));
        System.out.println(rw.getDeltaX2(numberOfRepeat, n));

        for (int i = 1; i <= 100; i++) {
            double deltaX2 = rw.getDeltaX2(numberOfRepeat, i);
            System.out.println(i);
            file.println(i + "      " + deltaX2);
        }
        file.flush();

    }
}
