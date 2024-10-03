package app.tools;

import java.util.Random;

/**
 * @author Mateus Fernandes <1210821@isep.ipp.pt>
 */

public class PasswordGenerator {

    private static final char[] pool = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
    private static final Random rand = new Random();

    private PasswordGenerator() {
        // Empty
    }

    public static String generatePassword(int howLong){
        StringBuilder password = new StringBuilder();

        for(int i=0; i<howLong; i++){
            password.append(pool[rand.nextInt(pool.length)]);
        }

        return password.toString();
    }

}
