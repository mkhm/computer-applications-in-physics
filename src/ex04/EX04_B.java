package ex04;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EX04_B {

    public static void main(String[] args) throws FileNotFoundException {
        int numberOfRepeat = 10000;
        int n = 100;
        double average[];
        PrintWriter file = new PrintWriter("EX04_B__Random_Walk_2D.txt");

//        double p[] = {0.5, 0.5};
//        RandomWalk rw = new RandomWalk(2, p, 1);
//        RandomWalk2D rw = new RandomWalk2D(0.5, 0.5, 1, 1);
        double p[] = {0.5, 0.5, 0.5};
        RandomWalk rw = new RandomWalk(3, p, 1);
        average = rw.getRVectorAvarage(numberOfRepeat, n);
        System.out.println(average[0] + ", " + average[1]);
        System.out.println(rw.getDeltaR2(numberOfRepeat, n));

        for (int i = 1; i <= 100; i++) {
            double deltaX2 = rw.getDeltaR2(numberOfRepeat, i);
            file.println(i + "      " + deltaX2);
        }
        file.flush();


    }
}
