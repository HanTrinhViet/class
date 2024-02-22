import java.util.Scanner;

public class VigenereKeyLoop {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyIndex = 0;

        for (char c : plaintext.toCharArray()) {
            int charIndex = ALPHABET.indexOf(c);
            int shift = ALPHABET.indexOf(key.charAt(keyIndex));

            int newIndex = (charIndex + shift) % ALPHABET.length();
            ciphertext.append(ALPHABET.charAt(newIndex));

            keyIndex = (keyIndex + 1) % key.length();
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        int keyIndex = 0;

        for (char c : ciphertext.toCharArray()) {
            int charIndex = ALPHABET.indexOf(c);
            int shift = ALPHABET.indexOf(key.charAt(keyIndex));

            int newIndex = (charIndex - shift + ALPHABET.length()) % ALPHABET.length();
            plaintext.append(ALPHABET.charAt(newIndex));

            keyIndex = (keyIndex + 1) % key.length();
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập văn bản cần mã hóa: ");
        String plaintext = scanner.nextLine();

        System.out.print("Nhập khóa mã hóa: ");
        String key = scanner.nextLine();

        String ciphertext = encrypt(plaintext, key);

        System.out.println("Văn bản sau khi mã hóa: " + ciphertext);


        System.out.print("Nhập văn bản cần giải mã: ");
        String ciphertext1 = scanner.nextLine();

        System.out.print("Nhập khóa giải mã: ");
        String key1 = scanner.nextLine();

        String plaintext1 = decrypt(ciphertext1, key1);

        System.out.println("Văn bản sau khi giải mã: " + plaintext1);
    }
}
