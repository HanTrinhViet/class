package _1_MA_CO_DIEN.singleLetter;

public class SingleLetter {
    private static final int ALPHABET_SIZE = 26;

    // Phương thức mã hóa
    public static String encode(String message, String key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0, j = 0; i < message.length(); i++) {
            char ch = message.charAt(i);

            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                int shift = key.charAt(j % key.length()) - (Character.isLowerCase(key.charAt(j % key.length())) ? 'a' : 'A');
                ch = (char) ((ch - base + shift + ALPHABET_SIZE) % ALPHABET_SIZE + base);
                j++;
            }

            result.append(ch);
        }

        return result.toString();
    }

    // Phương thức giải mã
    public static String decode(String encodedMessage, String key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0, j = 0; i < encodedMessage.length(); i++) {
            char ch = encodedMessage.charAt(i);

            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                int shift = key.charAt(j % key.length()) - (Character.isLowerCase(key.charAt(j % key.length())) ? 'a' : 'A');
                ch = (char) ((ch - base - shift + ALPHABET_SIZE) % ALPHABET_SIZE + base);
                j++;
            }

            result.append(ch);
        }

        return result.toString();
    }
    public static void main(String[] args) {
        String message = "LIKEFATHERLIKESO";
        String key = "LYFGMKNERXJPQIVATOHSZDBUCW";

        // Mã hóa
        String encodedMessage = encode(message, key);
        System.out.println("Encoded message: " + encodedMessage);

        // Giải mã
        String decodedMessage = decode(encodedMessage, key);
        System.out.println("Decoded message: " + decodedMessage);
    }
}
