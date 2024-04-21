import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Des1 {

    static String key;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the Key Text : ");
        key = sc.nextLine();

        System.out.print("Enter the Plain Text : ");
        String originalString = sc.nextLine();

        System.out.println("Original: " + originalString);

        if (key.length() > 8) {

            // Encrypt
            String encryptedString = encrypt(originalString);
            System.out.println("Encrypted: " + encryptedString);

            // Decrypt
            String decryptedString = decrypt(encryptedString);
            System.out.println("Decrypted: " + decryptedString);

        } else {
            System.out.println("Invalid password");
        }

    }

    public static String encrypt(String input) throws Exception {
        DESKeySpec keySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] inputBytes = input.getBytes("UTF-8");
        byte[] encryptedBytes = cipher.doFinal(inputBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedString) throws Exception {
        DESKeySpec keySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(keySpec);
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
        return new String(decryptedBytes, "UTF-8");
    }
}