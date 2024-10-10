package a01183994.data.util;

import java.util.regex.Pattern;

/**
 * Currently, it includes functionality to validate email addresses.
 * 
 * This class is designed to be extended in the future for additional validation needs.
 * 
 * @author Samson James Ordonez
 * @version 1.0
 */
public class Validator {

    // Regular expression for a basic email format validation
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    
    // Precompiled Pattern for email validation using the above regular expression
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    // Constant for returning in case of an invalid email address
    private static final String INVALID_FIELD = "N/A";
    
    /**
     * Validates the given email address against a basic regular expression pattern.
     * If the email address is valid, it returns the email. Otherwise, it returns "N/A".
     * 
     * @param email The email address to validate
     * @return The original email if valid, otherwise "N/A"
     */
    public static String validateEmail(String email) {
        // Check if the email matches the defined pattern
        return EMAIL_PATTERN.matcher(email).matches() ? email : INVALID_FIELD;
    }
}
