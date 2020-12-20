package Utils.Hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Represent Hashing functionality
 */
public class Hash {

    /**
     * Generate random salt
     * @return random string
     */
    public static String generateSalt() {
        Random r = new SecureRandom();
        byte[] bytes = new byte[20];
        r.nextBytes(bytes);
        String salt = bytesToString(bytes);
        return salt;
    }

    /**
     * Hash the string after adding salt
     * @param toHash String to hash
     * @param salt Random salt
     * @return Final hashed string
     * @throws NoSuchAlgorithmException - The algorithm does not exist
     */
    public static String hashStringWithSalt(String toHash, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(toHash.getBytes());
        String hashed = bytesToString(bytes);
        return hashed;
    }

    /**
     * Hashing the string without salt
     * @param toHash String to hash
     * @return Hashed string
     * @throws NoSuchAlgorithmException - The algorithm does not exist
     */
    public static String hashString(String toHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = md.digest(toHash.getBytes());
        String hashed = bytesToString(bytes);
        return hashed;
    }

    /**
     * Convert bytes into String
     * @param bytes Input bytes
     * @return A final string
     */
    private static String bytesToString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
