import java.util.Scanner;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

public class Sha

{

public static void main(String[] args)throws NoSuchAlgorithmException

{

Scanner sc = new Scanner(System.in);

System.out.println("Enter the String:");

String message = new String();

message = sc.next();

System.out.println("Mesage Digest for "+message);

System.out.println(sha1(message));

}

static String sha1(String input)throws NoSuchAlgorithmException

{

MessageDigest mDigest = MessageDigest.getInstance("SHA1");

byte[] result = mDigest.digest(input.getBytes());

StringBuffer sb = new StringBuffer(); for(int i = 0;i<result.length;i++)

{

sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));

}

return sb.toString();

}

}