package com.netcracker.wind.workflow.generator;

/**
 *
 * @author Alexander Kovriga
 */
public class PasswordGenerator {

    private static final int DEFAULT_PASS_SIZE = 10;
    private static final String ALPHABET = "ABCDEFGHJKLMNOPQRSTUVWXYZ"
            + "abcdefghjklmnopqrstuvwxyz1234567890";

    public static String generatePassword() {
        return "111111";
//        return generatePassword(DEFAULT_PASS_SIZE);
    }
    
    public static String generatePassword(int passwordSize) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < passwordSize; ++i) {
            sb.append(ALPHABET.charAt((int) (Math.random()
                    * ALPHABET.length())));
        }
        return sb.toString();
    }

}
