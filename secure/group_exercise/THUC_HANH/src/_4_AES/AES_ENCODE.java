package _4_AES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static _4_AES.AES_GEN_KEY.*;
import static _4_AES.AES_UTIL.*;


public class AES_ENCODE {
    public static void main(String[] args) {
        var matrixInput = divideInput(M);
        System.out.println("Before encode:");
        showMatrix(matrixInput);
        System.out.println();

        // sinh khoa
        for (int i = 0; i < K.length - 1; i++) {
            K[i + 1] = genKey(i);
        }

        matrixInput = encode(matrixInput);
        System.out.println("After encode:");
        showMatrix(matrixInput);
        System.out.println();
    }

    private static String[][] encode(String[][] matrixInput) {
        for (int i = 1; i < K.length - 1; i++) {
            // sub byte
            subByte(matrixInput);
            // shift rows
            shiftRows(matrixInput);
            // mix column
            matrixInput = mixColumn(FIXED_MATRIX, matrixInput);
            // add round key
            matrixInput = addRoundKey(matrixInput, divideInput(K[i]));
        }
        for (int i = K.length - 1; i < K.length; i++) {
            // sub byte
            subByte(matrixInput);
            // shift rows
            shiftRows(matrixInput);
            // add round key
            matrixInput = addRoundKey(matrixInput, divideInput(K[i]));
        }
        return matrixInput;
    }

    private static String[][] addRoundKey(String[][] matrixInput, String[][] matrixKey) {
        String[][] matrixAddedRoundKey = new String[MATRIX_SIZE][MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrixAddedRoundKey[i][j] = getHexXorHex(matrixInput[i][j], matrixKey[i][j]);
            }
        }
        return matrixAddedRoundKey;
    }

    private static String[][] mixColumn(String[][] fixedMatrix, String[][] matrixInput) {

        String[][] resultMatrix = new String[MATRIX_SIZE][MATRIX_SIZE];
        List<String> listCheck = new ArrayList<>();
        List<String> listCheck1 = new ArrayList<>();
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                for (int k = 0; k < MATRIX_SIZE; k++) {
                    listCheck.add(checkMixColumns(fixedMatrix[i][k], matrixInput[k][j]));
                }
            }
        }
        for (int i = 0; i < listCheck.size(); i += 4) {
            String hex1 = getHexXorHex(listCheck.get(i), listCheck.get(i + 1));
            String hex2 = getHexXorHex(listCheck.get(i + 2), listCheck.get(i + 3));
            String hex = getHexXorHex(hex1, hex2);
            listCheck1.add(hex);
        }
        int index = 0;
        int size = listCheck1.size();
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (index < size) {
                    resultMatrix[i][j] = listCheck1.get(index++);
                }
            }
        }

        return resultMatrix;
    }

    private static void shiftRows(String[][] matrixInput) {
        for (int i = 1; i < MATRIX_SIZE; i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < MATRIX_SIZE; j++) {
                temp.add(matrixInput[i][j]);
            }
            if (i == 1) {
                matrixInput[i][0] = temp.get(1);
                matrixInput[i][1] = temp.get(2);
                matrixInput[i][2] = temp.get(3);
                matrixInput[i][3] = temp.get(0);
            } else if (i == 2) {
                matrixInput[i][0] = temp.get(2);
                matrixInput[i][1] = temp.get(3);
                matrixInput[i][2] = temp.get(0);
                matrixInput[i][3] = temp.get(1);
            } else {
                matrixInput[i][0] = temp.get(3);
                matrixInput[i][1] = temp.get(0);
                matrixInput[i][2] = temp.get(1);
                matrixInput[i][3] = temp.get(2);
            }
        }
    }

    private static void subByte(String[][] matrixInput) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrixInput[i][j] = findNewSBoxHex(matrixInput[i][j]);
            }
        }
    }

    private static void showMatrix(String[][] matrixInput) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                System.out.print(matrixInput[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static String[][] divideInput(String key) {
        String[][] wordMatrix = new String[MATRIX_SIZE][MATRIX_SIZE];
        for (int j = 0; j < MATRIX_SIZE; j++) {
            String[] words = splitTo4Byte(key.substring(j * 8, (j + 1) * 8));
            for (int i = 0; i < MATRIX_SIZE; i++) {
                wordMatrix[i][j] = words[i];
            }
        }
        return wordMatrix;
    }


}
