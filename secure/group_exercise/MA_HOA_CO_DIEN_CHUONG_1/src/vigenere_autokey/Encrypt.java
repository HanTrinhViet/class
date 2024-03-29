package vigenere_autokey;

public class Encrypt {
    private static final int ALPHABET_SIZE = 26;

    public static String encrypt(String plaintext, String keyword) {
        plaintext = plaintext.toUpperCase();
        keyword = keyword.toUpperCase();

        StringBuilder ciphertext = new StringBuilder();
        int keywordIndex = 0;

        int plane_length = plaintext.length();

        for (int i = 0; i < plane_length; i++) {
            char currentChar = plaintext.charAt(i);
            if (Character.isLetter(currentChar)) {
                int shift = keyword.charAt(keywordIndex) - 'A';

                char encryptedChar = (char) ('A' + (currentChar - 'A' + shift) % ALPHABET_SIZE);
                ciphertext.append(encryptedChar);

                keywordIndex = (keywordIndex + 1) % keyword.length();
                keyword += currentChar; // Use the plaintext character instead of the encrypted character
            } else {
                ciphertext.append(currentChar);
            }
        }

        return ciphertext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "HONESTYISTHEBE";
        String keyword = "ABADBE";

        String encryptedText = encrypt(plaintext, keyword);
        System.out.println("Encrypted: " + encryptedText);
    }
}
