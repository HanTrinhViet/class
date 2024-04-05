package _2_MODULO.LogaritRoiRac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogaritRoiRac {
    public static void main(String[] args) {
        int a, b, n;
        System.out.println("-------------------------log(a,b) mod n-------------------------");
        try {
            Scanner in = new Scanner(new File("logarit.txt"));
            a = in.nextInt();
            b = in.nextInt();
            n = in.nextInt();
            in.close();

            boolean found = false;
            for (int i = 1; i < n; i++) {
                int result = 1;
                for (int j = 1; j <= i; j++) {
                    result = (result * a) % n; // tính a^i mod n mỗi lần
                }
                if (result == b) {
                    System.out.println("log(" + a + "," + b + ") mod " + n + " = " + i);
                    found = true;
                }
            }
            if (!found)
                System.out.println("Khong co");

        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file.");
        }
    }
}
