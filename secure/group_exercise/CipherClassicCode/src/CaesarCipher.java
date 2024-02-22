import java.util.Scanner;

public class CaesarCipher {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chọn chức năng: ");
        System.out.println("1. Mã hóa");
        System.out.println("2. Giải mã");
        int choice = Integer.parseInt(scanner.nextLine());

        System.out.println("Nhập văn bản: ");
        String text = scanner.nextLine();

        System.out.println("Nhập khóa (số từ 0-25): ");
        int key = scanner.nextInt();

        String result;
        switch (choice) {
            case 1:
                result = encode(text, key);
                System.out.println("Văn bản sau khi mã hóa: " + result);
                break;
            case 2:
                result = decode(text, key);
                System.out.println("Văn bản sau khi giải mã: " + result);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    private static String encode(String text, int key) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index == -1) {
                sb.append(c);
            } else {
                int newIndex = (index + key) % ALPHABET.length();
                sb.append(ALPHABET.charAt(newIndex));
            }
        }
        return sb.toString();
    }

    private static String decode(String text, int key) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index == -1) {
                sb.append(c);
            } else {
                int newIndex = (index - key + ALPHABET.length()) % ALPHABET.length();
                sb.append(ALPHABET.charAt(newIndex));
            }
        }
        return sb.toString();
    }
}