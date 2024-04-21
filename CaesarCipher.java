import java.util.Scanner;

public class  CaesarCipher{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choice, shift;
        do {
            System.out.println("\nMenu:\n====\n1.Encryption\n2.Decryption\n3.Exit");
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the plain text:");
                    String plaintext = scanner.next();
                    System.out.print("Enter the key value:\n");
                    shift = scanner.nextInt();
                    if (shift < 26)
                        System.out.println(encrypt(plaintext, shift));
                    else
                        System.out.println("Invalid key");
                    break;
                case 2:
                    System.out.println("Enter the Cipher text:");
                    String ciphertext = scanner.next().toLowerCase();
                    System.out.print("Enter the key value:\n");
                    shift = scanner.nextInt();
                    if (shift < 26)
                        System.out.println(decrypt(ciphertext, shift));
                    else
                        System.out.println("Invalid key");
                    break;
                default:
                    System.out.print("Exiting");
            }
        } while (choice <= 2);
    }

    public static String encrypt(String plaintext, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);
            char encryptedChar = (char) ('a' + (currentChar - 'a' + shift) % 26);
            encryptedText.append(encryptedChar);
        }
        return (encryptedText.toString()).toUpperCase();
    }

    public static String decrypt(String encryptedText, int shift) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < encryptedText.length(); i++) {
            char currentChar = encryptedText.charAt(i);
            char decryptedChar = (char) ('a' + (currentChar - 'a' - shift + 26) % 26);
            decryptedText.append(decryptedChar);
        }
        return (decryptedText.toString()).toLowerCase();
    }
}