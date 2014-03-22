package ex06;


public class EX06_A {

    public static void main(String[] args) {
        double p = MonteCarloFirstMethod.integrateByMonteCarloFirstMethod(1000000);
        System.out.println(p);
    }
}
