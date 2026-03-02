import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Change it so that it checks that the input provided by the user is a positive
 * integer. You should implement a new static method declared as follows:
 *
 * @author Rachel Thoi
 *
 */
public final class Hailstone5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone5() {
    }

    /**
     * Generates and outputs the Hailstone5 series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        // Initialize counters, length, and maximum value
        int counter = n; // Starting number of the series
        int max = n; // Maximum value initialized with the starting number
        int length = 1; // Length of the series initialized to include the starting number

        // Print the starting number
        out.print("" + counter);

        // Continue generating the series until it reaches 1
        while (counter != 1) {
            // Apply Hailstone sequence rules: even numbers are halved,
            // odd numbers are tripled and increased by 1
            if (counter % 2 == 0) {
                counter = counter / 2;
            } else {
                final int three = 3;
                counter = three * counter + 1;
            }
            // Output the next number in the series
            out.print(", " + counter);
            // Update the maximum value if the current number is greater
            if (counter > max) {
                max = counter;
            }
            // Increment the length of the series
            length++;
        }

        // Print the length of the series and the maximum value
        out.println();
        out.println("The length of the series is: " + length);
        out.println("The maximum number in the series is: " + max);
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        int p = 0;
        boolean valid = false;
        while (!valid) {
            out.print("Enter a positive integer: ");
            String input = in.nextLine();
            // Check if the input can be parsed as an integer
            if (FormatChecker.canParseInt(input)) {
                p = Integer.parseInt(input);
                // Check if the integer is positive
                if (p > 0) {
                    valid = true;
                } else {
                    out.println("Error: The number must be positive.");
                }
            }
        }
        return p;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // Initialize answer to 'y' to start the loop
        char answer = 'y';
        while (answer == 'y') {
            // Get a positive integer from the user
            int n = getPositiveInteger(in, out);
            // Generate and display the Hailstone series for the provided number
            generateSeries(n, out);
            // Ask if the user wants to input another series
            out.print("Would you like to input another series (y/n)? ");
            String yn = in.nextLine();
            // Get the first character of the response and use
            // it to determine whether to continue the loop
            if (yn.length() > 0) {
                answer = yn.charAt(0);
            }
        }
        // Close input and output streams
        in.close();
        out.close();
    }
}
