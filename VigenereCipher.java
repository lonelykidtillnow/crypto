import java.util.Scanner;

public class VigenereCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Options:");
            System.out.println("1. Encryption");
            System.out.println("2. Decryption");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter the text (only alphabetic characters): ");
            String text = readAlphabeticInput(scanner);

            System.out.print("Enter the key (only alphabetic characters): ");
            String key = readAlphabeticInput(scanner);

            String result = "";
            for (int i = 0; i < text.length(); i++) {
                int shift = (choice == 1 ? 1 : -1) * (key.charAt(i % key.length()) - 'a');
                char c = (char) ('a' + (text.charAt(i) - 'a' + shift + 26) % 26);
                result += c;
            }

            System.out.println(choice == 1 ? "Cipher text: " + result.toUpperCase() : "Plain text: " + result);
        }

        scanner.close();
    }

    private static String readAlphabeticInput(Scanner scanner) {
        String input;
        do {
            System.out.print(">> ");
            input = scanner.nextLine().toLowerCase().replaceAll("[^a-z]", "");
            if (input.isEmpty()) {
                System.out.println("Input must contain at least one alphabetic character. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }
}