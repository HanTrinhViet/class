package vigenere_lap;

public class Vigenere_lap {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);
            int shift = ALPHABET.indexOf(Character.toUpperCase(key.charAt(j % key.length())));
            char encryptedChar = shiftChar(currentChar, shift);
            ciphertext.append(encryptedChar);
            j++;
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0, j = 0; i < ciphertext.length(); i++) {
            char currentChar = ciphertext.charAt(i);
            int shift = ALPHABET.indexOf(Character.toUpperCase(key.charAt(j % key.length())));
            char decryptedChar = shiftChar(currentChar, -shift);
            plaintext.append(decryptedChar);
            j++;
        }

        return plaintext.toString();
    }

    private static char shiftChar(char c, int shift) {
        int charPosition = ALPHABET.indexOf(Character.toUpperCase(c));
        if (charPosition == -1) return c; // If the character is not in the alphabet
        int shiftedPosition = (charPosition + shift + ALPHABET.length()) % ALPHABET.length();
        return ALPHABET.charAt(shiftedPosition);
    }

    public static void main(String[] args) {
        String plaintext = "HONESTYISTHEBE";
        String key = "ABADBE";

        // Encrypt
        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted: " + encryptedText);

        // Decrypt
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted: " + decryptedText);
    }
}
