import java.util.Scanner;

public class PlayFairCipher {
    private static char[][] generateKeySquare(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        boolean[] used = new boolean[26];
        char[][] keySquare = new char[5][5];
        int index = 0;
        for (char c : key.toCharArray()) {
            if (!used[c - 'A']) {
                keySquare[index / 5][index % 5] = c;
                used[c - 'A'] = true;
                index++;
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && !used[c - 'A']) {
                keySquare[index / 5][index % 5] = c;
                index++;
            }
        }
        return keySquare;
    }
    
    private static String prepareText(String text) {
        return text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
    }

    private static String process(String text, char[][] keySquare, int mode) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';
            int[] firstPos = findPosition(first, keySquare);
            int[] secondPos = findPosition(second, keySquare);
            if (firstPos[0] == secondPos[0]) { // Same row
                result.append(keySquare[firstPos[0]][(firstPos[1] + mode) % 5]);
                result.append(keySquare[secondPos[0]][(secondPos[1] + mode) % 5]);
            } else if (firstPos[1] == secondPos[1]) { // Same column
                result.append(keySquare[(firstPos[0] + mode) % 5][firstPos[1]]);
                result.append(keySquare[(secondPos[0] + mode) % 5][secondPos[1]]);
            } else { // Rectangle
                result.append(keySquare[firstPos[0]][secondPos[1]]);
                result.append(keySquare[secondPos[0]][firstPos[1]]);
            }
        }
        return (mode == 1) ? result.toString() : result.toString().toLowerCase();
    }

    private static int[] findPosition(char c, char[][] keySquare) {
        int[] position = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keySquare[i][j] == c) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return position;
    }

    private static boolean isValid(String str) {
        return str.matches("[a-zA-Z]+");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] keySquare = null;
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }
            System.out.print("Enter the key: ");
            String key = scanner.nextLine();
            if (!isValid(key)) {
                System.out.println("Invalid key. Please enter alphabetic characters only.");
                continue;
            }
            System.out.print("Enter the text: ");
            String text = scanner.nextLine();
            if (!isValid(text)) {
                System.out.println("Invalid text. Please enter alphabetic characters only.");
                continue;
            }
            keySquare = generateKeySquare(key);
            switch (choice) {
                case 1:
                    System.out.println("Cipher text: " + process(prepareText(text), keySquare, 1));
                    break;
                case 2:
                    System.out.println("Plain text: " + process(prepareText(text), keySquare, 4));
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
        scanner.close();
    }
}