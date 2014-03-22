package ex06;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static java.lang.Math.abs;

public class EX06_B {

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("EX06_B__MonteCarloFirstMethod.txt");
        int n[] = {100, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

//        for (int i = 0; i < n.length; i++) {
//            file.println(n[i] + "         " + abs(MonteCarloFirstMethod.PI - MonteCarloFirstMethod.integrateByMonteCarloFirstMethod(n[i])));
//        }
        for (int i = 1; i < 1000; i++) {
            file.println(i + "         " + abs(MonteCarloFirstMethod.PI - MonteCarloFirstMethod.integrateByMonteCarloFirstMethod(i)));
        }
        file.flush();

    }
}
