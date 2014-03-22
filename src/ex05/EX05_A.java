package ex05;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EX05_A {

    public static void main(String[] args) throws FileNotFoundException {
//        int L[] = {10};
        int L[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
//        int L[] = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
        PrintWriter file = new PrintWriter("EX05_A__Percolation.txt");

        for (int i = 0; i < L.length; i++) {
            Percolation percolation = new Percolation(L[i], L[i], 0.01);
            double avg = percolation.getAveragePc(0.1, 100);
//            ConstraintPercolation percolation = new ConstraintPercolation(L[i], L[i], 1);
//            double avg = percolation.getAveragePc(1, 10);
            file.println((1 / (double) L[i]) + "        " + avg);
            System.out.println((1 / (double) L[i]) + "        " + avg);
        }
        file.flush();
    }
}
