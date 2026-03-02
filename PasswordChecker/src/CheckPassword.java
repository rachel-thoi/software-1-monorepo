import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program validates a password based on specific criteria. It checks for
 * minimum length, at least one uppercase letter, one lowercase letter, and one
 * numeric digit.
 *
 * @author Rachel Thoi
 */
public final class CheckPassword {

    /**
     * Minimum length for a valid password.
     */
    private static final int MIN_CASE_LENGTH = 8;

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private CheckPassword() {

    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if the string contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        int index = 0;
        // Loop through each character in the string
        while (index < str.length()) {
            // Check if the current character is uppercase
            if (Character.isUpperCase(str.charAt(index))) {
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Checks if the given String contains a lower case letter.
     *
     * @param str
     *            the String to check
     * @return true if the string contains a lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String str) {
        int index = 0;
        // Loop through each character in the string
        while (index < str.length()) {
            // Check if the current character is lowercase
            if (Character.isLowerCase(str.charAt(index))) {
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Checks if the given string contains a digit.
     *
     * @param str
     *            the String to check
     * @return true if the string contains at least one digit, false otherwise
     */
    private static boolean containsDigit(String str) {
        int index = 0;
        // Loop through each character in the string
        while (index < str.length()) {
            // Check if the current character is a digit
            if (Character.isDigit(str.charAt(index))) {
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Evaluates and prints whether the provided password meets the required
     * criteria.
     *
     * @param passwordCandidate
     *            the password to check
     * @param out
     *            the output stream to print messages to
     */
    private static void checkPassword(String passwordCandidate, SimpleWriter out) {
        // Check if all password criteria are met
        boolean characterLength = passwordCandidate.length() >= MIN_CASE_LENGTH;
        boolean isUpperCase = containsUpperCaseLetter(passwordCandidate);
        boolean isLowerCase = containsLowerCaseLetter(passwordCandidate);
        boolean hasDigit = containsDigit(passwordCandidate);

        // Validate the password and provide feedback
        if (characterLength && isUpperCase && isLowerCase && hasDigit) {
            out.println("It's a valid password.");
        } else {
            out.println("The password is invalid.");
            if (!characterLength) {
                out.println("The password is not long enough. It must be at least "
                        + MIN_CASE_LENGTH + " characters.");
            }
            if (!isUpperCase) {
                out.println("The password must contain at least one uppercase letter.");
            }
            if (!isLowerCase) {
                out.println("The password must contain at least one lowercase letter.");
            }
            if (!hasDigit) {
                out.println("The password must contain at least one digit.");
            }
        }
    }

    /**
     * Main method to execute the program.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // Prompt user for password input
        out.print("Please enter a password: ");
        String passwordCandidate = in.nextLine();

        // Perform password validation
        checkPassword(passwordCandidate, out);

        // Close input and output streams
        in.close();
        out.close();
    }
}
