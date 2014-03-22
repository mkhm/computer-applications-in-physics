package ex03;

import ex01.Coffee;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EX03_A {

    private static double r0 = 0.5;
    private static double rStep = 0.001;
    private static double x0 = 0.5;

    public static void main(String[] args) throws FileNotFoundException {
        
        initializeVariableFromInput();

        PrintWriter file = null;
        file = new PrintWriter("EX03__Descendant_Abundance.txt");
        int numberOfDecimalPlaces = Coffee.numberOfDecimalPlaces(String.valueOf(rStep));

        for (double r = r0; r <= 1; r = Coffee.round(r + rStep, numberOfDecimalPlaces)) {
            double x = x0;
            for (int i = 0; i <= 600; i++) {
                x = 4 * r * x * (1 - x);
                if (i > 500) {
                    file.println(r + "    " + x);
                }
            }

            file.flush();
        }
    }

    public static void initializeVariableFromInput() {
        Scanner scanner = new Scanner(System.in);
        String read;
        System.out.println("Please put number:");

        System.out.println("r0:");
        read = scanner.next();
        r0 = Coffee.round(Double.valueOf(read), Coffee.numberOfDecimalPlaces(read));

        System.out.println("r step:");
        read = scanner.next();
        rStep = Coffee.round(Double.valueOf(read), Coffee.numberOfDecimalPlaces(read));

        System.out.println("x0:");
        read = scanner.next();
        x0 = Coffee.round(Double.valueOf(read), Coffee.numberOfDecimalPlaces(read));
    }
}