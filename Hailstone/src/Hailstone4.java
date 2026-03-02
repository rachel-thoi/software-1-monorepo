import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Change it so that it repeatedly asks the user whether they wish to calculate
 * another series. If the response is "y", then the program should proceed; if
 * it is anything else, then the program should quit.
 *
 * @author Rachel Thoi
 *
 */
public final class Hailstone4 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone4() {
    }

    /**
     * Generates and outputs the Hailstone4 series starting with the given
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
            // Prompt the user for a starting number for the Hailstone series
            out.print("Enter a variable for the Hailstone series: ");
            int n = in.nextInteger();
            // Generate and display the Hailstone series for the provided number
            generateSeries(n, out);
            // Ask if the user wants to input another series
            out.print("Would you like to input another series (y/n): ");
            String yeahno = in.nextLine();
            // Get the first character of the response and use it
            // to determine whether to continue the loop
            answer = yeahno.charAt(0);
        }
        // Close input and output streams
        in.close();
        out.close();
    }
}
