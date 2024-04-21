import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.*;

public class Aes {

    public static void main(String[] args) throws Exception {

        String key;

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the Key Text : ");
        key = sc.nextLine();

        System.out.print("Enter the Aadhar Number : ");
        String plainText = sc.nextLine();

        if ((key.length() == 16) && (plainText.length() == 12)) {

            // Encryption
            byte[] encryptedBytes = encrypt(plainText, key);
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            System.out.println("Encrypted: " + encryptedText);

            // Decryption
            String decryptedText = decrypt(encryptedBytes, key);
            System.out.println("Decrypted: " + decryptedText);

        } else {
            System.out.println("Enter the valid Aadhar Number, 12bit key size");
        }

    }

    public static byte[] encrypt(String plainText, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plainText.getBytes());
    }

    public static String decrypt(byte[] cipherText, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(cipherText);
        return new String(decryptedBytes);
    }
}