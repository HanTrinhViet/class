package _3_DES;

public class Main {
    public static void main(String[] args) {
        int K1 = 0x17FFCC5A;
        int K2 = 0xDBF3EA87;
        System.out.print("\nKhóa K = ");
        showByte(K1);
        showByte(K2);

        int M1 = 0xE36B4C92;
        int M2 = 0xDE9AD726;
        System.out.print("\nBản tin M = ");
        showByte(M1);
        showByte(M2);

        int[] C = new int[2];
        maHoaDES(M1, M2, K1, K2, C);
        System.out.print("\nBản mã C = ");
        showByte(C[0]);
        showByte(C[1]);

//        int[] MC = new int[2];
//        giaiMaDES(C[0], C[1], K1, K2, MC);
//        System.out.print("\nBản giải mã MC = ");
//        showByte(MC[0]); showByte(MC[1]);
    }

    // use to get bit 32
    public static int getBit(int K1, int i) {
        int b = K1 >> (32 - i);
        int bit = b & 0x01;
        return bit;
    }

    // use to get bit 28
    public static int getBit28(int K1, int i) {
        int b = K1 >> (28 - i);
        int bit = b & 0x01;
        return bit;
    }

    // hoán vị khóa PC1 từ 64 bit -> 56 bit
    public static int PC1CD(int K1, int K2, int chiso1, int chiso2) {
        int[] pc1 = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};
        int pc1k1 = 0;
        for (int i = chiso1; i < chiso2; i++) {
            int viTri;
            int bit;
            if (pc1[i] > 32) {
                viTri = pc1[i] - 32;
                bit = getBit(K2, viTri);
            } else {
                viTri = pc1[i];
                bit = getBit(K1, viTri);
            }
            int b = bit & 0x01;
            pc1k1 = (pc1k1 << 1) | b;
        }
        return pc1k1;
    }

    // dịch vòng trái
    public static int shiftLeft(int C0, int s) {
        int C1;
        int sbit, bit28s;
        sbit = (C0 >> (28 - s));
        bit28s = (C0 << s) & 0xFFFFFFF;
        C1 = bit28s | sbit;
        return C1;
    }

    // sinh khóa k (48 bit)
    public static int KPC2(int C1, int D1, int chiso1, int chiso2) {
        int[] pc2 = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};
        int pc2k = 0;
        for (int i = chiso1; i < chiso2; i++) {
            int vitri;
            int bit;
            if (pc2[i] > 28) {
                vitri = pc2[i] - 28;
                bit = getBit28(D1, vitri);
            } else {
                vitri = pc2[i];
                bit = getBit28(C1, vitri);
            }
            int b = bit & 0x01;
            pc2k = (pc2k << 1) | b;
        }
        return pc2k;
    }

    // sinh 16 khóa
    public static void genKey(int K1, int K2, int[] key1, int[] key2) {
        int C0, D0;
        C0 = PC1CD(K1, K2, 0, 28);
        D0 = PC1CD(K1, K2, 28, 56);
        int[] s = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        int C1, D1;
        for (int i = 0; i < 16; i++) {
            C1 = shiftLeft(C0, s[i]);
            D1 = shiftLeft(D0, s[i]);
            key1[i] = KPC2(C1, D1, 0, 24);
            key2[i] = KPC2(C1, D1, 24, 48);

            C0 = C1;
            D0 = D1;
        }
    }

    // hoán vị IP
    public static int IPM(int M1, int M2, int chiso1, int chiso2) {
        int[] IP = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};
        int ipm1 = 0;
        for (int i = chiso1; i < chiso2; i++) {
            int viTri;
            int bit;
            if (IP[i] > 32) {
                viTri = IP[i] - 32;
                bit = getBit(M2, viTri);
            } else {
                viTri = IP[i];
                bit = getBit(M1, viTri);
            }
            int b = bit & 0x01;
            ipm1 = (ipm1 << 1) | b;
        }
        return ipm1;
    }

    // mở rộng nửa phải Ro => E[Ro]
    public static int ER0(int R0, int chiso1, int chiso2) {
        int[] E = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};
        int er1 = 0;
        for (int i = chiso1; i < chiso2; i++) {
            int viTri;
            int bit;

            viTri = E[i];
            bit = getBit(R0, viTri);
            int b = bit & 0x01;
            er1 = (er1 << 1) | b;
        }
        return er1;
    }

    public static int subByte(int A1, int A2) {
        int[] S1 = {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13};
        int[] S2 = {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9};
        int[] S3 = {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1, 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12};
        int[] S4 = {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14};
        int[] S5 = {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3};
        int[] S6 = {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13};
        int[] S7 = {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12};
        int[] S8 = {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11};
        int[] chiso = new int[9];
        int[] S = new int[9];
        int B;

        // Extract 4 groups of 6 bits from A1
        for (int i = 1; i <= 4; i++) {
            int b6i = (A1 >> (24 - 6 * i)) & 0x3F;
            int bit1 = (b6i >> 5) & 0x01;
            int bit6 = b6i & 0x01;
            int row = (bit1 << 1) | bit6;
            int col = (b6i >> 1) & 0xF;
            chiso[i] = (row << 4) | col;
        }

        // Extract 4 groups of 6 bits from A2
        for (int i = 5; i <= 8; i++) {
            int b6i = (A2 >> (24 - 6 * (i - 4))) & 0x3F;
            int bit1 = (b6i >> 5) & 0x01;
            int bit6 = b6i & 0x01;
            int row = (bit1 << 1) | bit6;
            int col = (b6i >> 1) & 0xF;
            chiso[i] = (row << 4) | col;
        }

        // Look up values in S tables
        S[1] = S1[chiso[1]];
        S[2] = S2[chiso[2]];
        S[3] = S3[chiso[3]];
        S[4] = S4[chiso[4]];
        S[5] = S5[chiso[5]];
        S[6] = S6[chiso[6]];
        S[7] = S7[chiso[7]];
        S[8] = S8[chiso[8]];

        // Concatenate 8 groups of 4 bits to form B (32 bits)
        B = 0;
        for (int i = 1; i <= 8; i++)
            B = (B << 4) | S[i];
        return B;
    }

    public static int hoanViP(int B) {
        int[] P = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};
        int fp = 0;
        for (int i = 0; i < 32; i++) {
            int vitri = P[i];
            int bit = getBit(B, vitri);
            int b = bit & 0x01;
            fp = (fp << 1) | b;
        }
        return fp;
    }

    // sinh khóa (sau sẽ lặp hàm này 16 vong)
    public static int F(int L0, int R0, int key1, int key2) {
        // Mở rộng nua phải Ro
        int ER01 = ER0(R0, 0, 24);
        int ER02 = ER0(R0, 24, 48);

        // XOR operation to calculate A = E[R0] + K1
        int A1 = key1 ^ ER01;
        int A2 = key2 ^ ER02;

        // Calculate B = S1(A1) S2(A2) S3(A3) S4(A4) S5(A5) S6(A6) S7(A7) S8(A8)
        int B = subByte(A1, A2);

        // Hoán vị P
        int FP = hoanViP(B);

        return FP;
    }

    public static int hoanViIP_1(int M1, int M2, int chiso1, int chiso2) {
        int[] IP1 = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25};
        int ipm1 = 0;
        for (int i = chiso1; i < chiso2; i++) {
            int viTri;
            int bit;
            if (IP1[i] > 32) {
                viTri = IP1[i] - 32;
                bit = getBit(M2, viTri);
            } else {
                viTri = IP1[i];
                bit = getBit(M1, viTri);
            }
            int b = bit & 0x01;
            ipm1 = (ipm1 << 1) | b;
        }
        return ipm1;
    }

    public static void showByte(int C) {
        for (int i = 1; i <= 8; i++) {
            int b = (C >> (32 - 4 * i)) & 0xF;
            System.out.printf("%X", b);
        }
    }

    public static void maHoaDES(int M1, int M2, int K1, int K2, int[] C) {
        int[] key1 = new int[16];
        int[] key2 = new int[16];
        genKey(K1, K2, key1, key2);

        // Thuc hien hoan vi IP
        int L0 = IPM(M1, M2, 0, 32);
        int R0 = IPM(M1, M2, 32, 64);

        int L1 = 0, R1 = 0;
        int FP;
        for (int i = 0; i < 16; i++) {
            FP = F(L0, R0, key1[i], key2[i]);
            R1 = FP ^ L0;
            L1 = R0;
            R0 = R1;
            L0 = L1;
            System.out.printf("\nL[%d] = %X", i + 1, L1);
            System.out.printf("      R[%d] = %X", i + 1, R1);
        }

        C[0] = hoanViIP_1(R1, L1, 0, 32);
        C[1] = hoanViIP_1(R1, L1, 32, 64);
    }

    public static void giaiMaDES(int M1, int M2, int K1, int K2, int[] C) {
        int[] key1 = new int[16];
        int[] key2 = new int[16];
        genKey(K1, K2, key1, key2);

        int L0 = IPM(M1, M2, 0, 32);
        int R0 = IPM(M1, M2, 32, 64);
        int L1 = 0, R1 = 0;
        for (int i = 0; i < 16; i++) {
            int FP = F(L0, R0, key1[15 - i], key2[15 - i]);
            R1 = FP ^ L0;
            L1 = R0;
            R0 = R1;
            L0 = L1;
            System.out.printf("\nL[%d] = %X", i + 1, L1);
            System.out.printf("      R[%d] = %X", i + 1, R1);
        }

        C[0] = hoanViIP_1(R1, L1, 0, 32);
        C[1] = hoanViIP_1(R1, L1, 32, 64);
    }
}