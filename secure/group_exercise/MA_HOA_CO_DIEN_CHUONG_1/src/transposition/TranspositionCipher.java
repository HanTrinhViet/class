package transposition;

public class TranspositionCipher {
    // Hàm mã hóa
    public static String encrypt(String plaintext, int key) {
        int cols = (int) Math.ceil((double) plaintext.length() / key);
        char[][] grid = new char[key][cols];
        int index = 0;

        // Đổ ký tự vào ma trận
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < key; row++) {
                if (index < plaintext.length()) {
                    grid[row][col] = plaintext.charAt(index++);
                } else {
                    grid[row][col] = 'X'; // Sử dụng ký tự đặc biệt để điền vào các ô trống
                }
            }
        }

        // Xây dựng chuỗi mã hóa từ ma trận
        StringBuilder ciphertext = new StringBuilder();
        for (int row = 0; row < key; row++) {
            for (int col = 0; col < cols; col++) {
                ciphertext.append(grid[row][col]);
            }
        }
        return ciphertext.toString();
    }

    // Hàm giải mã
    public static String decrypt(String ciphertext, int key) {
        int cols = (int) Math.ceil((double) ciphertext.length() / key);
        char[][] grid = new char[key][cols];
        int index = 0;

        // Đổ ký tự vào ma trận
        for (int row = 0; row < key; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = ciphertext.charAt(index++);
            }
        }

        // Xây dựng chuỗi giải mã từ ma trận
        StringBuilder plaintext = new StringBuilder();
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < key; row++) {
                plaintext.append(grid[row][col]);
            }
        }
        return plaintext.toString().replaceAll("X+$", ""); // Loại bỏ các ký tự đặc biệt được thêm vào
    }

    public static void main(String[] args) {
        String plaintext = "WHENINROMEDOASTHER";
        int key = 4; // Khoá hoán vị

        String encryptedText = encrypt(plaintext, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
