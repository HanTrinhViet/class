package _2_MODULO.CanNguyenThuy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CanNguyenThuy {
    private static int GCD(int u, int v) {
        while (v != 0) {
            int r = u % v;
            u = v;
            v = r;
        }
        return u;
    }

    private static int calPhi(int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (GCD(i, n) == 1)
                result++;
        }
        return result;
    }
    public static void main(String[] args) {
        int n, a;
        try {
            Scanner in = new Scanner(new File("cnt.txt"));
            a = in.nextInt();
            n = in.nextInt();
            in.close();

            if (GCD(n, a) == 1) {
                int phiN = calPhi(n);
                boolean flag = false;
                if (Math.pow(a, phiN) % n == 1) flag = true;

                for (int i = 1; i < phiN; i++) {
                    if (Math.pow(a, i) % n == 1) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    System.out.println(a + " la can nguyen thuy cua " + n);
                } else {
                    System.out.println(a + " khong la can nguyen thuy cua " + n);
                }
            } else {
                System.out.println(a + " khong la can nguyen thuy cua " + n);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file.");
        }
    }
}
