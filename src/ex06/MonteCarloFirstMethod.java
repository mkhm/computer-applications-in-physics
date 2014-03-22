package ex06;

import static java.lang.Math.random;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MonteCarloFirstMethod {

    public static final double PI = 3.14159265358979d;

    public static double integrateByMonteCarloFirstMethod(int n) {
        int ns = 0;
        double x;
        double y;
        int height = 2;
        int width = 2;
        for (int i = 0; i < n; i++) {
            x = random();
            y = random();
            if (y <= valueOfFunction(x)) {
                ns++;
            }
        }

        return (double) (height * width) * ((double) ns) / n;
    }

    public static double valueOfFunction(double x) {
        return sqrt(1 - pow(x, 2));
    }
}
