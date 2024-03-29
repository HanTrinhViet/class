package playfair;

import java.util.ArrayList;
import java.util.List;

public class PlayFair {
    private static final char[][] MATRIX = {
            {'P', 'L', 'A', 'Y', 'F'},
            {'I', 'R', 'E', 'X', 'M'},
            {'B', 'C', 'D', 'G', 'H'},
            {'K', 'N', 'O', 'Q', 'T'},
            {'U', 'V', 'W', 'S', 'Z'}
    };

    private static String prepareInput(String input) {
        // Loại bỏ các ký tự không phải chữ cái và chuyển thành chữ cái in hoa
        input = input.replaceAll("[^a-zA-Z]", "").toUpperCase();
        // Chuyển "J" thành "I"
        input = input.replace("J", "I");
        return input;
    }

    private static String[] generatePairs(String input) {
        // Tách input thành các cặp ký tự
        List<String> pairs = new ArrayList<>();
        for (int i = 0; i < input.length(); i += 2) {
            if (i == input.length() - 1 || input.charAt(i) == input.charAt(i + 1)) {
                pairs.add(input.charAt(i) + "X");
                i--;
            } else {
                pairs.add(input.substring(i, i + 2));
            }
        }
        return pairs.toArray(new String[0]);
    }

    private static String encrypt(String[] pairs) {
        StringBuilder result = new StringBuilder();

        for (String pair : pairs) {
            char first = pair.charAt(0);
            char second = pair.charAt(1);

            int[] firstPos = findPosition(first);
            int[] secondPos = findPosition(second);

            if (firstPos[0] == secondPos[0]) {
                result.append(MATRIX[firstPos[0]][(firstPos[1] + 1) % 5]);
                result.append(MATRIX[secondPos[0]][(secondPos[1] + 1) % 5]);
            } else if (firstPos[1] == secondPos[1]) {
                result.append(MATRIX[(firstPos[0] + 1) % 5][firstPos[1]]);
                result.append(MATRIX[(secondPos[0] + 1) % 5][secondPos[1]]);
            } else {
                result.append(MATRIX[firstPos[0]][secondPos[1]]);
                result.append(MATRIX[secondPos[0]][firstPos[1]]);
            }
        }

        return result.toString();
    }

    private static String decrypt(String[] pairs) {
        StringBuilder result = new StringBuilder();

        for (String pair : pairs) {
            char first = pair.charAt(0);
            char second = pair.charAt(1);

            int[] firstPos = findPosition(first);
            int[] secondPos = findPosition(second);

            if (firstPos[0] == secondPos[0]) {
                result.append(MATRIX[firstPos[0]][(firstPos[1] + 4) % 5]);
                result.append(MATRIX[secondPos[0]][(secondPos[1] + 4) % 5]);
            } else if (firstPos[1] == secondPos[1]) {
                result.append(MATRIX[(firstPos[0] + 4) % 5][firstPos[1]]);
                result.append(MATRIX[(secondPos[0] + 4) % 5][secondPos[1]]);
            } else {
                result.append(MATRIX[firstPos[0]][secondPos[1]]);
                result.append(MATRIX[secondPos[0]][firstPos[1]]);
            }
        }

        return result.toString();
    }

    private static int[] findPosition(char ch) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (MATRIX[i][j] == ch) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String message = "SOFARSOGOODSO";
        String key = "EASTORW";

        // Mã hóa
        String preparedInput = prepareInput(message);
        String[] pairs = generatePairs(preparedInput);
        String encryptedMessage = encrypt(pairs);
        System.out.println("Encrypted message: " + encryptedMessage);

        // Giải mã
        String[] encryptedPairs = generatePairs(encryptedMessage);
        String decryptedMessage = decrypt(encryptedPairs);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}
