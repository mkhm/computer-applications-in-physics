package ex08;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static java.lang.Math.abs;
public class Potential {

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("EX08_A.txt");

        int n = 0;

        int nx = 50;
        int ny = 50;
        double c = 1.5;
        double v01 = 1;
        double v02 = 0;
        double v03 = 0;
        double v04 = 1;
        double v0 = c * 0.9 * (v01 + v02 + v03 + v04);
        double deltaV = 0;
        double limit = 0.00001;
        int deltaV_i = 0;
        int deltaV_j = 0;
        double[][] v = new double[nx + 2][ny + 2];
        double[][] vOld = new double[nx + 2][ny + 2];


        for (int i = 1; i < nx + 1; i++) {
            v[i][0] = v01;
            v[i][ny + 1] = v03;
        }
        for (int j = 1; j < ny + 1; j++) {
            v[0][j] = v02;
            v[nx + 1][j] = v04;
        }

        for (int i = 1; i < nx + 1; i++) {
            for (int j = 1; j < ny + 1; j++) {
                v[i][j] = v0;
            }
        }


        do {
            deltaV = 0;
            for (int i = 0; i < nx + 2; i++) {
                for (int j = 0; j < ny + 2; j++) {
                    vOld[i][j] = v[i][j];
                }
            }
//            for (int i = 0; i < nx + 2; i++) {
//                System.arraycopy(v[i], 0, vOld[i], 0, ny + 2);
//            }

            for (int i = 1; i < nx + 1; i++) {
                for (int j = 1; j < ny + 1; j++) {
                    v[i][j] = 0.25 * (v[i - 1][j] + v[i + 1][j] + v[i][j + 1] + v[i][j - 1]);
                    if (v[i][j] != 0 && abs(v[i][j] - vOld[i][j]) > deltaV) {
                        deltaV = abs(v[i][j] - vOld[i][j]);
                        deltaV_i = i;
                        deltaV_j = j;
                    }
                }
            }
//            System.out.println(deltaV + "     " + deltaV / v[deltaV_i][deltaV_j]);
            n++;
        } while (deltaV / v[deltaV_i][deltaV_j] > limit);

        for (int i = 0; i < nx + 2; i++) {
            for (int j = 0; j < ny + 2; j++) {
                file.println(i + "        " + j + "       " + v[i][j]);
            }
        }
        file.flush();
        System.out.println(n);
    }
}
