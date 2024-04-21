import java.util.Scanner;

public class RailFence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your choice (1 for Encryption, 2 for Decryption, 3 to exit):");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice != 1 && choice != 2 && choice != 3) {
            System.out.println("Invalid choice.");
        } else if (choice != 3) {
            if (choice == 1) {
                System.out.println("Enter the plaintext:");
            } else {
                System.out.println("Enter the cipher text:");
            }

            String text = scanner.nextLine().toLowerCase();

            if (!text.matches("[a-z]+")) {
                System.out.println("Sorry, input must be a word.");
            } else {
                System.out.println("Enter the key:");
                if (!scanner.hasNextInt()) {
                    System.out.println("Sorry, input must be a number.");
                } else {
                    int key = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    if (key <= 0) {
                        System.out.println("Sorry, input must be a positive number.");
                    } else {
                        if (choice == 1) {
                            String encryptedText = encrypt(text, key);
                            System.out.println("Encrypted Text: " + encryptedText.toUpperCase());
                        } else {
                            String decryptedText = decrypt(text, key);
                            System.out.println("Decrypted Text: " + decryptedText);
                        }
                    }
                }
            }
        } else {
            System.out.println("Bye");
        }
        scanner.close();
    }

    public static String encrypt(String plainText, int key) {
        char[][] rail = new char[key][plainText.length()];

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < plainText.length(); j++) {
                rail[i][j] = '\n';
            }
        }

        boolean down = false;
        int row = 0, col = 0;

        for (int i = 0; i < plainText.length(); i++) {
            if (row == 0 || row == key - 1) {
                down = !down;
            }
            rail[row][col++] = plainText.charAt(i);
            row += down ? 1 : -1;
        }

        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < plainText.length(); j++) {
                if (rail[i][j] != '\n') {
                    encryptedText.append(rail[i][j]);
                }
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, int key) {
        char[][] rail = new char[key][encryptedText.length()];

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < encryptedText.length(); j++) {
                rail[i][j] = '\n';
            }
        }

        boolean down = false;
        int row = 0, col = 0;

        for (int i = 0; i < encryptedText.length(); i++) {
            if (row == 0 || row == key - 1) {
                down = !down;
            }
            rail[row][col++] = '*';
            row += down ? 1 : -1;
        }

        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < encryptedText.length(); j++) {
                if (rail[i][j] == '*' && index < encryptedText.length()) {
                    rail[i][j] = encryptedText.charAt(index++);
                }
            }
        }

        StringBuilder decryptedText = new StringBuilder();
        row = 0;
        col = 0;

        down = false;
        for (int i = 0; i < encryptedText.length(); i++) {
            if (row == 0 || row == key - 1) {
                down = !down;
            }
            decryptedText.append(rail[row][col++]);
            row += down ? 1 : -1;
        }

        return decryptedText.toString();
    }
}