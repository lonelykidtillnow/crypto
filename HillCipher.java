import java.util.Scanner;

public class HillCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Encryption\n2.Decryption");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > 2) {
            System.out.println("Invalid choice. Exiting...");
            return;
        }

        System.out.println("Enter text (lower case for encryption, upper case for decryption):");
        scanner.nextLine(); // Consume newline
        String text = scanner.nextLine().replaceAll("[^a-zA-Z]", "").toUpperCase();

        System.out.println("Enter size of the square matrix (rows/columns):");
        int size = scanner.nextInt();

        int[][] matrix = readMatrix(scanner, size);

        if (choice == 1) {
            System.out.println("Cipher text: " + encrypt(text, matrix));
        } else {
            System.out.println("Plain text: " + decrypt(text, matrix));
        }
    }

    public static int[][] readMatrix(Scanner scanner, int size) {
        System.out.println("Enter matrix values in row wise:");
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = scanner.nextInt() % 26; // Ensure values are in range [0, 25]
            }
        }
        return matrix;
    }

    public static String encrypt(String text, int[][] key) {
        return process(text, key, 1);
    }

    public static String decrypt(String text, int[][] key) {
        return process(text, key, -1);
    }

    public static String process(String text, int[][] key, int direction) {
        int size = key.length;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += size) {
            int[] chunk = new int[size];
            for (int j = 0; j < size; j++) {
                chunk[j] = (i + j < text.length()) ? text.charAt(i + j) - 'A' : 0; // Pad with 0 if chunk is incomplete
            }

            for (int j = 0; j < size; j++) {
                int sum = 0;
                for (int k = 0; k < size; k++) {
                    sum += key[j][k] * chunk[k];
                }
                result.append((char) ((sum % 26 + 26) % 26 + 'A')); // Ensure positive result within [0, 25]
            }
        }
        return result.toString();
    }
}