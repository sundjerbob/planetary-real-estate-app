package com.db_course.db.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Define the strength of the hashing algorithm
    private static final int HASH_STRENGTH = 12;

    // Hashes a password using BCrypt
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(HASH_STRENGTH));
    }

    // Verifies a password against a hashed password
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
