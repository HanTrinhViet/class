package _1_MA_CO_DIEN.caesar;

public class CaesarCipher {
    // Hàm mã hóa
    public static String encrypt(String plaintext, int shift) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    char encrypted = (char) ('A' + (ch - 'A' + shift) % 26);
                    ciphertext.append(encrypted);
                } else if (Character.isLowerCase(ch)) {
                    char encrypted = (char) ('a' + (ch - 'a' + shift) % 26);
                    ciphertext.append(encrypted);
                }
            } else {
                ciphertext.append(ch);
            }
        }
        return ciphertext.toString();
    }

    // Hàm giải mã
    public static String decrypt(String ciphertext, int shift) {
        return encrypt(ciphertext, 26 - shift); // Đối với giải mã, chúng ta di chuyển ngược lại
    }

    public static void main(String[] args) {
        String plaintext = "Meet me after the toga party";
        int shift = 3;
        String encryptedText = encrypt(plaintext, shift);
        String decryptedText = decrypt(encryptedText, shift);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
