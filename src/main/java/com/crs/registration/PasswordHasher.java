/**
 * 
 */
package com.crs.registration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {
    
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hex = String.format("%064x", new java.math.BigInteger(1, digest));
        return hex;
    }
    
    public static boolean verifyPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        String hashedInputPassword = hashPassword(password);
        return hashedInputPassword.equals(hashedPassword);
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String password = "myPassword123";
        String hashedPassword = hashPassword(password);
        System.out.println("Hashed password: " + hashedPassword);
        
        String userInputPassword = "myPassword123";
        boolean isMatch = verifyPassword(userInputPassword, hashedPassword);
        System.out.println("Is password match? " + isMatch);
        
        String incorrectPassword = "password123";
        boolean isIncorrectMatch = verifyPassword(incorrectPassword, hashedPassword);
        System.out.println("Is incorrect password match? " + isIncorrectMatch);
    }
}
