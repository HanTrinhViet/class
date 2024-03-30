package _1_MA_CO_DIEN.vigenere_autokey;

public class Decrypt {
    public static void main(String[] args) {
        // đây là code giải mã
        String cipherText = "HPNHTXFWFXZXZM";
        String key = "ABADBE";


        key = key.toLowerCase();
        cipherText = cipherText.toLowerCase();


        int klength = key.length();

        StringBuilder newpl = new StringBuilder();

        int a = 'a';

        int cipher_length = cipherText.length();

        for (int i = 0; i < cipher_length; i++) {
            int c = (int)cipherText.charAt(i) - a;
            if (c < 0) c += 26;

            int k = (int)key.charAt(i % key.length()) - a;
            if (k < 0) k += 26;

            int p = (c - k);
            p %= 26;
            if (p < 0) p += 26;

            p += a;
            newpl.append((char) p);
            if(key.length() < cipher_length) {
                key += (char)p;
            }
        }
        System.out.println("Key: "+ key.toUpperCase());
        System.out.println("Decrypt: " + newpl);
    }
}
