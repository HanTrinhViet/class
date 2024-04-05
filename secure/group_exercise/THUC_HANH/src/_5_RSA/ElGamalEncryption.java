package _5_RSA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ElGamalEncryption {
    private static long haBac(long a, long m, long n) {
        if (m == 1) return a % n;
        if (m % 2 == 0) {
            return (haBac(a, m / 2, n) * haBac(a, m / 2, n)) % n;
        } else {
            return (haBac(a, m / 2, n) * haBac(a, m / 2, n) * haBac(a, 1, n)) % n;
        }
    }
    private static long nghichDao(long a, long n) {
        long Q, A1 = 1, A2 = 0, A3 = n, B1 = 0, B2 = 1, B3 = a;
        while (B3 != 0 && B3 != 1) {
            Q = A3 / B3;
            long B1_cp = B1, B2_cp = B2, B3_cp = B3;
            B1 = A1 - B1 * Q;
            B2 = A2 - B2 * Q;
            B3 = A3 - B3 * Q;

            A1 = B1_cp;
            A2 = B2_cp;
            A3 = B3_cp;
        }
        return (B2 + n) % n;
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("elgamal.txt"));
            long q = in.nextLong();
            long a = in.nextLong();
            long xA = in.nextLong();
            long yA = haBac(a, xA, q);
            System.out.println("a) Khoa cong khai cua An PU={ " + q + ", " + a + ", " + yA + "};");

            long k = in.nextLong();
            long M = in.nextLong();
            long K = haBac(yA, k, q);
            long C1 = haBac(a, k, q);
            long C2 = (K * M) % q;
            System.out.println("b) Ban ma la: (C1;C2) = (" + C1 + "; " + C2 + ");");

            long KA = haBac(C1, xA, q);
            long MA = ((C2 % q) * nghichDao(KA, q)) % q;
            System.out.println("c) K = " + KA + "; M = " + MA);

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file.");
        }
    }
}
