package _4_AES;

import java.util.Arrays;

import static _4_AES.AES_UTIL.*;


public class AES_GEN_KEY {
    public static void main(String[] args) {
        for (int i = 0; i < K.length - 1; i++) {
            K[i + 1] = genKey(i);
        }
        System.out.println(Arrays.toString(K));
    }

    public static String genKey(int i) {
        StringBuilder key = new StringBuilder();
        var word_matrix = divideKey(K[i]);
        var rot_w = rotWord(word_matrix);
        var sub_w = subWord(rot_w);
        var xor_rc_w = xorRCJ(sub_w, i);
        var next_word_matrix = xorWord(xor_rc_w, word_matrix);

        for (int j = 0; j < MATRIX_SIZE; j++) {
            for (int k = 0; k < MATRIX_SIZE; k++) {
                key.append(next_word_matrix[k][j]);
            }
        }
        return key.toString();
    }


    /**
     * @return
     */
    public static String[][] divideKey(String key) {
        String[][] wordMatrix = new String[MATRIX_SIZE][MATRIX_SIZE];
        for (int j = 0; j < MATRIX_SIZE; j++) {
            String[] words = splitTo4Byte(key.substring(j * 8, (j + 1) * 8));
            for (int i = 0; i < MATRIX_SIZE; i++) {
                wordMatrix[i][j] = words[i];
            }
        }
        return wordMatrix;
    }

    /**
     * @return
     */
    public static String[] rotWord(String[][] word_matrix) {
        String[] w = new String[4];
        String w_first = word_matrix[0][MATRIX_SIZE - 1];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            if (i != MATRIX_SIZE - 1) {
                w[i] = word_matrix[i + 1][MATRIX_SIZE - 1];
            } else {
                w[i] = w_first;
            }
        }
        return w;
    }

    /**
     * @return
     */
    public static String[] subWord(String[] rot_w) {
        String[] sub_w = new String[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            String s_box_hex = findNewSBoxHex(rot_w[i]);
            sub_w[i] = s_box_hex;
        }
        return sub_w;
    }

    private static String[] xorRCJ(String[] subW, int r_con_j_index) {
        String[] xor_rc_w = new String[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            if (i == 0) {
                xor_rc_w[i] = getHexXorHex(subW[i], RC[r_con_j_index]);
            } else {
                xor_rc_w[i] = subW[i];
            }
        }
        return xor_rc_w;
    }

    private static String[][] xorWord(String[] xorRcW, String[][] wordMatrix) {
        String[][] next_word_matrix = new String[MATRIX_SIZE][MATRIX_SIZE];
        for (int j = 0; j < MATRIX_SIZE; j++) {
            for (int i = 0; i < MATRIX_SIZE; i++) {
                if (j == 0) {
                    next_word_matrix[i][j] = getHexXorHex(xorRcW[i], wordMatrix[i][j]);
                } else {
                    next_word_matrix[i][j] = getHexXorHex(next_word_matrix[i][j - 1], wordMatrix[i][j]);
                }
            }
        }
        return next_word_matrix;
    }
}
