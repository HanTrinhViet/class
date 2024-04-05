package _2_MODULO.HaBac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HaBac {
    static long haBac(long a, long m, long n) {
        if (m == 1) return a % n;
        if (m % 2 == 0) {
            return (haBac(a, (m / 2), n) * haBac(a, (m / 2), n)) % n;
        }
        else {
            return (haBac(a, (m / 2), n) * haBac(a, (m / 2), n) * haBac(a, 1, n)) % n;
        }
    }

    public static void main(String[] args) {
        long a, m, n;
        try {
            File file = new File("habac.txt");
            Scanner in = new Scanner(file);
            a = in.nextLong();
            m = in.nextLong();
            n = in.nextLong();
            in.close();

            if (m < 0 || n <= 0) {
                System.out.println("Error: Invalid input for exponent or modulus.");
                return;
            }

            System.out.println("\n=> " + a + "^" + m + " mod " + n + " = " + haBac(a, m, n));

        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file.");
        }
    }
}
