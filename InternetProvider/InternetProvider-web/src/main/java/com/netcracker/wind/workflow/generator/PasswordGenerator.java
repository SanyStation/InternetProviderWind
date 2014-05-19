package com.netcracker.wind.workflow.generator;

/**
 * The {@code PasswordGenerator} class designed to generating random passwords.
 * 
 * @author Alexander Kovriga
 */
public class PasswordGenerator {

    /**
     * Default length of generated password.
     */
    private static final int DEFAULT_PASS_SIZE = 10;
    
    /**
     * Alphabet for generating random password.
     */
    private static final String ALPHABET = "ABCDEFGHJKLMNOPQRSTUVWXYZ"
            + "abcdefghjklmnopqrstuvwxyz1234567890";

    /**
     * Generates random password with predefined length (by default 10 symbols).
     * 
     * @return random password
     */
    public static String generatePassword() {
        return "111111";
//        return generatePassword(DEFAULT_PASS_SIZE);
    }
    
    /**
     * Generates random password with defined length.
     * 
     * @param passwordSize length of random password
     * @return random password
     */
    public static String generatePassword(int passwordSize) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < passwordSize; ++i) {
            sb.append(ALPHABET.charAt((int) (Math.random()
                    * ALPHABET.length())));
        }
        return sb.toString();
    }

}
